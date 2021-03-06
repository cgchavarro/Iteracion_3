package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.IngredienteProducto;
public class DAOTablaIngredienteProducto  extends DAO
{

	public DAOTablaIngredienteProducto()
	{

	}

	public void agregarIngredienteProducto(Connection conn, IngredienteProducto ingredienteProducto, String log)
	{
		String sql = "INSERT INTO INGREDIENTE_PRODUCTO VALUES (?,?)";
		String mensajeLog =sql+"&"+ingredienteProducto.toParametros();
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, ingredienteProducto.getIdIngrediente());
			preStat.setLong(2, ingredienteProducto.getIdProducto());
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

	public ArrayList<IngredienteProducto> darIngredientesProductosPorIdIngrediente(Connection conn, Long idIngrediente, String log)
	{
		ArrayList<IngredienteProducto> ingredientesProductos = new ArrayList<>();
		String sql = "SELECT * FROM INGREDIENTE_PRODUCTO WHERE ID_INGREDIENTE = ?";
		String mensajeLog =sql+"&"+Long.class.getName()+":"+idIngrediente;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, idIngrediente);
			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				Long idIngrediente1 = rs.getLong("ID_INGREDIENTE");
				Long idProducto = rs.getLong("ID_PRODUCTO");
				ingredientesProductos.add(new IngredienteProducto(idIngrediente1, idProducto));
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
		return ingredientesProductos;
	}
	
	public ArrayList<IngredienteProducto> darIngredientesProductosPorIdProducto(Connection conn, Long idProducto, String log)
	{
		ArrayList<IngredienteProducto> ingredientesProductos = new ArrayList<>();
		String sql = "SELECT * FROM INGREDIENTE_PRODUCTO WHERE ID_PRODUCTO = ?";
		String mensajeLog =sql+"&"+Long.class.getName()+":"+idProducto;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, idProducto);
			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				Long idIngrediente = rs.getLong("ID_INGREDIENTE");
				Long idProducto1 = rs.getLong("ID_PRODUCTO");
				ingredientesProductos.add(new IngredienteProducto(idIngrediente, idProducto1));
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
		return ingredientesProductos;
	}

	public ArrayList<IngredienteProducto> darIngredientesProductos(Connection conn, String log)
	{
		ArrayList<IngredienteProducto> ingredientesProductos = new ArrayList<>();
		String sql = "SELECT * FROM INGREDIENTE_PRODUCTO";
		String mensajeLog =sql;
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				Long idIngrediente = rs.getLong("ID_INGREDIENTE");
				Long idProducto = rs.getLong("ID_PRODUCTO");
				ingredientesProductos.add(new IngredienteProducto(idIngrediente, idProducto));
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
		return ingredientesProductos;
	}

	public void actualizarIngredienteDeIngredienteProducto(Connection conn, IngredienteProducto ingredienteProducto, String log)
	{
		String sql = "UPDATE INGREDIENTE_PRODUCTO SET ID_INGREDIENTE = ? WHERE ID_PRODUCTO = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, ingredienteProducto.getIdIngrediente());
			preStat.setLong(2, ingredienteProducto.getIdProducto());
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
	
	public void actualizarProductoDeIngredienteProducto(Connection conn, IngredienteProducto ingredienteProducto, String log)
	{
		String sql = "UPDATE INGREDIENTE_PRODUCTO SET ID_PRODUCTO = ? WHERE ID_INGREDIENTE = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, ingredienteProducto.getIdProducto());
			preStat.setLong(2, ingredienteProducto.getIdIngrediente());
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

	public void eliminarIngredienteProducto(Connection conn, IngredienteProducto ingredienteProducto, String log)
	{
		String sql = "DELETE FROM INGREDIENTE_PRODUCTO WHERE ID_INGREDIENTE = ? AND ID_PRODUCTO = ?";
		String mensajeLog =sql+"&"+Long.class.getName()+":"+ingredienteProducto.toParametros();
		escribirLog(mensajeLog, log);
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, ingredienteProducto.getIdProducto());
			preStat.setLong(2, ingredienteProducto.getIdIngrediente());
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
