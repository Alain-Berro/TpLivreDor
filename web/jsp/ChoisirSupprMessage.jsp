<%@page import="livredor.metier.Util"%>
<%@page import="java.util.List"%>
<%@page import="livredor.bd.ExceptionLivreDor"%>
<%@page import="livredor.metier.MessageDor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="livredor.bd.Bd"%>
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

        <h1>S&eacute;lection du ou des message(s) &agrave; supprimer</h1>

		<form action="Confirmer"
			  method="get">

		<table class="tab">
		<tr><th>Choisir</th><th>Num&eacute;ro</th><th>Pseudo</th><th>Message</th></tr>
		<%
			/*----- Liste préalable des messages à supprimer -----*/
			String[] liste_suppr = (String[])session.getAttribute("liste_suppr");
			session.removeAttribute("liste_suppr"); // Après type_action=Suprimer

			/*----- Lecture de la liste des messages et affichage -----*/
			for (MessageDor msg : (ArrayList<MessageDor>)request.getAttribute("liste"))
				{
				out.println("<tr><td><input type=\"checkbox\"" + Util.isChecked(liste_suppr,msg.getId()) + " value=\""
							+ msg.getId() +"\" name=\"cb_numMessage\" /></td>");
				out.println("<td>" + msg.getId() + "</td>");
				out.println("<td>" + msg.getPseudo() + "</td>");
				out.println("<td>" + msg.getMessage() + "</td>");
				out.println("</tr>");
				}
		%>
		</table>

		<p><input type="submit" value="Supprimer" /></p>
		</form>

		<div class="msg_erreur">${msg_erreur}</div>
		<div class="msg_info">${msg_info}</div>

		<form action="Central"
			  method="get">
			<input type="submit" name="type_action" value="Retour" />
		</form>

		<!-- Partie JSTL -->

		<hr/>
		<h3>Affichage du tableau avec JSTL</h3>
		<table class="tab">
		<tr><th>Choisir</th><th>Num&eacute;ro</th><th>Pseudo</th><th>Message</th></tr>
		<c:forEach var="msg" items="${requestScope.liste_triee}">
			<tr>
				<td><input type="checkbox" name="cb_numMessage" value="${msg.key.id}" ${msg.value} /></td>
				<td>${msg.key.id}</td>
				<td>${msg.key.pseudo}</td>
				<td>${msg.key.message}</td>
			</tr>
		</c:forEach>
		</table>

    </body>
</html>
