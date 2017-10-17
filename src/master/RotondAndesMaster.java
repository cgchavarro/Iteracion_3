package master;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Properties;

import dao.DAOTablaAdministradorRestaurante;
import dao.DAOTablaAdministradorRotonda;
import dao.DAOTablaCategoria;
import dao.DAOTablaCliente;
import dao.DAOTablaContabilidadGeneral;
import dao.DAOTablaIngrediente;
import dao.DAOTablaIngredienteProducto;
import dao.DAOTablaMenu;
import dao.DAOTablaOrdenRestaurante;
import dao.DAOTablaPreferenciaCliente;
import dao.DAOTablaProducto;
import dao.DAOTablaReserva;
import dao.DAOTablaRestaurante;
import dao.DAOTablaRotonda;
import dao.DAOTablaTipo;
import dao.DAOTablaTipoProducto;

import dao.DAOTablaZona;
import dao.DAOTablaContabilidadRestaurante;
import vo.AdministradorRestaurante;
import vo.AdministradorRotonda;
import vo.Categoria;
import vo.Cliente;
import vo.ContabilidadGeneral;
import vo.ContabilidadRestaurante;
import vo.Ingrediente;
import vo.IngredienteProducto;
import vo.Producto;
import vo.Reserva;
import vo.Restaurante;
import vo.Rotonda;
import vo.Tipo;
import vo.TipoProducto;

import vo.Zona;
import vo.Menu;
import vo.OrdenRestaurante;
import vo.PreferenciaCliente;

public class RotondAndesMaster 
{
//	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";
//
//	private String url;
//
//	private String user;
//
//	private String password;
//
//	private String driver;
//	
//	/**
//	 * conexion a la base de datos
//	 */
//	private Connection conn;
//
//	public RotondAndesMaster(String path)
//	{
//		connectionData(path);
//	}
//
//	private Connection darConexion() throws SQLException
//	{
//		return DriverManager.getConnection(url, user, password);
//	}
//
//	private void connectionData(String path) 
//	{	try
//		{
//			File arch = new File(path + CONNECTION_DATA_FILE_NAME_REMOTE);
//			Properties prop = new Properties();
//			FileInputStream in = new FileInputStream(arch);
//			prop.load(in);
//			in.close();
//			url = prop.getProperty("url");
//			user = prop.getProperty("usuario");
//			password = prop.getProperty("clave");
//			driver = prop.getProperty("driver");
//			Class.forName(driver);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * Path relativo del archivo con los datos de la conexión
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Path absoluto de la conexión
	 */
	private  String connectionDataPath;
	/**
	 * Usuario con el que se va a conectar a la BD
	 */
	private String user;
	/**
	 * Constraseña con la que se va a conectar a la BD
	 */
	private String password;
	/**
	 * URL que se va a utilizar para conectarse a la BD
	 */
	private String url;
	/**
	 * Driver que se va a utilizar para conectarse a la BD
	 */
	private String driver;
	/**
	 * Conexión a la BD
	 */
	//private Connection conn;

	/**
	 * Método constructor. Inicializa y modela las transacciones que se
	 * llevarán a cabo.
	 * @param contextPathP, path absoluto en el servidor del contexto del deploy actual
	 */
	public RotondAndesMaster(String contextPathP) {
		connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
		initConnectionData();
	}

	/**
	 * Inicializa la conexión a la BD leyendo el archivo con las propiedades
	 */
	private void initConnectionData() {
		try {
			File arch = new File(this.connectionDataPath);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(arch);
			prop.load(in);
			in.close();
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("usuario");
			this.password = prop.getProperty("clave");
			this.driver = prop.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retorna el objeto que modela la conexión a la BD
	 * @return la conexión
	 * @throws SQLException
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("Connecting to: " + url + " With user: " + user);
		return DriverManager.getConnection(url, user, password);
	}

	// ----------------------------------  Inicio metodos Categoria	----------------------------------



	public void crearCategoria(Categoria categoria)
	{
		DAOTablaCategoria dao = new DAOTablaCategoria();
		try(Connection conn = darConexion())
		{
			dao.agregarCategoria(conn, categoria);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public Categoria darCategoriaPorId(Long id)
	{
		Categoria categoria = null;
		DAOTablaCategoria dao = new DAOTablaCategoria();
		try(Connection conn = darConexion())
		{
			categoria = dao.darCategoriaPorId(conn, id);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return categoria;		
	}

	public ArrayList<Categoria> darCategoriaPorNombre(String nombre)
	{
		ArrayList<Categoria> categorias = new ArrayList<>();
		DAOTablaCategoria dao = new DAOTablaCategoria();
		try(Connection conn = darConexion())
		{
			categorias = dao.darCategoriasPorNombre(conn, nombre);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return categorias;		
	}

	public ArrayList<Categoria> darCategorias()
	{
		ArrayList<Categoria> categorias = new ArrayList<>();
		DAOTablaCategoria dao = new DAOTablaCategoria();
		try(Connection conn = darConexion())
		{
			categorias = dao.darCategorias(conn);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return categorias;
	}

	public void actualizarCategoria(Categoria categoria)
	{
		DAOTablaCategoria dao = new DAOTablaCategoria();
		try(Connection conn = darConexion())
		{
			dao.actualizarCategoria(conn, categoria);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public void eliminarCategoria(Categoria categoria)
	{
		DAOTablaCategoria dao = new DAOTablaCategoria();
		try(Connection conn = darConexion())
		{
			dao.eliminarCategoria(conn, categoria);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}


	// ----------------------------------   Fin metodos Categoria	----------------------------------


	// ----------------------------------	Inicio metodos Cliente	----------------------------------

	public void crearCliente(Cliente cliente)
	{
		DAOTablaCliente dao = new DAOTablaCliente();
		try(Connection conn = darConexion())
		{
			dao.agregarCliente(conn, cliente);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public Cliente darClientePorCedula(Long cedula)
	{
		Cliente cliente = null;
		DAOTablaCliente dao = new DAOTablaCliente();
		try(Connection conn = darConexion())
		{
			cliente = dao.darClientePorCedula(conn, cedula);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return cliente;
	}

	public Cliente darClientePorCorreo(String correo)
	{
		Cliente cliente = null;
		DAOTablaCliente dao = new DAOTablaCliente();
		try(Connection conn = darConexion())
		{
			cliente = dao.darClientePorCorreo(conn, correo);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return cliente;
	}

	public ArrayList<Cliente> darClientePorNombre(String nombre)
	{
		ArrayList<Cliente> cliente = null;
		DAOTablaCliente dao = new DAOTablaCliente();
		try(Connection conn = darConexion())
		{
			cliente = dao.darClientesPorNombre(conn, nombre);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return cliente;
	}

	public ArrayList<Cliente> darClientes()
	{
		ArrayList<Cliente> cliente = null;
		DAOTablaCliente dao = new DAOTablaCliente();
		try(Connection conn = darConexion())
		{
			cliente = dao.darClientes(conn);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return cliente;
	}

	public void actualizarCliente(Cliente cliente)
	{
		DAOTablaCliente dao = new DAOTablaCliente();
		try(Connection conn = darConexion())
		{
			dao.actualizarCliente(conn, cliente);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public void eliminarCliente(Cliente cliente)
	{
		DAOTablaCliente dao = new DAOTablaCliente();
		try(Connection conn = darConexion())
		{
			dao.eliminarCliente(conn, cliente);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	// ----------------------------------	 Fin metodos Cliente	----------------------------------

	// ---------------------------------- Inicio metodos Ingrediente ----------------------------------

	public void crearIngrediente(Ingrediente ingrediente)
	{
		DAOTablaIngrediente dao = new DAOTablaIngrediente();
		try(Connection conn = darConexion())
		{
			dao.agregarIngrediente(conn, ingrediente);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public Ingrediente darIngredientePorId(Long id)
	{
		Ingrediente ingrediente = null;
		DAOTablaIngrediente dao = new DAOTablaIngrediente();
		try(Connection conn = darConexion())
		{
			ingrediente = dao.darIngredientePorId(conn, id);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return ingrediente;		
	}

	public ArrayList<Ingrediente> darIngredientePorNombre(String nombre)
	{
		ArrayList<Ingrediente> ingredientes = new ArrayList<>();
		DAOTablaIngrediente dao = new DAOTablaIngrediente();
		try(Connection conn = darConexion())
		{
			ingredientes = dao.darIngredientesPorNombre(conn, nombre);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return ingredientes;		
	}

	public ArrayList<Ingrediente> darIngredientes()
	{
		ArrayList<Ingrediente> ingredientes = new ArrayList<>();
		DAOTablaIngrediente dao = new DAOTablaIngrediente();
		try(Connection conn = darConexion())
		{
			ingredientes = dao.darIngredientes(conn);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return ingredientes;
	}

	public void actualizarIngrediente(Ingrediente ingrediente)
	{
		DAOTablaIngrediente dao = new DAOTablaIngrediente();
		try(Connection conn = darConexion())
		{
			dao.actualizarIngrediente(conn, ingrediente);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public void eliminarIngrediente(Ingrediente ingrediente)
	{
		DAOTablaIngrediente dao = new DAOTablaIngrediente();
		try(Connection conn = darConexion())
		{
			dao.eliminarIngrediente(conn, ingrediente);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	// ----------------------------------  Fin metodos Ingrediente	----------------------------------


	// ----------------------------------Inicio metodos IngredienteProducto	----------------------------------

	public void crearIngredienteProducto(IngredienteProducto ingredienteProducto)
	{
		DAOTablaIngredienteProducto dao = new DAOTablaIngredienteProducto();
		try(Connection conn = darConexion())
		{
			dao.agregarIngredienteProducto(conn, ingredienteProducto);
			conn.commit();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}

	public ArrayList<IngredienteProducto> darIngredientesProductoPorIdIngrediente(Long idIngrediente)
	{
		ArrayList<IngredienteProducto> ingredientesProductos = new ArrayList<>();
		DAOTablaIngredienteProducto dao = new DAOTablaIngredienteProducto();
		try(Connection conn = darConexion())
		{
			ingredientesProductos = dao.darIngredientesProductosPorIdIngrediente(conn, idIngrediente);
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		return ingredientesProductos;
	}

	public ArrayList<IngredienteProducto> darIngredientesProductoPorIdProducto(Long idProducto)
	{
		ArrayList<IngredienteProducto> ingredientesProductos = new ArrayList<>();
		DAOTablaIngredienteProducto dao = new DAOTablaIngredienteProducto();
		try(Connection conn = darConexion())
		{
			ingredientesProductos = dao.darIngredientesProductosPorIdProducto(conn, idProducto);
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		return ingredientesProductos;
	}

	public ArrayList<IngredienteProducto> darIngredientesProductos()
	{
		ArrayList<IngredienteProducto> ingredientesProductos = new ArrayList<>();
		DAOTablaIngredienteProducto dao = new DAOTablaIngredienteProducto();
		try(Connection conn = darConexion())
		{
			ingredientesProductos = dao.darIngredientesProductos(conn);
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		return ingredientesProductos;
	}

	public void actualizarProductoDeIngredienteProducto(IngredienteProducto ingredienteProducto)
	{
		DAOTablaIngredienteProducto dao = new DAOTablaIngredienteProducto();
		try(Connection conn = darConexion())
		{
			dao.actualizarProductoDeIngredienteProducto(conn, ingredienteProducto);
			conn.commit();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}

	public void actualizarIngredienteDeIngredienteProducto(IngredienteProducto ingredienteProducto)
	{
		DAOTablaIngredienteProducto dao = new DAOTablaIngredienteProducto();
		try(Connection conn = darConexion())
		{
			dao.actualizarIngredienteDeIngredienteProducto(conn, ingredienteProducto);
			conn.commit();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}

	public void eliminarIngredienteProducto(IngredienteProducto ingredienteProducto)
	{
		DAOTablaIngredienteProducto dao = new DAOTablaIngredienteProducto();
		try(Connection conn = darConexion())
		{
			dao.eliminarIngredienteProducto(conn, ingredienteProducto);
			conn.commit();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}

	// ----------------------------------  Fin metodos IngredienteProducto	----------------------------------

	// ----------------------------------	   Inicio metodos Menu		----------------------------------

	public void crearMenu(Menu menu)
	{
		DAOTablaMenu dao = new DAOTablaMenu();
		try(Connection conn = darConexion())
		{
			dao.agregarMenu(conn, menu);
			conn.commit();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}

	public Menu darMenuPorId(Long id)
	{
		Menu menu = null;
		DAOTablaMenu dao = new DAOTablaMenu();
		try(Connection conn = darConexion())
		{
			menu = dao.darMenuPorId(conn, id);
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		return menu;
	}

	public ArrayList<Menu> darMenusPorRestaurante(Long idRestaurante)
	{
		ArrayList<Menu> menus = null;
		DAOTablaMenu dao = new DAOTablaMenu();
		try(Connection conn = darConexion())
		{
			menus = dao.darMenusPorRestaurante(conn, idRestaurante);
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		return menus;
	}

	public ArrayList<Menu> darMenusPorPostre(Long idRestaurante)
	{
		ArrayList<Menu> menus = null;
		DAOTablaMenu dao = new DAOTablaMenu();
		try(Connection conn = darConexion())
		{
			menus = dao.darMenusPorPostre(conn, idRestaurante);
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		return menus;
	}
	
	public ArrayList<Menu> darMenusPorEntrada(Long idRestaurante)
	{
		ArrayList<Menu> menus = null;
		DAOTablaMenu dao = new DAOTablaMenu();
		try(Connection conn = darConexion())
		{
			menus = dao.darMenusPorEntrada(conn, idRestaurante);
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		return menus;
	}
	
	public ArrayList<Menu> darMenusPorPlatoFuerte(Long idRestaurante)
	{
		ArrayList<Menu> menus = null;
		DAOTablaMenu dao = new DAOTablaMenu();
		try(Connection conn = darConexion())
		{
			menus = dao.darMenusPorPlatoFuerte(conn, idRestaurante);
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		return menus;
	}
	public ArrayList<Menu> darMenusPorAcompaniamiento(Long idRestaurante)
	{
		ArrayList<Menu> menus = null;
		DAOTablaMenu dao = new DAOTablaMenu();
		try(Connection conn = darConexion())
		{
			menus = dao.darMenusPorAcompaniamiento(conn, idRestaurante);
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		return menus;
	}
	public ArrayList<Menu> darMenusPorBebida(Long idRestaurante)
	{
		ArrayList<Menu> menus = null;
		DAOTablaMenu dao = new DAOTablaMenu();
		try(Connection conn = darConexion())
		{
			menus = dao.darMenusPorBebida(conn, idRestaurante);
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		return menus;
	}
	public ArrayList<Menu> darMenus()
	{
		ArrayList<Menu> menus = null;
		DAOTablaMenu dao = new DAOTablaMenu();
		try(Connection conn = darConexion())
		{
			menus = dao.darMenus(conn);
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		return menus;
	}

	public void actualizarMenu(Menu menu)
	{
		DAOTablaMenu dao = new DAOTablaMenu();
		try(Connection conn = darConexion())
		{
			dao.actualizarMenu(conn, menu);
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}

	public void eliminarMenu(Menu menu)
	{
		DAOTablaMenu dao = new DAOTablaMenu();
		try(Connection conn = darConexion())
		{
			dao.eliminarMenu(conn, menu);
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}

	// ----------------------------------  		 Fin metodos Menu		----------------------------------

	// ----------------------------------Inicio metodos OrdenRestaurante	----------------------------------

	public void crearOrdenRestaurante(OrdenRestaurante ordenRestaurante)
	{
		DAOTablaOrdenRestaurante dao = new DAOTablaOrdenRestaurante();
		try(Connection conn = darConexion())
		{
			dao.agregarOrdenRestaurante(conn, ordenRestaurante);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public OrdenRestaurante darOrdenRestaurantePorId(Long id)
	{
		OrdenRestaurante ordenRestaurante = null;
		DAOTablaOrdenRestaurante dao = new DAOTablaOrdenRestaurante();
		try(Connection conn = darConexion())
		{
			ordenRestaurante = dao.darOrdenRestaurantePorId(conn, id);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return ordenRestaurante;		
	}

	public ArrayList<OrdenRestaurante> darOrdenRestaurantePorIdCliente(Long idMenu)
	{
		ArrayList<OrdenRestaurante> ordenRestaurantes = new ArrayList<>();
		DAOTablaOrdenRestaurante dao = new DAOTablaOrdenRestaurante();
		try(Connection conn = darConexion())
		{
			ordenRestaurantes = dao.darOrdenRestaurantesPorIdCliente(conn, idMenu);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return ordenRestaurantes;		
	}
	public ArrayList<OrdenRestaurante> darOrdenRestaurantePorMenu(Long idMenu)
	{
		ArrayList<OrdenRestaurante> ordenRestaurantes = new ArrayList<>();
		DAOTablaOrdenRestaurante dao = new DAOTablaOrdenRestaurante();
		try(Connection conn = darConexion())
		{
			ordenRestaurantes = dao.darOrdenRestaurantesPorMenu(conn, idMenu);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return ordenRestaurantes;		
	}

	public ArrayList<OrdenRestaurante> darOrdenRestaurantes()
	{
		ArrayList<OrdenRestaurante> ordenRestaurantes = new ArrayList<>();
		DAOTablaOrdenRestaurante dao = new DAOTablaOrdenRestaurante();
		try(Connection conn = darConexion())
		{
			ordenRestaurantes = dao.darOrdenRestaurantes(conn);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return ordenRestaurantes;
	}

	public void actualizarOrdenRestaurante(OrdenRestaurante ordenRestaurante)
	{
		DAOTablaOrdenRestaurante dao = new DAOTablaOrdenRestaurante();
		try(Connection conn = darConexion())
		{
			dao.actualizarOrdenRestaurante(conn, ordenRestaurante);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public void eliminarOrdenRestaurante(OrdenRestaurante ordenRestaurante)
	{
		DAOTablaOrdenRestaurante dao = new DAOTablaOrdenRestaurante();
		try(Connection conn = darConexion())
		{
			dao.eliminarOrdenRestaurante(conn, ordenRestaurante);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	// ----------------------------------  Fin metodos OrdenRestaurante	----------------------------------

	// ----------------------------------Inicio metodos Reserva	----------------------------------

	public void crearReserva(Reserva reserva)
	{
		DAOTablaReserva dao = new DAOTablaReserva();
		try(Connection conn = darConexion())
		{
			dao.agregarReserva(conn, reserva);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public Reserva darReservaPorId(Long id)
	{
		Reserva reserva = null;
		DAOTablaReserva dao = new DAOTablaReserva();
		try(Connection conn = darConexion())
		{
			reserva = dao.darReservaPorId(conn, id);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return reserva;
	}

	public ArrayList<Reserva> darReservaPorIdCliente(Long idCliente)
	{
		ArrayList<Reserva> reservas = null;
		DAOTablaReserva dao = new DAOTablaReserva();
		try(Connection conn = darConexion())
		{
			reservas = dao.darReservasPorCliente(conn, idCliente);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return reservas;
	}

	public ArrayList<Reserva> darReservaPorNombre(Long idZona)
	{
		ArrayList<Reserva> reserva = null;
		DAOTablaReserva dao = new DAOTablaReserva();
		try(Connection conn = darConexion())
		{
			reserva = dao.darReservasPorZona(conn, idZona);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return reserva;
	}

	public ArrayList<Reserva> darReservas()
	{
		ArrayList<Reserva> reserva = null;
		DAOTablaReserva dao = new DAOTablaReserva();
		try(Connection conn = darConexion())
		{
			reserva = dao.darReservas(conn);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return reserva;
	}

	public void actualizarReserva(Reserva reserva)
	{
		DAOTablaReserva dao = new DAOTablaReserva();
		try(Connection conn = darConexion())
		{
			dao.actualizarReserva(conn, reserva);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public void eliminarReserva(Reserva reserva)
	{
		DAOTablaReserva dao = new DAOTablaReserva();
		try(Connection conn = darConexion())
		{
			dao.eliminarReserva(conn, reserva);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}


	// ----------------------------------  Fin metodos Reserva	----------------------------------

	// ----------------------------------Inicio metodos Restaurante	----------------------------------

	public void crearRestaurante(Restaurante restaurante)
	{
		DAOTablaRestaurante dao = new DAOTablaRestaurante();
		try(Connection conn = darConexion())
		{
			dao.agregarRestaurante(conn, restaurante);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public Restaurante darRestaurantePorNombre(String nombre)
	{
		Restaurante restaurante = null;
		DAOTablaRestaurante dao = new DAOTablaRestaurante();
		try(Connection conn = darConexion())
		{
			restaurante = dao.darRestaurantePorNombre(conn, nombre);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return restaurante;
	}

	
	//	public ArrayList<Restaurante> darRestaurantesPorNombre(String nombre)
	//	{
	//		ArrayList<Restaurante> restaurantes = new ArrayList<>();
	//		DAOTablaRestaurante dao = new DAOTablaRestaurante();
	//		try(Connection conn = darConexion())
	//		{
	//			restaurantes = dao.darRestaurantesPorNombre(conn, nombre);
	//		}
	//		catch(SQLException e)
	//		{
	//			 
	//			e.printStackTrace();
	//		}
	//		return restaurantes;
	//	}
	//	
	public ArrayList<Restaurante> darRestaurantes()
	{
		ArrayList<Restaurante> restaurantes = new ArrayList<>();
		DAOTablaRestaurante dao = new DAOTablaRestaurante();
		try(Connection conn = darConexion())
		{
			restaurantes = dao.darRestaurantes(conn);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return restaurantes;
	}
	public ArrayList<Restaurante> darRestaurantesPorZona(Long id)
	{
		ArrayList<Restaurante> restaurantes = new ArrayList<>();
		DAOTablaRestaurante dao = new DAOTablaRestaurante();
		try(Connection conn = darConexion())
		{
			restaurantes = dao.darRestaurantesPorZona(conn, id);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return restaurantes;
	}

	public void actualizarRestaurante(Restaurante restaurante)
	{
		DAOTablaRestaurante dao = new DAOTablaRestaurante();
		try(Connection conn = darConexion())
		{
			dao.actualizarRestaurante(conn, restaurante);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public void eliminarRestaurante(Restaurante restaurante)
	{
		DAOTablaRestaurante dao = new DAOTablaRestaurante();
		try(Connection conn = darConexion())
		{
			dao.eliminarRestaurante(conn, restaurante);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	// ----------------------------------  Fin metodos Restaurante	----------------------------------

	// ----------------------------------Inicio metodos Tipo	----------------------------------

	public void crearTipo(Tipo tipo)
	{
		DAOTablaTipo dao = new DAOTablaTipo();
		try(Connection conn = darConexion())
		{
			dao.agregarTipo(conn, tipo);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public Tipo darTipoPorId(Long id)
	{
		Tipo tipo = null;
		DAOTablaTipo dao = new DAOTablaTipo();
		try(Connection conn = darConexion())
		{
			tipo = dao.darTipoPorId(conn, id);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return tipo;
	}

	public ArrayList<Tipo> darTiposPorNombre(String nombre)
	{
		ArrayList<Tipo> tipos = new ArrayList<>();
		DAOTablaTipo dao = new DAOTablaTipo();
		try(Connection conn = darConexion())
		{
			tipos = dao.darTiposPorNombre(conn, nombre);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return tipos;
	}

	public ArrayList<Tipo> darTipos()
	{
		ArrayList<Tipo> tipos = new ArrayList<>();
		DAOTablaTipo dao = new DAOTablaTipo();
		try(Connection conn = darConexion())
		{
			tipos = dao.darTipos(conn);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return tipos;
	}

	public void actualizarTipo(Tipo tipo)
	{
		DAOTablaTipo dao = new DAOTablaTipo();
		try(Connection conn = darConexion())
		{
			dao.actualizarTipo(conn, tipo);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public void eliminarTipo(Tipo tipo)
	{
		DAOTablaTipo dao = new DAOTablaTipo();
		try(Connection conn = darConexion())
		{
			dao.eliminarTipo(conn, tipo);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	// ----------------------------------  Fin metodos Tipo	----------------------------------

	// ----------------------------------Inicio metodos TipoProducto	----------------------------------

	public void crearTipoProducto(TipoProducto tipoProducto)
	{
		DAOTablaTipoProducto dao = new DAOTablaTipoProducto();
		try(Connection conn = darConexion())
		{
			dao.agregarTipoProducto(conn, tipoProducto);
			conn.commit();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}

	public ArrayList<TipoProducto> darTiposProductoPorIdTipo(Long idTipo)
	{
		ArrayList<TipoProducto> tiposProductos = new ArrayList<>();
		DAOTablaTipoProducto dao = new DAOTablaTipoProducto();
		try(Connection conn = darConexion())
		{
			tiposProductos = dao.darTiposProductosPorIdTipo(conn, idTipo);
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		return tiposProductos;
	}

	public ArrayList<TipoProducto> darTiposProductoPorIdProducto(Long idProducto)
	{
		ArrayList<TipoProducto> tiposProductos = new ArrayList<>();
		DAOTablaTipoProducto dao = new DAOTablaTipoProducto();
		try(Connection conn = darConexion())
		{
			tiposProductos = dao.darTiposProductosPorIdProducto(conn, idProducto);
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		return tiposProductos;
	}

	public ArrayList<TipoProducto> darTiposProductos()
	{
		ArrayList<TipoProducto> tiposProductos = new ArrayList<>();
		DAOTablaTipoProducto dao = new DAOTablaTipoProducto();
		try(Connection conn = darConexion())
		{
			tiposProductos = dao.darTiposProductos(conn);
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
		return tiposProductos;
	}

	public void actualizarProductoDeTipoProducto(TipoProducto tipoProducto)
	{
		DAOTablaTipoProducto dao = new DAOTablaTipoProducto();
		try(Connection conn = darConexion())
		{
			dao.actualizarProductoTipoProducto(conn, tipoProducto);
			conn.commit();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}

	public void actualizarTipoDeTipoProducto(TipoProducto tipoProducto)
	{
		DAOTablaTipoProducto dao = new DAOTablaTipoProducto();
		try(Connection conn = darConexion())
		{
			dao.actualizarTipoTipoProducto(conn, tipoProducto);
			conn.commit();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}

	public void eliminarTipoProducto(TipoProducto tipoProducto)
	{
		DAOTablaTipoProducto dao = new DAOTablaTipoProducto();
		try(Connection conn = darConexion())
		{
			dao.eliminarTipoProducto(conn, tipoProducto);
			conn.commit();
		} 
		catch (SQLException e) 
		{

			e.printStackTrace();
		}
	}

	// ----------------------------------  Fin metodos TipoProducto	----------------------------------

	// ----------------------------------Inicio metodos Zona	----------------------------------

	public void crearZona(Zona zona)
	{
		DAOTablaZona dao = new DAOTablaZona();
		try(Connection conn = darConexion())
		{
			dao.agregarZona(conn, zona);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public Zona darZonaPorId(Long id)
	{
		Zona zona = null;
		DAOTablaZona dao = new DAOTablaZona();
		try(Connection conn = darConexion())
		{
			zona = dao.darZonaPorId(conn, id);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return zona;
	}

	public ArrayList<Zona> darZonasPorNombre(String nombre)
	{
		ArrayList<Zona> zonas = new ArrayList<>();
		DAOTablaZona dao = new DAOTablaZona();
		try(Connection conn = darConexion())
		{
			zonas = dao.darZonasPorNombre(conn, nombre);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return zonas;
	}

	public ArrayList<Zona> darZonas()
	{
		ArrayList<Zona> zonas = new ArrayList<>();
		DAOTablaZona dao = new DAOTablaZona();
		try(Connection conn = darConexion())
		{
			zonas = dao.darZonas(conn);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return zonas;
	}

	public void actualizarZona(Zona zona)
	{
		DAOTablaZona dao = new DAOTablaZona();
		try(Connection conn = darConexion())
		{
			dao.actualizarZona(conn, zona);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public void eliminarZona(Zona zona)
	{
		DAOTablaZona dao = new DAOTablaZona();
		try(Connection conn = darConexion())
		{
			dao.eliminarZona(conn, zona);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	// ----------------------------------  Fin metodos Zona	----------------------------------


	// ----------------------------------	Inicio metodos Rotonda	----------------------------------

	public void crearRotonda(Rotonda rotonda)
	{
		DAOTablaRotonda dao = new DAOTablaRotonda();
		try(Connection conn = darConexion())
		{
			dao.agregarRotonda(conn, rotonda);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public Rotonda darRotondaPorId(Long id)
	{
		Rotonda rotonda = null;
		DAOTablaRotonda dao = new DAOTablaRotonda();
		try(Connection conn = darConexion())
		{
			rotonda = dao.darRotondaPorId(conn, id);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return rotonda;
	}

	public ArrayList<Rotonda> darRotondasPorNombre(String nombre)
	{
		ArrayList<Rotonda> rotondas = new ArrayList<>();
		DAOTablaRotonda dao = new DAOTablaRotonda();
		try(Connection conn = darConexion())
		{
			rotondas = dao.darRotondasPorNombre(conn, nombre);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return rotondas;
	}

	public ArrayList<Rotonda> darRotondas()
	{
		ArrayList<Rotonda> rotondas = new ArrayList<>();
		DAOTablaRotonda dao = new DAOTablaRotonda();
		try(Connection conn = darConexion())
		{
			rotondas = dao.darRotondas(conn);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return rotondas;
	}

	public void actualizarRotonda(Rotonda rotonda)
	{
		DAOTablaRotonda dao = new DAOTablaRotonda();
		try(Connection conn = darConexion())
		{
			dao.actualizarRotonda(conn, rotonda);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public void eliminarRotonda(Rotonda rotonda)
	{
		DAOTablaRotonda dao = new DAOTablaRotonda();
		try(Connection conn = darConexion())
		{
			dao.eliminarRotonda(conn, rotonda);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	// ----------------------------------	Fin metodos Producto	----------------------------------


	// ----------------------------------	Inicio metodos Producto	----------------------------------

	public void crearProducto(Producto producto)
	{
		DAOTablaProducto dao = new DAOTablaProducto();
		try(Connection conn = darConexion())
		{
			dao.agregarProducto(conn, producto);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public Producto darProductoPorId(Long id)
	{
		Producto producto = null;
		DAOTablaProducto dao = new DAOTablaProducto();
		try(Connection conn = darConexion())
		{
			producto = dao.darProductoPorId(conn, id);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return producto;
	}

	public ArrayList<Producto> darProductosPorNombre(String nombre)
	{
		ArrayList<Producto> productos = new ArrayList<>();
		DAOTablaProducto dao = new DAOTablaProducto();
		try(Connection conn = darConexion())
		{
			productos = dao.darProductosPorNombre(conn, nombre);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return productos;
	}
	
	public ArrayList<Producto> darProductosCategoria(Long id) {

		ArrayList<Producto> productos = new ArrayList<>();
		DAOTablaProducto dao = new DAOTablaProducto();
		try(Connection conn = darConexion())
		{
			productos = dao.darProductosPorCategoria(conn, id);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return productos;
	}
	public ArrayList<Producto> darProductosRangoPrecio(double min, double max) {
		ArrayList<Producto> productos = new ArrayList<>();
		DAOTablaProducto dao = new DAOTablaProducto();
		try(Connection conn = darConexion())
		{
			productos = dao.darProductosPorRangoPrecio(conn, min,max);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return productos;
	}



	public ArrayList<Producto> darProductosRestaurante(String name) 
	{
		ArrayList<Producto> productos = new ArrayList<>();
		DAOTablaProducto dao = new DAOTablaProducto();
		try(Connection conn = darConexion())
		{
			productos = dao.darProductosPorNombreRestaurante(conn, name);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return productos;
	}
	public ArrayList<Producto> darProductos()
	{
		ArrayList<Producto> productos = new ArrayList<>();
		DAOTablaProducto dao = new DAOTablaProducto();
		try(Connection conn = darConexion())
		{
			productos = dao.darProductos(conn);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return productos;
	}

	public void actualizarProducto(Producto producto)
	{
		DAOTablaProducto dao = new DAOTablaProducto();
		try(Connection conn = darConexion())
		{
			dao.actualizarProducto(conn, producto);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public void eliminarProducto(Producto producto)
	{
		DAOTablaProducto dao = new DAOTablaProducto();
		try(Connection conn = darConexion())
		{
			dao.eliminarProducto(conn, producto);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	// ----------------------------------	 Fin metodos Producto	----------------------------------
	// ----------------------------------	Inicio metodos AdministradorRotonda	----------------------------------

	public void crearAdministradorRotonda(AdministradorRotonda cliente)
	{
		DAOTablaAdministradorRotonda dao = new DAOTablaAdministradorRotonda();
		try(Connection conn = darConexion())
		{
			dao.agregarAdministradorRotonda(conn, cliente);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public AdministradorRotonda darAdministradorRotondaPorCedula(Long cedula)
	{
		AdministradorRotonda cliente = null;
		DAOTablaAdministradorRotonda dao = new DAOTablaAdministradorRotonda();
		try(Connection conn = darConexion())
		{
			cliente = dao.darAdministradorRotondaPorCedula(conn, cedula);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return cliente;
	}

	public AdministradorRotonda darAdministradorRotondaPorCorreo(String correo)
	{
		AdministradorRotonda cliente = null;
		DAOTablaAdministradorRotonda dao = new DAOTablaAdministradorRotonda();
		try(Connection conn = darConexion())
		{
			cliente = dao.darAdministradorRotondaPorCorreo(conn, correo);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return cliente;
	}

	public ArrayList<AdministradorRotonda> darAdministradorRotondaPorNombre(String nombre)
	{
		ArrayList<AdministradorRotonda> cliente = null;
		DAOTablaAdministradorRotonda dao = new DAOTablaAdministradorRotonda();
		try(Connection conn = darConexion())
		{
			cliente = dao.darAdministradorRotondasPorNombre(conn, nombre);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return cliente;
	}

	public ArrayList<AdministradorRotonda> darAdministradorRotondas()
	{
		ArrayList<AdministradorRotonda> cliente = null;
		DAOTablaAdministradorRotonda dao = new DAOTablaAdministradorRotonda();
		try(Connection conn = darConexion())
		{
			cliente = dao.darAdministradorRotondas(conn);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return cliente;
	}

	public void actualizarAdministradorRotonda(AdministradorRotonda cliente)
	{
		DAOTablaAdministradorRotonda dao = new DAOTablaAdministradorRotonda();
		try(Connection conn = darConexion())
		{
			dao.actualizarAdministradorRotonda(conn, cliente);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public void eliminarAdministradorRotonda(AdministradorRotonda cliente)
	{
		DAOTablaAdministradorRotonda dao = new DAOTablaAdministradorRotonda();
		try(Connection conn = darConexion())
		{
			dao.eliminarAdministradorRotonda(conn, cliente);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	// ----------------------------------	 Fin metodos AdministradorRotonda	----------------------------------
	// ----------------------------------	Inicio metodos AdministradorRestaurante	----------------------------------

	public void crearAdministradorRestaurante(AdministradorRestaurante cliente)
	{
		DAOTablaAdministradorRestaurante dao = new DAOTablaAdministradorRestaurante();
		try(Connection conn = darConexion())
		{
			dao.agregarAdministradorRestaurante(conn, cliente);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public AdministradorRestaurante darAdministradorRestaurantePorCedula(Long cedula)
	{
		AdministradorRestaurante cliente = null;
		DAOTablaAdministradorRestaurante dao = new DAOTablaAdministradorRestaurante();
		try(Connection conn = darConexion())
		{
			cliente = dao.darAdministradorRestaurantePorCedula(conn, cedula);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return cliente;
	}

	public AdministradorRestaurante darAdministradorRestaurantePorCorreo(String correo)
	{
		AdministradorRestaurante cliente = null;
		DAOTablaAdministradorRestaurante dao = new DAOTablaAdministradorRestaurante();
		try(Connection conn = darConexion())
		{
			cliente = dao.darAdministradorRestaurantePorCorreo(conn, correo);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return cliente;
	}

	public ArrayList<AdministradorRestaurante> darAdministradorRestaurantePorNombre(String nombre)
	{
		ArrayList<AdministradorRestaurante> cliente = null;
		DAOTablaAdministradorRestaurante dao = new DAOTablaAdministradorRestaurante();
		try(Connection conn = darConexion())
		{
			cliente = dao.darAdministradorRestaurantesPorNombre(conn, nombre);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return cliente;
	}

	public ArrayList<AdministradorRestaurante> darAdministradorRestaurantes()
	{
		ArrayList<AdministradorRestaurante> cliente = null;
		DAOTablaAdministradorRestaurante dao = new DAOTablaAdministradorRestaurante();
		try(Connection conn = darConexion())
		{
			cliente = dao.darAdministradorRestaurantes(conn);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return cliente;
	}

	public void actualizarAdministradorRestaurante(AdministradorRestaurante cliente)
	{
		DAOTablaAdministradorRestaurante dao = new DAOTablaAdministradorRestaurante();
		try(Connection conn = darConexion())
		{
			dao.actualizarAdministradorRestaurante(conn, cliente);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public void eliminarAdministradorRestaurante(AdministradorRestaurante cliente)
	{
		DAOTablaAdministradorRestaurante dao = new DAOTablaAdministradorRestaurante();
		try(Connection conn = darConexion())
		{
			dao.eliminarAdministradorRestaurante(conn, cliente);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	// ----------------------------------	 Fin metodos AdministradorRestaurante	----------------------------------
	// ----------------------------------Inicio metodos ContabilidadRestaurante	----------------------------------

	public void crearContabilidadRestaurante(ContabilidadRestaurante re)
	{
		DAOTablaContabilidadRestaurante dao = new DAOTablaContabilidadRestaurante();
		try(Connection conn = darConexion())
		{
			dao.agregarContabilidadRestaurante(conn, re);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}
	public ContabilidadRestaurante darContabilidadRestaurantePorId(Long id)
	{
		ContabilidadRestaurante contabilidadRestaurante = null;
		DAOTablaContabilidadRestaurante dao = new DAOTablaContabilidadRestaurante();
		try(Connection conn = darConexion())
		{
			contabilidadRestaurante = dao.darContabilidadRestaurantePorId(conn, id);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return contabilidadRestaurante;
	}
	public ArrayList<ContabilidadRestaurante> darContabilidadRestaurantePorFecha(Date fecha)
	{
		ArrayList<ContabilidadRestaurante> reserva = null;
		DAOTablaContabilidadRestaurante dao = new DAOTablaContabilidadRestaurante();
		try(Connection conn = darConexion())
		{
			reserva = dao.darContabilidadesPorFecha(conn, fecha);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return reserva;
	}

	public ArrayList<ContabilidadRestaurante> darContabilidadesRestaurante()
	{
		ArrayList<ContabilidadRestaurante> reserva = null;
		DAOTablaContabilidadRestaurante dao = new DAOTablaContabilidadRestaurante();
		try(Connection conn = darConexion())
		{
			reserva = dao.darContabilidades(conn);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return reserva;
	}

	public void actualizarContabilidadRestaurante(ContabilidadRestaurante re)
	{
		DAOTablaContabilidadRestaurante dao = new DAOTablaContabilidadRestaurante();
		try(Connection conn = darConexion())
		{
			dao.actualizarContabilidadRestaurante(conn, re);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public void eliminarContabilidadRestaurante(ContabilidadRestaurante re)
	{
		DAOTablaContabilidadRestaurante dao = new DAOTablaContabilidadRestaurante();
		try(Connection conn = darConexion())
		{
			dao.eliminarContabilidadRestaurante(conn, re);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}


	// ----------------------------------  Fin métodos ContabilidadRestaurante	----------------------------------

	// ----------------------------------Inicio metodos ContabilidadGeneral	----------------------------------

	public void crearContabilidadGeneral(ContabilidadGeneral re)
	{
		DAOTablaContabilidadGeneral dao = new DAOTablaContabilidadGeneral();
		try(Connection conn = darConexion())
		{
			dao.agregarContabilidadGeneral(conn, re);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public ArrayList<ContabilidadGeneral> darContabilidadGeneralPorFecha(Date fecha)
	{
		ArrayList<ContabilidadGeneral> reserva = null;
		DAOTablaContabilidadGeneral dao = new DAOTablaContabilidadGeneral();
		try(Connection conn = darConexion())
		{
			reserva = dao.darContabilidadesPorFecha(conn, fecha);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return reserva;
	}

	public ArrayList<ContabilidadGeneral> darContabilidadesGeneral()
	{
		ArrayList<ContabilidadGeneral> reserva = null;
		DAOTablaContabilidadGeneral dao = new DAOTablaContabilidadGeneral();
		try(Connection conn = darConexion())
		{
			reserva = dao.darContabilidades(conn);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return reserva;
	}

	public void actualizarContabilidadGeneral(ContabilidadGeneral re)
	{
		DAOTablaContabilidadGeneral dao = new DAOTablaContabilidadGeneral();
		try(Connection conn = darConexion())
		{
			dao.actualizarContabilidadGeneral(conn, re);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public void eliminarContabilidadGeneral(ContabilidadGeneral re)
	{
		DAOTablaContabilidadGeneral dao = new DAOTablaContabilidadGeneral();
		try(Connection conn = darConexion())
		{
			dao.eliminarContabilidadGeneral(conn, re);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}


	// ----------------------------------  Fin métodos ContabilidadGeneral	----------------------------------
	// ----------------------------------  Inicio métodos Consultas	----------------------------------


	// ----------------------------------  Fin métodos Consultas	----------------------------------

	// ----------------------------------  Inicio metodos PreferenciaCliente	----------------------------------



	public void crearPreferenciaCliente(PreferenciaCliente categoria)
	{
		DAOTablaPreferenciaCliente dao = new DAOTablaPreferenciaCliente();
		try(Connection conn = darConexion())
		{
			dao.agregarPreferenciaCliente(conn, categoria);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public PreferenciaCliente darPreferenciaClientePorId(Long id)
	{
		PreferenciaCliente categoria = null;
		DAOTablaPreferenciaCliente dao = new DAOTablaPreferenciaCliente();
		try(Connection conn = darConexion())
		{
			categoria = dao.darPreferenciaClientePorId(conn, id);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return categoria;		
	}

	public ArrayList<PreferenciaCliente> darPreferenciaClientePorNombre(String nombre)
	{
		ArrayList<PreferenciaCliente> categorias = new ArrayList<>();
		DAOTablaPreferenciaCliente dao = new DAOTablaPreferenciaCliente();
		try(Connection conn = darConexion())
		{
			categorias = dao.darPreferenciasPorNombre(conn, nombre);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return categorias;		
	}

	public ArrayList<PreferenciaCliente> darPreferenciaClientes()
	{
		ArrayList<PreferenciaCliente> categorias = new ArrayList<>();
		DAOTablaPreferenciaCliente dao = new DAOTablaPreferenciaCliente();
		try(Connection conn = darConexion())
		{
			categorias = dao.darPreferenciaCliente(conn);
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
		return categorias;
	}

	public void actualizarPreferenciaCliente(PreferenciaCliente categoria)
	{
		DAOTablaPreferenciaCliente dao = new DAOTablaPreferenciaCliente();
		try(Connection conn = darConexion())
		{
			dao.actualizarPreferencia(conn, categoria);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	public void eliminarPreferenciaCliente(PreferenciaCliente categoria)
	{
		DAOTablaPreferenciaCliente dao = new DAOTablaPreferenciaCliente();
		try(Connection conn = darConexion())
		{
			dao.eliminarPreferencia(conn, categoria);
			conn.commit();
		}
		catch(SQLException e)
		{

			e.printStackTrace();
		}
	}

	
	
	


	// ----------------------------------   Fin metodos PreferenciaCliente	----------------------------------
}
