$(document)
		.ready(
				function() {

					fillTable();

					function fillTable() {
						clearTable();
						$.ajax({
							url : 'CRUDVeiculoServlet',
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
					;

					// Renderiza A Table Com As Cidades
					function render(data) {
						$('#slc_Modelo').children().remove();
						var tabela = $('#tableDiv').append(
								$('<table></table>').prop("id", "tabela"));
						var tHead = "<thead id='tableHead'></thead>";
						$('#tabela').append(tHead);
						var rowH = "<tr> " + "<th>ID</th>" + "<th>Modelo</th>"
								+ "<th>Cor</th>" + "<th>Placa</th>"
								+ "<th>Status</th>" + "<th>Actions</th>"
								+ "</tr>";
						$('#tableHead').append(rowH);

						$('#tabela').addClass("responsive-table");
						$('#tabela').append(
								$('<tbody></tbody>').attr("id", "tabelaBody"));

						$.each(
										data,
										function(index, value) {

											var td = $("<tr id= 'row" + index
													+ "'>" + "</tr>");

											var row1 = $("<td>"
													+ value.idVeiculo + "</td>");

											var row2 = $("<td>"
													+ value.idModelo.descModelo
													+ "</td>");

											var row3 = $("<td>"
													+ value.corVeiculo
													+ "</td>");

											var row4 = $("<td>"
													+ value.placaVeiculo
													+ "</td>");

											var row5 = $("<td>"
													+ value.statusVeiculo
													+ "</td>");

											// Efetua A Funcao Ao Clicar No
											// Botao
											// btn-update
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

											var row6 = $("<td></td>");
											row6.append(buttonUpdate);
											row6.append(buttonRemove);

											td.append(row1);
											td.append(row2);
											td.append(row3);
											td.append(row4);
											td.append(row5);
											td.append(row6);

											$('#tabela tbody').append(td);

											$('#set')
													.append('</tbody></table>');

										})

						// Preenche O Select Modeço
						getModelos();

					}
					;

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

					// Pega As Marcas E Popula O Select
					function getModelos() {
						$.ajax({
							url : 'CRUDModeloServlet',
							type : 'POST',
							data : {
								"operation" : "list"
							},
							success : function(response) {
								// alert(JSON.stringify(response));
								fillSelectModelos(response);
							}
						})
					}
					;

					function fillSelectModelos(modelo) {

						// Percorre Cada Estado E Adiciona Ao Select
						$.each(modelo, function(index, value) {
							// Faz O Preenchimento Do Select Estado
							var opt = $("<option value ='" + value.idModelo
									+ "'>" + value.descModelo + "</option>");
							$('#slc_Modelo').append(opt);
						})
						$('select').formSelect();
					}
					;

					// Efetua A Funcao Ao Clicar no Botao btn-operation
					$(function() {
						
						$("#btn-operation").click(function() {
							if (checkInputs() == true) {
								doOperation();
							} else {
								alert("Preencha Os Campos Faltando");
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

					// Define Os Valores Da Tabela Para O Input (UPDATE ACTION)
					function setInputData(event) {

						// console.log($('#row'));
						clearFields();
						var row = "#" + $(this).parent().parent().attr('id');

						console.log(row);

						var idVeiculo = $(row).find("td:eq(0)").text();

						var descModelo = $(row).find("td:eq(1)").text();

						var corVeiculo = $(row).find("td:eq(2)").text();

						var placaVeiculo = $(row).find("td:eq(3)").text();

						var statusVeiculo = $(row).find("td:eq(4)").text();

						console.log("Status: " + statusVeiculo);

						$('#tf_Id').val(idVeiculo);
						$('#tf_Placa').val(placaVeiculo);

						// Define O Valor Do Status Como Numero Para
						if (corVeiculo.toLowerCase() === "preto") {
							$('#slc_Cor').val(0);
						} else if (corVeiculo.toLowerCase() === "branco") {
							$('#slc_Cor').val(1);
						} else if (corVeiculo.toLowerCase() === "prata") {
							$('#slc_Cor').val(2);
						} else if (corVeiculo.toLowerCase() === "vermelho") {
							$('#slc_Cor').val(3);
						}

						// Define O Valor Do Status Como Numero Para
						if (statusVeiculo.toLowerCase() === "ativo") {
							$('#slc_Status').val(0);
						} else if (statusVeiculo.toLowerCase() === "inativo") {
							$('#slc_Status').val(1);
						}

						// Define O Valor Da Marca No Select
						$.each($('#slc_Modelo').children(), function(k, v) {
							// Define O Valor Do Option Para False Para Depois
							// Definir
							// Um Valor Como TRUE
							$(v).prop('selected', false);

							// Efetua A Comparação Para Ver Se O Valor Da Table
							// é igual
							// Ao valor do OPTION
							if ($(v).text().toLowerCase() === descModelo
									.toLowerCase()) {
								$(v).prop('selected', true);
							}
						});

						// Define O Valor Do TipoVeiculo No Select
						$.each($('#slc_Cor').children(), function(k, v) {
							// Define O Valor Do Option Para False Para
							// Depois
							// Definir
							// Um Valor Como TRUE
							$(v).prop('selected', false);

							// Efetua A Comparação Para Ver Se O Valor
							// Da Table
							// é igual
							// Ao valor do OPTION
							if ($(v).text().toLowerCase() === corVeiculo
									.toLowerCase()) {
								$(v).prop('selected', true);
								$(v).val();
							}
						});

						// Acessa O Componente Filho Do Select(OPTION)
						// roda a funcao para separar o valor(value) do conteudo
						// do
						// OPTION
						$.each($('#slc_Status').children(), function(k, v) {
							// Define O Valor Do Option Para False Para Depois
							// Definir
							// Um Valor Como TRUE
							$(v).prop('selected', false);

							// Efetua A Comparação Para Ver Se O Valor Da Table
							// é igual
							// Ao valor do OPTION
							if ($(v).text().toLowerCase() === statusVeiculo
									.toLowerCase()) {
								$(v).prop('selected', true);
							}
						});

						var button = $('#btn-operation-span');
						if ($('#tf_Id').val() != "") {
							button.text("Atualizar Veículo");
						} else if ($('#tf_Id').val() == "") {
							button.text("Inserir Veículo");
						}

						// Atualiza Os Componentes SELECT Materialize
						$('#slc_Cor').formSelect();
						$('#slc_Status').formSelect();
						$('#slc_Modelo').formSelect();

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
						$('#tf_Placa').val(null);
						$('#slc_Modelo').val(0);
						$('#slc_Cor').val(0);
						$('#slc_Status').val(0);
						$('#btn-operation-span').text("Criar Veículo");
					}
					;

					// Pega Os Valores Dos Campos Input
					function getInputData(operacao) {

						var placaVeiculo = $('#tf_Placa').val();
						// alert(nomeEstado);

						// Pega O Valor Do Option Sigla
						// var idEstado = $('#slc_Estado').attr('selected',
						// true).val();

						var idModelo = $('#slc_Modelo').children(
								"option:selected").val();

						var corVeiculo = $('#slc_Cor').children(
								"option:selected").val();

						// Pega O Valor Do Option Status Selecionado
						var statusVeiculo = $('#slc_Status').attr('selected',
								true).val();
						// alert(statusEstado);

						if (operacao == "update") {
							var idVeiculo = $('#tf_Id').val();

							var veiculo = {
								"idVeiculo" : idVeiculo,
								"placaVeiculo" : placaVeiculo,
								"idModelo" : idModelo,
								"corVeiculo" : corVeiculo,
								"statusVeiculo" : statusModelo
							};
						} else if (operacao == "insert") {
							var veiculo = {
								"placaVeiculo" : placaVeiculo,
								"idModelo" : idModelo,
								"corVeiculo" : corVeiculo,
								"statusVeiculo" : statusVeiculo
							};
						}
						// console.log(estado.idEstado);
						return veiculo;
					}
					;

					// Chama O Servlet Para Update
					function callUpdateServlet(veiculo) {
						$.ajax({
							url : 'CRUDVeiculoServlet',
							type : 'POST',
							data : {
								'operation' : 'update',
								'id-veiculo' : veiculo.idVeiculo,
								'placa-veiculo' : veiculo.placaVeiculo,
								'id-modelo' : veiculo.idModelo,
								'cor-veiculo' : veiculo.corVeiculo,
								'status-veiculo' : veiculo.statusVeiculo
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
						var idVeiculo = $(row).find("td:eq(0)").text();
						var placaVeiculo = $(row).find("td:eq(3)").text();

						var confirmacao = confirm("Deseja Excluir O Veiculo De Placa: "
								+ placaVeiculo + " ?");

						if (confirmacao == true) {
							$.ajax({
								url : 'CRUDVeiculoServlet',
								type : 'POST',
								data : {
									'operation' : 'delete',
									'id-veiculo' : idVeiculo,
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
					function callInsertServlet(veiculo) {
						$.ajax({
							url : 'CRUDVeiculoServlet',
							type : 'POST',
							data : {
								'operation' : 'insert',
								'placa-veiculo' : veiculo.placaVeiculo,
								'id-modelo' : veiculo.idModelo,
								'cor-veiculo' : veiculo.corVeiculo,
								'status-veiculo' : veiculo.statusVeiculo
							},
							success : function(response) {
								alert("Veículo Cadastrado Com Sucesso!");
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