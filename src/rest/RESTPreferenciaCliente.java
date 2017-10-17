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
import vo.PreferenciaCliente;


@Path("preferencias")
public class RESTPreferenciaCliente 
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
	public Response crearPreferenciaCliente(PreferenciaCliente categoria) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearPreferenciaCliente(categoria);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(categoria).build();
	}
		
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darPreferenciaClienteId( @PathParam( "id" ) Long id )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			PreferenciaCliente categoria = tm.darPreferenciaClientePorId(id);
			return Response.status( 200 ).entity( categoria ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darPreferenciaClientePorNombre( @PathParam( "nombre" ) String nombre )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			ArrayList<PreferenciaCliente> categorias = tm.darPreferenciaClientePorNombre(nombre);
			return Response.status( 200 ).entity( categorias ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darPreferenciaClientes() {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		List<PreferenciaCliente> categorias;
		try {
			categorias = tm.darPreferenciaClientes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(categorias).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarPreferenciaCliente(PreferenciaCliente categoria) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.actualizarPreferenciaCliente(categoria);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(categoria).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarPreferenciaCliente(PreferenciaCliente categoria) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.eliminarPreferenciaCliente(categoria);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(categoria).build();
	}
	
}
