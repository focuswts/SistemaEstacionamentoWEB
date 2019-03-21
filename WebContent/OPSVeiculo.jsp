<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Operações Veículos</title>
<script src="js/jquery-3.3.1.js"></script>
<script src="js/crudOpsVeiculo.js"></script>
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" />
<link rel="stylesheet" href="css/styleVeiculo.css" />
<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
</head>
<body>

	<div class="row container center-align">
		<div>
			<h1>Veículos</h1>
		</div>
	</div>

	<div class="row container">
		<div id="tableContent" class="container centered">
			<div id="tableDiv"></div>
		</div>
	</div>


	<div class="row container center-align">
		<div id="form" class="">
			<form method="POST">


				<div class="row">
					<div class="input-field col s2 push-s6">
						<label for="tf_Id" class="col s2">ID</label>
						 <input type="text"
							disabled id="tf_Id" placeholder="ID" class="" />
					</div>
				</div>

				<div class="row">
					<div class="input-field col s4 push-s4">
						<label for="slc_Modelo" class="col s4 push-s4">Modelo</label> <select
							id="slc_Modelo" class="col s2 center-align">
						</select>
					</div>
				</div>


				<div class="row">
					<div class="input-field col s4 push-s4">
						<label for="slc_Cor" class="col s4 push-s4">Cor</label> 
						<select
							id="slc_Cor" class="col s2 center-align">
							<option value=0>Preto</option>
							<option value=1>Branco</option>
							<option value=2>Prata</option>
							<option value=3>Vermelho</option>
						</select>
					</div>
				</div>

				<div class="row">
					<div class="input-field col s4 push-s4">
					<label for="tf_Placa">Placa</label>
						<input type="text" id="tf_Placa" placeholder="Placa" class=""/>
					</div>
				</div>


				<div class="input-field">
					<select id="slc_Status">
						<option value=0>Ativo</option>
						<option value=1>Inativo</option>
					</select>
				</div>

				<div class="input-field">
					<input type="button" value="Criar Modelo" id="btn-operation" />
				</div>

				<div class="input-field">
					<input type="button" value="Limpar Campos" id="btn-clear" />
				</div>




			</form>
		</div>
	</div>

</body>
</html>