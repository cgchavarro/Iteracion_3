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

import master.RotondAndesTM;
import vo.Categoria;


@Path("categoria")
public class RESTCategoria 
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
	public Response crearCategoria(Categoria categoria) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.crearCategoria(categoria);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(categoria).build();
	}
		
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darCategoriaId( @PathParam( "id" ) Long id )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			Categoria categoria = tm.darCategoriaPorId(id);
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
	public Response darCategoriaNombre( @PathParam( "nombre" ) String nombre )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			ArrayList<Categoria> categorias = tm.darCategoriaPorNombre(nombre);
			return Response.status( 200 ).entity( categorias ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darCategorias() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<Categoria> categorias;
		try {
			categorias = tm.darCategorias();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(categorias).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarCategoria(Categoria categoria) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.actualizarCategoria(categoria);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(categoria).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarCategoria(Categoria categoria) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.eliminarCategoria(categoria);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(categoria).build();
	}
	
}
