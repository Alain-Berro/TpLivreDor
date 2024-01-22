package livredor.ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import livredor.bd.Bd;
import livredor.bd.ExceptionLivreDor;
import livredor.metier.MessageDor;


/**
 * Enregistre le message.
 */
public class Enregistrer extends HttpServlet
{
    protected void processRequest (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
		/*----- Vérification des informations saisies par l'utilisateur -----*/
		String m = request.getParameter("message");
		String p = request.getParameter("pseudo");

		String msg_erreur = "";
		if (m.isEmpty() && p.isEmpty())
			msg_erreur = "Veuillez saisir les informations demandées";
		else
			{
			if (m.isEmpty()) msg_erreur = "Veuillez saisir un message";
			if (p.isEmpty()) msg_erreur = "Veuillez saisir votre pseudo";
			}

		if (!msg_erreur.isEmpty())
			{
			/*----- Retour sur la page de saisie avec une message et ... -----*/
			request.setAttribute("msg_info", msg_erreur);
			request.getRequestDispatcher("SaisirMessage").forward(request,response);
			}
		else
			{
			/*----- Création d'un objet métier MessageDor -----*/
			MessageDor msgdor = new MessageDor(m,p);

			try {
				/*----- Enregistrement du message -----*/
				Bd.enregistrerPrepared(msgdor);

				/*----- Affichage de le liste des messages -----*/
				response.sendRedirect("Central?type_action=Afficher");
				}
			catch (ExceptionLivreDor ex)
				{
				/*----- Retour sur la page de saisie du message -----*/
				request.setAttribute("msg_erreur", ex.getMessage());
				request.getRequestDispatcher("SaisirMessage").forward(request,response);
				}
			}
        }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

} /*----- Fin de la classe Enregistrer -----*/