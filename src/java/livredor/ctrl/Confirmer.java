package livredor.ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Contrôle si l'utilisateur a bien sélectionné des éléments à supprimer.
 */
public class Confirmer extends HttpServlet
{
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
		/*----- Liste des identifiants des messages à supprimer -----*/
		String[] liste = request.getParameterValues("cb_numMessage");

		if (liste != null)
			{
			/*----- Mise en session de la liste des messages à supprimer -----*/
			request.getSession().setAttribute("liste_suppr", liste);

			/*----- Affichage d'une demande de confirmation de suppression -----*/
			request.getRequestDispatcher("ConfirmerSupprMessage").forward(request,response);
			}
		else
			{
			/*----- L'utilisateur n'a pas fait de choix -----*/
			request.setAttribute("msg_info", "Vous devez choisir au moins un message à supprimer.");
			request.getRequestDispatcher("Central?type_action=Supprimer").forward(request,response);
			}
		}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
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
	 * Handles the HTTP <code>POST</code> method.
	 *
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
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo()
		{
		return "Short description";
		}// </editor-fold>

} /*----- Fin de la classe Confirmer -----*/
