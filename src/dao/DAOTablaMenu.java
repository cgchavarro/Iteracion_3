package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vo.Menu;

public class DAOTablaMenu 
{
	public DAOTablaMenu()
	{
		
	}
	
	public void agregarMenu(Connection conn, Menu menu)
	{
		String sql = "INSERT INTO MENU VALUES (?,?,?,?,?,?,?,?,?)";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, menu.getIdMenu());
			preStat.setDouble(2, menu.getCosto());
			preStat.setDouble(3, menu.getPrecio());
			preStat.setString(4, menu.getNombreRestaurante());
			preStat.setLong(5, menu.getIdPostre());
			preStat.setLong(6, menu.getIdEntrada());
			preStat.setLong(7, menu.getIdPlatoFuerte());
			preStat.setLong(8, menu.getIdAcompaniamiento());
			preStat.setLong(9, menu.getIdBebida());
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
	
	public Menu darMenuPorId(Connection conn, Long id)
	{
		Menu menu = null;
		String sql = "SELECT * FROM MENU WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, id);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long idMenu = rs.getLong("ID");
				double costo = rs.getDouble("COSTO");
				double precio = rs.getDouble("PRECIO");
				String idRestaurante = rs.getString("NOMBRE_RESTAURANTE");
				Long idPostre = rs.getLong("POSTRE");
				Long idEntrada = rs.getLong("ENTRADA");
				Long idPlatoFuerte = rs.getLong("PLATOFUERTE");
				Long idAcompaniamiento = rs.getLong("ACOMPANIAMIENTO");
				Long idBebida = rs.getLong("BEBIDA");
				menu = new Menu(idMenu, costo, precio, idRestaurante,idPostre,idEntrada,idPlatoFuerte,idAcompaniamiento,idBebida);
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
		return menu;
	}
	
	public ArrayList<Menu> darMenusPorRestaurante(Connection conn, Long idRestaurante)
	{
		ArrayList<Menu> menus = new ArrayList<>();
		String sql = "SELECT * FROM MENU WHERE NOMBRE_RESTAURANTE = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, idRestaurante);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long idMenu = rs.getLong("ID");
				double costo = rs.getDouble("COSTO");
				double precio = rs.getDouble("PRECIO");
				String id = rs.getString("NOMBRE_RESTAURANTE");
				Long idPostre = rs.getLong("POSTRE");
				Long idEntrada = rs.getLong("ENTRADA");
				Long idPlatoFuerte = rs.getLong("PLATOFUERTE");
				Long idAcompaniamiento = rs.getLong("ACOMPANIAMIENTO");
				Long idBebida = rs.getLong("BEBIDA");
				menus.add(new Menu(idMenu, costo, precio, id,idPostre,idEntrada,idPlatoFuerte,idAcompaniamiento,idBebida));
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
		return menus;
	}
	
	public ArrayList<Menu> darMenus(Connection conn)
	{
		ArrayList<Menu> menus = new ArrayList<>();
		String sql = "SELECT * FROM MENU";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long idMenu = rs.getLong("ID");
				double costo = rs.getDouble("COSTO");
				double precio = rs.getDouble("PRECIO");
				String id = rs.getString("NOMBRE_RESTAURANTE");
				Long idPostre = rs.getLong("POSTRE");
				Long idEntrada = rs.getLong("ENTRADA");
				Long idPlatoFuerte = rs.getLong("PLATOFUERTE");
				Long idAcompaniamiento = rs.getLong("ACOMPANIAMIENTO");
				Long idBebida = rs.getLong("BEBIDA");
				menus.add(new Menu(idMenu, costo, precio, id,idPostre,idEntrada,idPlatoFuerte,idAcompaniamiento,idBebida));
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
		return menus;
	}
	
	public void actualizarMenu(Connection conn, Menu menu)
	{
		String sql = "UPDATE MENU SET COSTO = ?, PRECIO = ?, NOMBRE_RESTAURANTE = ?, POSTRE = ?, ENTRADA =?, PLATOFUERTE =?, ACOMPANIAMIENTO =?, BEBIDA = ? WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setDouble(1, menu.getCosto());
			preStat.setDouble(2, menu.getPrecio());
			preStat.setString(3, menu.getNombreRestaurante());
			preStat.setLong(4, menu.getIdPostre());
			preStat.setLong(5, menu.getIdEntrada());
			preStat.setLong(6, menu.getIdPlatoFuerte());
			preStat.setLong(7, menu.getIdAcompaniamiento());
			preStat.setLong(8, menu.getIdBebida());
			preStat.setLong(9, menu.getIdMenu());
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
	
	public void eliminarMenu(Connection conn, Menu menu)
	{
		String sql = "DELETE FROM MENU WHERE ID = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, menu.getIdMenu());
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

	
	public ArrayList<Menu> darMenusPorBebida(Connection conn, Long id)
	{
		ArrayList<Menu> menus = new ArrayList<>();
		String sql = "SELECT * FROM MENU WHERE BEBIDA = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, id);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long idMenu = rs.getLong("ID");
				double costo = rs.getDouble("COSTO");
				double precio = rs.getDouble("PRECIO");
				String idr = rs.getString("NOMBRE_RESTAURANTE");
				Long idPostre = rs.getLong("POSTRE");
				Long idEntrada = rs.getLong("ENTRADA");
				Long idPlatoFuerte = rs.getLong("PLATOFUERTE");
				Long idAcompaniamiento = rs.getLong("ACOMPANIAMIENTO");
				Long idBebida = rs.getLong("BEBIDA");
				menus.add(new Menu(idMenu, costo, precio, idr,idPostre,idEntrada,idPlatoFuerte,idAcompaniamiento,idBebida));
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
		return menus;
	}
	public ArrayList<Menu> darMenusPorAcompaniamiento(Connection conn, Long id)
	{
		ArrayList<Menu> menus = new ArrayList<>();
		String sql = "SELECT * FROM MENU WHERE ACOMPANIAMIENTO = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, id);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long idMenu = rs.getLong("ID");
				double costo = rs.getDouble("COSTO");
				double precio = rs.getDouble("PRECIO");
				String idr = rs.getString("NOMBRE_RESTAURANTE");
				Long idPostre = rs.getLong("POSTRE");
				Long idEntrada = rs.getLong("ENTRADA");
				Long idPlatoFuerte = rs.getLong("PLATOFUERTE");
				Long idAcompaniamiento = rs.getLong("ACOMPANIAMIENTO");
				Long idBebida = rs.getLong("BEBIDA");
				menus.add(new Menu(idMenu, costo, precio, idr,idPostre,idEntrada,idPlatoFuerte,idAcompaniamiento,idBebida));
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
		return menus;
	}
	
	public ArrayList<Menu> darMenusPorPlatoFuerte(Connection conn, Long id)
	{
		ArrayList<Menu> menus = new ArrayList<>();
		String sql = "SELECT * FROM MENU WHERE PLATOFUERTE = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, id);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long idMenu = rs.getLong("ID");
				double costo = rs.getDouble("COSTO");
				double precio = rs.getDouble("PRECIO");
				String idr = rs.getString("NOMBRE_RESTAURANTE");
				Long idPostre = rs.getLong("POSTRE");
				Long idEntrada = rs.getLong("ENTRADA");
				Long idPlatoFuerte = rs.getLong("PLATOFUERTE");
				Long idAcompaniamiento = rs.getLong("ACOMPANIAMIENTO");
				Long idBebida = rs.getLong("BEBIDA");
				menus.add(new Menu(idMenu, costo, precio, idr,idPostre,idEntrada,idPlatoFuerte,idAcompaniamiento,idBebida));
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
		return menus;
	}
	
	public ArrayList<Menu> darMenusPorEntrada(Connection conn, Long id)
	{
		ArrayList<Menu> menus = new ArrayList<>();
		String sql = "SELECT * FROM MENU WHERE ENTRADA = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, id);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long idMenu = rs.getLong("ID");
				double costo = rs.getDouble("COSTO");
				double precio = rs.getDouble("PRECIO");
				String idr = rs.getString("NOMBRE_RESTAURANTE");
				Long idPostre = rs.getLong("POSTRE");
				Long idEntrada = rs.getLong("ENTRADA");
				Long idPlatoFuerte = rs.getLong("PLATOFUERTE");
				Long idAcompaniamiento = rs.getLong("ACOMPANIAMIENTO");
				Long idBebida = rs.getLong("BEBIDA");
				menus.add(new Menu(idMenu, costo, precio, idr,idPostre,idEntrada,idPlatoFuerte,idAcompaniamiento,idBebida));
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
		return menus;
	}
	
	public ArrayList<Menu> darMenusPorPostre(Connection conn, Long id)
	{
		ArrayList<Menu> menus = new ArrayList<>();
		String sql = "SELECT * FROM MENU WHERE POSTRE = ?";
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			preStat.setLong(1, id);
			ResultSet rs = preStat.executeQuery();
			
			while(rs.next())
			{
				Long idMenu = rs.getLong("ID");
				double costo = rs.getDouble("COSTO");
				double precio = rs.getDouble("PRECIO");
				String idr = rs.getString("NOMBRE_RESTAURANTE");
				Long idPostre = rs.getLong("POSTRE");
				Long idEntrada = rs.getLong("ENTRADA");
				Long idPlatoFuerte = rs.getLong("PLATOFUERTE");
				Long idAcompaniamiento = rs.getLong("ACOMPANIAMIENTO");
				Long idBebida = rs.getLong("BEBIDA");
				menus.add(new Menu(idMenu, costo, precio, idr,idPostre,idEntrada,idPlatoFuerte,idAcompaniamiento,idBebida));
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
		return menus;
	}
}
