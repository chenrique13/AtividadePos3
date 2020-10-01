<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro TOSCO!</title>
</head>
<body>
	<h1>Cadastro de Cliente</h1>
	<form action="CadastroTosco.jsp" method="GET">
	<label id="label-1" for="nome">Nome:</label>
	<input type="text" name="nome" id="nome" />
	<br/>
	<label id="label-2" for="cpf">CPF:</label>
	<input type="text" name="cpf" id="cpf" />
	<br/>
	<label id="label-3" for="email">E-mail:</label>
	<input type="text" name="email" id="email" />
	<br/>
	<input type="submit" value="Salvar"/>
	</form>
	
	<%
	out.print("<h2>java</h2>");

	/*ACESSO AO BD*/
	String usu = "root";
	String sen = "123456";
	String uri = "jdbc:mysql://localhost:3306/lojaunit?useTimezone=true&serverTimezone=UTC";
	String sql = "";
	Connection c = DriverManager.getConnection(uri,usu,sen);

	/*SALVA OS DADOS*/
	if((request.getParameter("nome")!=null) && (!request.getParameter("nome").isEmpty())){
		String nomeDoCara = request.getParameter("nome");
		String cpfDoCara = request.getParameter("cpf");
		String emailDoCara = request.getParameter("email");
		
		/*ACESSA O BD E SALVA OS DADOS*/
		sql = "INSERT INTO clientes (nome, cpf, email) VALUES(?,?,?)";
		PreparedStatement pstm = c.prepareStatement(sql);
		pstm.setString(1,nomeDoCara);
		pstm.setString(2,cpfDoCara);
		pstm.setString(3,emailDoCara);
		pstm.executeUpdate();
		pstm.close();
	}else if((request.getParameter("excluir")!=null) && (request.getParameter("excluir").equals("1")) && (request.getParameter("id")!=null)){
		String idDoCara = request.getParameter("id");
		
		/*ACESSA O BD E DETONA OS DADOS*/
		sql = "DELETE FROM clientes WHERE id=?";
		PreparedStatement pstm = c.prepareStatement(sql);
		pstm.setString(1,idDoCara);
		pstm.executeUpdate();
		pstm.close();
	}
	
	/*ACESSA O BD E LISTA OS DADOS */
	sql = "SELECT id, email, nome, cpf FROM clientes";
	Statement stm = c.createStatement();
	ResultSet rs = stm.executeQuery(sql);
	%>
	
	<table border="1">
		<tr>
			<td>CPF</td>
			<td>Nome</td>
			<td>E-mail</td>
			<td></td>
		</tr>

		<%
		while(rs.next()){
			out.print("<tr>");
			out.print("<td>" + rs.getString("cpf") + "</td>");
			out.print("<td>" + rs.getString("nome") + "</td>");
			out.print("<td>" + rs.getString("email") + "</td>");
			out.print("<td><a href='CadastroTosco.jsp?excluir=1&id="+ rs.getInt("id") +"'>excluir</a> | <a>alterar</a> </td>");
			out.print("</tr>");
		}
		%>

	</table>
</body>
</html>