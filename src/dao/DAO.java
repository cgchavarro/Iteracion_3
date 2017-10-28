package dao;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

public class DAO {

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
