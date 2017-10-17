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
import vo.ContabilidadGeneral;



@Path("contabilidadgeneral")
public class RESTContabilidadGeneral 
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
	public Response crearContabilidadGeneral(ContabilidadGeneral reserva) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearContabilidadGeneral(reserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reserva).build();
	}



	@GET
	@Path( "{fecha}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darContabilidadGeneralPorFecha( @PathParam( "fecha" ) String fecha )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{ Date dateObj= Date.valueOf(fecha); 
		ArrayList<ContabilidadGeneral> reservas = tm.darContabilidadGeneralPorFecha(dateObj);
		return Response.status( 200 ).entity( reservas ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}



	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darContabilidadesGeneral() {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		List<ContabilidadGeneral> reservas;
		try {
			reservas = tm.darContabilidadesGeneral();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reservas).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarContabilidadGeneral(ContabilidadGeneral reserva) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.actualizarContabilidadGeneral(reserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reserva).build();
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarContabilidadGeneral(ContabilidadGeneral reserva) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.eliminarContabilidadGeneral(reserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reserva).build();
	}


}
