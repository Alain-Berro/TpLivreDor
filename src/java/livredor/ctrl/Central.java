package livredor.ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import livredor.bd.Bd;
import livredor.bd.ExceptionLivreDor;
import livredor.metier.MessageDor;
import livredor.metier.Util;


/**
 * Controleur.
 */
public class Central extends HttpServlet
{
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
		String action = request.getParameter("type_action");
		String url;

		// --> Démarrage de l'application ou  retour !
		if (action ==  null)
			url = "LeLivreDor";
		else
			{
			switch (action)
				{
				case "Saisir":
					url = "SaisirMessage";
					break;

				case "Modifier":
					try {
						url="ModifierMessage";
						request.setAttribute("message", Bd.lireMessage(request.getParameter("id")));
						}
					catch (ExceptionLivreDor ex)
						{
						url = "LeLivreDor";
						request.setAttribute("msg_erreur", ex.getMessage());
						}
					break;

				case "Afficher":
					try {
						url = "LireMessage";
						request.setAttribute("liste", Bd.lireMessages());
						}
					catch (ExceptionLivreDor e)
						{
						url = "LeLivreDor";
						request.setAttribute("msg_erreur", e.getMessage());
						}
					break;

				case "Supprimer":
					try {
						/*----- Partie pour affichage JSP -----*/
						url = "ChoisirSupprMessage";
						request.setAttribute("liste", Bd.lireMessages());

						/*----- Partie affichage JSTL -----*/
						/*
						 * Le modèle est plus proche du MVC.
						 */
						ArrayList<MessageDor> lt = Bd.lireMessages();
						String[] t = (String[])request.getSession().getAttribute("liste_suppr");

						TreeMap<MessageDor,String> tmap= new TreeMap<>();

						lt.forEach((msg) -> { tmap.put(msg, Util.isChecked(t, msg.getId())); });

						request.setAttribute("liste_triee", tmap);
						}
					catch (ExceptionLivreDor e)
						{
						url = "LeLivreDor";
						request.setAttribute("msg_erreur", e.getMessage());
						}
					break;

				case "Annuler":
					/*----- Suppression d'un élément en session -----*/
					request.getSession().removeAttribute("liste_suppr"); // après type_action=Annuler
				case "Retour": ;

				default:
					url = "LeLivreDor";
				}
			}

		// Chainage.
		request.getRequestDispatcher(url).forward(request, response);
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

} /*----- Fin de la classe -----*/
