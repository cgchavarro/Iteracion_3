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
import vo.MensajeError;
import vo.Menu;
import vo.OrdenRestaurante;
import vo.Producto;


@Path("ordenRestaurante")
public class RESTOrdenRestaurante 
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
	public Response crearOrdenRestaurante(OrdenRestaurante ordenRestaurante) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
	
		
		Menu m = tm.darMenuPorId(ordenRestaurante.getIdMenu());
		
		ArrayList<Producto> productos = new ArrayList<Producto>();
		
		productos.add(tm.darProductoPorId(m.getIdAcompaniamiento()));
		productos.add(tm.darProductoPorId(m.getIdBebida()));
		productos.add(tm.darProductoPorId(m.getIdEntrada()));
		productos.add(tm.darProductoPorId(m.getIdPlatoFuerte()));
		productos.add(tm.darProductoPorId(m.getIdPostre()));
		
	boolean	verificar=verificarProductos(productos);
		
		if(verificar)
		{
		try {
			tm.crearOrdenRestaurante(ordenRestaurante);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		
		return Response.status(200).entity(ordenRestaurante).build();
		}
		else
		{
		MensajeError ex = new MensajeError("no se logro crear la orden");
			System.out.println("no se logro crear");
			return Response.status(500).entity(ex).build();
		}
		
	}
		
	

	private boolean verificarProductos(ArrayList<Producto> productos) 
	{
		// TODO Auto-generated method stub
		boolean puede=true;
		for(Producto p:productos)
		{
			if(p.getCantidad()<=0)
			{
			puede=false;
			}
		}
		return puede;
	}

	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darOrdenRestauranteId( @PathParam( "id" ) Long id )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			OrdenRestaurante ordenRestaurante = tm.darOrdenRestaurantePorId(id);
			return Response.status( 200 ).entity( ordenRestaurante ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
//	
//	@GET
//	@Path( "{idMenu: \\d+}" )
//	@Produces( { MediaType.APPLICATION_JSON } )
//	public Response darOrdenRestauranteNombre( @PathParam( "idMenu" ) Long idMenu )
//	{
//		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
//		try
//		{
//			ArrayList<OrdenRestaurante> ordenRestaurantes = tm.darOrdenRestaurantePorMenu(idMenu);
//			return Response.status( 200 ).entity( ordenRestaurantes ).build( );			
//		}
//		catch( Exception e )
//		{
//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
//		}
//	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darOrdenRestaurantes() {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		List<OrdenRestaurante> ordenRestaurantes;
		try {
			ordenRestaurantes = tm.darOrdenRestaurantes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ordenRestaurantes).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarOrdenRestaurante(OrdenRestaurante ordenRestaurante) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.actualizarOrdenRestaurante(ordenRestaurante);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ordenRestaurante).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarOrdenRestaurante(OrdenRestaurante ordenRestaurante) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.eliminarOrdenRestaurante(ordenRestaurante);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ordenRestaurante).build();
	}
	
}
