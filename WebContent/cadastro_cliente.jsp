<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro Cliente</title>
</head>
<body>
<h1>Cadastro Cliente</h1>
<form action="ClienteController" method="POST">
	<input type="hidden" name="action" value="salvar"/>
	Nome: <input type="text" name="nome" required="required"/><br/>
	CPF: <input type="text" name="cpf" required="required"/><br/>
	E-Mail: <input type="text" name="email" required="required"/><br/>
	<input type="submit" value="Salvar"/>
</form>
</body>
</html>