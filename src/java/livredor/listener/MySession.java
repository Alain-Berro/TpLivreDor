package livredor.listener;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


@WebListener
public class MySession implements HttpSessionListener
{
	private static final Logger LOG = Logger.getLogger(MyContext.class.getName());

	@Override
	public void sessionCreated(HttpSessionEvent se)
		{
		LOG.log(Level.INFO,"-----> Session créée...");
		}

	@Override
	public void sessionDestroyed(HttpSessionEvent se)
		{
		LOG.log(Level.INFO,"... Session détruite ----->");
		}

} /*----- Fin de la classe -----*/
