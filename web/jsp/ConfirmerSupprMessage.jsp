<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <!-- Partie JSTL -->
<!DOCTYPE html>
<html>
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Le livre d'or</title>

		<link href="css/LeLivreDor.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
		<%@include file="bandeau.txt" %>

		<h1>Confirmer la suppression du ou des message(s) suivant(s)</h1>

		<form action="Central"
			  method="get">

			<%
				/*----- Liste des identifiants des messages à supprimer -----*/
				String[] liste = (String[])session.getAttribute("liste_suppr");

				out.print("<p>");
				for (int i=0; i<liste.length; i++)
					out.print(liste[i] + " ");

				out.println("</p>");
			%>

			<button type="submit" formaction="Supprimer">Oui</button>
			<button type="submit" name="type_action" value="Supprimer">Non</button>
			<button type="submit" name="type_action" value="Annuler">Annuler</button>
		</form>

		<!-- Partie JSTL -->
		<hr/>
		<h3>Affichage JSTL</h3>
		<c:forEach var="num" items="${sessionScope.liste_suppr}">${num} </c:forEach>

    </body>
</html>
