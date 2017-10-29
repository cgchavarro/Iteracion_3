package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Producto;
import vo.ProductoVenta;

public class DAOTablaProducto  extends DAO{

	public DAOTablaProducto()
	{

	}



	public void agregarProducto(Connection conn, Producto producto, String log)
	{
		String sql = "INSERT INTO PRODUCTO VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		String mensajeLog =sql+"&"+producto.toParametros();
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, producto.getIdProducto());
			preStat.setString(2, producto.getNombre());
			preStat.setLong(3, producto.getCategoria());
			preStat.setString(4, producto.getDescripcionEsp());
			preStat.setString(5, producto.getDescripcionIng());
			preStat.setInt(6, producto.getTiempo());
			preStat.setDouble(7, producto.getCosto());
			preStat.setDouble(8, producto.getPrecio());
			preStat.setInt(9, producto.getCantidad());
			preStat.setString(10, producto.getNombreRestaurante());
			preStat.setInt(11, producto.getCantidadMaxima());
			preStat.executeQuery();
			conn.commit();
		}
		catch(SQLException e)
		{
			try 
			{
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();
		}				
	}

	public Producto darProductoPorId(Connection conn, Long id, String log)
	{
		Producto producto = null;
		String sql = "SELECT * FROM PRODUCTO WHERE ID = ?";
		String mensajeLog =sql+"&"+Long.class.getName()+":"+id;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, id);
			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				Long idProducto = rs.getLong("ID");
				String nombre = rs.getString("NOMBRE");
				Long idCategoria = rs.getLong("ID_CATEGORIA");
				String descripcionEsp = rs.getString("DESCRIPCION_ESP");
				String descripcionIng = rs.getString("DESCRIPCION_ING");
				int tiempo = rs.getInt("TIEMPO");
				double costo = rs.getDouble("COSTO");
				double precio = rs.getDouble("PRECIO");
				int cantidad = rs.getInt("CANTIDAD");
				String nombreR = rs.getString("NOMBRE_RESTAURANTE");
				int cantidadM = rs.getInt("CANTIDAD_MAXIMA");
				producto = new Producto(idProducto, nombre, idCategoria, descripcionEsp, descripcionIng, tiempo, costo, precio,cantidad,nombreR,cantidadM);
			}
			conn.commit();
		}
		catch(SQLException e)
		{
			try 
			{
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
		return producto;
	}

	public ArrayList<Producto> darProductosPorNombre(Connection conn, String nombre, String log)
	{
		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT * FROM PRODUCTO WHERE NOMBRE = ?";
		String mensajeLog =sql+"&"+String.class.getName()+":"+nombre;
		escribirLog(mensajeLog, log);

		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, nombre);
			ResultSet rs = preStat.executeQuery();

			while (rs.next()) {
				String nombres = rs.getString("NOMBRE");
				Long id = rs.getLong("ID");
				Long idCategoria = rs.getLong("ID_CATEGORIA");
				String descripcionEsp = rs.getString("DESCRIPCION_ESP");
				String descripcionIng = rs.getString("DESCRIPCION_ING");
				int tiempo = rs.getInt("TIEMPO");
				double costo = rs.getDouble("COSTO");
				double precio = rs.getDouble("PRECIO");
				int cantidad = rs.getInt("CANTIDAD");
				String nombreR = rs.getString("NOMBRE_RESTAURANTE");
				int cantidadM = rs.getInt("CANTIDAD_MAXIMA");
				productos.add( new Producto(id, nombres, idCategoria, descripcionEsp, descripcionIng, tiempo, costo, precio,cantidad, nombreR, cantidadM));
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productos;
	}
	

	public ArrayList<Producto> darProductosPorNombreRestaurante(Connection conn, String name, String log) {
		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT * FROM PRODUCTO WHERE NOMBRE_RESTAURANTE = ?";
		String mensajeLog =sql+"&"+String.class.getName()+":"+name;
		escribirLog(mensajeLog, log);

		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, name);
			ResultSet rs = preStat.executeQuery();

			while (rs.next()) {
				String nombres = rs.getString("NOMBRE");
				Long id = rs.getLong("ID");
				Long idCategoria = rs.getLong("ID_CATEGORIA");
				String descripcionEsp = rs.getString("DESCRIPCION_ESP");
				String descripcionIng = rs.getString("DESCRIPCION_ING");
				int tiempo = rs.getInt("TIEMPO");
				double costo = rs.getDouble("COSTO");
				double precio = rs.getDouble("PRECIO");
				int cantidad = rs.getInt("CANTIDAD");
				String nombreR = rs.getString("NOMBRE_RESTAURANTE");
				int cantidadM = rs.getInt("CANTIDAD_MAXIMA");
				productos.add( new Producto(id, nombres, idCategoria, descripcionEsp, descripcionIng, tiempo, costo, precio,cantidad, nombreR, cantidadM));
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productos;
	}
	

	public ArrayList<Producto> darProductosPorCategoria(Connection conn, Long id, String log) {
		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT * FROM PRODUCTO WHERE ID_CATEGORIA = ?";
		String mensajeLog =sql+"&"+Long.class.getName()+":"+id;
		escribirLog(mensajeLog, log);

		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, id);
			ResultSet rs = preStat.executeQuery();

			while (rs.next()) {
				String nombres = rs.getString("NOMBRE");
				Long id1 = rs.getLong("ID");
				Long idCategoria = rs.getLong("ID_CATEGORIA");
				String descripcionEsp = rs.getString("DESCRIPCION_ESP");
				String descripcionIng = rs.getString("DESCRIPCION_ING");
				int tiempo = rs.getInt("TIEMPO");
				double costo = rs.getDouble("COSTO");
				double precio = rs.getDouble("PRECIO");
				int cantidad = rs.getInt("CANTIDAD");
				String nombreR = rs.getString("NOMBRE_RESTAURANTE");
				int cantidadM = rs.getInt("CANTIDAD_MAXIMA");
				productos.add( new Producto(id1, nombres, idCategoria, descripcionEsp, descripcionIng, tiempo, costo, precio,cantidad, nombreR, cantidadM));
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productos;
	}
	public ArrayList<Producto> darProductosPorRangoPrecio(Connection conn, double min, double max, String log) {
		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT * FROM PRODUCTO WHERE PRECIO > ? AND PRECIO < ?";
		String mensajeLog =sql+"&"+Double.class.getName()+":"+min+","+Double.class.getName()+":"+max;
		escribirLog(mensajeLog, log);

		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setDouble(1, min);
			preStat.setDouble(2, max);
			ResultSet rs = preStat.executeQuery();

			while (rs.next()) {
				String nombres = rs.getString("NOMBRE");
				Long id1 = rs.getLong("ID");
				Long idCategoria = rs.getLong("ID_CATEGORIA");
				String descripcionEsp = rs.getString("DESCRIPCION_ESP");
				String descripcionIng = rs.getString("DESCRIPCION_ING");
				int tiempo = rs.getInt("TIEMPO");
				double costo = rs.getDouble("COSTO");
				double precio = rs.getDouble("PRECIO");
				int cantidad = rs.getInt("CANTIDAD");
				String nombreR = rs.getString("NOMBRE_RESTAURANTE");
				int cantidadM = rs.getInt("CANTIDAD_MAXIMA");
				productos.add( new Producto(id1, nombres, idCategoria, descripcionEsp, descripcionIng, tiempo, costo, precio,cantidad, nombreR, cantidadM));
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productos;
	}

	public ArrayList<Producto> darProductos(Connection conn, String log) throws SQLException, Exception {
		ArrayList<Producto> productos = new ArrayList<Producto>();

		String sql = "SELECT * FROM PRODUCTO";
		String mensajeLog =sql;
		escribirLog(mensajeLog, log);

		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			ResultSet rs = preStat.executeQuery();

			while (rs.next()) {
				Long id = rs.getLong("ID");
				String nombre = rs.getString("NOMBRE");
				Long idCategoria = rs.getLong("ID_CATEGORIA");
				String descripcionEsp = rs.getString("DESCRIPCION_ESP");
				String descripcionIng = rs.getString("DESCRIPCION_ING");
				int tiempo = rs.getInt("TIEMPO");
				double costo = rs.getDouble("COSTO");
				double precio = rs.getDouble("PRECIO");
				int cantidad = rs.getInt("CANTIDAD");
				String nombreR = rs.getString("NOMBRE_RESTAURANTE");
				int cantidadM = rs.getInt("CANTIDAD_MAXIMA");
				productos.add( new Producto(id, nombre, idCategoria, descripcionEsp, descripcionIng, tiempo, costo, precio,cantidad, nombreR, cantidadM));
			}
			conn.commit();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productos;
	}	

	public void actualizarProducto(Connection conn, Producto producto, String log)
	{
		String sql = "UPDATE PRODUCTO SET NOMBRE = ?, ID_CATEGORIA = ?, DESCRIPCION_ESP = ?, DESCRIPCION_ING = ?, TIEMPO = ?, COSTO = ?, PRECIO = ?, CANTIDAD = ?, NOMBRE_RESTAURANTE =?, CANTIDAD_MAXIMA =? WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, producto.getNombre());
			preStat.setLong(2, producto.getCategoria());
			preStat.setString(3, producto.getDescripcionEsp());
			preStat.setString(4, producto.getDescripcionIng());
			preStat.setInt(5, producto.getTiempo());
			preStat.setDouble(6, producto.getCosto());
			preStat.setDouble(7, producto.getPrecio());		
			preStat.setInt(8, producto.getCantidad());
			preStat.setString(9, producto.getNombreRestaurante());
			preStat.setInt(10, producto.getCantidadMaxima());
			preStat.setLong(11, producto.getIdProducto());				
			preStat.executeQuery();
			conn.commit();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void eliminarProducto(Connection conn, Producto producto, String log)
	{
		String sql = "DELETE FROM PRODUCTO WHERE ID = ?";
		String mensajeLog =sql+"&"+Long.class.getName()+":"+producto.getIdProducto();
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, producto.getIdProducto());
			preStat.executeQuery();
			conn.commit();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public void surtirProductosRestaurante(Connection conn, String nombreRestaurante, String log)
	{
		String sql = "UPDATE PRODUCTO SET CANTIDAD = CANTIDAD_MAXIMA WHERE NOMBRE_RESTAURANTE =?";
		String mensajeLog =sql+"&"+String.class.getName()+":"+nombreRestaurante;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
		
		preStat.setString(1, nombreRestaurante);
						
			preStat.executeQuery();
			conn.commit();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}


	
	public ArrayList<ProductoVenta> darProductosVentaPorRestaurante(Connection conn, String name, String log) {
		ArrayList<ProductoVenta> productos = new ArrayList<ProductoVenta>();

		String sql = "SELECT ID,CANTIDADVENTAS*PRECIO-CANTIDADVENTAS*COSTO AS VENTASTOTALES, NOMBRE_RESTAURANTE FROM PRODUCTO LEFT JOIN( SELECT PRODUCTO.ID AS IDENTIFICADOR, COUNT(*) AS CANTIDADVENTAS FROM PRODUCTO LEFT JOIN ( SELECT MENU.POSTRE AS POSTRE FROM ORDEN_RESTAURANTE LEFT JOIN MENU ON ORDEN_RESTAURANTE.ID_MENU = MENU.ID) ON POSTRE= PRODUCTO.ID GROUP BY PRODUCTO.ID ORDER BY ID) ON PRODUCTO.ID = IDENTIFICADOR WHERE NOMBRE_RESTAURANTE =? ORDER BY NOMBRE_RESTAURANTE";
		String mensajeLog =sql+"&"+String.class.getName()+":"+name;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, name);
			ResultSet rs = preStat.executeQuery();

			while (rs.next()) {
			
				Long id = rs.getLong("ID");
				
				int tiempo = rs.getInt("VENTASTOTALES");
			
				String nombreR = rs.getString("NOMBRE_RESTAURANTE");
			
				productos.add( new ProductoVenta(id,nombreR ,tiempo));
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productos;
	}


	public ArrayList<ProductoVenta> darProductosVenta(Connection conn, String log) {
		ArrayList<ProductoVenta> productos = new ArrayList<ProductoVenta>();

		String sql = "SELECT ID,CANTIDADVENTAS*PRECIO-CANTIDADVENTAS*COSTO AS VENTASTOTALES, NOMBRE_RESTAURANTE FROM PRODUCTO LEFT JOIN( SELECT PRODUCTO.ID AS IDENTIFICADOR, COUNT(*) AS CANTIDADVENTAS FROM PRODUCTO LEFT JOIN ( SELECT MENU.POSTRE AS POSTRE FROM ORDEN_RESTAURANTE LEFT JOIN MENU ON ORDEN_RESTAURANTE.ID_MENU = MENU.ID) ON POSTRE= PRODUCTO.ID GROUP BY PRODUCTO.ID ORDER BY ID) ON PRODUCTO.ID = IDENTIFICADOR  ORDER BY NOMBRE_RESTAURANTE";
		String mensajeLog =sql;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			
			ResultSet rs = preStat.executeQuery();

			while (rs.next()) {
			
				Long id = rs.getLong("ID");
				
				int tiempo = rs.getInt("VENTASTOTALES");
			
				String nombreR = rs.getString("NOMBRE_RESTAURANTE");
			
				productos.add( new ProductoVenta(id,nombreR ,tiempo));
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return productos;
	}







}
