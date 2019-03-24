$(document).ready(function() {

	importDependencies();
	renderNavbar();

	$('dropdownUser').ready(function() {
		$('.dropdown-trigger').dropdown();
	});

});

// Faz A Importao Dos Plugins Necessários
function importDependencies() {
	var head = $('head');

	var materializeJs = "<script src='https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js'></script>";

	var materializeCss = "<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css'/>";

	var materializeIcons = "<link href='https://fonts.googleapis.com/icon?family=Material+Icons' rel='stylesheet'>";

	head.append(materializeCss);
	head.append(materializeIcons);
	// head.append(materializeJs);

};

// Estrutura o escopo da navbar
function renderNavbar() {
	var divMenu = $('#divMenu');

	var menuDiv = "<div id='navbarMenu' class='navbar-fixed'></div>";
	var menuNav = "<nav id='navMenu'></nav>";
	var navDiv = "<div class='nav-wrapper'> </div>";
	var navLogo = "<a href='menu.jsp' class='brand-logo'>SYSParking</a>";
	var navUl = "<ul id='nav-mobile' class='right hide-on-med-and-down'> </ul>";

	// Cria Os Componentes Que Serao Atrelados A Navbar
	var ulUsuario = "<li><a href='OPSUsuario.jsp'>Usuários</a></li>";
	var ulMensalista = "<li><a href='OPSMensalista.jsp'>Mensalistas</a></li>";
	var ulEstadia = "<li><a href='OPSEstadia.jsp'>Estadias</a></li>";
	var ulTabelaPreco = "<li><a href='OPSTabelaPreco.jsp'>Tabelas De Preco</a></li>";

	// Botao Para Mostrar Dropdown
	var dropdownTrigger = "<li><a id='dropTrigger' class='btn dropdown-trigger' href='#' data-target='dropdownUser'><i class='material-icons'>person</i></a></li>";

	var userDropdown = "<ul id='dropdownUser' class='dropdown-content'></ul>";

	var userStatus = "<li><span id='spanUsername'>'Teste'</span></li>";

	divMenu.append(menuDiv);

	$('#navbarMenu').append(userDropdown);
	$('#dropdownUser').append(userStatus);
	$('#dropdownUser').append("<li><a>teste</a></li>");
	// Juntando Os Componentes Para Renderizacao

	$('#navbarMenu').append(menuNav);
	$('#navMenu').append(navDiv);
	$('#navMenu').children().append(navLogo);
	$('#navMenu').children().append(navUl);

	$('#nav-mobile').append(ulUsuario);
	$('#nav-mobile').append(ulMensalista);
	$('#nav-mobile').append(ulEstadia);
	$('#nav-mobile').append(ulTabelaPreco);
	$('#nav-mobile').append(dropdownTrigger);
};

