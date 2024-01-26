<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Le livre d'or</title>

		<link href="css/LeLivreDor.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<%@include file="bandeau.txt" %>

		<h1>Le livre d'or (en JSP/Servlet)</h1>
		<div>(Interaction avec une base de donn&eacute;es MySQL)</div>
		<ul>
			<li><a href="Central?type_action=Saisir">Saisir un message</a></li>
			<li><a href="Central?type_action=Afficher">Afficher les messages</a></li>
			<li><a href="Central?type_action=Supprimer">Supprimer un ou plusieurs message(s)</a></li>
		</ul>

        <%
			/*----- Affichage du message d'erreur (éventuel) -----*/
			/*
			 * Les EL permettent de simplifier cette écriture de variable
			 * dans le HTML.
			 */
			String msg_erreur = (String)request.getAttribute("msg_erreur");

			if (msg_erreur != null)
				out.println("<p class=\"msg_erreur\">" + msg_erreur + "</p>");
        %>

	</body>
</html>