<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Operações Mensalistas</title>
<script src="js/jquery-3.3.1.js"></script>
<script src="js/CRUDOpsMensalista.js"></script>
</head>
<body>

	<div id="tableDiv"></div>

	<div id="form">
		<form method="POST">
			<input type="text" disabled id="tf_Id"  placeholder="ID"/> 
			<input type="text" id="tf_Nome" placeholder="Nome Mensalista" /> 
			<input type="text" id="tf_Cpf" placeholder="CPF Mensalista" /> 
				<select	id="slc_Status">
				<option value=0>Ativo</option>
				<option value=1>Inativo</option>
			</select> 
			<input type="button" value="Criar Mensalista" id="btn-operation" /> <input
				type="button" value="Limpar Campos" id="btn-clear" />
		</form>
	</div>




</body>
</html>