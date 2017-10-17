package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Tipo;

public class DAOTablaTipo 
{
	public DAOTablaTipo()
	{
		
	}
	
	public void agregarTipo(Connection conn, Tipo tipo)
	{
		String sql = "INSERT INTO TIPO VALUES (?,?)";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, tipo.getIdTipo());
			preStat.setString(2, tipo.getNombre());
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
	
	public Tipo darTipoPorId(Connection conn, Long id)
	{
		Tipo tipo = null;
		String sql = "SELECT * FROM TIPO WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, id);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long idTipo = rs.getLong("ID");
				String nombre = rs.getString("NOMBRE");
				tipo = new Tipo(idTipo, nombre);
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
		return tipo;
	}
	
	
	public ArrayList<Tipo> darTiposPorNombre(Connection conn, String nombre)
	{
		ArrayList<Tipo> tipos = new ArrayList<>();
		String sql = "SELECT * FROM TIPO WHERE NOMBRE = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, nombre);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long id = rs.getLong("ID");
				String nombreTipo = rs.getString("NOMBRE");
				tipos.add(new Tipo(id, nombreTipo));
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
		return tipos;
	}
	
	public ArrayList<Tipo> darTipos(Connection conn)
	{
		ArrayList<Tipo> tipos = new ArrayList<>();
		String sql = "SELECT * FROM TIPO";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long id = rs.getLong("ID");
				String nombreTipo = rs.getString("NOMBRE");
				tipos.add(new Tipo(id, nombreTipo));
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
		return tipos;
	}
	
	public void actualizarTipo(Connection conn, Tipo tipo)
	{
		String sql = "UPDATE TIPO SET NOMBRE = ? WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, tipo.getNombre());
			preStat.setLong(2, tipo.getIdTipo());
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
	
	public void eliminarTipo(Connection conn, Tipo tipo)
	{
		String sql = "DELETE FROM TIPO WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, tipo.getIdTipo());
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
