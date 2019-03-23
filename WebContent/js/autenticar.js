$(document).ready(function() {

	// Pega O Evento Click Do Botao
	$('#btn-login').click(function() {
	
		if(checkInputs() == true){
			login(getInputData());	
		}else{
			alert("Preencha Todos Os Campos");
		}
	
	
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

			}
			
		})

	}


	//VERIFICA SE OS CAMPOS FORAM PREENCHIDOS
	function checkInputs() {
		var isValid = true;
		$('input').filter('[required]').each(function() {
			if ($(this).val() === '') {
				isValid = false;
				return false;
			}
		});
		if (isValid) {
			$('#confirm').prop('disabled', false)
		}
		return isValid;
	}

	
	
});