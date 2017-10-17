package rest;

import java.sql.Date;
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
import vo.ContabilidadRestaurante;


@Path("contabilidadrestaurante")
public class RESTContabilidadRestaurante 
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
	public Response crearContabilidadRestaurante(ContabilidadRestaurante reserva) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearContabilidadRestaurante(reserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reserva).build();
	}


	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darContabilidadRestauranteId( @PathParam( "id" ) Long id )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			ContabilidadRestaurante reserva = tm.darContabilidadRestaurantePorId(id);
			return Response.status( 200 ).entity( reserva ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}

	@GET
	@Path( "{fecha}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darContabilidadRestaurantePorFecha( @PathParam( "fecha" ) String fecha )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{ Date dateObj= Date.valueOf(fecha); 
		ArrayList<ContabilidadRestaurante> reservas = tm.darContabilidadRestaurantePorFecha(dateObj);
		return Response.status( 200 ).entity( reservas ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}



	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darContabilidadRestaurantes() {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		List<ContabilidadRestaurante> reservas;
		try {
			reservas = tm.darContabilidadesRestaurante();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reservas).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarContabilidadRestaurante(ContabilidadRestaurante reserva) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.actualizarContabilidadRestaurante(reserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reserva).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarContabilidadRestaurante(ContabilidadRestaurante reserva) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.eliminarContabilidadRestaurante(reserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reserva).build();
	}


}
