package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.AdministradorRestaurante;


public class DAOTablaAdministradorRestaurante extends DAO
{
	public DAOTablaAdministradorRestaurante()
	{
		
	}
	
	public void agregarAdministradorRestaurante(Connection conn, AdministradorRestaurante cliente, String log)
	{
		String sql = "INSERT INTO ADMINISTRADORRESTAURANTE VALUES (?,?,?,?)";
		String mensajeLog =sql+"&"+cliente.toParametros();
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, cliente.getCedula());
			preStat.setString(2, cliente.getNombre());
			preStat.setString(3, cliente.getCorreo());
			preStat.setString(4, cliente.getNombreRestaurante());
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
	
	public AdministradorRestaurante darAdministradorRestaurantePorCedula(Connection conn, Long cedula, String log)
	{
		AdministradorRestaurante cliente = null;
		String sql = "SELECT * FROM ADMINISTRADORRESTAURANTE WHERE CEDULA = ?";
		String mensajeLog =sql+"&"+Long.class.getName()+":"+cedula;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, cedula);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long cedula1 = rs.getLong("CEDULA");
				String nombre = rs.getString("NOMBRE");
				String correo = rs.getString("CORREO");
				String idRotonda = rs.getString("NOMBRE_RESTAURANTE");
				cliente = new AdministradorRestaurante(cedula1, nombre, correo, idRotonda);
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
		return cliente;
	}
	
	
	public AdministradorRestaurante darAdministradorRestaurantePorCorreo(Connection conn, String correo, String log)
	{
		AdministradorRestaurante cliente = null;
		String sql = "SELECT * FROM ADMINISTRADORRESTAURANTE WHERE CORREO = ?";
		String mensajeLog =sql+"&"+String.class.getName()+":"+correo;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, correo);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long cedula1 = rs.getLong("CEDULA");
				String nombre = rs.getString("NOMBRE");
				String correo1 = rs.getString("CORREO");
				String idRotonda = rs.getString("NOMBRE_RESTAURANTE");
				cliente = new AdministradorRestaurante(cedula1, nombre, correo1, idRotonda);
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
		return cliente;
	}
	
	
	public ArrayList<AdministradorRestaurante> darAdministradorRestaurantesPorNombre(Connection conn, String nombre, String log)
	{
		ArrayList<AdministradorRestaurante> clientes = new ArrayList<>();
		String sql = "SELECT * FROM ADMINISTRADORRESTAURANTE WHERE NOMBRE = ?";
		String mensajeLog =sql+"&"+String.class.getName()+":"+nombre;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, nombre);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long cedula = rs.getLong("CEDULA");
				String nombre1 = rs.getString("NOMBRE");
				String correo = rs.getString("CORREO");
				String idRotonda = rs.getString("NOMBRE_RESTAURANTE");
				clientes.add(new AdministradorRestaurante(cedula, nombre1, correo, idRotonda));
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
		return clientes;
	}
	
	public ArrayList<AdministradorRestaurante> darAdministradorRestaurantes(Connection conn, String log)
	{
		ArrayList<AdministradorRestaurante> clientes = new ArrayList<>();
		String sql = "SELECT * FROM ADMINISTRADORRESTAURANTE";
		String mensajeLog =sql;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long cedula = rs.getLong("CEDULA");
				String nombre1 = rs.getString("NOMBRE");
				String correo = rs.getString("CORREO");
				String idRotonda = rs.getString("NOMBRE_RESTAURANTE");
				clientes.add(new AdministradorRestaurante(cedula, nombre1, correo, idRotonda));
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
		return clientes;
	}
	
	public void actualizarAdministradorRestaurante(Connection conn, AdministradorRestaurante cliente, String log)
	{
		String sql = "UPDATE ADMINISTRADORRESTAURANTE SET NOMBRE = ?, CORREO = ?, NOMBRE_RESTAURANTE = ? WHERE CEDULA = ?";
		String mensajeLog =sql+"&"+cliente.toParametros();
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1,  cliente.getNombre());
			preStat.setString(2, cliente.getCorreo());
			preStat.setString(3, cliente.getNombreRestaurante());
			preStat.setLong(4, cliente.getCedula());
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
	
	public void eliminarAdministradorRestaurante(Connection conn, AdministradorRestaurante cliente, String log)
	{
		String sql = "DELETE FROM ADMINISTRADORRESTAURANTE WHERE CEDULA = ?";
		String mensajeLog =sql+"&"+Long.class.getName()+":"+cliente.getCedula();
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, cliente.getCedula());
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