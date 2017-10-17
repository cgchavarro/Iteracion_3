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
import vo.Cliente;
import vo.OrdenRestaurante;
import vo.PreferenciaCliente;


@Path("clientes")
public class RESTCliente 
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
	public Response crearCliente(Cliente cliente) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearCliente(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}
	
	
	@POST
	@Path("/preferences")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearPreferencia(PreferenciaCliente cliente) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearPreferenciaCliente(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}
	
	@PUT
	@Path("/preferences")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarPreferencia(PreferenciaCliente cliente) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.actualizarPreferenciaCliente(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}
//	@GET
//	@Path( "{id: \\d+}" )
//	@Produces( { MediaType.APPLICATION_JSON } )
//	public Response darClienteId( @PathParam( "id" ) Long id )
//	{
//		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
//		try
//		{
//			Cliente cliente = tm.darClientePorCedula(id);
//			return Response.status( 200 ).entity( cliente ).build( );			
//		}
//		catch( Exception e )
//		{
//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
//		}
//	}
	
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darClienteId( @PathParam( "id" ) Long id )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			Cliente cliente = tm.darClientePorCedula(id);
			ArrayList<OrdenRestaurante> orden =tm.darOrdenRestaurantePorIdCliente(id);
			PreferenciaCliente p = tm.darPreferenciaClientePorId(id);
			
			ArrayList c = new ArrayList();
			c.add(cliente); c.addAll(orden); c.add(p);
			return Response.status( 200 ).entity( c ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darClienteNombre( @PathParam( "nombre" ) String nombre )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			ArrayList<Cliente> clientes = tm.darClientePorNombre(nombre);
			return Response.status( 200 ).entity( clientes ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "{correo}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darClienteCorreo( @PathParam( "correo" ) String correo )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			Cliente cliente = tm.darClientePorCorreo(correo);
			return Response.status( 200 ).entity( cliente ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darClientes() {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		List<Cliente> clientes;
		try {
			clientes = tm.darClientes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(clientes).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarCliente(Cliente cliente) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.actualizarCliente(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarCliente(Cliente cliente) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.eliminarCliente(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}
	
}
