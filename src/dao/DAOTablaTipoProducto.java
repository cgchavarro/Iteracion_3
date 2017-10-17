package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.TipoProducto;
public class DAOTablaTipoProducto 
{

	public DAOTablaTipoProducto()
	{

	}

	public void agregarTipoProducto(Connection conn, TipoProducto tipoProducto)
	{
		String sql = "INSERT INTO TIPO_PRODUCTO VALUES (?,?)";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, tipoProducto.getIdTipo());
			preStat.setLong(2, tipoProducto.getIdProducto());
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

	public ArrayList<TipoProducto> darTiposProductosPorIdTipo(Connection conn, Long idTipo)
	{
		ArrayList<TipoProducto> tiposProductos = new ArrayList<>();
		String sql = "SELECT * FROM TIPO_PRODUCTO WHERE ID_TIPO = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, idTipo);
			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				Long idTipo1 = rs.getLong("ID_TIPO");
				Long idProducto = rs.getLong("ID_PRODUCTO");
				tiposProductos.add(new TipoProducto(idTipo1, idProducto));
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
	
	public ArrayList<TipoProducto> darTiposProductosPorIdProducto(Connection conn, Long idProducto)
	{
		ArrayList<TipoProducto> tiposProductos = new ArrayList<>();
		String sql = "SELECT * FROM TIPO_PRODUCTO WHERE ID_PRODUCTO = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, idProducto);
			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				Long idTipo = rs.getLong("ID_TIPO");
				Long idProducto1 = rs.getLong("ID_PRODUCTO");
				tiposProductos.add(new TipoProducto(idTipo, idProducto1));
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

	public ArrayList<TipoProducto> darTiposProductos(Connection conn)
	{
		ArrayList<TipoProducto> tiposProductos = new ArrayList<>();
		String sql = "SELECT * FROM TIPO_PRODUCTO";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			ResultSet rs = preStat.executeQuery();

			while(rs.next())
			{
				Long idTipo = rs.getLong("ID_TIPO");
				Long idProducto = rs.getLong("ID_PRODUCTO");
				tiposProductos.add(new TipoProducto(idTipo, idProducto));
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

	public void actualizarTipoTipoProducto(Connection conn, TipoProducto tipoProducto)
	{
		String sql = "UPDATE TIPO_PRODUCTO SET ID_TIPO = ? WHERE ID_PRODUCTO = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, tipoProducto.getIdTipo());
			preStat.setLong(2, tipoProducto.getIdProducto());
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
	
	public void actualizarProductoTipoProducto(Connection conn, TipoProducto tipoProducto)
	{
		String sql = "UPDATE TIPO_PRODUCTO SET ID_PRODUCTO = ? WHERE ID_TIPO = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, tipoProducto.getIdProducto());
			preStat.setLong(2, tipoProducto.getIdTipo());
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

	public void eliminarTipoProducto(Connection conn, TipoProducto tipoProducto)
	{
		String sql = "DELETE FROM TIPO_PRODUCTO WHERE ID_PRODUCTO = ? AND ID_TIPO = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, tipoProducto.getIdProducto());
			preStat.setLong(2, tipoProducto.getIdTipo());
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
