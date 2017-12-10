package dtm;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.jms.JMSException;
import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.rabbitmq.jms.admin.RMQConnectionFactory;

import jms.AllRestauranteProductoMDB;
import jms.AllRestaurantesMDB;
import jms.BorrarRestauranteMDB;
import jms.BorrarRestauranteTwoPhaseMDB;
import jms.NonReplyException;
import jms.SpecificRestauranteProductoMDB;
import master.RotondAndesTM;
import vo.OrdenRestaurante;
import vo.Restaurante;

public class RotondAndesDistributed {
	
	private final static String MQ_CONNECTION_NAME = "java:global/RMQSuperRotonda";
	
	private static RotondAndesDistributed instance;
	
	private RotondAndesTM tm;
	
	
	private TopicConnectionFactory factory;
	
	private AllRestaurantesMDB allRestaurantesMQ;
	
	private AllRestauranteProductoMDB allRestauranteProductoMQ;
	
	private SpecificRestauranteProductoMDB specificResProMQ;
	
	private BorrarRestauranteMDB borrarResMQ;
	
	private BorrarRestauranteTwoPhaseMDB borrarRes2PhaseMQ;

	
	private static String path;


	private RotondAndesDistributed() throws NamingException, JMSException
	{
		InitialContext ctx = new InitialContext();
		factory = (RMQConnectionFactory) ctx.lookup(MQ_CONNECTION_NAME);
		if(factory==null)
			System.out.println("paila");
		allRestaurantesMQ = new AllRestaurantesMDB(factory, ctx);
		
		InitialContext ctx2 = new InitialContext();
		factory = (RMQConnectionFactory) ctx2.lookup(MQ_CONNECTION_NAME);
		allRestauranteProductoMQ = new AllRestauranteProductoMDB(factory, ctx2);
		
		InitialContext ctx3 = new InitialContext();
		factory = (RMQConnectionFactory) ctx3.lookup(MQ_CONNECTION_NAME);
		specificResProMQ = new SpecificRestauranteProductoMDB(factory, ctx3);
		
		InitialContext ctx4 = new InitialContext();
		factory = (RMQConnectionFactory) ctx4.lookup(MQ_CONNECTION_NAME);
		borrarResMQ = new BorrarRestauranteMDB(factory, ctx4);
		
		InitialContext ctx5 = new InitialContext();
		factory = (RMQConnectionFactory) ctx5.lookup(MQ_CONNECTION_NAME);
		borrarRes2PhaseMQ = new BorrarRestauranteTwoPhaseMDB(factory, ctx5);
		
		allRestaurantesMQ.start();
		allRestauranteProductoMQ.start();
		specificResProMQ.start();
		borrarResMQ.start();
		
	}
	
	public void stop() throws JMSException
	{
		allRestaurantesMQ.close();
		allRestauranteProductoMQ.close();
		specificResProMQ.close();
		borrarResMQ.close();
		borrarRes2PhaseMQ.close();
	}
	
	/**
	 * Método que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	public static void setPath(String p) {
		path = p;
	}
	
	public void setUpTransactionManager(RotondAndesTM tm)
	{
	   this.tm = tm;
	}
	
	
	public static RotondAndesDistributed getInstance(RotondAndesTM tm)
	{
		if(instance == null)
		{
			try {
				instance = new RotondAndesDistributed();
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		instance.setUpTransactionManager(tm);
		return instance;
	}
	
	public static RotondAndesDistributed getInstance()
	{
		if(instance == null)
		{
			RotondAndesTM tm = new RotondAndesTM(path);
			return getInstance(tm);
		}
		if(instance.tm != null)
		{
			return instance;
		}
		RotondAndesTM tm = new RotondAndesTM(path);
		return getInstance(tm);
	}
	
	public ArrayList<Restaurante> getLocalRestaurantes() throws Exception
	{
		//TODO what
		return tm.darRestaurantes();
	}
	
	public ArrayList<OrdenRestaurante> getLocalRestauranteProducto() throws Exception
	{
		//TODO
		return null;
	}
	
	public ArrayList<Restaurante> getRemoteRestaurantes() throws JsonGenerationException, JsonMappingException, JMSException, IOException, NonReplyException, InterruptedException, NoSuchAlgorithmException
	{
		//TODO
		return null;
	}
	
	public ArrayList<OrdenRestaurante> getRemoteRestauranteProducto() throws JsonGenerationException, JsonMappingException, JMSException, IOException, NonReplyException, InterruptedException, NoSuchAlgorithmException
	{
		//TODO
		return null;
	}
	
	public OrdenRestaurante getLocalSpecificRestauranteProducto(String nomR, String nomP) throws Exception
	{
		//TODO
		return null;
	}

	public void borrarRemoteRestaurante(String nomR) throws JsonGenerationException, JsonMappingException, NoSuchAlgorithmException, JMSException, IOException, NonReplyException, InterruptedException{		

		//TODO
	}
	
	public String borrarRemoteRestaurante2Phase(String nomR) throws Exception{		
		return borrarRes2PhaseMQ.borrarRemoteRestaurante(nomR);
	}

	public void borrarLocalRestaurante(String nomR) throws Exception{
		//	TODO
	}
	
	public String confirmarCambiosRemote() throws Exception
	{
		return borrarRes2PhaseMQ.borrarRemoteRestaurante("Confirmar");
	}
	
}
