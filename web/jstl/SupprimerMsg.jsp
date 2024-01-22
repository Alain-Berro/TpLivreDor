<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Page de suppression (étape 3/3)</title>

	<link href="../css/LeLivreDor.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<h1>Compte-rendu de la suppression du ou des message(s)</h1>

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
		   numéros de messages mis en session, on effectue la mise à jour et
		   on confirme l'action.
	--%>
	<c:forEach var="idMsg" items="${sessionScope.idsMsgs}">
		<sql:update var="compteRenduSuppressionMsg" dataSource="${bdMsg}">
			DELETE FROM Message WHERE NumMsg = ${idMsg}
		</sql:update>

		<p> ${idMsg} :
			<c:if test="${compteRenduSuppressionMsg == 1}">
				<jsp:text>suppression réussie</jsp:text>
			</c:if>
			<c:if test="${compteRenduSuppressionMsg != 1}">
				<jsp:text>suppression impossible</jsp:text>
			</c:if>
		</p>
	</c:forEach>

	<%--
		3. on supprime les données mises en session.
	--%>
	<c:remove scope="session" var="idsMsgs" />

	<p><a href="index.html">Retour menu principal</a></p>
</body>
</html>