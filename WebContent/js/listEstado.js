$(document).ready(function() {

	$('#btn-salvar').on('click',function(){
		
		$.ajax({
			url: 'ListarEstadoServlet',
			type: 'POST',
			data:{
				'nome-estado' : $('#tf_estado').val(),
				'sigla-estado' : $('#tf_sigla').val(),
				'status-estado' : $('#slc-status').val()				
			}, success: function(response){
				
			}
		})
		
		
	});
	
	
});