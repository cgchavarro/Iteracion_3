package dao;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import vo.Zona;

public class DAOTablaZona 
{

	private DAOInterpretacionLog daoPrueba;

	public DAOTablaZona()
	{
		daoPrueba = new DAOInterpretacionLog();
	}

	public void agregarZona(Connection conn, Zona zona, String log)
	{
		String sql = "INSERT INTO ZONA VALUES (?,?,?,?,?,?,?)";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, zona.getIdZona());
			preStat.setString(2, zona.getNombre());
			preStat.setString(3, darStringBoolean(zona.isEsZonaAbierta()));
			preStat.setInt(4, zona.getCapacidad());
			preStat.setString(5, darStringBoolean(zona.isAptoParaTodos()));
			preStat.setString(6, zona.getCondicionesTecnicas());
			preStat.setLong(7, zona.getIdRotonda());
			//preStat.executeQuery();
			String mensajeLog =sql+"&"+zona.toParametros();
			daoPrueba.ejecutarInstruccionLog(mensajeLog, conn);
			escribirLog(mensajeLog, log);
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

	public Zona darZonaPorId(Connection conn, Long id, String log)
	{
		Zona zona = null;
		String sql = "SELECT * FROM ZONA WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, id);
			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				Long idZona = rs.getLong("ID");
				String nombre = rs.getString("NOMBRE");
				boolean esZonaAbierta = darBooleanString(rs.getString("ES_ZONA_ABIERTA"));
				int capacidad = rs.getInt("CAPACIDAD");
				boolean aptoParaTodos = darBooleanString(rs.getString("APTO_PARA_TODOS"));
				String condicionesTecnicas = rs.getString("CONDICIONES_TECNICAS");
				Long idRotonda = rs.getLong("ID_ROTONDA");
				zona = new Zona(idZona, nombre, esZonaAbierta, capacidad, aptoParaTodos, condicionesTecnicas, idRotonda);
			}	

			conn.commit();
			//	escribirLog(preStat.toString());
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
		return zona;
	}


	public ArrayList<Zona> darZonasPorNombre(Connection conn, String nombre, String log)
	{
		ArrayList<Zona> zonas = new ArrayList<>();
		String sql = "SELECT * FROM ZONA WHERE NOMBRE = ?";
		String query = "SELECT * FROM ZONA WHERE NOMBRE = '"+nombre+"'";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, nombre);
			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				Long id = rs.getLong("ID");
				String nombreZona = rs.getString("NOMBRE");
				boolean esZonaAbierta = darBooleanString(rs.getString("ES_ZONA_ABIERTA"));
				int capacidad = rs.getInt("CAPACIDAD");
				boolean aptoParaTodos = darBooleanString(rs.getString("APTO_PARA_TODOS"));
				String condicionesTecnicas = rs.getString("CONDICIONES_TECNICAS");
				Long idRotonda = rs.getLong("ID_ROTONDA");
				zonas.add(new Zona(id, nombreZona, esZonaAbierta, capacidad, aptoParaTodos, condicionesTecnicas, idRotonda));
			}	

			conn.commit();
			escribirLog(query, log);
			//	escribirLog(preStat.toString());
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
		return zonas;
	}

	public ArrayList<Zona> darZonas(Connection conn, String log)
	{
		ArrayList<Zona> zonas = new ArrayList<>();
		String sql = "SELECT * FROM ZONA";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				Long id = rs.getLong("ID");
				String nombreZona = rs.getString("NOMBRE");
				boolean esZonaAbierta = darBooleanString(rs.getString("ES_ZONA_ABIERTA"));
				int capacidad = rs.getInt("CAPACIDAD");
				boolean aptoParaTodos = darBooleanString(rs.getString("APTO_PARA_TODOS"));
				String condicionesTecnicas = rs.getString("CONDICIONES_TECNICAS");
				Long idRotonda = rs.getLong("ID_ROTONDA");
				zonas.add(new Zona(id, nombreZona, esZonaAbierta, capacidad, aptoParaTodos, condicionesTecnicas, idRotonda));
			}	

			conn.commit();
			System.out.print(preStat.toString());
			escribirLog(sql, log);
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
		return zonas;
	}

	public void actualizarZona(Connection conn, Zona zona, String log)
	{
		String sql = "UPDATE ZONA SET NOMBRE = ?, ES_ZONA_ABIERTA = ?, CAPACIDAD = ?, APTO_PARA_TODOS = ?, CONDICIONES_TECNICAS = ?, ID_ROTONDA = ? WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, zona.getNombre());
			preStat.setString(2, darStringBoolean(zona.isEsZonaAbierta()));
			preStat.setInt(3, zona.getCapacidad());
			preStat.setString(4, darStringBoolean(zona.isAptoParaTodos()));
			preStat.setString(5, zona.getCondicionesTecnicas());			
			preStat.setLong(6, zona.getIdRotonda());
			preStat.setLong(7, zona.getIdZona());
			preStat.executeQuery();

			conn.commit();
			//	escribirLog(preStat.toString());
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

	public void eliminarZona(Connection conn, Zona zona, String log)
	{
		String sql = "DELETE FROM ZONA WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, zona.getIdZona());
			preStat.executeQuery();
			conn.commit();
			//	escribirLog(preStat.toString());
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

	private String darStringBoolean(boolean bool)
	{
		if(bool)
		{
			return "t";
		}
		return "f";
	}

	private boolean darBooleanString(String string)
	{
		if(string.equals("f"))
		{
			return false;
		}
		return true;
	}

	public void escribirLog(String pCausa, String ruta) 
	{
		Date fecha = new Date();
		PrintWriter log;
		try 

		{ 
			log = new PrintWriter(ruta);
			log.println ( fecha  +";" + pCausa);
			log.close();	
		} catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
