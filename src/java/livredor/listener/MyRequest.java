package livredor.listener;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class MyRequest implements ServletRequestListener
{
	private static final Logger LOG = Logger.getLogger(MyContext.class.getName());

	@Override
	public void requestDestroyed(ServletRequestEvent sre)
		{
		LOG.log(Level.INFO, "-----> Requ\u00eate supprim\u00e9 : {0}", sre.toString());
		}

	@Override
	public void requestInitialized(ServletRequestEvent sre)
		{
		LOG.log(Level.INFO, "-----> Requ\u00eate initialis\u00e9 : {0}", sre.toString());
		}

} /*----- Fin de la classe -----*/

