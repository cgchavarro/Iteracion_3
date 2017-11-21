package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.AdministradorRotonda;
import vo.ClienteRFC;
import vo.ProductoConsumo;


public class DAOTablaAdministradorRotonda extends DAO
{
	public DAOTablaAdministradorRotonda()
	{
		
	}
	
	public void agregarAdministradorRotonda(Connection conn, AdministradorRotonda cliente, String log)
	{
		String sql = "INSERT INTO ADMINISTRADORROTONDA VALUES (?,?,?,?)";
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
			escribirLog("COMMIT", log);
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
	
	public AdministradorRotonda darAdministradorRotondaPorCedula(Connection conn, Long cedula, String log)
	{
		AdministradorRotonda cliente = null;
		String sql = "SELECT * FROM ADMINISTRADORROTONDA WHERE CEDULA = ?";
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
				cliente = new AdministradorRotonda(cedula1, nombre, correo, idRotonda);
			}		
			conn.commit();
			escribirLog("COMMIT", log);
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
	
	
	public AdministradorRotonda darAdministradorRotondaPorCorreo(Connection conn, String correo, String log)
	{

AdministradorRotonda cliente = null;
		String sql = "SELECT * FROM ADMINISTRADORROTONDA WHERE CORREO = ?";
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
				cliente = new AdministradorRotonda(cedula1, nombre, correo1, idRotonda);
			}
			conn.commit();
			escribirLog("COMMIT", log);
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
	
	
	public ArrayList<AdministradorRotonda> darAdministradorRotondasPorNombre(Connection conn, String nombre, String log)
	{
		ArrayList<AdministradorRotonda> clientes = new ArrayList<>();
		String sql = "SELECT * FROM ADMINISTRADORROTONDA WHERE NOMBRE = ?";
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
				clientes.add(new AdministradorRotonda(cedula, nombre1, correo, idRotonda));
			}
			conn.commit();
			escribirLog("COMMIT", log);
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
	
	public ArrayList<AdministradorRotonda> darAdministradorRotondas(Connection conn, String log)
	{
		ArrayList<AdministradorRotonda> clientes = new ArrayList<>();
		String sql = "SELECT * FROM ADMINISTRADORROTONDA";
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
				clientes.add(new AdministradorRotonda(cedula, nombre1, correo, idRotonda));
			}
			conn.commit();
			escribirLog("COMMIT", log);
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
	
	public void actualizarAdministradorRotonda(Connection conn, AdministradorRotonda cliente, String log)
	{
		String sql = "UPDATE ADMINISTRADORROTONDA SET NOMBRE = ?, CORREO = ?, ID_ROTONDA = ? WHERE CEDULA = ?";
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
			escribirLog("COMMIT", log);
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
	
	public void eliminarAdministradorRotonda(Connection conn, AdministradorRotonda cliente, String log)
	{
		String sql = "DELETE FROM ADMINISTRADORROTONDA WHERE CEDULA = ?";
		String mensajeLog =sql+"&"+Long.class.getName()+":"+cliente.getCedula();
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, cliente.getCedula());
			preStat.executeQuery();
			conn.commit();
			escribirLog("COMMIT", log);
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

	public ArrayList consultarFuncionalidad(Connection conn, String fechaMin,String fechaMax, String log)
	{
		ArrayList clientes = new ArrayList<>();
		
		String sql = "select id,nombre, nombre_restaurante,fecha, conteo from (select PRODUCTO.ID, producto.nombre,producto.nombre_restaurante, orden_restaurante.fecha, count(*) as conteo from producto left join (menu left join ORDEN_RESTAURANTE on menu.id = orden_Restaurante.id_menu) on (menu.postre=producto.ID or menu.platofuerte = producto.id or menu.acompaniamiento = producto.id or menu.bebida=producto.id or menu.entrada=producto.id) where orden_restaurante.fecha >= ? and orden_restaurante.fecha <= ? group by PRODUCTO.ID, producto.nombre, producto.nombre_restaurante, orden_restaurante.fecha order by orden_restaurante.fecha) where conteo>=3 or (conteo<2 and conteo >0) order by fecha desc";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, fechaMin);
			preStat.setString(2, fechaMax);
			preStat.setMaxRows(100);
		    ResultSet rs = preStat.executeQuery();
		
			Date fechaA = new Date(1, 1,1999);
					int contAct =0;
			while(rs.next())
			{
				Date fecha = rs.getDate("FECHA");
				
				int cont = rs.getInt("CONTEO");
				Long idProdu = rs.getLong("ID");
				String nombre1 = rs.getString("NOMBRE");
				String nombre2 = rs.getString("NOMBRE_RESTAURANTE");
		
				if(fechaA.getDay()!=fecha.getDay())
				{
					fechaA=fecha;
					contAct=cont;
					clientes.add(new ProductoConsumo(idProdu,nombre1,nombre2,fecha,cont));
				}
				else if(cont==1&& contAct>1)
				{	contAct=cont;
				clientes.add(new ProductoConsumo(idProdu,nombre1,nombre2,fecha,cont));
				}
				else if(cont==1&& contAct==1)
				{
					
				}
	
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
	
	
}