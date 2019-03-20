$(document).ready(function() {

	// Pega O Evento Click Do Botao
	$('#btn-login').click(function() {
		login(getInputData());
	});

	function getInputData() {
		var user = $('#tf_user_name').val();
		var password = $('#tf_user_password').val();

		var usuario = {
			'user' : user,
			'password' : password,
		}
		return usuario;
	}

	function login(usuario) {

		$.ajax({
			url : 'CRUDUsuarioServlet',
			data : {
				'operation' : "authentication",
				'user' : usuario.user,
				'password' : usuario.password
			},
			type : 'POST',
			success : function(response) {
				// alert(JSON.stringify(response));
				alert(response);

			},
		})

	}

});