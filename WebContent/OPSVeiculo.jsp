<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Operações Veículos</title>

<script src="js/jquery-3.3.1.js"></script>

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

<!-- Material Icons -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>

<script src="js/crudOpsVeiculo.js"></script>
<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" />

<link rel="stylesheet" href="css/styleVeiculo.css" />

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
			<form method="POST" id="formVeiculo">


				<div class="row col s12 m12 l12">
					<div class="input-field col s2 m2 l2 push-s5 push-m5 push-l5">

						<div class="push-s4 push-m4 push-l4">
							<label for="tf_Id">ID</label>
						</div>

						<div>
							<input type="text" disabled id="tf_Id" />
						</div>

					</div>



				</div>

				<div class="row col s12 m12 l12">
					<div class="input-field col s4 m4 l4 push-s2 push-m2 push-l2">
						<div class="push-s4 push-m4 push-l4">
							<label for="slc_Modelo">Modelo</label>
						</div>

						<div class="txtMiddle">
							<select id="slc_Modelo" class="col s2 m2 l2 validate" required>
							</select>

						</div>

					</div>

					<div class="input-field col s4 m4 l4 push-s2 push-m2 push-l2">
						<div class="push-s2 push-m2 push-l2">
							<label for="slc_Cor">Cor</label>
						</div>

						<div class="txtMiddle">
							<select id="slc_Cor" class="col s2 m2 l2 validate" required>
								<option value=0>Preto</option>
								<option value=1>Branco</option>
								<option value=2>Prata</option>
								<option value=3>Vermelho</option>
							</select>
						</div>
					</div>


				</div>

				<div class="row col s12 m12 l12">

					<div class="input-field col s4 m4 l4 push-s2 push-m2 push-l2">
						<div class="push-s2 push-m2 push-l2">
							<label for="tf_Placa">Placa</label> 
							<input type="text"
								id="tf_Placa" class="txtMiddle validate" required/>
						</div>


					</div>

					<div class="input-field col s4 m4 l4 push-s2 push-m2 push-l2">
						<div class="push-s2 push-m2 push-l2">
							<label for="slc_Status">Status</label>
						</div>

						<div class="txtMiddle">
							<select id="slc_Status" class="col s2 m2 l2 validate" required>
								<option value=0>Ativo</option>
								<option value=1>Inativo</option>
							</select>
						</div>

					</div>

				</div>


			</form>

			<div id="actionButtons" class="row col s12 m12 l12">

				<div class="input-field">
					<button class="waves-effect waves-light btn" type="submit"
						id="btn-operation">
						<i class="material-icons left">add_circle_outline</i>
						<span id="btn-operation-span" class="right">Adicionar Veículo</span>
					</button>
				</div>

				<div class="input-field">
					<button id="btn-clear" class="waves-effect waves-light btn"
						type="submit"> 
						<i class="material-icons left">clear_all</i>
						<span class="right">Limpar Campos</span>
						</button>
				</div>


			</div>


		</div>
	</div>
</body>
</html>