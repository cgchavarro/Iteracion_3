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


@Path("menus")
public class RESTMenu 
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
	public Response crearMenu(Menu menu) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearMenu(menu);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(menu).build();
	}
		
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darMenuId( @PathParam( "id" ) Long id )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			Menu menu = tm.darMenuPorId(id);
			return Response.status( 200 ).entity( menu ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "{idRestaurante: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darMenuRestaurante( @PathParam( "idRestaurante" ) Long idRestaurante )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			ArrayList<Menu> menus = tm.darMenusPorRestaurante(idRestaurante);
			return Response.status( 200 ).entity( menus ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darMenus() {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		List<Menu> menus;
		try {
			menus = tm.darMenus();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(menus).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarMenu(Menu menu) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.actualizarMenu(menu);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(menu).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarMenu(Menu menu) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.eliminarMenu(menu);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(menu).build();
	}
	
}
