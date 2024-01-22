package livredor.ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import livredor.bd.Bd;
import livredor.bd.ExceptionLivreDor;


/**
 * Supprime les messages.
 */
public class Supprimer extends HttpServlet
{
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
		/*----- Récupération en session de la liste des messages à supprimer -----*/
		HttpSession session = request.getSession(false);

		String[] liste_suppr = (String[])session.getAttribute("liste_suppr");

		/*----- Suppression des messages -----*/
		try {
			/*----- Effacement des messages -----*/
			Bd.effacerMessage(liste_suppr);

			/*----- Suppression de la liste en session -----*/
			session.removeAttribute("liste_suppr"); // avant type_action=Afficher

			/*----- Affichage de la liste des messages -----*/
			response.sendRedirect("Central?type_action=Afficher");
			}
		catch (ExceptionLivreDor ex)
			{
			/*----- Retour sur la page de choix des messages à supprimer -----*/
			request.setAttribute("msg_erreur", ex.getMessage());
			request.getRequestDispatcher("Central?type_action=Supprimer").forward(request,response);
			}
		}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP
	 * <code>GET</code> method.
	 * <p/>
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
		{
		processRequest(request, response);
		}

	/**
	 * Handles the HTTP
	 * <code>POST</code> method.
	 * <p/>
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
		{
		processRequest(request, response);
		}

	/**
	 * Returns a short description of the servlet.
	 * <p/>
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo()
		{
		return "Short description";
		}// </editor-fold>

} /*----- Fin de la classe Supprimer -----*/