<%@page import="org.apache.commons.text.StringEscapeUtils"%>
<%@page import="livredor.bd.ExceptionLivreDor"%>
<%@page import="livredor.metier.MessageDor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="livredor.bd.Bd"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <!-- Partie JSTL -->
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>Le livre d'or</title>

		<link href="css/LeLivreDor.css" rel="stylesheet" type="text/css" />
    </head>

    <body>
		<%@include file="bandeau.txt" %>

        <h1>Les messages du livre d'or</h1>

		<table border="1" cellspacing="1" cellpadding="5">
			<tr><th>Pseudo</th><th>Message</th><th></th><th>En Ajax</th></tr>
		<%
			/*----- Lecture de la liste des messages et affichage -----*/
			for (MessageDor msg : (ArrayList<MessageDor>)request.getAttribute("liste"))
				{
				out.print("<tr id=\"tr_" + msg.getId() + "\">\n<td>" + StringEscapeUtils.escapeXml11(msg.getPseudo()) + "</td>\n<td>" + StringEscapeUtils.escapeXml11(msg.getMessage()) + "</td>\n"); /* Ajax */
				out.print("<td><a href=\"Central?type_action=Modifier&id=" + msg.getId() + "\"><b>Modifier</b></a></td>\n");
				out.print("<td><span class=\"commande\" value=\"" + msg.getId() + "\"><b>Supprimer</b></span></td>\n");  /* Ajax */
				out.println("</tr>");
				}
		%>
		</table>
		<br/>
		<form action="Central"
			  method="get">
			<button type="submit" name="type_action" value="Saisir">Ajouter un message</button>
			<button type="submit" name="type_action" value="Retour">Retour</button>
		</form>

		<!-- Partie JSTL
		<hr/>
		<h3>Affichage du tableau avec JSTL</h3>
		<table border="1" cellspacing="1" cellpadding="5">
		<tr><th>Pseudo</th><th>Message</th></tr>
			<c:forEach var="msg" items="${requestScope.liste}">
				<tr><td>${fn:escapeXml(msg.pseudo)}</td><td>${fn:escapeXml(msg.message)}</td></tr>
			</c:forEach>
		</table>
		-->

                <script type="text/JavaScript" src="js/FctAjax.js"></script>
    </body>
</html>
