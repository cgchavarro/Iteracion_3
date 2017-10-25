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
import vo.EquivalenciaProductos;
import vo.MensajeError;
import vo.Menu;
import vo.OrdenRestaurante;
import vo.OrdenRestauranteEquivalencias;
import vo.Producto;


@Path("ordenRestaurante")
public class RESTOrdenRestaurante 
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
	public Response crearOrdenRestaurante(OrdenRestaurante ordenRestaurante) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());


		Menu m = tm.darMenuPorId(ordenRestaurante.getIdMenu());

		ArrayList<Producto> productos = new ArrayList<Producto>();

		productos.add(tm.darProductoPorId(m.getIdAcompaniamiento()));
		productos.add(tm.darProductoPorId(m.getIdBebida()));
		productos.add(tm.darProductoPorId(m.getIdEntrada()));
		productos.add(tm.darProductoPorId(m.getIdPlatoFuerte()));
		productos.add(tm.darProductoPorId(m.getIdPostre()));

		boolean	verificar=verificarProductos(productos);

		if(verificar)
		{
			try {
				tm.crearOrdenRestaurante(ordenRestaurante);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}

			return Response.status(200).entity(ordenRestaurante).build();
		}
		else
		{
			MensajeError ex = new MensajeError("no se logro crear la orden");
			System.out.println("no se logro crear");
			return Response.status(500).entity(ex).build();
		}

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("equivalencias")
	public Response crearOrdenRestauranteConEquivalencias(OrdenRestauranteEquivalencias ordenEquiv) {
		long start = System.currentTimeMillis();
		long startTotal = System.currentTimeMillis();


		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		EquivalenciaProductos[] equivalencias = ordenEquiv.getEquivalencias();
		OrdenRestaurante ordenRestaurante = ordenEquiv.getOrdenRestaurante();


		Menu m = tm.darMenuPorId(ordenRestaurante.getIdMenu());

		ArrayList<Producto> productos = new ArrayList<Producto>();


		for(EquivalenciaProductos equivalencia:equivalencias)
		{
			if(verificarEquivalenciaProducto(equivalencia)==false)
			{
				MensajeError ex = new MensajeError("no se logro crear la orden   verificando equivalencias");
				System.out.println("no se logro crear");
				return Response.status(500).entity(ex).build();
			}
		}

		productos.add(tm.darProductoPorId(m.getIdAcompaniamiento()));
		productos.add(tm.darProductoPorId(m.getIdBebida()));
		productos.add(tm.darProductoPorId(m.getIdEntrada()));
		productos.add(tm.darProductoPorId(m.getIdPlatoFuerte()));
		productos.add(tm.darProductoPorId(m.getIdPostre()));

		productos = reemplazarConEquivalencias(productos, equivalencias);
	
		

		Long idMenuTemp =  (long) (Math.random()* Long.MAX_VALUE - m.getIdMenu());

		
	
		Menu menuTemp = new Menu(idMenuTemp, m.getCosto(), m.getPrecio(), m.getNombreRestaurante(), productos.get(0).getIdProducto(), productos.get(1).getIdProducto(), productos.get(2).getIdProducto(), productos.get(3).getIdProducto(), productos.get(4).getIdProducto());
		tm.crearMenu(menuTemp);

	

		ordenRestaurante.setIdMenu(idMenuTemp);
		boolean	verificar=verificarProductos(productos);

		if(verificar)
		{
			try {
				System.out.println("inicio crear orden");
				tm.crearOrdenRestaurante(ordenRestaurante);
				System.out.println("Tiempo total " + (System.currentTimeMillis() - startTotal));

			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
	
			Menu x = tm.darMenuPorId(idMenuTemp);
			return Response.status(200).entity(x).build();
		}
		else
		{
			MensajeError ex = new MensajeError("no se logro crear la orden creando la orden");
			System.out.println("no se logro crear");
			return Response.status(500).entity(ex).build();
		}

	}

	private ArrayList<Producto> reemplazarConEquivalencias(ArrayList<Producto> productos, EquivalenciaProductos[] equivalencias) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		for(int i = 0; i < productos.size(); i ++)
		{
			Producto producto = productos.get(i);
			for(EquivalenciaProductos equivalencia:equivalencias)
			{
				if(producto.getIdProducto() == equivalencia.getIdProducto1())
				{
					productos.set(i, tm.darProductoPorId(equivalencia.getIdProducto2()));
				}
			}
		}
		return productos;
	}

	private boolean verificarEquivalenciaProducto(EquivalenciaProductos equivalencia)
	{
		RotondAndesMaster tm = new RotondAndesMaster(getPath());

		if(tm.darEquivalenciaProductos(equivalencia)!=null)
		{
			return true;
		}
//		ArrayList<EquivalenciaProductos> equivalencias = tm.darEquivalenciasProductos();
//
//		for(EquivalenciaProductos equiv:equivalencias)
//		{
//			if(equiv.getIdProducto1() == equivalencia.getIdProducto1() && equiv.getIdProducto2() == equivalencia.getIdProducto2())
//			{
//				return true;
//			}
//		}
		else
		{
		return false;
		}
	}



	private boolean verificarProductos(ArrayList<Producto> productos) 
	{
		// TODO Auto-generated method stub
		boolean puede=true;
		for(Producto p:productos)
		{
			if(p.getCantidad()<=0)
			{
				puede=false;
			}
		}
		return puede;
	}

	@GET
	@Path( "{id: \\d+}" )
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response darOrdenRestauranteId( @PathParam( "id" ) Long id )
	{
		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
		try
		{
			OrdenRestaurante ordenRestaurante = tm.darOrdenRestaurantePorId(id);
			return Response.status( 200 ).entity( ordenRestaurante ).build( );			
		}
		catch( Exception e )
		{
			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
		}
	}
	//	
	//	@GET
	//	@Path( "{idMenu: \\d+}" )
	//	@Produces( { MediaType.APPLICATION_JSON } )
	//	public Response darOrdenRestauranteNombre( @PathParam( "idMenu" ) Long idMenu )
	//	{
	//		RotondAndesMaster tm = new RotondAndesMaster( getPath( ) );
	//		try
	//		{
	//			ArrayList<OrdenRestaurante> ordenRestaurantes = tm.darOrdenRestaurantePorMenu(idMenu);
	//			return Response.status( 200 ).entity( ordenRestaurantes ).build( );			
	//		}
	//		catch( Exception e )
	//		{
	//			return Response.status( 500 ).entity( doErrorMessage( e ) ).build( );
	//		}
	//	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response darOrdenRestaurantes() {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		List<OrdenRestaurante> ordenRestaurantes;
		try {
			ordenRestaurantes = tm.darOrdenRestaurantes();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ordenRestaurantes).build();
	}

	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("ordenmesa/{idMesa}")
	public Response servirOrdenMesa (@PathParam( "idMesa" ) String id)
	{
		long start = System.currentTimeMillis();

		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		ArrayList<OrdenRestaurante> ordenes = tm.darOrdenRestaurantePorMesa(id);

		System.out.println("Tiempo 1 " + (System.currentTimeMillis() - start));
		ArrayList respuestas = new ArrayList();
		int contador = 0;
		for(OrdenRestaurante o :ordenes)
		{
			contador ++;
			System.out.println(("Tiempo " + contador) + (System.currentTimeMillis() - start));
			o.setServida(true);
			respuestas.add(actualizarOrdenRestaurante(o).getEntity());
		}
		System.out.println("Tiempo total " + (System.currentTimeMillis() - start));
		return Response.status(200).entity(respuestas).build();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarOrdenRestaurante(OrdenRestaurante ordenRestaurante) 
	{
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		OrdenRestaurante ordenAModificar = tm.darOrdenRestaurantePorId(ordenRestaurante.getIdOrdenRestaurante());
		if(ordenAModificar.isServida()==false&&ordenRestaurante.isServida()==true)
		{
			actualizarProductos(ordenRestaurante,tm);
			try {
				
				tm.actualizarOrdenRestaurante(ordenRestaurante);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(ordenRestaurante).build();
		}
		else
		{
			try 
			{
				tm.actualizarOrdenRestaurante(ordenRestaurante);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(ordenRestaurante).build();
		}
	}

	private void actualizarProductos(OrdenRestaurante ordenRestaurante, RotondAndesMaster tm) 
	{
		// TODO Auto-generated method stub
		Menu m = tm.darMenuPorId(ordenRestaurante.getIdMenu());

		ArrayList<Producto> productos = new ArrayList<Producto>();

		productos.add(tm.darProductoPorId(m.getIdAcompaniamiento()));
		productos.add(tm.darProductoPorId(m.getIdBebida()));
		productos.add(tm.darProductoPorId(m.getIdEntrada()));
		productos.add(tm.darProductoPorId(m.getIdPlatoFuerte()));
		productos.add(tm.darProductoPorId(m.getIdPostre()));


		for(Producto p:productos)
		{
			p.setCantidad(p.getCantidad()-1);
			tm.actualizarProducto(p);
		}


	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarOrdenRestaurante(OrdenRestaurante ordenRestaurante) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		try {
			tm.eliminarOrdenRestaurante(ordenRestaurante);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(ordenRestaurante).build();
	}


	@DELETE
	@Path( "{id: \\d+}" )
	@Consumes(MediaType.APPLICATION_JSON)	
	@Produces( { MediaType.APPLICATION_JSON } )
	public Response cancelarOrdenPedido(OrdenRestaurante ordenRestaurante, @PathParam( "id" ) Long id)
	{
		RotondAndesMaster tm = new RotondAndesMaster(getPath());
		OrdenRestaurante orden = tm.darOrdenRestaurantePorId(ordenRestaurante.getIdOrdenRestaurante());
		if(!orden.isServida())
		{
			try {
				tm.eliminarOrdenRestaurante(ordenRestaurante);
				restaurarProductosOrden(ordenRestaurante);
			} catch (Exception e) {
				return Response.status(500).entity(doErrorMessage(e)).build();
			}
			return Response.status(200).entity(ordenRestaurante).build();
		}
		else
		{

			MensajeError ex = new MensajeError("no se logro eliminar la orden");
			System.out.println("no se logro eliminar");
			return Response.status(500).entity(ex).build();
		}
	}

	private void restaurarProductosOrden(OrdenRestaurante ordenRestaurante) {
		RotondAndesMaster tm = new RotondAndesMaster(getPath());		

		Menu m = tm.darMenuPorId(ordenRestaurante.getIdMenu());

		tm.actualizarProducto(tm.darProductoPorId(m.getIdAcompaniamiento()));
		tm.actualizarProducto(tm.darProductoPorId(m.getIdBebida()));
		tm.actualizarProducto(tm.darProductoPorId(m.getIdEntrada()));
		tm.actualizarProducto(tm.darProductoPorId(m.getIdPlatoFuerte()));
		tm.actualizarProducto(tm.darProductoPorId(m.getIdPostre()));
	}

}
