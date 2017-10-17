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
	
	public void agregarOrdenRestaurante(Connection conn, OrdenRestaurante ordenRestaurante)
	{
		String sql = "INSERT INTO ORDEN_RESTAURANTE VALUES (?,?,?,?,?,?)";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, ordenRestaurante.getIdOrdenRestaurante());
			preStat.setDate(2, ordenRestaurante.getFecha());
			preStat.setLong(3, ordenRestaurante.getIdMenu());
			preStat.setLong(4, ordenRestaurante.getIdRotonda());
			preStat.setLong(5, ordenRestaurante.getIdCliente());
			preStat.setString(6, darStringBoolean(ordenRestaurante.isServida()));
			
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
	
	public OrdenRestaurante darOrdenRestaurantePorId(Connection conn, Long id)
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
				ordenRestaurante = new OrdenRestaurante(idOrdenRestaurante, fecha, idMenu, idRotonda, idCliente,servida);
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
	
	public ArrayList<OrdenRestaurante> darOrdenRestaurantesPorIdCliente(Connection conn, Long idMenu)
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
				ordenRestaurantes.add(new OrdenRestaurante(id, fecha, idMenu1, idRotonda, idCliente,servida));
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
	
	public ArrayList<OrdenRestaurante> darOrdenRestaurantesPorMenu(Connection conn, Long idMenu)
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
				ordenRestaurantes.add(new OrdenRestaurante(id, fecha, idMenu1, idRotonda, idCliente,servida));
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
	
	public ArrayList<OrdenRestaurante> darOrdenRestaurantes(Connection conn)
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
				ordenRestaurantes.add(new OrdenRestaurante(id, fecha, idMenu, idRotonda, idCliente,servida));
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
	
	public void actualizarOrdenRestaurante(Connection conn, OrdenRestaurante ordenRestaurante)
	{
		String sql = "UPDATE ORDEN_RESTAURANTE SET FECHA = ?, ID_MENU = ?, ID_ROTONDA = ?, ID_CLIENTE = ?, SERVIDA = ?  WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setDate(1, ordenRestaurante.getFecha());
			preStat.setLong(2, ordenRestaurante.getIdMenu());
			preStat.setLong(3, ordenRestaurante.getIdRotonda());
			preStat.setLong(4, ordenRestaurante.getIdCliente());
			preStat.setString(5, darStringBoolean(ordenRestaurante.isServida()));
			preStat.setLong(6, ordenRestaurante.getIdOrdenRestaurante());
			preStat.executeQuery();
			conn.commit();
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
	
	public void eliminarOrdenRestaurante(Connection conn, OrdenRestaurante ordenRestaurante)
	{
		String sql = "DELETE FROM ORDEN_RESTAURANTE WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, ordenRestaurante.getIdOrdenRestaurante());
			preStat.executeQuery();
			conn.commit();
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
}
