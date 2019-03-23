<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Autenticação</title>
<script src="js/jquery-3.3.1.js"></script>

<!-- Compiled and minified JavaScript -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>

<!-- Material Icons -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>


<!-- Compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css" />

<link rel="stylesheet" href="css/styleLogin.css" />
<script src="js/autenticar.js"></script>
</head>
<body>

	<div class="row container center-align">
		<div>
			<h1>Autenticação De Usuário</h1>
		</div>
	</div>


	<div class="row col s12 m12 l12">

		<div class="container">
			<div id="loginDiv">
				<form method="POST" id="loginForm">
					<div class="input-field col s4 m4 l4 push-s4 push-m4 push-l4">
						<div class="row">
							<div class="txtMiddle">
								<label for="tf_user_name"><i class="material-icons left">person</i></label>

								<input type="text" id="tf_user_name" class="right" required />
							</div>
						</div>

						<div class="row">
							<div class="txtMiddle">
								<label for="tf_user_password" class="">Password</label> <input
									type="password" id="tf_user_password" required />
							</div>
						</div>
					</div>


				</form>
				
				<div class="row col s12 m12 l12">
					<div class="col s4 m4 l4 push-s5 push-m5 push-l5">
						<button class="waves-effect waves-light btn" type="submit"
							id="btn-login">Login</button>
					</div>

				</div>
			</div>

		</div>


	</div>



</body>
</html>