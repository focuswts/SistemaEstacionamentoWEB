$(document).ready(
				function() {

					fillTable();

					function fillTable() {
						clearTable();
						$.ajax({
							url : 'CRUDUsuarioServlet',
							data : {
								'operation' : "list"
							},
							type : 'POST',
							success : function(response) {
								// alert(JSON.stringify(response));
								render(response);
								// console.log("teste");
							}
						})
					}

					// Renderiza A Table Com Os Usuarios
					function render(data) {
						var tabela = $('#tableDiv').append(
								$('<table></table>').attr("id", "tabela"));
						var tHead = "<thead id='tableHead'></thead>";
						$('#tabela').append(tHead);
						var rowH = "<tr> " + "<th>ID</th>" + "<th>Login</th>"
								+ "<th>Senha</th>" + "<th>Status</th>"
								+ "<th>Actions</th>" + "</tr>";
						$('#tableHead').append(rowH);

						$('#tabela').addClass("responsive-table");

						$('#tabela').append(
								$('<tbody></tbody>').attr("id", "tabelaBody"));

						$.each(data,
										function(index, value) {

											var td = $("<tr id= 'row" + index
													+ "'>" + "</tr>");

											var row1 = $("<td>"
													+ value.idUsuario + "</td>");

											var row2 = $("<td>"
													+ value.nomeUsuario
													+ "</td>");

											var row3 = $("<td>"
													+ value.senhaUsuario
													+ "</td>");

											var row4 = $("<td>"
													+ value.statusUsuario
													+ "</td>");

											var buttonUpdate = $(
													'<a class="waves-effect waves-light btn"><i class="material-icons center">mode_edit</i></a>')
													.attr(
															"id",
															"btn-update-"
																	+ index)
													.click({
														p1 : this
													}, setInputData);

											var buttonRemove = $(
													'<a class="waves-effect waves-light btn"><i class="material-icons center">delete</i></a>')
													.attr(
															"id",
															"btn-remove-"
																	+ index)
													.click({
														p1 : this
													}, callRemoveServlet);

											var row5 = $("<td></td>");
											row5.append(buttonUpdate);
											row5.append(buttonRemove);

											td.append(row1);
											td.append(row2);
											td.append(row3);
											td.append(row4);
											td.append(row5);

											$('#tabela tbody').append(td);

											$('#set')
													.append('</tbody></table>');

										})
						$('select').formSelect();
					
					}
					;

					// VERIFICA SE OS CAMPOS FORAM PREENCHIDOS
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

					// Efetua A Funcao Ao Clicar no Botao btn-operation
					$(function() {
						$("#btn-operation").click(function() {
							if (checkInputs() == true) {
								doOperation();
							} else {
								alert("Preencha Todos Os Campos Necessários");
							}

						});
					});

					// Efetua A Funcao Limpar Campos Ao Clicar No Botao
					// LimparCampos
					$(function() {
						$("#btn-clear").click(function() {
							clearFields();
						});
					});

					// Define Os Valores Do Input Para Update
					function setInputData(event) {

						// console.log($('#row'));
						clearFields();
						var row = "#" + $(this).parent().parent().attr('id');

						var idUsuario = $(row).find("td:eq(0)").text();

						var nomeUsuario = $(row).find("td:eq(1)").text();

						var senhaUsuario = $(row).find("td:eq(2)").text();

						var statusUsuario = $(row).find("td:eq(3)").text();

						console.log("Status: " + statusUsuario);

						$('#tf_Id').val(idUsuario);
						$('#tf_Nome').val(nomeUsuario);
						$('#tf_Senha').val(senhaUsuario);

						if (statusUsuario.toLowerCase() === "ativo") {
							$('#slc_Status').val(0);
						} else if (statusUsuario.toLowerCase() === "inativo") {
							$('#slc_Status').val(1);
						}

						// Acessa O Componente Filho Do Select(OPTION)
						// roda a funcao para separar o valor(value) do conteudo
						// do
						// OPTION
						$.each($('#slc_status').children(), function(k, v) {
							// Define O Valor Do Option Para False Para Depois
							// Definir
							// Um Valor Como TRUE
							$(v).prop('selected', false);

							// Efetua A Comparação Para Ver Se O Valor Da Table
							// é igual
							// Ao valor do OPTION
							if ($(v).text().toLowerCase() === statusUsuario
									.toLowerCase()) {
								$(v).prop('selected', true);
							}
						});

						var button = $('#btn-operation');
						if ($('#tf_Id').val() != null) {
							button.text("Atualizar Usuário");
						} else if ($('#tf_Id').val() == null) {
							button.text("Inserir Usuário");
						}

						// Atualiza Os Componentes SELECT Materialize
						$('#slc_Status').formSelect();
					
					}
					;

					// Limpa A Tabela
					function clearTable() {
						$('#tabela thead').remove();
						$('#tabela tbody').remove();
					}

					// Limpa Os Campos Input
					function clearFields() {
						$('#tf_Id').val(null);
						$('#tf_Nome').val(null);
						$('#tf_Senha').val(null);
						$('#slc_Status').val(0);
						$('#btn-operation').val("Inserir Usuário");
					}
					;

					// Pega Os Valores Dos Campos Input
					function getInputData(operacao) {

						var nomeUsuario = $('#tf_Nome').val();
						// alert(nomeEstado);

						var senhaUsuario = $('#tf_Senha').val();
						// alert(siglaEstado);

						// Pega O Valor Do Option Selecionado
						var statusUsuario = $('#slc_Status').attr('selected',
								true).val();
						// alert(statusEstado);

						if (operacao == "update") {
							var idUsuario = $('#tf_Id').val();

							var usuario = {
								"idUsuario" : idUsuario,
								"nomeUsuario" : nomeUsuario,
								"senhaUsuario" : senhaUsuario,
								"statusUsuario" : statusUsuario
							};
						} else if (operacao == "insert") {
							var usuario = {
								"nomeUsuario" : nomeUsuario,
								"senhaUsuario" : senhaUsuario,
								"statusUsuario" : statusUsuario
							};
						}
						// console.log(estado.idEstado);
						return usuario;
					}
					;

					// Chama O Servlet Para Update
					function callUpdateServlet(usuario) {
						$.ajax({
							url : 'CRUDUsuarioServlet',
							type : 'POST',
							data : {
								'operation' : 'update',
								'id-usuario' : usuario.idUsuario,
								'nome-usuario' : usuario.nomeUsuario,
								'senha-usuario' : usuario.senhaUsuario,
								'status-usuario' : usuario.statusUsuario
							},
							success : function(response) {
								alert("Atualizado Com Sucesso");
								clearFields();
								fillTable();
							}
						})
					}
					;

					// Chama O Servlet Para Remoção
					function callRemoveServlet(event) {
						var row = "#" + $(this).parent().parent().attr('id');
						var idUsuario = $(row).find("td:eq(0)").text();
						var nomeUsuario = $(row).find("td:eq(1)").text();

						var confirmacao = confirm("Deseja Excluir O Usuário: "
								+ nomeUsuario + " ?");

						if (confirmacao == true) {
							$.ajax({
								url : 'CRUDUsuarioServlet',
								type : 'POST',
								data : {
									'operation' : 'delete',
									'id-usuario' : idUsuario,
								},
								success : function(response) {
									alert("Removido Com Sucesso");
									clearFields();
									fillTable();
								}
							})
						}

					}
					;

					// Chama O Servlet De Inserção
					function callInsertServlet(usuario) {
						$.ajax({
							url : 'CRUDUsuarioServlet',
							type : 'POST',
							data : {
								'operation' : 'insert',
								'nome-usuario' : usuario.nomeUsuario,
								'senha-usuario' : usuario.senhaUsuario,
								'status-usuario' : usuario.statusUsuario
							},
							success : function(response) {
								alert("Usuário Cadastrado Com Sucesso");
								clearFields();
								fillTable();
							}
						})
					}

					// verifica Qual Operação Será Necessária E Efetua
					function doOperation() {
						var inputId = $('#tf_Id').val();

						// Verifica Se O Campo InputID é Vazio
						if (inputId == "") {
							console.log("Insert Servlet Call");
							// Passa o Valor Insert para O Método Decidir Qual
							// Operacao
							// Será feita
							callInsertServlet(getInputData("insert"));
						} else if (inputId != "") {
							console.log("Update Servlet Call");
							callUpdateServlet(getInputData("update"));
						}

					}

				
				
				});

