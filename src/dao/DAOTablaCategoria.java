package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Categoria;

public class DAOTablaCategoria {

	public DAOTablaCategoria()
	{

	}

	public void agregarCategoria(Connection conn, Categoria categoria)
	{
		String sql = "INSERT INTO CATEGORIA VALUES (?,?)";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, categoria.getId());
			preStat.setString(2, categoria.getNombre());
			preStat.executeQuery();
			conn.commit();
		}
		catch(SQLException e)
		{
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
	}

	public Categoria darCategoriaPorId(Connection conn, Long id)
	{
		Categoria categoria = null;
		String sql = "SELECT * FROM CATEGORIA WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, id);
			ResultSet rs = preStat.executeQuery();
			while(rs.next())
			{
				Long id1 = rs.getLong("ID");
				String nombre = rs.getString("NOMBRE");
				categoria = new Categoria(id1, nombre);
			}
			conn.commit();
		}
		catch(SQLException e)
		{
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
		return categoria;
	}

	public ArrayList<Categoria> darCategoriasPorNombre(Connection conn, String nombre)
	{
		ArrayList<Categoria> categorias = new ArrayList<>();
		String sql = "SELECT * FROM CATEGORIA WHERE NOMBRE = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, nombre);
			ResultSet rs = preStat.executeQuery();
			while(rs.next())
			{
				Long id = rs.getLong("ID");
				String nombre1 = rs.getString("NOMBRE");
				categorias.add(new Categoria(id, nombre1));
			}
			conn.commit();
		}
		catch(SQLException e)
		{
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
		return categorias;
	}

	public ArrayList<Categoria> darCategorias(Connection conn) throws SQLException
	{
		ArrayList<Categoria> categorias = new ArrayList<>();
		String sql = "SELECT * FROM ISIS2304A331720.CATEGORIA";
//		try(PreparedStatement preStat = conn.prepareStatement(sql))
//		{
//			
//		}
		PreparedStatement preStat = conn.prepareStatement(sql);
			ResultSet rs = preStat.executeQuery();
			while(rs.next())
			{
				Long id = rs.getLong("ID");
				String nombre = rs.getString("NOMBRE");
				categorias.add(new Categoria(id, nombre));
			}
//			conn.commit();
//		}
//		catch(SQLException e)
//		{
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//			e.printStackTrace();
//		}
		return categorias;
	}

	public void actualizarCategoria(Connection conn, Categoria categoria)
	{
		String sql = "UPDATE CATEGORIA SET NOMBRE = ? WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, categoria.getNombre());
			preStat.setLong(2, categoria.getId());
			preStat.executeQuery();

			conn.commit();
		}
		catch(SQLException e)
		{
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			e.printStackTrace();
		}
	}

	public void eliminarCategoria(Connection conn, Categoria categoria)
	{
		String sql = "DELETE FROM CATEGORIA WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, categoria.getId());
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
