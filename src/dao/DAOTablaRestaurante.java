package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Restaurante;

public class DAOTablaRestaurante  extends DAO
{
	public DAOTablaRestaurante()
	{
		
	}
	
	public void agregarRestaurante(Connection conn, Restaurante restaurante, String log)
	{
		String sql = "INSERT INTO RESTAURANTE VALUES (?,?,?,?,?,?)";
		String mensajeLog =sql+"&"+restaurante.toParametros();
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{preStat.setMaxRows(100);
		
			preStat.setString(1, restaurante.getNombre());
			preStat.setString(2, restaurante.getTipoComida());
			preStat.setString(3, restaurante.getPagWeb());
			preStat.setString(4, restaurante.getEncargado());
			preStat.setLong(5, restaurante.getIdZona());
			preStat.setLong(6, restaurante.getIdRotonda());
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
	
	public Restaurante darRestaurantePorNombre(Connection conn, String nombre, String log)
	{
		Restaurante restaurante = null;
		String sql = "SELECT * FROM RESTAURANTE WHERE NOMBRE = ?";
		String mensajeLog =sql+"&"+String.class.getName()+":"+nombre;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, nombre);preStat.setMaxRows(100);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				
				String nombre1 = rs.getString("NOMBRE");
				String tipoComida = rs.getString("TIPO_COMIDA");
				String pagWeb = rs.getString("PAGINA_WEB");
				String encargado = rs.getString("ENCARGADO");
				Long idZona = rs.getLong("ID_ZONA");
				Long idRotonda = rs.getLong("ID_ROTONDA");
				restaurante = new Restaurante(nombre1, tipoComida, pagWeb, encargado, idZona, idRotonda);
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
		return restaurante;
	}
	
	public ArrayList<Restaurante> darRestaurantesPorZona(Connection conn, Long nombre, String log)
	{
		ArrayList<Restaurante> restaurantes = new ArrayList<>();
		String sql = "SELECT * FROM RESTAURANTE WHERE ID_ZONA = ?";
		String mensajeLog =sql+"&"+Long.class.getName()+":"+nombre;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, nombre);preStat.setMaxRows(100);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				String nombre1 = rs.getString("NOMBRE");
				String tipoComida = rs.getString("TIPO_COMIDA");
				String pagWeb = rs.getString("PAGINA_WEB");
				String encargado = rs.getString("ENCARGADO");
				Long idZona = rs.getLong("ID_ZONA");
				Long idRotonda = rs.getLong("ID_ROTONDA");
				restaurantes.add(new Restaurante(nombre1, tipoComida, pagWeb, encargado, idZona, idRotonda));
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
		return restaurantes;
	}
	
	public ArrayList<Restaurante> darRestaurantes(Connection conn, String log)
	{
		ArrayList<Restaurante> restaurantes = new ArrayList<>();
		String sql = "SELECT * FROM RESTAURANTE";
		String mensajeLog =sql;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			ResultSet rs = preStat.executeQuery();preStat.setMaxRows(100);
			
			while(rs.next())
			{
				
				String nombre = rs.getString("NOMBRE");
				String tipoComida = rs.getString("TIPO_COMIDA");
				String pagWeb = rs.getString("PAGINA_WEB");
				String encargado = rs.getString("ENCARGADO");
				Long idZona = rs.getLong("ID_ZONA");
				Long idRotonda = rs.getLong("ID_ROTONDA");
				restaurantes.add(new Restaurante(nombre, tipoComida, pagWeb, encargado, idZona, idRotonda));
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
		return restaurantes;
	}
	
	public void actualizarRestaurante(Connection conn, Restaurante restaurante, String log)
	{
		String sql = "UPDATE RESTAURANTE SET PAGINA_WEB= ?, ID_ZONA = ?, ID_ROTONDA = ? WHERE NOMBRE = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, restaurante.getPagWeb());
			preStat.setLong(2, restaurante.getIdZona());
			preStat.setLong(3, restaurante.getIdRotonda());
			preStat.setString(4, restaurante.getNombre());
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
	
	public void eliminarRestaurante(Connection conn, Restaurante restaurante, String log)
	{
		String sql = "DELETE FROM RESTAURANTE WHERE Nombre = ?";
		String mensajeLog =sql+"&"+String.class.getName()+":"+restaurante.getNombre();
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, restaurante.getNombre());
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
