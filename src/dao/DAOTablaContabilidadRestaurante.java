package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import vo.ContabilidadRestaurante;


public class DAOTablaContabilidadRestaurante 
{
	public DAOTablaContabilidadRestaurante()
	{
		
	}
	
	public void agregarContabilidadRestaurante(Connection conn, ContabilidadRestaurante restaurante)
	{
		String sql = "INSERT INTO CONTABILIDADRESTAURANTE VALUES (?,?,?,?,?)";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
		
			preStat.setLong(1, restaurante.getIdVenta());
			preStat.setDouble(2, restaurante.getCostoVenta());
			preStat.setDouble(3, restaurante.getPrecioVenta());
			preStat.setDate(4, restaurante.getFechaVenta());
			preStat.setString(5, restaurante.getNombreRestaurante());
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
	
	public ContabilidadRestaurante darContabilidadRestaurantePorId(Connection conn,Long idVenta)
	{
		ContabilidadRestaurante restaurante = null;
		String sql = "SELECT * FROM CONTABILIDADRESTAURANTE WHERE ID_ORDEN = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, idVenta);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				
				Long nombre1 = rs.getLong("ID_ORDEN");
				double costoVenta = rs.getDouble("COSTO_VENTA");
				double precioVenta = rs.getDouble("PRECIO_VENTA");
				Date fecha = rs.getDate("FECHA_VENTA");
				String idZona = rs.getString("NOMBRE_RESTAURANTE");
			
				restaurante = new ContabilidadRestaurante(nombre1, costoVenta,precioVenta, fecha, idZona);
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
	
	public ArrayList<ContabilidadRestaurante> darContabilidadesPorFecha(Connection conn, Date nombre)
	{
		ArrayList<ContabilidadRestaurante> restaurantes = new ArrayList<>();
		String sql = "SELECT * FROM CONTABILIDADRESTAURANTE WHERE FECHA_VENTA = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setDate(1, nombre);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long nombre1 = rs.getLong("ID_ORDEN");
				double costoVenta = rs.getDouble("COSTO_VENTA");
				double precioVenta = rs.getDouble("PRECIO_VENTA");
				Date fecha = rs.getDate("FECHA_VENTA");
				String idZona = rs.getString("NOMBRE_RESTAURANTE");
			
				restaurantes.add(new ContabilidadRestaurante(nombre1, costoVenta,precioVenta, fecha, idZona));
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
	
	public ArrayList<ContabilidadRestaurante> darContabilidades(Connection conn)
	{
		ArrayList<ContabilidadRestaurante> restaurantes = new ArrayList<>();
		String sql = "SELECT * FROM CONTABILIDADRESTAURANTE";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				
				Long nombre1 = rs.getLong("ID_ORDEN");
				double costoVenta = rs.getDouble("COSTO_VENTA");
				double precioVenta = rs.getDouble("PRECIO_VENTA");
				Date fecha = rs.getDate("FECHA_VENTA");
				String idZona = rs.getString("NOMBRE_RESTAURANTE");
			
				restaurantes.add(new ContabilidadRestaurante(nombre1, costoVenta,precioVenta, fecha, idZona));
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
	
	public void actualizarContabilidadRestaurante(Connection conn, ContabilidadRestaurante restaurante)
	{
		String sql = "UPDATE CONTABILIDADRESTAURANTE SET COSTO_VENTA= ?, PRECIO_VENTA = ?, NOMBRE_RESTAURANTE = ?, FECHA_VENTA =? WHERE ID_ORDEN = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setDouble(1, restaurante.getCostoVenta());
			preStat.setDouble(2, restaurante.getPrecioVenta());
			preStat.setString(3, restaurante.getNombreRestaurante());
			preStat.setDate(4, restaurante.getFechaVenta());
			preStat.setLong(5, restaurante.getIdVenta());
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

	public void eliminarContabilidadRestaurante(Connection conn, ContabilidadRestaurante restaurante)
	{
		String sql = "DELETE FROM CONTABILIDADRESTAURANTE WHERE ID_ORDEN = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, restaurante.getIdVenta());
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
