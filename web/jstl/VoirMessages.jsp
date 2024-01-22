<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Page d'affichage du contenu de la table des messages</title>

	<link href="../css/LeLivreDor.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<h1>Les messages du livre d'or</h1>

	<%--
		1. Définition de la source de données. kolga.univ-tlse1.fr
	--%>
	<sql:setDataSource user="berro"
					   password="berro"
					   driver="com.mysql.jdbc.Driver"
					   url="jdbc:mysql://localhost:3306/ensg_berro"
					   var="bdMsg" />

	<%--
		2. Envoi de la requête d'extraction des messages.
	--%>
   <sql:query var="listeNupletsMessage" dataSource="${bdMsg}">
		SELECT Texte, Pseudo FROM Message
   </sql:query>

	<%--
		3. Parcours de la liste des nuplets à l'aide d'une boucle.
		   Chaque nuplet est traité un à un.
	--%>
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<th>Texte</th>
			<th>Pseudo</th>
		</tr>
		<c:forEach var="nupletMessage" items="${listeNupletsMessage.rows}">
			<tr>
				<td>${nupletMessage.Texte}</td>
				<td>${nupletMessage.Pseudo}</td>
			</tr>
		</c:forEach>
	</table>

	<p><a href="index.html">Retour menu principal</a></p>

</body>
</html>