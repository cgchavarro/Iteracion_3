package rest;


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
import vo.Restaurante;


@Path("restaurantes")
public class RESTRestaurante 
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
	public Response crearRestaurante(Restaurante restaurante) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearRestaurante(restaurante);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(restaurante).build();
	}
		
	@GET
	@Path( "{username: [a-zA-Z][a-zA-Z_0-9]}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darRestauranteNombre( @PathParam( "username" ) String id )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			Restaurante restaurante = tm.darRestaurantePorNombre(id);
			return Response.status( 200 ).entity( restaurante ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
//	@GET
//	@Path( "{nombre}" )
//	@Produces( { MediaType.APPLICATION_JSON } )
//	public Response darRestauranteNombre( @PathParam( "nombre" ) String nombre )
//	{
//		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
//		try
//		{
//			ArrayList<Restaurante> restaurantes = tm.darRestaurantesPorNombre(nombre);
//			return Response.status( 200 ).entity( restaurantes ).build( );			
//		}
//		catch( Exception e )
//		{
//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
//		}
//	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darRestaurantes() {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		List<Restaurante> restaurantes;
		try {
			restaurantes = tm.darRestaurantes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(restaurantes).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarRestaurante(Restaurante restaurante) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.actualizarRestaurante(restaurante);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(restaurante).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarRestaurante(Restaurante restaurante) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.eliminarRestaurante(restaurante);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(restaurante).build();
	}
	
}
