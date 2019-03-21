$(document)
		.ready(
				function() {

					fillTable();

					function fillTable() {
						clearTable();
						$.ajax({
							url : 'CRUDModeloServlet',
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
						$('#slc_Marca').children().remove();
						$('#slc_TipoVeiculo').children().remove();
						var tabela = $('#tableDiv').append(
								$('<table></table>').attr("id", "tabela"));
						var tHead = "<thead id='tableHead'></thead>";
						$('#tabela').append(tHead);
						var rowH = "<tr> " + "<th>ID</th>" + "<th>Marca</th>"
								+ "<th>Tipo Veiculo</th>" + "<th>Modelo</th>"
								+ "<th>Status</th>" + "<th>Actions</th>"
								+ "</tr>";
						$('#tableHead').append(rowH);

						$('#tabela').append(
								$('<tbody></tbody>').attr("id", "tabelaBody"));

						$.each(data, function(index, value) {

											var td = $("<tr id= 'row" + index
													+ "'>" + "</tr>");

											var row1 = $("<td>"
													+ value.idModelo + "</td>");

											var row2 = $("<td>"
													+ value.idMarca.descMarca
													+ "</td>");

											var row3 = $("<td>"
													+ value.idTipoV.descTipoV
													+ "</td>");

											var row4 = $("<td>"
													+ value.descModelo
													+ "</td>");

											var row5 = $("<td>"
													+ value.statusModelo
													+ "</td>");

											// Efetua A Funcao Ao Clicar No
											// Botao
											// btn-update
											var buttonUpdate = $(
													'<button>Modificar</button>')
													.attr(
															"id",
															"btn-update-"
																	+ index)
													.click({
														p1 : this
													}, setInputData);

											var buttonRemove = $(
													'<button>Remover</button>')
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

						// Preenche O Select Marca
						getMarcas();

						// Preenche O Select TipoVeiculo
						getTiposVeiculos();

					}
					;

					// Pega As Marcas E Popula O Select
					function getMarcas() {
						$.ajax({
							url : 'CRUDMarcaServlet',
							type : 'POST',
							data : {
								"operation" : "list"
							},
							success : function(response) {
								// alert(JSON.stringify(response));
								fillSelectMarcas(response);
							}
						})
					}
					;

					function fillSelectMarcas(marca) {

						// Percorre Cada Estado E Adiciona Ao Select
						$.each(marca, function(index, value) {
							// Faz O Preenchimento Do Select Estado
							var opt = $("<option value ='" + value.idMarca
									+ "'>" + value.descMarca + "</option>");
							$('#slc_Marca').append(opt);
						})
					}
					;

					// Pega Os TiposVeiculos E Preenche O Select
					function getTiposVeiculos() {
						$.ajax({
							url : 'CRUDTipoVeiculoServlet',
							type : 'POST',
							data : {
								"operation" : "list"
							},
							success : function(response) {
								// alert(JSON.stringify(response));
								fillSelectTiposVeiculos(response);

							}
						})
					}
					;

					function fillSelectTiposVeiculos(tipoVeiculo) {

						// Percorre Cada Estado E Adiciona Ao Select
						$.each(tipoVeiculo, function(index, value) {
							// Faz O Preenchimento Do Select Estado
							var opt = $("<option value ='" + value.idTipoV
									+ "'>" + value.descTipoV + "</option>");
							$('#slc_TipoVeiculo').append(opt);
						})
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

					// Define Os Valores Da Tabela Para O Input (UPDATE ACTION)
					function setInputData(event) {

						// console.log($('#row'));
						clearFields();
						var row = "#" + $(this).parent().parent().attr('id');

						console.log(row);

						var idModelo = $(row).find("td:eq(0)").text();

						var descMarca = $(row).find("td:eq(1)").text();

						var descTipoV = $(row).find("td:eq(2)").text();

						var descModelo = $(row).find("td:eq(3)").text();

						var statusModelo = $(row).find("td:eq(4)").text();

						console.log("Status: " + statusModelo);

						$('#tf_Id').val(idModelo);
						$('#tf_Descricao').val(descModelo);

						// Define O Valor Do Status Como Numero Para
						if (statusModelo.toLowerCase() === "ativo") {
							$('#slc_Status').val(0);
						} else if (statusModelo.toLowerCase() === "inativo") {
							$('#slc_Status').val(1);
						}

						// Define O Valor Da Marca No Select
						$.each($('#slc_Marca').children(), function(k, v) {
							// Define O Valor Do Option Para False Para Depois
							// Definir
							// Um Valor Como TRUE
							$(v).attr('selected', false);

							// Efetua A Comparação Para Ver Se O Valor Da Table
							// é igual
							// Ao valor do OPTION
							if ($(v).text().toLowerCase() === descMarca
									.toLowerCase()) {

								$(v).attr('selected', true);

							}
						});

						// Define O Valor Do TipoVeiculo No Select
						$.each($('#slc_TipoVeiculo').children(),
								function(k, v) {
									// Define O Valor Do Option Para False Para
									// Depois
									// Definir
									// Um Valor Como TRUE
									$(v).attr('selected', false);

									// Efetua A Comparação Para Ver Se O Valor
									// Da Table
									// é igual
									// Ao valor do OPTION
									if ($(v).text().toLowerCase() === descTipoV
											.toLowerCase()) {

										$(v).attr('selected', true);

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
							$(v).attr('selected', false);

							// Efetua A Comparação Para Ver Se O Valor Da Table
							// é igual
							// Ao valor do OPTION
							if ($(v).text().toLowerCase() === statusModelo
									.toLowerCase()) {
								$(v).attr('selected', true);
							}
						});

						var button = $('#btn-operation');
						if ($('#tf_Id').val() != "") {
							button.val("Atualizar Modelo");
						} else if ($('#tf_Id').val() == "") {
							button.val("Inserir Modelo");
						}

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
						$('#tf_Descricao').val(null);
						$('#slc_Marca').val(0);
						$('#slc_TipoVeiculo').val(0);
						$('#slc_Status').val(0);
						$('#btn-operation').val("Criar Modelo");
					}
					;

					// Pega Os Valores Dos Campos Input
					function getInputData(operacao) {

						var descModelo = $('#tf_Descricao').val();
						// alert(nomeEstado);

						// Pega O Valor Do Option Sigla
						// var idEstado = $('#slc_Estado').attr('selected',
						// true).val();

						var idMarca = $('#slc_Marca').children(
								"option:selected").val();

						var idTipoV = $('#slc_TipoVeiculo').children(
								"option:selected").val();

						// Pega O Valor Do Option Status Selecionado
						var statusModelo = $('#slc_Status').attr('selected',
								true).val();
						// alert(statusEstado);

						if (operacao == "update") {
							var idModelo = $('#tf_Id').val();

							var modelo = {
								"idModelo" : idModelo,
								"descModelo" : descModelo,
								"idMarca" : idMarca,
								"idTipoV" : idTipoV,
								"statusModelo" : statusModelo
							};
						} else if (operacao == "insert") {
							var modelo = {
								"descModelo" : descModelo,
								"idMarca" : idMarca,
								"idTipoV" : idTipoV,
								"statusModelo" : statusModelo
							};
						}
						// console.log(estado.idEstado);
						return modelo;
					}
					;

					// Chama O Servlet Para Update
					function callUpdateServlet(modelo) {
						$.ajax({
							url : 'CRUDModeloServlet',
							type : 'POST',
							data : {
								'operation' : 'update',
								'id-modelo' : modelo.idModelo,
								'desc-modelo' : modelo.descModelo,
								'id-marca' : modelo.idMarca,
								'id-tipoV' : modelo.idTipoV,
								'status-modelo' : modelo.statusModelo
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
						var idModelo = $(row).find("td:eq(0)").text();
						var descModelo = $(row).find("td:eq(3)").text();

						var confirmacao = confirm("Deseja Excluir O Modelo: "
								+ descModelo + " ?");

						if (confirmacao == true) {
							$.ajax({
								url : 'CRUDModeloServlet',
								type : 'POST',
								data : {
									'operation' : 'delete',
									'id-modelo' : idModelo,
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
					function callInsertServlet(modelo) {
						$.ajax({
							url : 'CRUDModeloServlet',
							type : 'POST',
							data : {
								'operation' : 'insert',
								'desc-modelo' : modelo.descModelo,
								'id-marca' : modelo.idMarca,
								'id-tipoV' : modelo.idTipoV,
								'status-modelo' : modelo.statusModelo
							},
							success : function(response) {
								alert("Modelo Cadastrado Com Sucesso!");
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