package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Ingrediente;

public class DAOTablaIngrediente 
{
	public DAOTablaIngrediente()
	{
		
	}
	
	public void agregarIngrediente(Connection conn, Ingrediente ingrediente)
	{
		String sql = "INSERT INTO INGREDIENTE VALUES (?,?,?,?)";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, ingrediente.getIdIngrediente());
			preStat.setString(2, ingrediente.getNombre());
			preStat.setString(3, ingrediente.getDescripcionEsp());
			preStat.setString(4, ingrediente.getDescripcionIng());
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
	
	public Ingrediente darIngredientePorId(Connection conn, Long id)
	{
		Ingrediente ingrediente = null;
		String sql = "SELECT * FROM INGREDIENTE WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, id);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long idIngrediente = rs.getLong("ID");
				String nombre = rs.getString("NOMBRE");
				String descripcionEsp = rs.getString("DESCRIPCION_ESP");
				String descripcionIng = rs.getString("DESCRIPCION_ING");
				ingrediente = new Ingrediente(idIngrediente, nombre, descripcionEsp, descripcionIng);
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
		return ingrediente;
	}
	
	
	public ArrayList<Ingrediente> darIngredientesPorNombre(Connection conn, String nombre)
	{
		ArrayList<Ingrediente> ingredientes = new ArrayList<>();
		String sql = "SELECT * FROM INGREDIENTE WHERE NOMBRE = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, nombre);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long id = rs.getLong("ID");
				String nombreIngrediente = rs.getString("NOMBRE");
				String descripcionEsp = rs.getString("DESCRIPCION_ESP");
				String descripcionIng = rs.getString("DESCRIPCION_ING");
				ingredientes.add(new Ingrediente(id, nombreIngrediente, descripcionEsp, descripcionIng));
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
		return ingredientes;
	}
	
	public ArrayList<Ingrediente> darIngredientes(Connection conn)
	{
		ArrayList<Ingrediente> ingredientes = new ArrayList<>();
		String sql = "SELECT * FROM INGREDIENTE";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long id = rs.getLong("ID");
				String nombreIngrediente = rs.getString("NOMBRE");
				String descripcionEsp = rs.getString("DESCRIPCION_ESP");
				String descripcionIng = rs.getString("DESCRIPCION_ING");
				ingredientes.add(new Ingrediente(id, nombreIngrediente, descripcionEsp, descripcionIng));
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
		return ingredientes;
	}
	
	public void actualizarIngrediente(Connection conn, Ingrediente ingrediente)
	{
		String sql = "UPDATE INGREDIENTE SET NOMBRE = ?, DESCRIPCION_ESP = ?, DESCRIPCION_ING = ? WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, ingrediente.getNombre());
			preStat.setString(2, ingrediente.getDescripcionEsp());
			preStat.setString(3, ingrediente.getDescripcionIng());
			preStat.setLong(4, ingrediente.getIdIngrediente());
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
	
	public void eliminarIngrediente(Connection conn, Ingrediente ingrediente)
	{
		String sql = "DELETE FROM INGREDIENTE WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, ingrediente.getIdIngrediente());
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
