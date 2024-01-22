package livredor.ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import livredor.bd.Bd;
import livredor.bd.ExceptionLivreDor;


/**
 * Supprime un message suite à l'appel d'une requête AJAX.
 */
public class SupprAjax extends HttpServlet
{
	@Override
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
		/*----- Type de la réponse -----*/
		response.setContentType("application/xml");
		try (PrintWriter out = response.getWriter())
			{
			/*----- Ecriture de la page XML -----*/
			out.println("<?xml version=\"1.0\"?>");
			out.print("<id>");

			/*----- Récupération des paramètres -----*/
			String id = request.getParameter("id_tuple");

			try {
				/*----- Effacement en base de données -----*/
				Bd.effacerMessage(id);

				/*----- Compte-rendu dans le retour XML -----*/
				out.print(id);
				}
			catch (ExceptionLivreDor ex)
				{
				out.print(ex.getMessage());
				}

			out.println("</id>");
			}
		}

	@Override
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { doGet(request, response); }

} /*----- Fin de la servlet SupprAjax -----*/
