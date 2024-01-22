<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modifier un message</title>

        <link href="css/LeLivreDor.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
	<%@include file="bandeau.txt" %>

        <h1>Modifiez votre message</h1>
		<form action="Modifier"
			  method="get">
			<table>
				<tr><td>Identifiant</td><td><input type="text" name="id" value="${requestScope.message.id}" readonly="true" /></td></tr>
				<tr><td>Pseudo</td><td><input type="text" name="pseudo" value="${requestScope.message.pseudo}" /></td></tr>
				<tr><td>Message</td><td><input type="text" name="message" value="${requestScope.message.message}" /></td></tr>
				<tr><td></td><td><input type="submit" value="Enregistrer" /></td></tr>
			</table>
        </form>

		<div class="msg_erreur">${msg_erreur}</div>
		<div class="msg_info">${msg_info}</div>

		<form action="Central"
			  method="get">
			<input type="submit" name="type_action" value="Retour" />
		</form>
    </body>
</html>
