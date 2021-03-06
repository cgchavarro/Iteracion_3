package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.ContabilidadGeneral;


public class DAOTablaContabilidadGeneral  extends DAO
{
	public DAOTablaContabilidadGeneral()
	{

	}

	public void agregarContabilidadGeneral(Connection conn, ContabilidadGeneral c, String log)
	{
		String sql = "INSERT INTO CONTABILIDADGENERAL VALUES (?,?,?,?,?)";
		String mensajeLog =sql+"&"+c.toParametros();
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{

			preStat.setString(1, c.getNombreRestaurante());
			preStat.setDate(2, c.getFechaContabilidad());
			preStat.setDouble(3, c.getValorCostos());
			preStat.setDouble(4, c.getValorVentas());
			preStat.setLong(5, c.getIdRotonda());
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


	public ArrayList<ContabilidadGeneral> darContabilidadesPorFecha(Connection conn, Date nombre, String log)
	{
		ArrayList<ContabilidadGeneral> restaurantes = new ArrayList<>();
		String sql = "SELECT * FROM CONTABILIDADGENERAL WHERE FECHA = ?";
		String mensajeLog =sql+"&"+Date.class.getName()+":"+nombre.getTime();
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setDate(1, nombre);
			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				String nombre1 = rs.getString("NOMBRE_RESTAURANTE");
				Date costoVenta = rs.getDate("FECHA");
				double costo = rs.getDouble("VALOR_COSTOS");
				double precio = rs.getDouble("VALOR_VENTAS");
				Long idZona = rs.getLong("ID_ROTONDA");

				restaurantes.add(new ContabilidadGeneral(nombre1, costoVenta,costo, precio, idZona));
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

	public ArrayList<ContabilidadGeneral> darContabilidades(Connection conn, String log)
	{
		ArrayList<ContabilidadGeneral> restaurantes = new ArrayList<>();
		String sql = "SELECT * FROM CONTABILIDADGENERAL";
		String mensajeLog =sql;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{

				String nombre1 = rs.getString("NOMBRE_RESTAURANTE");
				Date costoVenta = rs.getDate("FECHA");
				double costo = rs.getDouble("VALOR_COSTOS");
				double precio = rs.getDouble("VALOR_VENTAS");
				Long idZona = rs.getLong("ID_ROTONDA");

				restaurantes.add(new ContabilidadGeneral(nombre1, costoVenta,costo, precio, idZona));
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

	public void actualizarContabilidadGeneral(Connection conn, ContabilidadGeneral restaurante, String log)
	{
		String sql = "UPDATE CONTABILIDADGENERAL SET VALOR_COSTOS= ?, VALOR_VENTAS = ? "
				+ "WHERE NOMBRE_RESTAURANTE = ? AND FECHA =? AND ID_ROTONDA =? ";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setDouble(1, restaurante.getValorCostos());
			preStat.setDouble(2, restaurante.getValorVentas());
			preStat.setString(3, restaurante.getNombreRestaurante());
			preStat.setDate(4, restaurante.getFechaContabilidad());
			preStat.setLong(5, restaurante.getIdRotonda());
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

	public void eliminarContabilidadGeneral(Connection conn, ContabilidadGeneral restaurante, String log)
	{
		String sql = "DELETE FROM CONTABILIDADGENERAL WHERE NOMBRE_RESTAURANTE = ? AND FECHA =? AND ID_ROTONDA =?";
		String mensajeLog =sql+"&"+String.class.getName()+":"+restaurante.getNombreRestaurante()+","+Date.class.getName()+":"+restaurante.getFechaContabilidad().getTime()+","+Long.class.getName()+":"+restaurante.getIdRotonda();
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setString(1, restaurante.getNombreRestaurante());
			preStat.setDate(2, restaurante.getFechaContabilidad());
			preStat.setLong(3, restaurante.getIdRotonda());
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
