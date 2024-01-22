package livredor.listener;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;


@WebListener
public class MyAttribute implements HttpSessionAttributeListener
{
	private static final Logger LOG = Logger.getLogger(MyContext.class.getName());

	@Override
	public void attributeAdded(HttpSessionBindingEvent event)
		{
		LOG.log(Level.INFO, "-----> Attribut ajout\u00e9 : {0} = {1}", new Object[]{event.getName(), event.getValue().toString()});
		}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event)
		{
		LOG.log(Level.INFO, "-----> Atrtibut supprim\u00e9 :{0} = {1}", new Object[]{event.getName(), event.getValue().toString()});
		}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event)
		{
		LOG.log(Level.INFO, "-----> Attribut modifi\u00e9 :{0} = {1}", new Object[]{event.getName(), event.getValue().toString()});
		}

 } /*----- Fin de la classe -----*/
