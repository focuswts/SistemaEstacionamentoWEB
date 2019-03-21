<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Operações Veículos</title>
<script src="js/jquery-3.3.1.js"></script>
<script src="js/crudOpsVeiculo.js"></script>
</head>
<body>

	<div id="tableDiv"></div>

	<div id="form">
		<form method="POST">
			<input type="text" disabled id="tf_Id"  placeholder="ID"/>
			
				<select	id="slc_Modelo">
			</select>
			
				<select	id="slc_Cor">
					<option value=0>Preto</option>
				<option value=1>Branco</option>
				<option value=2>Prata</option>
				<option value=3>Vermelho</option>
			</select>
			
			 <input	type="text" id="tf_Placa" placeholder="Placa" /> 
				
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