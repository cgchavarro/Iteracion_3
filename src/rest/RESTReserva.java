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
import vo.Reserva;


@Path("reservas")
public class RESTReserva 
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
	public Response crearReserva(Reserva reserva) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearReserva(reserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reserva).build();
	}
		
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darReservaId( @PathParam( "id" ) Long id )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			Reserva reserva = tm.darReservaPorId(id);
			return Response.status( 200 ).entity( reserva ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "{idZona: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darReservaNombre( @PathParam( "idZona" ) Long idZona )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			ArrayList<Reserva> reservas = tm.darReservaPorNombre(idZona);
			return Response.status( 200 ).entity( reservas ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Path( "{idCliente: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darReservaCorreo( @PathParam( "idCliente" ) Long idCliente )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			ArrayList<Reserva> reserva = tm.darReservaPorIdCliente(idCliente);
			return Response.status( 200 ).entity( reserva ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darReservas() {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		List<Reserva> reservas;
		try {
			reservas = tm.darReservas();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reservas).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarReserva(Reserva reserva) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.actualizarReserva(reserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reserva).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarReserva(Reserva reserva) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.eliminarReserva(reserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reserva).build();
	}
	
}
