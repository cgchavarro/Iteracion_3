package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.AdministradorRotonda;


public class DAOTablaAdministradorRotonda
{
	public DAOTablaAdministradorRotonda()
	{
		
	}
	
	public void agregarAdministradorRotonda(Connection conn, AdministradorRotonda cliente)
	{
		String sql = "INSERT INTO ADMINISTRADORROTONDA VALUES (?,?,?,?)";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, cliente.getCedula());
			preStat.setString(2, cliente.getNombre());
			preStat.setString(3, cliente.getCorreo());
			preStat.setLong(4, cliente.getIdRotonda());
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
	
	public AdministradorRotonda darAdministradorRotondaPorCedula(Connection conn, Long cedula)
	{
		AdministradorRotonda cliente = null;
		String sql = "SELECT * FROM ADMINISTRADORROTONDA WHERE CEDULA = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, cedula);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long cedula1 = rs.getLong("CEDULA");
				String nombre = rs.getString("NOMBRE");
				String correo = rs.getString("CORREO");
				Long idRotonda = rs.getLong("ID_ROTONDA");
				cliente = new AdministradorRotonda(cedula1, nombre, correo, idRotonda);
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
	
	
	public AdministradorRotonda darAdministradorRotondaPorCorreo(Connection conn, String correo)
	{

AdministradorRotonda cliente = null;
		String sql = "SELECT * FROM ADMINISTRADORROTONDA WHERE CORREO = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, correo);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long cedula1 = rs.getLong("CEDULA");
				String nombre = rs.getString("NOMBRE");
				String correo1 = rs.getString("CORREO");
				Long idRotonda = rs.getLong("ID_ROTONDA");
				cliente = new AdministradorRotonda(cedula1, nombre, correo1, idRotonda);
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
	
	
	public ArrayList<AdministradorRotonda> darAdministradorRotondasPorNombre(Connection conn, String nombre)
	{
		ArrayList<AdministradorRotonda> clientes = new ArrayList<>();
		String sql = "SELECT * FROM ADMINISTRADORROTONDA WHERE NOMBRE = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, nombre);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long cedula = rs.getLong("CEDULA");
				String nombre1 = rs.getString("NOMBRE");
				String correo = rs.getString("CORREO");
				Long idRotonda = rs.getLong("ID_ROTONDA");
				clientes.add(new AdministradorRotonda(cedula, nombre1, correo, idRotonda));
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
	
	public ArrayList<AdministradorRotonda> darAdministradorRotondas(Connection conn)
	{
		ArrayList<AdministradorRotonda> clientes = new ArrayList<>();
		String sql = "SELECT * FROM ADMINISTRADORROTONDA";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long cedula = rs.getLong("CEDULA");
				String nombre1 = rs.getString("NOMBRE");
				String correo = rs.getString("CORREO");
				Long idRotonda = rs.getLong("ID_ROTONDA");
				clientes.add(new AdministradorRotonda(cedula, nombre1, correo, idRotonda));
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
	
	public void actualizarAdministradorRotonda(Connection conn, AdministradorRotonda cliente)
	{
		String sql = "UPDATE ADMINISTRADORROTONDA SET NOMBRE = ?, CORREO = ?, ID_ROTONDA = ? WHERE CEDULA = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1,  cliente.getNombre());
			preStat.setString(2, cliente.getCorreo());
			preStat.setLong(3, cliente.getIdRotonda());
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
	
	public void eliminarAdministradorRotonda(Connection conn, AdministradorRotonda cliente)
	{
		String sql = "DELETE FROM ADMINISTRADORROTONDA WHERE CEDULA = ?";
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