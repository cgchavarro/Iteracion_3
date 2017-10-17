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
import vo.Producto;
import vo.Restaurante;
import vo.Zona;


@Path("zonas")
public class RESTZona 
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
	public Response crearZona(Zona zona) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearZona(zona);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(zona).build();
	}
		
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darZonaId( @PathParam( "id" ) Long id )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			Zona zona = tm.darZonaPorId(id);
			ArrayList<Restaurante> restaurantes = tm.darRestaurantesPorZona(id);
	
			ArrayList<Producto> productos = tm.darProductos();
	ArrayList<Producto> productosZona = darProductosZona(restaurantes, productos);
			ArrayList c= new ArrayList<>();
			c.add(zona); 
			c.addAll(restaurantes);  
			c.addAll(productosZona);
			return Response.status( 200 ).entity( c ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	private ArrayList<Producto> darProductosZona(ArrayList<Restaurante> restaurantes, ArrayList<Producto> productos) 
	{
		// TODO Auto-generated method stub
		ArrayList<Producto> pro = new ArrayList<Producto>();
		for(int res =0; res<restaurantes.size();res++)
		{
			for (int prod =0; prod<productos.size();prod++)
			{
				if(productos.get(prod).getNombreRestaurante().equals(restaurantes.get(res).getNombre()))
				{
					pro.add(productos.get(prod));
				}
			}
		}
		return pro;
	}

//	private ArrayList<Restaurante> darRestaurantesZona(ArrayList<Restaurante> restaurantes, Long id)
//	{
//		ArrayList<Restaurante> res = new ArrayList<Restaurante>();
//		for (int i=0;i<restaurantes.size();i++)
//		{
//			Restaurante actual = restaurantes.get(i);
//			if(actual.getIdZona()==id)
//			{
//				res.add(actual);
//			}
//		}
//		return res;
//	}

	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darZonaNombre( @PathParam( "nombre" ) String nombre )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			ArrayList<Zona> zonas = tm.darZonasPorNombre(nombre);
			return Response.status( 200 ).entity( zonas ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darZonas() {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		List<Zona> zonas;
		try {
			zonas = tm.darZonas();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(zonas).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarZona(Zona zona) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.actualizarZona(zona);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(zona).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarZona(Zona zona) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.eliminarZona(zona);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(zona).build();
	}
	
}
