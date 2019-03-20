
$(document).ready(function(){
	
	
	$('#btn-salvar').on('click',function(){
		
		$.ajax({
			url: 'HelloWorld',
			type: 'POST',
			data:{
				'nome-estado' : $('#tf_estado').val(),
				'sigla-estado' : $('#tf_uf').val(),
				'status-estado' : $('#slc_status').val()				
			}, success: function(response){
				
			}
		})
		
		
	});
	
});