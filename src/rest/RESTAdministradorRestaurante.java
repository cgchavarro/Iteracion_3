package rest;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import vo.AdministradorRestaurante;
import vo.ClienteRFC;
import vo.EquivalenciaIngredientes;
import vo.EquivalenciaProductos;
import vo.Ingrediente;
import vo.MensajeError;
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
	@Path("/productos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearProducto(Producto cliente) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		if(cliente.getCosto()>0)
		{
			try {
				tm.crearProducto(cliente);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(cliente).build();
		}
		return Response.status(401).entity(cliente).build();
	}

	@POST
	@Path("/ingredientes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearIngrediente(Ingrediente cliente) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.crearIngrediente(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}
	@POST
	@Path("/menus")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearMenu(Menu cliente) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
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
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
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
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
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
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
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

	@PUT
	@Path( "/surtirrestaurante/{nombreRestaurante}" )
	@Produces(MediaType.APPLICATION_JSON)
	public Response surtirRestaurante(@PathParam( "nombreRestaurante" ) String nombre ) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		ArrayList<Producto> cliente;
		try {
			tm.surtirProductosRestaurante(nombre);
			cliente = tm.darProductosRestaurante(nombre);
		} catch (Exception e) {
			return Response.status(500).entity(new MensajeError("No actualizo")).build();
		}
		if(cliente.size()==0)
		{
			return Response.status(500).entity(new MensajeError("No actualizo")).build();
		}
		return Response.status(200).entity(cliente).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darAdministradorRestaurantes() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
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
		RotondAndesTM tm = new RotondAndesTM(getPath());
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
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.eliminarAdministradorRestaurante(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}


	//equivalencia productos

	@POST
	@Path("/equivalenciaproductos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearSimilitudProductos(EquivalenciaProductos cliente) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.crearEquivalenciasProducto(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}

	@GET
	@Path("/equivalenciaproductos")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darEquivalenciasProductos() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<EquivalenciaProductos> clientes;
		try {
			clientes = tm.darEquivalenciasProductos();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(clientes).build();
	}

	@DELETE
	@Path("/equivalenciaproductos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarEquivalenciaProductos(EquivalenciaProductos cliente) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.eliminarEquivalenciasProducto(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}

	//equivalencia pingredientes

	@POST
	@Path("/equivalenciaingredientes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearSimilitudIngredientes(EquivalenciaIngredientes cliente) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.crearEquivalenciasIngrediente(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}

	@GET
	@Path("/equivalenciaingredientes")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darEquivalenciasIngredientes() {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<EquivalenciaIngredientes> clientes;
		try {
			clientes = tm.darEquivalenciasIngredientes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(clientes).build();
	}

	@DELETE
	@Path("/equivalenciaingredientes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarEquivalenciaIngredientes(EquivalenciaIngredientes cliente) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.eliminarEquivalenciasIngredientes(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}


	//TODO RFC8. CONSULTAR PEDIDOS
	//		Muestra la información consolidada de los pedidos hechos en RotondAndes.
	//		Consolida, como mínimo, para cada uno los restaurantes y para cada uno de sus productos 
	//		las ventas totales (en dinero y en cantidad), lo consumidos por clientes registrados 
	//		y por clientes no registrados.
	//		Esta operación es realizada por el administrador de RotondAndes.
	//		NOTA: Respetando la privacidad de los clientes, 
	//		cuando un restaurante hace esta consulta obtiene la información de sus propias actividades,
	//		mientras que el administrador obtiene toda la información. Ver RNF1.

	//	

	@GET
	@Path("/consultardedidos/{idRestaurante}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darPedidosRestaurante(@PathParam( "idRestaurante" ) String id)
	{

		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<EquivalenciaIngredientes> clientes;
		try {
			clientes = tm.darEquivalenciasIngredientes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(clientes).build();
	}

	/**
	 * RFC9. CONSULTAR CONSUMO EN ROTONDANDES
	 * Se quiere conocer la información de los usuarios que consumieron al menos un producto 
	 * de un determinado restaurante en un rango de fechas. Los resultados deben ser clasificados 
	 * según un criterio deseado por quien realiza la consulta. En la clasificación debe ofrecerse 
	 * la posibilidad de agrupamiento y ordenamiento de las respuestas según los intereses del usuario 
	 * que consulta como, por ejemplo, por los datos del cliente, por producto y por tipo de producto. 
	 * Esta operación es realizada por los usuarios restaurante y por el gerente general de RotondAndes.
	 * NOTA: Respetando la privacidad de los clientes, cuando un cliente restaurante hace esta consulta 
	 * obtiene la información de su propia actividad, mientras que el administrador obtiene toda la información 
	 * de cualquiera de los clientes. Ver RNF1.
	 */

	@GET
	@Path("/consultarConsumo/{idRestaurante}/{fechaMin}/{fechaMax}/{orderBy}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darConsumoRotondandes(@PathParam( "idRestaurante" ) String id, @PathParam( "fechaMin" ) String fechaMin, @PathParam( "fechaMax" ) String fechaMax, @PathParam( "orderBy" ) String orderBy)
	{		
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<ClienteRFC> clientes;
		try {
			clientes = tm.consultarConsumoClientes(id, fechaMin, fechaMax, orderBy);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(clientes).build();
	}
	
	@GET
	@Path("/consultarNoConsumo/{idRestaurante}/{fechaMin}/{fechaMax}/{orderBy}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darNoConsumoRotondandes(@PathParam( "idRestaurante" ) String id, @PathParam( "fechaMin" ) String fechaMin, @PathParam( "fechaMax" ) String fechaMax, @PathParam( "orderBy" ) String orderBy)
	{		
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<ClienteRFC> clientes;
		try {
			clientes = tm.consultarNoConsumoClientes(id, fechaMin, fechaMax, orderBy);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(clientes).build();
	}
}
