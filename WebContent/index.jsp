<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aula Servlet</title>
<script src="js/jquery-3.3.1.js"></script>
<script src="js/index.js"></script>
</head>
<body>
	<!-- 
     <form method="post" action="HelloWorld">
           <input type = "text" placeholder="Nome" name = "tf_nome"/>
           <input type = "text" placeholder="idade" name = "tf_idade"/>
           <input type = "submit"/>
     </form>
     -->

	<form method="POST" action="HelloWorld">
		<input type="text" placeholder="Estado" id="tf_estado" name="tf_estado"/> 
		<input type="text" placeholder="uf" id="tf_uf" /> 
		<select	name="slc_status" id="slc_status">
			<option value=0>Ativo</option>
			<option value=1>Inativo</option>
		</select>
		 <input type="button" value="Salvar" id="btn-salvar"/>
	</form>


</body>
</html>