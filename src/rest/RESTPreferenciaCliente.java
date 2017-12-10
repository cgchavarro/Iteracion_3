package rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import master.RotondAndesTM;
import vo.PreferenciaCliente;


@Path("preferencia")
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
		
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darPreferenciaClienteId( @PathParam( "id" ) Long id )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
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
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
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
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<PreferenciaCliente> categorias;
		try {
			categorias = tm.darPreferenciaClientes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(categorias).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarPreferenciaCliente(PreferenciaCliente categoria) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.eliminarPreferenciaCliente(categoria);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(categoria).build();
	}
	
}
