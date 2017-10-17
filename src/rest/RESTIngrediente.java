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
import vo.Ingrediente;


@Path("ingredientes")
public class RESTIngrediente 
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
	public Response crearIngrediente(Ingrediente ingrediente) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearIngrediente(ingrediente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ingrediente).build();
	}
		
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darIngredienteId( @PathParam( "id" ) Long id )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			Ingrediente ingrediente = tm.darIngredientePorId(id);
			return Response.status( 200 ).entity( ingrediente ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darIngredienteNombre( @PathParam( "nombre" ) String nombre )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			ArrayList<Ingrediente> ingredientes = tm.darIngredientePorNombre(nombre);
			return Response.status( 200 ).entity( ingredientes ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darIngredientes() {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		List<Ingrediente> ingredientes;
		try {
			ingredientes = tm.darIngredientes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ingredientes).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarIngrediente(Ingrediente ingrediente) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.actualizarIngrediente(ingrediente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ingrediente).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarIngrediente(Ingrediente ingrediente) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.eliminarIngrediente(ingrediente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ingrediente).build();
	}
	
}
