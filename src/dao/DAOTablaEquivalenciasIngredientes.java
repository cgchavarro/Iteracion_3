package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.EquivalenciaIngredientes;


public class DAOTablaEquivalenciasIngredientes 
{

	public DAOTablaEquivalenciasIngredientes()
	{

	}

	public void agregarEquivalenciaIngredientes(Connection conn, EquivalenciaIngredientes eq, String log)
	{
		String sql = "INSERT INTO EQUIVALENCIASINGREDIENTES VALUES (?,?)";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, eq.getIdIngrediente1());
			preStat.setLong(2, eq.getIdIngrediente2());
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

	
	public ArrayList<EquivalenciaIngredientes> darEquivalencias(Connection conn, String log)
	{
		ArrayList<EquivalenciaIngredientes> tiposINGREDIENTES = new ArrayList<>();
		String sql = "SELECT * FROM EQUIVALENCIASINGREDIENTES";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{

			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				Long iding1 = rs.getLong("IDINGREDIENTE1");
				Long iding2 = rs.getLong("IDINGREDIENTE2");
				tiposINGREDIENTES.add(new EquivalenciaIngredientes(iding1, iding2));
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
		return tiposINGREDIENTES;
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
	
	

	public void eliminarEquivalencia(Connection conn, EquivalenciaIngredientes tipoProducto, String log)
	{
		String sql = "DELETE FROM EQUIVALENCIASINGREDIENTES WHERE IDINGREDIENTE1 =? AND IDINGREDIENTE2=?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, tipoProducto.getIdIngrediente1());
			preStat.setLong(2, tipoProducto.getIdIngrediente2());
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
