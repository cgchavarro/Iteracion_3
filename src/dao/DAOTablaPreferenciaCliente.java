package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import vo.PreferenciaCliente;

public class DAOTablaPreferenciaCliente {

	public DAOTablaPreferenciaCliente()
	{

	}

	public void agregarPreferenciaCliente(Connection conn, PreferenciaCliente categoria)
	{
		String sql = "INSERT INTO PREFERENCIACLIENTE VALUES (?,?)";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, categoria.getIdCliente());
			preStat.setString(2, categoria.getPreferencia());
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

	public PreferenciaCliente darPreferenciaClientePorId(Connection conn, Long id)
	{
		PreferenciaCliente categoria = null;
		String sql = "SELECT * FROM PREFERENCIACLIENTE WHERE ID_CLIENTE = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, id);
			ResultSet rs = preStat.executeQuery();
			while(rs.next())
			{
				Long id1 = rs.getLong("ID_CLIENTE");
				String nombre = rs.getString("PREFERENCIA");
				categoria = new PreferenciaCliente(id1, nombre);
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

	public ArrayList<PreferenciaCliente> darPreferenciasPorNombre(Connection conn, String nombre)
	{
		ArrayList<PreferenciaCliente> categorias = new ArrayList<>();
		String sql = "SELECT * FROM PREFERENCIACLIENTE WHERE PREFERENCIA = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, nombre);
			ResultSet rs = preStat.executeQuery();
			while(rs.next())
			{
				Long id = rs.getLong("ID_CLIENTE");
				String nombre1 = rs.getString("PREFERENCIA");
				categorias.add(new PreferenciaCliente(id, nombre1));
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

	public ArrayList<PreferenciaCliente> darPreferenciaCliente(Connection conn)
	{
		ArrayList<PreferenciaCliente> categorias = new ArrayList<>();
		String sql = "SELECT * FROM PREFERENCIACLIENTE";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			ResultSet rs = preStat.executeQuery();
			while(rs.next())
			{
				Long id = rs.getLong("ID_CLIENTE");
				String nombre1 = rs.getString("PREFERENCIA");
				categorias.add(new PreferenciaCliente(id, nombre1));
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

	public void actualizarPreferencia(Connection conn, PreferenciaCliente categoria)
	{
		String sql = "UPDATE PREFERENCIACLIENTE SET PREFERENCIA = ? WHERE ID_CLIENTE = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, categoria.getPreferencia());
			preStat.setLong(2, categoria.getIdCliente());
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

	public void eliminarPreferencia(Connection conn,PreferenciaCliente categoria)
	{
		String sql = "DELETE FROM PREFERENCIACLIENTE WHERE ID_CLIENTE = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, categoria.getIdCliente());
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
