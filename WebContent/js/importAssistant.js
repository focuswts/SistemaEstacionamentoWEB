$(document).ready(function() {

	importDependencies();
});


// Faz A Importao Dos Plugins Necessários
function importDependencies() {

	var head = $('head');

	var materializeJs = "<script src='https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js'></script>";

	var materializeCss = "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css'/>";

	var materializeIcons = "<link href='https://fonts.googleapis.com/icon?family=Material+Icons' rel='stylesheet'>";

	var menu = "<script src='js/menu.js'></script>";

	head.append(materializeJs);
	head.append(materializeCss);
	head.append(materializeIcons);
	head.append(menu);

	
	
};