<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Operações Usuários</title>
<script src='js/jquery-3.3.1.js'></script>
<script type="text/javascript" src='js/importAssistant.js'></script>

<script src="js/crudOpsUsuario.js"></script>

<link rel="stylesheet" href="css/styleLogin.css">

</head>
<body>

	<div id="divMenu"></div>

	<div class="row container center-align">
		<div>
			<h1>Usuários</h1>
		</div>
	</div>

	<div class="row container">
		<div id="tableContent" class="container centered">
			<div id="tableDiv"></div>
		</div>
	</div>

	<div class="row container center-align">

		<div id="form">
			<form method="POST">

				<div class="row col s12 m12 l12">
					<div class="input-field col s2 m2 l2 push-s5 push-m5 push-l5">

						<div class="push-s4 push-m4 push-l4">
							<label for="tf_Id">ID</label>
						</div>

						<div>
							<input type="text" disabled id="tf_Id" placeholder="ID">
						</div>


					</div>
				</div>

				<div class="row col s12 m12 l12">
					<div class="input-field col s6 m6 l6 left">

						<div class="">
							<label for="tf_Nome">Usuário</label>
						</div>

						<div class="">
							<input type="text" id="tf_Nome" required />
						</div>


					</div>


					<div class="input-field col s6 m6 l6 right">

						<div class="push-s4 push-m4 push-l4">
							<label for="tf_Senha">Senha</label>
						</div>

						<div>
							<input type="text" id="tf_Senha" required />
						</div>

					</div>


				</div>

				<div class="input-field col s6 m6 l6 push-s3 push-m3 push-l3">
					<div class="push-s2 push-m2 push-l2">
						<label for="slc_Status">Status</label>
					</div>

					<div class="txtMiddle">
						<select id="slc_Status" class="validate" required>
							<option value=0>Ativo</option>
							<option value=1>Inativo</option>
						</select>
					</div>

				</div>

			</form>

			<div id="actionButtons" class="row col s12 m12 l12">

				<div class="input-field">
					<button class="waves-effect waves-light btn" type="submit"
						id="btn-operation">
						<i class="material-icons left">add_circle_outline</i> <span
							id="btn-operation-span" class="right">Adicionar Usuário</span>
					</button>
				</div>

				<div class="input-field">
					<button id="btn-clear" class="waves-effect waves-light btn"
						type="submit">
						<i class="material-icons left">clear_all</i> <span class="right">Limpar
							Campos</span>
					</button>
				</div>


			</div>


		</div>



	</div>
</body>
</html>