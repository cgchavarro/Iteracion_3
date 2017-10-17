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
import vo.AdministradorRestaurante;
import vo.Ingrediente;
import vo.Menu;
import vo.Producto;


@Path("administradorrestaurante")
public class RESTAdministradorRestaurante 
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
	public Response crearAdministradorRestaurante(AdministradorRestaurante cliente) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearAdministradorRestaurante(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}
	
	@POST
	@Path("/product")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearProducto(Producto cliente) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearProducto(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}
	
	@POST
	@Path("/ingredient")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearIngrediente(Ingrediente cliente) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearIngrediente(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}
	@POST
	@Path("/men")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearMenu(Menu cliente) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearMenu(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}
		
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darAdministradorRestauranteId( @PathParam( "id" ) Long id )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			AdministradorRestaurante cliente = tm.darAdministradorRestaurantePorCedula(id);
			return Response.status( 200 ).entity( cliente ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darAdministradorRestauranteNombre( @PathParam( "nombre" ) String nombre )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			ArrayList<AdministradorRestaurante> clientes = tm.darAdministradorRestaurantePorNombre(nombre);
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
	public Response darAdministradorRestauranteCorreo( @PathParam( "correo" ) String correo )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			AdministradorRestaurante cliente = tm.darAdministradorRestaurantePorCorreo(correo);
			return Response.status( 200 ).entity( cliente ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darAdministradorRestaurantes() {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		List<AdministradorRestaurante> clientes;
		try {
			clientes = tm.darAdministradorRestaurantes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(clientes).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarAdministradorRestaurante(AdministradorRestaurante cliente) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.actualizarAdministradorRestaurante(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarAdministradorRestaurante(AdministradorRestaurante cliente) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.eliminarAdministradorRestaurante(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}
	
}
