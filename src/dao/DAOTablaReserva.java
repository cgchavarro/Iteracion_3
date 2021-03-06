package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Reserva;

public class DAOTablaReserva  extends DAO
{
	//TODO completar con los atributos
	public DAOTablaReserva()
	{
		
	}
	
	public void agregarReserva(Connection conn, Reserva reserva, String log)
	{
		String sql = "INSERT INTO RESERVA VALUES (?,?,?,?,?)";
		String mensajeLog =sql+"&"+reserva.toParametros();
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, reserva.getIdReserva());
			preStat.setDate(2, reserva.getFecha());
			preStat.setInt(3, reserva.getNumComensales());
			preStat.setLong(4, reserva.getIdCliente());
			preStat.setLong(5, reserva.getIdZona());
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
	
	public Reserva darReservaPorId(Connection conn, Long id, String log)
	{
		Reserva reserva = null;
		String sql = "SELECT * FROM RESERVA WHERE ID = ?";
		String mensajeLog =sql+"&"+Long.class.getName()+":"+id;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, id);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long idReserva = rs.getLong("ID");
				Date fecha = rs.getDate("FECHA");
				int numComensales = rs.getInt("NUM_COMENSALES");
				Long idCliente = rs.getLong("ID_CLIENTE");
				Long idZona = rs.getLong("ID_ZONA");
				reserva = new Reserva(idReserva, fecha, numComensales, idCliente, idZona);
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
		return reserva;
	}
	
	public ArrayList<Reserva> darReservasPorCliente(Connection conn, Long idCliente, String log)
	{
		ArrayList<Reserva> reservas = new ArrayList<>();
		String sql = "SELECT * FROM RESERVA WHERE ID_CLIENTE = ?";
		String mensajeLog =sql+"&"+Long.class.getName()+":"+idCliente;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, idCliente);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long idReserva = rs.getLong("ID");
				Date fecha = rs.getDate("FECHA");
				int numComensales = rs.getInt("NUM_COMENSALES");
				Long id = rs.getLong("ID_CLIENTE");
				Long idZona = rs.getLong("ID_ZONA");
				reservas.add(new Reserva(idReserva, fecha, numComensales, id, idZona));
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
		return reservas;
	}
	
	public ArrayList<Reserva> darReservasPorZona(Connection conn, Long idZona, String log)
	{
		ArrayList<Reserva> reservas = new ArrayList<>();
		String sql = "SELECT * FROM RESERVA WHERE ID_ZONA = ?";
		String mensajeLog =sql+"&"+Long.class.getName()+":"+idZona;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, idZona);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long idReserva = rs.getLong("ID");
				Date fecha = rs.getDate("FECHA");
				int numComensales = rs.getInt("NUM_COMENSALES");
				Long idCliente = rs.getLong("ID_CLIENTE");
				Long id = rs.getLong("ID_ZONA");
				reservas.add(new Reserva(idReserva, fecha, numComensales, idCliente, id));
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
		return reservas;
	}
	
	public ArrayList<Reserva> darReservas(Connection conn, String log)
	{
		ArrayList<Reserva> reservas = new ArrayList<>();
		String sql = "SELECT * FROM RESERVA";
		String mensajeLog =sql;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long idReserva = rs.getLong("ID");
				Date fecha = rs.getDate("FECHA");
				int numComensales = rs.getInt("NUM_COMENSALES");
				Long idCliente = rs.getLong("ID_CLIENTE");
				Long idZona = rs.getLong("ID_ZONA");
				reservas.add(new Reserva(idReserva, fecha, numComensales, idCliente, idZona));
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
		return reservas;
	}
	
	public void actualizarReserva(Connection conn, Reserva reserva, String log)
	{
		String sql = "UPDATE RESERVA SET FECHA = ?, NUM_COMENSALES = ?, ID_CLIENTE = ?, ID_ZONA = ? WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setDate(1, reserva.getFecha());
			preStat.setInt(2, reserva.getNumComensales());
			preStat.setLong(3, reserva.getIdCliente());
			preStat.setLong(4, reserva.getIdZona());
			preStat.setLong(5, reserva.getIdReserva());
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
	
	public void eliminarReserva(Connection conn, Reserva reserva, String log)
	{
		String sql = "DELETE FROM RESERVA WHERE ID = ?";
		String mensajeLog =sql+"&"+Long.class.getName()+":"+reserva.getIdReserva();
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, reserva.getIdReserva());
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
