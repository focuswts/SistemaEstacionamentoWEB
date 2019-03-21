<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Operações Modelos</title>
<script src="js/jquery-3.3.1.js"></script>
<script src="js/crudOpsModelo.js"></script>
</head>
<body>

	<div id="tableDiv"></div>

	<div id="form">
		<form method="POST">
			<input type="text" disabled id="tf_Id"  placeholder="ID"/>
			
			<select id="slc_Marca">
			</select> 
			
				<select	id="slc_TipoVeiculo">
			</select>
			
			 <input	type="text" id="tf_Descricao" placeholder="Modelo" /> 
				
			<select id="slc_Status">
				<option value=0>Ativo</option>
				<option value=1>Inativo</option>
			</select>
			 <input type="button" value="Criar Modelo" id="btn-operation" /> <input
				type="button" value="Limpar Campos" id="btn-clear" />
		</form>
	</div>


</body>
</html>