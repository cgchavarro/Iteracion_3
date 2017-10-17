package rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import master.RotondAndesMaster;
import vo.Menu;
import vo.Producto;



@Path("productos")
public class RESTProducto 
{
	@Context
	private ServletContext context;

	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearProducto(Producto producto) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearProducto(producto);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(producto).build();
	}
		
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darProductoId( @PathParam( "id" ) Long id )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			Producto producto = tm.darProductoPorId(id);
			return Response.status( 200 ).entity( producto ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darProductoNombre( @PathParam( "nombre" ) String nombre )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			ArrayList<Producto> productos = tm.darProductosPorNombre(nombre);
			return Response.status( 200 ).entity( productos ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darProductos() {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		List<Producto> productos;
		try {
			productos = tm.darProductos();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(productos).build();
	}
	
	@GET
	@Path( "/restauranteproductos/{nombre}" )
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darProductosRestaurante(@PathParam( "nombre" ) String name) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		List<Producto> productos;
		try {
			productos = tm.darProductosRestaurante(name);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(productos).build();
	}
	
	@GET
	@Path( "/categoriaproductos/{idCategoria}" )
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darProductosRestaurante(@PathParam( "idCategoria" ) Long id) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		List<Producto> productos;
		try {
			productos = tm.darProductosCategoria(id);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(productos).build();
	}
	
	@GET
	@Path( "/precios/{precioMenor}/max/{precioMax}" )
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darProductosRestaurante(@PathParam( "precioMenor" ) double min, @PathParam( "precioMax" )double max) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		List<Producto> productos;
		try {
			productos = tm.darProductosRangoPrecio(min,max);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(productos).build();
	}
	@GET
	@Path( "/masofrecido" )
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darProductoMasOfrecido() {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		
		try {
			ArrayList<Producto> productos;
		
			productos = tm.darProductos();
			ArrayList<Long> idsProductos = darArregloIds(productos);
			ArrayList<Menu> menus =tm.darMenus();
			Long max;
			max = darIdMax(idsProductos, menus);
			Producto rta = tm.darProductoPorId(max);
			return Response.status(200).entity(rta).build();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
	
	}
	
private Long darIdMax(ArrayList<Long> productos, ArrayList<Menu> menus) 
{
	Long max=Long.MIN_VALUE;
	int contador = 0;
	for(int i=0; i< productos.size();i++)
	{
		Long idActual = productos.get(i);
		int vecesActual=0;
		for(int m=0; m<menus.size();m++)
		{
			Menu actual = menus.get(m);
			if(actual.getIdBebida()==idActual || actual.getIdAcompaniamiento() == idActual || actual.getIdPlatoFuerte()== idActual
					|| actual.getIdEntrada()==idActual || actual.getIdPostre()== idActual)
			{
				vecesActual++;
			}
		}
		if(vecesActual>contador)
		{
			contador=vecesActual;
			max=idActual;
		}
	}
		
		return max;
	}

private ArrayList<Long> darArregloIds(ArrayList<Producto> productos) 
{
	ArrayList<Long> arre = new ArrayList<Long>();
	for(int i=0;i<productos.size();i++)
	{
		arre.add(productos.get(i).getIdProducto());
	}
	return arre;
	}

//	@GET
//	@Path( "{id: \\d+}" )
//	@Produces( { MediaType.APPLICATION_JSON } )
//	public Response darZonaId( @PathParam( "id" ) Long id )
//	{
//		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
//		try
//		{
//			Zona zona = tm.darZonaPorId(id);
//			ArrayList<Restaurante> restaurantes = tm.darRestaurantesPorZona(id);
//	
//			ArrayList<Producto> productos = tm.darProductos();
//	ArrayList<Producto> productosZona = darProductosZona(restaurantes, productos);
//			ArrayList c= new ArrayList<>();
//			c.add(zona); 
//			c.addAll(restaurantes);  
//			c.addAll(productosZona);
//			return Response.status( 200 ).entity( c ).build( );			
//		}
//		catch( Exception e )
//		{
//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
//		}
//	}
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarProducto(Producto producto) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.actualizarProducto(producto);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(producto).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarProducto(Producto producto) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.eliminarProducto(producto);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(producto).build();
	}
	
}
