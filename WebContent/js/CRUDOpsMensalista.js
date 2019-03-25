$(document)
		.ready(
				function() {

					fillTable();

					function fillTable() {
						clearTable();
						$.ajax({
							url : 'CRUDMensalistaServlet',
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
						var rowH = "<tr> " + "<th>ID</th>"
								+ "<th>Mensalista</th>" + "<th>CPF</th>"
								+ "<th>Status</th>" + "<th>Actions</th>"
								+ "</tr>";
						$('#tableHead').append(rowH);

						$('#tabela').append(
								$('<tbody></tbody>').attr("id", "tabelaBody"));

						$.each(
										data,
										function(index, value) {

											var td = $("<tr id= 'row" + index
													+ "'>" + "</tr>");

											var row1 = $("<td>"
													+ value.idMensalista
													+ "</td>");

											var row2 = $("<td>"
													+ value.nomeMensalista
													+ "</td>");

											var row3 = $("<td>"
													+ value.cpfMensalista
													+ "</td>");

											var row4 = $("<td>"
													+ value.statusMensalista
													+ "</td>");

											// Efetua A Funcao Ao Clicar No
											// Botao btn-update
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

					// Efetua A Funcao Ao Clicar no Botao btn-operation
					$(function() {
						$("#btn-operation").click(function() {
							doOperation();
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

						var idMensalista = $(row).find("td:eq(0)").text();

						var nomeMensalista = $(row).find("td:eq(1)").text();

						var cpfMensalista = $(row).find("td:eq(2)").text();

						var statusMensalista = $(row).find("td:eq(3)").text();

						console.log("Status: " + statusMensalista);

						$('#tf_Id').val(idMensalista);
						$('#tf_Nome').val(nomeMensalista);
						$('#tf_Cpf').val(cpfMensalista);

						if (statusMensalista.toLowerCase() === "ativo") {
							$('#slc_Status').val(0);
						} else if (statusMensalista.toLowerCase() === "inativo") {
							$('#slc_Status').val(1);
						}

						// Acessa O Componente Filho Do Select(OPTION)
						// roda a funcao para separar o valor(value) do conteudo
						// do
						// OPTION
						$.each($('#slc_Status').children(), function(k, v) {
							// Define O Valor Do Option Para False Para Depois
							// Definir
							// Um Valor Como TRUE
							$(v).attr('selected', false);

							// Efetua A Comparação Para Ver Se O Valor Da Table
							// é igual
							// Ao valor do OPTION
							if ($(v).text().toLowerCase() === statusMensalista
									.toLowerCase()) {
								$(v).attr('selected', true);
							}
						});

						var button = $('#btn-operation');
						if ($('#tf_Id').val() != null) {
							button.text("Atualizar Mensalista");
						} else if ($('#tf_Id').val() == null) {
							button.text("Inserir Mensalista");
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
						$('#tf_Cpf').val(null);
						$('#slc_Status').val(0);
						$('#btn-operation').val("Inserir Mensalista");
					}
					;

					// Pega Os Valores Dos Campos Input
					function getInputData(operacao) {

						var nomeMensalista = $('#tf_Nome').val();
						// alert(nomeEstado);

						var cpfMensalista = $('#tf_Cpf').val();

						// Pega O Valor Do Option Selecionado
						var statusMensalista = $('#slc_Status').attr(
								'selected', true).val();
						// alert(statusEstado);

						if (operacao == "update") {
							var idMensalista = $('#tf_Id').val();

							var mensalista = {
								"idMensalista" : idMensalista,
								"nomeMensalista" : nomeMensalista,
								"cpfMensalista" : cpfMensalista,
								"statusMensalista" : statusMensalista
							};
						} else if (operacao == "insert") {
							var mensalista = {
								"nomeMensalista" : nomeMensalista,
								"cpfMensalista" : cpfMensalista,
								"statusMensalista" : statusMensalista
							};
						}
						// console.log(estado.idEstado);
						return mensalista;
					}
					;

					// Chama O Servlet Para Update
					function callUpdateServlet(mensalista) {
						$
								.ajax({
									url : 'CRUDMensalistaServlet',
									type : 'POST',
									data : {
										'operation' : 'update',
										'id-mensalista' : mensalista.idMensalista,
										'nome-mensalista' : mensalista.nomeMensalista,
										'cpf-mensalista' : mensalista.cpfMensalista,
										'status-mensalista' : mensalista.statusMensalista
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
						var idMensalista = $(row).find("td:eq(0)").text();
						var nomeMensalista = $(row).find("td:eq(1)").text();

						var confirmacao = confirm("Deseja Excluir O Mensalista: "
								+ nomeMensalista + " ?");

						if (confirmacao == true) {
							$.ajax({
								url : 'CRUDMensalistaServlet',
								type : 'POST',
								data : {
									'operation' : 'delete',
									'id-mensalista' : idMensalista,
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
					function callInsertServlet(mensalista) {
						$
								.ajax({
									url : 'CRUDMensalistaServlet',
									type : 'POST',
									data : {
										'operation' : 'insert',
										'nome-mensalista' : mensalista.nomeMensalista,
										'cpf-mensalista' : mensalista.cpfMensalista,
										'status-mensalista' : mensalista.statusMensalista
									},
									success : function(response) {
										alert("Mensalista Cadastrado Com Sucesso!");
										clearFields();
										fillTable();
									}
								})
					}
					;

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
					;

				});