package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Cliente;
import vo.ClienteTipo;


public class DAOTablaCliente  extends DAO
{
	public DAOTablaCliente()
	{
		
	}
	
	public void agregarCliente(Connection conn, Cliente cliente, String log)
	{
		String sql = "INSERT INTO CLIENTE VALUES (?,?,?,?)";
		String mensajeLog =sql+"&"+cliente.toParametros();
		escribirLog(mensajeLog, log);
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
	
	public Cliente darClientePorCedula(Connection conn, Long cedula, String log)
	{
		Cliente cliente = null;
		String sql = "SELECT * FROM CLIENTE WHERE CEDULA = ?";
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
				Long idRotonda = rs.getLong("ID_ROTONDA");
				cliente = new Cliente(cedula1, nombre, correo, idRotonda);
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
	
	
	public Cliente darClientePorCorreo(Connection conn, String correo, String log)
	{
		Cliente cliente = null;
		String sql = "SELECT * FROM CLIENTE WHERE CORREO = ?";
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
				Long idRotonda = rs.getLong("ID_ROTONDA");
				cliente = new Cliente(cedula1, nombre, correo1, idRotonda);
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
	
	
	public ArrayList<Cliente> darClientesPorNombre(Connection conn, String nombre, String log)
	{
		ArrayList<Cliente> clientes = new ArrayList<>();
		String sql = "SELECT * FROM CLIENTE WHERE NOMBRE = ?";
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
				Long idRotonda = rs.getLong("ID_ROTONDA");
				clientes.add(new Cliente(cedula, nombre1, correo, idRotonda));
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
	
	public ArrayList<Cliente> darClientes(Connection conn, String log)
	{
		ArrayList<Cliente> clientes = new ArrayList<>();
		String sql = "SELECT * FROM CLIENTE";
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
				Long idRotonda = rs.getLong("ID_ROTONDA");
				clientes.add(new Cliente(cedula, nombre1, correo, idRotonda));
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
	
	public void actualizarCliente(Connection conn, Cliente cliente, String log)
	{
		String sql = "UPDATE CLIENTE SET NOMBRE = ?, CORREO = ?, ID_ROTONDA = ? WHERE CEDULA = ?";
		String mensajeLog =sql+"&"+cliente.toParametros();
		escribirLog(mensajeLog, log);
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
	
	public void eliminarCliente(Connection conn, Cliente cliente, String log)
	{
		String sql = "DELETE FROM CLIENTE WHERE CEDULA = ?";
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
	
	public ArrayList<ClienteTipo> darClientesTipo(Connection conn, String fechaMin,String fechaMax, String log) {
		ArrayList<ClienteTipo> clientes = new ArrayList<ClienteTipo>();

		String sql = "SELECT CLIENTE.CEDULA,CLIENTE.NOMBRE,\r\n" + 
				"CAST ((COUNT(ORDEN_RESTAURANTE.FECHA)/52) AS INTEGER )  AS NUMEROORDENES, MAX(PRODUCTO.PRECIO) AS PRECIOMINIMO \r\n" + 
				"FROM CLIENTE LEFT JOIN  \r\n" + 
				"(ORDEN_RESTAURANTE \r\n" + 
				"        LEFT JOIN  (MENU \r\n" + 
				"                        RIGHT JOIN PRODUCTO  \r\n" + 
				"                        ON MENU.PLATOFUERTE = PRODUCTO.ID)  \r\n" + 
				"        ON ORDEN_RESTAURANTE.ID_MENU = MENU.ID)  \r\n" + 
				"ON  CLIENTE.CEDULA = ORDEN_RESTAURANTE.ID_CLIENTE  \r\n" + 
				"where orden_restaurante.fecha BETWEEN  ? AND ? \r\n" + 
				"GROUP BY CLIENTE.CEDULA, CLIENTE.NOMBRE \r\n" + 
				"ORDER BY NUMEROORDENES DESC";
		String mensajeLog =sql;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, fechaMin);
			preStat.setString(2, fechaMax);
			preStat.setMaxRows(100);
			ResultSet rs = preStat.executeQuery();

			
			while (rs.next()) {
			
				Long id = rs.getLong("CEDULA");
				
		
				String nombre = rs.getString("NOMBRE");
			
				int ventidos = rs.getInt("NUMEROORDENES");
				int ventidos2 = rs.getInt("PRECIOMINIMO");
				String tipo ;
				if(ventidos>1)
					tipo = new String("Cliente consume una vez por semana");
				else if(ventidos2>24000)
					tipo = new String("Cliente consume productos caros");
				
				else
					tipo = new String("Cliente no consume");
				clientes.add( new ClienteTipo(id,nombre, tipo));
				
			}
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return clientes;
	}

}