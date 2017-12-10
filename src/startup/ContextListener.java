package startup;

import javax.jms.JMSException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import dtm.RotondAndesDistributed;

public class ContextListener {
private RotondAndesDistributed dtm;
	
	public void contextDestroyed(ServletContextEvent arg0) 
	{
		try {
			dtm.stop();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		
		System.out.println("Iniciando conexión");
		final ServletContext context = arg0.getServletContext();
		RotondAndesDistributed.setPath(context.getRealPath("WEB-INF/ConnectionData"));
		dtm = RotondAndesDistributed.getInstance();
		System.out.println("Conexión completa");
	}

}
