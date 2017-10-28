package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Rotonda;

public class DAOTablaRotonda  extends DAO
{
	public DAOTablaRotonda()
	{

	}

	public void agregarRotonda(Connection conn, Rotonda rotonda, String log)
	{
		String sql = "INSERT INTO ROTONDANDES VALUES (?,?)";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, rotonda.getId());
			preStat.setString(2, rotonda.getNombre());
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

	public Rotonda darRotondaPorId(Connection conn, Long id, String log)
	{
		Rotonda rotonda = null;
		String sql = "SELECT * FROM ROTONDANDES WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, id);
			ResultSet rs = preStat.executeQuery();
			while(rs.next())
			{
				Long id1 = rs.getLong("ID");
				String nombre = rs.getString("NOMBRE");
				rotonda = new Rotonda(id1, nombre);
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
		return rotonda;
	}

	public ArrayList<Rotonda> darRotondasPorNombre(Connection conn, String nombre, String log)
	{
		ArrayList<Rotonda> rotondas = new ArrayList<>();
		String sql = "SELECT * FROM ROTONDANDES WHERE NOMBRE = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, nombre);
			ResultSet rs = preStat.executeQuery();
			while(rs.next())
			{
				Long id = rs.getLong("ID");
				String nombre1 = rs.getString("NOMBRE");
				rotondas.add(new Rotonda(id, nombre1));
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
		return rotondas;
	}

	public ArrayList<Rotonda> darRotondas(Connection conn, String log)
	{
		ArrayList<Rotonda> rotondas = new ArrayList<>();
		String sql = "SELECT * FROM ROTONDANDES";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			ResultSet rs = preStat.executeQuery();
			while(rs.next())
			{
				Long id = rs.getLong("ID");
				String nombre = rs.getString("NOMBRE");
				rotondas.add(new Rotonda(id, nombre));
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
		return rotondas;
	}

	public void actualizarRotonda(Connection conn, Rotonda rotonda, String log)
	{
		String sql = "UPDATE ROTONDANDES SET NOMBRE = ? WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, rotonda.getNombre());
			preStat.setLong(2, rotonda.getId());
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
	
	public void eliminarRotonda(Connection conn, Rotonda rotonda, String log)
	{
		String sql = "DELETE FROM ROTONDANDES WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, rotonda.getId());
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
