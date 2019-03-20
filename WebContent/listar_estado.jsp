<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Listar Estados</title>
<script src="js/jquery-3.3.1.js"></script>
<script src="js/listEstado.js"></script>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>

	<div id="set"></div>
	<div id="form">
		<form method="post">
			<input type = "text" disabled id="tf_id" name="tf_id"/>
			<input type="text" placeholder="Estado" id="tf_estado" name="tf_estado"/> 
			<input type="text" placeholder="uf" id="tf_uf" name="tf_uf"/> 
			<select id = "slc_status_update" name="slc_status_update">
				<option value=0>Ativo</option>
				<option value=1>Inativo</option>
			</select> 
			<input type="button" value="Inserir Estado" id="btn-update-db" />
			<input type="button" value="Limpar Campos" id="btn-clear" />
		</form>
	</div>
</body>
</html>