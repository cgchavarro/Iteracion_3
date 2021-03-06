package rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import master.RotondAndesTM;
import vo.Menu;


@Path("menu")
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

		
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darMenuId( @PathParam( "id" ) Long id )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
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
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
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
		RotondAndesTM tm = new RotondAndesTM(getPath());
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
		RotondAndesTM tm = new RotondAndesTM(getPath());
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
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.eliminarMenu(menu);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(menu).build();
	}
	
}
