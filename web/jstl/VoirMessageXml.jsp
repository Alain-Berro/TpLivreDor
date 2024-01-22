<?xml version="1.0" encoding="utf-8"?>

<!--
<?xml-stylesheet type="text/xsl" href="message.xsl"?>
-->

<%@page contentType="text/xml" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<sql:setDataSource user="berro"
				   password="berro"
				   driver="com.mysql.jdbc.Driver"
				   url="jdbc:mysql://localhost:3306/ensg_berro"
				   var="bdMsg" />

<sql:query var="listeNupletsMessage" dataSource="${bdMsg}">
	SELECT Texte, Pseudo FROM Message
</sql:query>

<livredor>
	<c:forEach var="nupletMessage" items="${listeNupletsMessage.rows}">
		<message>
			<texte>${nupletMessage.Texte}</texte>
			<pseudo>${nupletMessage.Pseudo}</pseudo>
		</message>
	</c:forEach>
</livredor>