package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOInterpretacionLog {
	public DAOInterpretacionLog()
	{

	}

	public void ejecutarInstruccionLog(String instruccion, Connection conn)
	{
		String[] partes = instruccion.split("&");
		String[] parametros = partes[1].split(",");
		String sql = partes[0];
		try(PreparedStatement preStat = conn.prepareStatement(sql))
		{
			for(int i = 0; i < parametros.length; i ++)
			{
				String parametro = parametros[i];
				String[] partesParametro = parametro.split(":");
				if(partesParametro[0].equals(Integer.class.getName()))
				{
					preStat.setInt(i+1, Integer.parseInt(partesParametro[1]));
				}
				else if(partesParametro[0].equals(Double.class.getName()))
				{
					preStat.setDouble(i+1, Double.parseDouble(partesParametro[1]));
				}
				else if(partesParametro[0].equals(Long.class.getName()))
				{
					preStat.setLong(i+1, Long.parseLong(partesParametro[1]));
				}
				else if(partesParametro[0].equals(Date.class.getName()))
				{
					//TODO parsear date
				}
				else if(partesParametro[0].equals(String.class.getName()))
				{
					preStat.setString(i+1, partesParametro[1]);
				}
				else
				{
					System.out.println("No se guardan correctamente los datos.\n"+instruccion);
				}
			}
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
