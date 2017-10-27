package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.OrdenRestaurante;

public class DAOTablaOrdenRestaurante 
{
	public DAOTablaOrdenRestaurante()
	{

	}

	public void agregarOrdenRestaurante(Connection conn, OrdenRestaurante ordenRestaurante, String log)
	{

		String sql = "INSERT INTO ORDEN_RESTAURANTE VALUES (?,?,?,?,?,?,?)";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, ordenRestaurante.getIdOrdenRestaurante());
			preStat.setDate(2, ordenRestaurante.getFecha());
			preStat.setLong(3, ordenRestaurante.getIdMenu());
			preStat.setLong(4, ordenRestaurante.getIdRotonda());
			preStat.setLong(5, ordenRestaurante.getIdCliente());
			preStat.setString(6, darStringBoolean(ordenRestaurante.isServida()));
			preStat.setString(7, ordenRestaurante.getMesa());

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

	public void agregarOrdenRestauranteMesa(Connection conn, OrdenRestaurante[] ordenRestaurante, String log)
	{
		for(OrdenRestaurante orden:ordenRestaurante) {
			String sql = "INSERT INTO ORDEN_RESTAURANTE VALUES (?,?,?,?,?,?,?)";
			try(PreparedStatement preStat = conn.prepareStatement(sql))
			{
				preStat.setLong(1, orden.getIdOrdenRestaurante());
				preStat.setDate(2, orden.getFecha());
				preStat.setLong(3, orden.getIdMenu());
				preStat.setLong(4, orden.getIdRotonda());
				preStat.setLong(5, orden.getIdCliente());
				preStat.setString(6, darStringBoolean(orden.isServida()));
				preStat.setString(7, orden.getMesa());

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
	}


	public OrdenRestaurante darOrdenRestaurantePorId(Connection conn, Long id, String log)
	{
		OrdenRestaurante ordenRestaurante = null;
		String sql = "SELECT * FROM ORDEN_RESTAURANTE WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, id);
			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				Long idOrdenRestaurante = rs.getLong("ID");
				Date fecha = rs.getDate("FECHA");
				Long idMenu = rs.getLong("ID_MENU");
				Long idRotonda = rs.getLong("ID_ROTONDA");
				Long idCliente = rs.getLong("ID_CLIENTE");
				boolean servida = darBooleanString(rs.getString("SERVIDA"));
				String mesa = rs.getString("MESA");
				ordenRestaurante = new OrdenRestaurante(idOrdenRestaurante, fecha, idMenu, idRotonda, idCliente,servida,mesa);
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
		return ordenRestaurante;
	}

	public ArrayList<OrdenRestaurante> darOrdenRestaurantesPorIdCliente(Connection conn, Long idMenu, String log)
	{
		ArrayList<OrdenRestaurante> ordenRestaurantes = new ArrayList<>();
		String sql = "SELECT * FROM ORDEN_RESTAURANTE WHERE ID_CLIENTE = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, idMenu);
			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				Long id = rs.getLong("ID");
				Date fecha = rs.getDate("FECHA");
				Long idMenu1 = rs.getLong("ID_MENU");
				Long idRotonda = rs.getLong("ID_ROTONDA");
				Long idCliente = rs.getLong("ID_CLIENTE");
				boolean servida = darBooleanString(rs.getString("SERVIDA"));
				String mesa = rs.getString("MESA");

				ordenRestaurantes.add(new OrdenRestaurante(id, fecha, idMenu1, idRotonda, idCliente,servida,mesa));
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
		return ordenRestaurantes;
	}



	public ArrayList<OrdenRestaurante> darOrdenRestaurantesPorMenu(Connection conn, Long idMenu, String log)
	{
		ArrayList<OrdenRestaurante> ordenRestaurantes = new ArrayList<>();
		String sql = "SELECT * FROM ORDEN_RESTAURANTE WHERE ID_MENU = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, idMenu);
			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				Long id = rs.getLong("ID");
				Date fecha = rs.getDate("FECHA");
				Long idMenu1 = rs.getLong("ID_MENU");
				Long idRotonda = rs.getLong("ID_ROTONDA");
				Long idCliente = rs.getLong("ID_CLIENTE");
				boolean servida = darBooleanString(rs.getString("SERVIDA"));
				String mesa = rs.getString("MESA");

				ordenRestaurantes.add(new OrdenRestaurante(id, fecha, idMenu1, idRotonda, idCliente,servida,mesa));
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
		return ordenRestaurantes;
	}


	public ArrayList<OrdenRestaurante> darOrdenRestaurantesPorMesa(Connection conn, String idMenu, String log)
	{
		ArrayList<OrdenRestaurante> ordenRestaurantes = new ArrayList<>();
		String sql = "SELECT * FROM ORDEN_RESTAURANTE WHERE MESA = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, idMenu);
			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				Long id = rs.getLong("ID");
				Date fecha = rs.getDate("FECHA");
				Long idMenu1 = rs.getLong("ID_MENU");
				Long idRotonda = rs.getLong("ID_ROTONDA");
				Long idCliente = rs.getLong("ID_CLIENTE");
				boolean servida = darBooleanString(rs.getString("SERVIDA"));
				String mesa = rs.getString("MESA");

				ordenRestaurantes.add(new OrdenRestaurante(id, fecha, idMenu1, idRotonda, idCliente,servida,mesa));
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
		return ordenRestaurantes;
	}


	public ArrayList<OrdenRestaurante> darOrdenRestaurantesServidas(Connection conn, String log)
	{
		ArrayList<OrdenRestaurante> ordenRestaurantes = new ArrayList<>();
		String sql = "SELECT * FROM ORDEN_RESTAURANTE WHERE SERVIDA = t";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{

			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				Long id = rs.getLong("ID");
				Date fecha = rs.getDate("FECHA");
				Long idMenu1 = rs.getLong("ID_MENU");
				Long idRotonda = rs.getLong("ID_ROTONDA");
				Long idCliente = rs.getLong("ID_CLIENTE");
				boolean servida = darBooleanString(rs.getString("SERVIDA"));
				String mesa = rs.getString("MESA");

				ordenRestaurantes.add(new OrdenRestaurante(id, fecha, idMenu1, idRotonda, idCliente,servida,mesa));
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
		return ordenRestaurantes;
	}

	public ArrayList<OrdenRestaurante> darOrdenRestaurantes(Connection conn, String log)
	{
		ArrayList<OrdenRestaurante> ordenRestaurantes = new ArrayList<>();
		String sql = "SELECT * FROM ORDEN_RESTAURANTE";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				Long id = rs.getLong("ID");
				Date fecha = rs.getDate("FECHA");
				Long idMenu = rs.getLong("ID_MENU");
				Long idRotonda = rs.getLong("ID_ROTONDA");
				Long idCliente = rs.getLong("ID_CLIENTE");
				boolean servida = darBooleanString(rs.getString("SERVIDA"));
				String mesa = rs.getString("MESA");

				ordenRestaurantes.add(new OrdenRestaurante(id, fecha, idMenu, idRotonda, idCliente,servida,mesa));
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
		return ordenRestaurantes;
	}

	public void actualizarOrdenRestaurante(Connection conn, OrdenRestaurante ordenRestaurante, String log)
	{
		String sql = "UPDATE ORDEN_RESTAURANTE SET FECHA = ?, ID_MENU = ?, ID_ROTONDA = ?, ID_CLIENTE = ?, SERVIDA = ?, MESA = ?  WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setDate(1, ordenRestaurante.getFecha());
			preStat.setLong(2, ordenRestaurante.getIdMenu());
			preStat.setLong(3, ordenRestaurante.getIdRotonda());
			preStat.setLong(4, ordenRestaurante.getIdCliente());
			preStat.setString(5, darStringBoolean(ordenRestaurante.isServida()));
			preStat.setString(6, ordenRestaurante.getMesa());
			preStat.setLong(7, ordenRestaurante.getIdOrdenRestaurante());
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

	public void eliminarOrdenRestaurante(Connection conn, OrdenRestaurante ordenRestaurante, String log)
	{
		String sql = "DELETE FROM ORDEN_RESTAURANTE WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, ordenRestaurante.getIdOrdenRestaurante());
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
	private String darStringBoolean(boolean bool)
	{
		if(bool)
		{
			return "t";
		}
		return "f";
	}

	private boolean darBooleanString(String string)
	{
		if(string.equals("f"))
		{
			return false;
		}
		return true;
	}

	public ArrayList<OrdenRestaurante> actualizarOrdenesRestauranteComoServidas(Connection conn, ArrayList<OrdenRestaurante> ordenes, String log) {
		System.out.println("algo");
		ArrayList<OrdenRestaurante> ordenRestaurantes = new ArrayList<>();
		String sql = "UPDATE ORDEN_RESTAURANTE SET SERVIDA = ? WHERE MESA = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			System.out.println("algo0");
			preStat.setString(1, "t");
			System.out.println("algo1");
			System.out.println("tamano" + ordenes.size());
			preStat.setString(2, ordenes.get(0).getMesa());
			System.out.println("algo2 " + ordenes.get(0).getMesa());
			System.out.println(preStat.toString());
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
		catch(Throwable e)
		{
			e.printStackTrace();
			throw e;
		}
		return ordenRestaurantes;
	}

	public void eliminarOrdenRestauranteMesa(Connection conn, String idMesa, String logs) {
		String sql = "DELETE FROM ORDEN_RESTAURANTE WHERE MESA = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, idMesa);
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
}
