<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Page de confirmation d'enregistrement du message</title>

	<link href="../css/LeLivreDor.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<h1>Compte-rendu de l'opération</h1>

	<%--
		1. Définition de la source de données.
	--%>
	<sql:setDataSource user="berro"
					   password="berro"
					   driver="com.mysql.jdbc.Driver"
					   url="jdbc:mysql://localhost:3306/ensg_berro"
					   var="bdMsg" />

	<%--
		2. Récupérer les valeurs saisies.
	--%>
	<c:set var="message" value="${param.txt_message}" />
	<c:set var="pseudo" value="${param.txt_pseudo}" />

	<%--
		3. Envoi de l'ordre d'enregistrement du message.
	--%>
	<sql:update var="nbMsgsAjoutes" dataSource="${bdMsg}">
		 INSERT INTO Message (Texte, Pseudo) VALUES ('${message}', '${pseudo}')
	</sql:update>

	<%--
		4. Confirmation de l'enregistrement.
	--%>
	<p>${nbMsgsAjoutes} message(s) enregistré(s).</p>

	<p><a href="index.html">Retour menu principal</a></p>
</body>
</html>