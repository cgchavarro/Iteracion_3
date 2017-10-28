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
import vo.AdministradorRotonda;
import vo.Cliente;
import vo.MensajeError;
import vo.Menu;
import vo.OrdenRestaurante;
import vo.PreferenciaCliente;
import vo.Producto;
import vo.Restaurante;
import vo.Zona;


@Path("administradorrotonda")
public class RESTAdministradorRotonda 
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
	public Response crearAdministradorRotonda(AdministradorRotonda cliente) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearAdministradorRotonda(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}
		
	@POST
	@Path("/clientes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearCliente(Cliente atraco) 
	{
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearCliente(atraco);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(atraco).build();
	}
	
	
	@POST
	@Path("/adminrestaurante")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearRestaurante(AdministradorRestaurante atraco) 
	{
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearAdministradorRestaurante(atraco);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(atraco).build();
	}
	
	
	@POST
	@Path("/restaurantes")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearRestaurante(Restaurante atraco) 
	{
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearRestaurante(atraco);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(atraco).build();
	}

	@POST
	@Path("/zonas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearZona(Zona atraco) 
	{
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.crearZona(atraco);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(atraco).build();
	}
	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darAdministradorRotondaId( @PathParam( "id" ) Long id )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			AdministradorRotonda cliente = tm.darAdministradorRotondaPorCedula(id);
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
	public Response darAdministradorRotondaNombre( @PathParam( "nombre" ) String nombre )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			ArrayList<AdministradorRotonda> clientes = tm.darAdministradorRotondaPorNombre(nombre);
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
	public Response darAdministradorRotondaCorreo( @PathParam( "correo" ) String correo )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			AdministradorRotonda cliente = tm.darAdministradorRotondaPorCorreo(correo);
			return Response.status( 200 ).entity( cliente ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darAdministradorRotondas() {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		List<AdministradorRotonda> clientes;
		try {
			clientes = tm.darAdministradorRotondas();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(clientes).build();
	}
	
	@SuppressWarnings("unchecked")
	@GET
	@Path( "/consumocliente/{idcliente}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darConsumoClienteId( @PathParam( "idcliente" ) Long id )
	{
		long startTime = System.currentTimeMillis();
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			Cliente cliente = tm.darClientePorCedula(id);
			if(cliente==null)
			{
				return Response.status(500).entity(new MensajeError("No existe este cliente")).build();
			}
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
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarAdministradorRotonda(AdministradorRotonda cliente) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.actualizarAdministradorRotonda(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(cliente).build();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarAdministradorRotonda(AdministradorRotonda cliente) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.eliminarAdministradorRotonda(cliente);
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
	
}
