<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Page de confirmation de la suppression (étape 2/3)</title>

	<link href="../css/LeLivreDor.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<h1>Confirmer la suppression du ou des message(s) suivant(s)</h1>

	<%--
		1. Définition de la source de données.
	--%>
	<sql:setDataSource user="berro"
					   password="berro"
					   driver="com.mysql.jdbc.Driver"
					   url="jdbc:mysql://localhost:3306/ensg_berro"
					   var="bdMsg" />

	<%--
		2. Pour chaque message dont le numéro est récupéré dans la liste des
		   numéros de messages associés aux boites à cocher de la page
		   precedente, on ré-affiche le numéro transmis (les valeurs
		   "choisies/cochées" sont stockées dans "paramValues.cb_numMessage")
	--%>
	<p>
		<c:forEach var="idMessage" items="${paramValues.cb_numMessage}">
			${idMessage}
		</c:forEach>
	</p>
	<p><a href="SupprimerMsg.jsp">Oui</a> <a href="ChoisirMessages.jsp">Non</a></p>

	<p><a href="index.html">Retour menu principal</a></p>

	<%--
		3. Stockage des numéros dans une session. Les numéros des messages
		   seront alors accessibles à la page suivante pour effectuer les
		   suppressions. 'sessionScope' est un espace permettant de stocker des
		   variables partagées par plusieurs pages (ici la page suivante qui
		   effectuera les suppressions).
	--%>
	<c:set scope="session" value="${paramValues.cb_numMessage}" var="idsMsgs" />

</body>
</html>