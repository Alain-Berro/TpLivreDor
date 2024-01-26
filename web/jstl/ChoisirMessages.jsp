<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Page d'affichage du contenu de la table messages (étape 1/3)</title>

	<link href="../css/LeLivreDor.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<h1>Sélection du ou des message(s) à supprimer</h1>

	<%--
		1. Définition de la source de données.
	--%>
	<sql:setDataSource user="berro"
					   password="berro"
					   driver="com.mysql.jdbc.Driver"
					   url="jdbc:mysql://localhost:3306/ensg_berro"
					   var="bdMsg" />

	<%--
		2. Envoi de la requete d'extraction des messages.
	--%>
   <sql:query var="listeNupletsMessage" dataSource="${bdMsg}">
		SELECT NumMsg, Texte, Pseudo FROM Message
   </sql:query>

	<%--
		3. Parcours de la liste des nuplets à l'aide d'une boucle.
		   Chaque nuplet est traité un à un :
		 . Génération d'une boite à cocher pour que l'utisateur puisse
		   sélectionner le ou les messages à supprimer.
		 . A chaque boite est associée une valeur correspondant à
		   l'identifiant de message. Ainsi lorsque l'utilisateur
		   clique sur le bouton "submit", sont alors envoyés les numéros
		   des messages à supprimer.
	--%>
	<form id="frmChoixMessages"
		  action="ConfirmerSupprMsg.jsp"
		  method="get">

	<table class="tab">
		<tr>
			<th>Choisir</th>
			<th>Numéro</th>
			<th>Texte</th>
			<th>Pseudo</th>
		</tr>

		<c:forEach var="nupletMessage" items="${listeNupletsMessage.rows}">
			<tr>
				<td align="center"><input type="checkbox" value="${nupletMessage.NumMsg}" name="cb_numMessage" /></td>
				<td align="center">${nupletMessage.NumMsg}</td>
				<td>${nupletMessage.Texte}</td>
				<td>${nupletMessage.Pseudo}</td>
			</tr>
		</c:forEach>
	</table>

	<p><input type="submit" value="Supprimer" /></p>
	</form>

	<p><a href="index.html">Retour menu principal</a></p>
</body>
</html>