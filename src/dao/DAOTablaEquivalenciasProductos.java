package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import vo.EquivalenciaProductos;

public class DAOTablaEquivalenciasProductos  extends DAO
{

	public DAOTablaEquivalenciasProductos()
	{

	}

	public void agregarEquivalenciaProductos(Connection conn, EquivalenciaProductos eq, String log)
	{
		String sql = "INSERT INTO EQUIVALENCIASPRODUCTOS VALUES (?,?)";
		String mensajeLog =sql+"&"+eq.toParametros();
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, eq.getIdProducto1());
			preStat.setLong(2, eq.getIdProducto2());
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

	
	public ArrayList<EquivalenciaProductos> darEquivalencias(Connection conn, String log)
	{
		ArrayList<EquivalenciaProductos> tiposProductos = new ArrayList<>();
		String sql = "SELECT * FROM EQUIVALENCIASPRODUCTOS";
		String mensajeLog =sql;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{

			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				Long idProducto1 = rs.getLong("IDPRODUCTO1");
				Long idProducto2 = rs.getLong("IDPRODUCTO2");
				tiposProductos.add(new EquivalenciaProductos(idProducto1, idProducto2));
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
		return tiposProductos;
	}



//	public void actualizarEquivalencia(Connection conn, equiva tipoProducto)
//	{
//		String sql = "UPDATE TIPO_PRODUCTO SET ID_TIPO = ? WHERE ID_PRODUCTO = ?";
//		try(PreparedStatement preStat = conn.prepareStatement(sql))
//		{
//			preStat.setLong(1, tipoProducto.getIdTipo());
//			preStat.setLong(2, tipoProducto.getIdProducto());
//			preStat.executeQuery();
//			conn.commit();
//		}
//		catch(SQLException e)
//		{
//			try 
//			{
//				conn.rollback();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//			e.printStackTrace();
//		}
//	}
	
	

	public void eliminarEquivalencia(Connection conn, EquivalenciaProductos eq, String log)
	{
		String sql = "DELETE FROM EQUIVALENCIASPRODUCTOS WHERE IDPRODUCTO1 = ? AND IDPRODUCTO2 = ?";
		String mensajeLog =sql+"&"+eq.toParametros();
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, eq.getIdProducto1());
			preStat.setLong(2, eq.getIdProducto2());
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

	public EquivalenciaProductos darEquivalencia(Connection conn, EquivalenciaProductos eq, String log) 
	{
		EquivalenciaProductos tiposProductos = null;
		String sql = "SELECT * FROM EQUIVALENCIASPRODUCTOS WHERE  IDPRODUCTO1 = ? AND IDPRODUCTO2 = ?";
		String mensajeLog =sql+"&"+eq.toParametros();
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, eq.getIdProducto1());
			preStat.setLong(2, eq.getIdProducto2());

			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				Long idProducto1 = rs.getLong("IDPRODUCTO1");
				Long idProducto2 = rs.getLong("IDPRODUCTO2");
			tiposProductos =	new EquivalenciaProductos(idProducto1, idProducto2);
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
		return tiposProductos;
		
	}


}
