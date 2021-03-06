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
import vo.Cliente;
import vo.ClienteRFC;
import vo.OrdenRestaurante;
import vo.PreferenciaCliente;
import vo.Producto;
import vo.Menu;

@Path("cliente")
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
	@Path("/preferencias")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearPreferencia(PreferenciaCliente cliente) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.crearPreferenciaCliente(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}
	
	@PUT
	@Path("/preferencias")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarPreferencia(PreferenciaCliente cliente) {
		RotondAndesTM tm = new RotondAndesTM(getPath());
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
	
	@SuppressWarnings("unchecked")
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darClienteId( @PathParam( "id" ) Long id )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			Cliente cliente = tm.darClientePorCedula(id);
			ArrayList<OrdenRestaurante> orden =tm.darOrdenRestaurantePorIdCliente(id);
			PreferenciaCliente p = tm.darPreferenciaClientePorId(id);
			
			@SuppressWarnings("rawtypes")
			ArrayList c = new ArrayList();
			c.add(cliente); c.addAll(orden); c.add(p);
			return Response.status( 200 ).entity( c ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
//	RFC7. CONSULTAR EL CONSUMO DE UN CLIENTE REGISTRADO
//	Consulta los productos que ha consumido un cliente registrado de RotondAndes. Debe discriminar los productos que ha
//	solicitado independientemente, los de los men�s y los solicitados en una mesa. Esta operaci�n es realizada por los clientes
//	registrados y por el usuario administrador de RotondAndes.
//	NOTA: Respetando la privacidad de los clientes, cuando un cliente registrado hace esta consulta obtiene la informaci�n
//	de su propia actividad, mientras que el administrador obtiene toda la informaci�n de cualquiera de los clientes. Ver RNF1.
	
	@SuppressWarnings("unchecked")
	@GET
	@Path( "/consumocliente/{idcliente}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darConsumoClienteId( @PathParam( "idcliente" ) Long id )
	{
		long startTime = System.currentTimeMillis();
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
		try
		{
			Cliente cliente = tm.darClientePorCedula(id);
			ArrayList<OrdenRestaurante> orden =tm.darOrdenRestaurantePorIdCliente(id);
			ArrayList<Menu> menus = new ArrayList<Menu>();
			ArrayList<Producto> productos = new ArrayList<Producto>();
			for(OrdenRestaurante o: orden)
			{
				Menu menuActual = tm.darMenuPorId(o.getIdMenu());
				menus.add(menuActual);
				
			}
			for(Menu m:menus)
			{
				productos.add(tm.darProductoPorId(m.getIdAcompaniamiento()));
				productos.add(tm.darProductoPorId(m.getIdBebida()));
				productos.add(tm.darProductoPorId(m.getIdEntrada()));
				productos.add(tm.darProductoPorId(m.getIdPlatoFuerte()));
				productos.add(tm.darProductoPorId(m.getIdPostre()));
				
			}
			PreferenciaCliente p = tm.darPreferenciaClientePorId(id);
			productos = eliminarRepetidos(productos);
			@SuppressWarnings("rawtypes")
			ArrayList c = new ArrayList();
			c.add(cliente); c.addAll(orden); c.add(menus); c.add(productos); c.add(p);
			long endTime = System.currentTimeMillis();
			System.out.println("Total time spent (milliseconds): "+(endTime-startTime));
			return Response.status( 200 ).entity( c ).build( );	
		
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	
	}
	

	private ArrayList<Producto> eliminarRepetidos(ArrayList<Producto> productos) 
	{
	ArrayList<Producto> pro = productos;
	for(int i=0; i<pro.size();i++)
	{
		Producto iActual = pro.get(i);
		for(int j=0;j<pro.size();j++)
		{
			Producto jActual = pro.get(j);
			if(iActual.getIdProducto()==jActual.getIdProducto()&&j!=i)
			{
				pro.remove(j);
			}
		}
	}
	return pro;
}

	@GET
	@Path( "{nombre}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darClienteNombre( @PathParam( "nombre" ) String nombre )
	{
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
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
		RotondAndesTM tm = new RotondAndesTM( getPath( ) );
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
		RotondAndesTM tm = new RotondAndesTM(getPath());
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
		RotondAndesTM tm = new RotondAndesTM(getPath());
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
		RotondAndesTM tm = new RotondAndesTM(getPath());
		try {
			tm.eliminarCliente(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}
	
	@GET
	@Path("/{id: \\d+}/consultarConsumo/{idRestaurante}/{fechaMin}/{fechaMax}/{orderBy}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darConsumoRotondandes(@PathParam( "id" ) Long id, @PathParam( "idRestaurante" ) String idRest, @PathParam( "fechaMin" ) String fechaMin, @PathParam( "fechaMax" ) String fechaMax, @PathParam( "orderBy" ) String orderBy)
	{		
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<ClienteRFC> clientes;
		try {
			clientes = tm.consultarConsumoDeUnCliente(id, idRest, fechaMin, fechaMax, orderBy);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(clientes).build();
	}
	
	@GET
	@Path("/{id: \\d+}/consultarNoConsumo/{idRestaurante}/{fechaMin}/{fechaMax}/{orderBy}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darNoConsumoRotondandes(@PathParam( "id" ) Long id, @PathParam( "idRestaurante" ) String idRest, @PathParam( "fechaMin" ) String fechaMin, @PathParam( "fechaMax" ) String fechaMax, @PathParam( "orderBy" ) String orderBy)
	{		
		RotondAndesTM tm = new RotondAndesTM(getPath());
		List<ClienteRFC> clientes;
		try {
			clientes = tm.consultarNoConsumoDeUnCliente(id, idRest, fechaMin, fechaMax, orderBy);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(clientes).build();
	}
	
}
