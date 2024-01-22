package livredor.listener;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class MyContext implements ServletContextListener
{
	private static final Logger LOG = Logger.getLogger(MyContext.class.getName());

	@Override
	public void contextInitialized(ServletContextEvent sce)
		{
		LOG.log(Level.INFO,"---------- Lancement ----------");
		}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
		{
		LOG.log(Level.INFO,"---------- Arrêt ----------");
		}

} /*----- Fin de la classe -----*/
