$(document).ready(
		function() {

			fillTable();

			function fillTable() {
				clearTable();
				$.ajax({
					url : 'CRUDCidadeServlet',
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
				var tabela = $('#tableDiv').append(
						$('<table></table>').attr("id", "tabela"));
				var tHead = "<thead id='tableHead'></thead>";
				$('#tabela').append(tHead);
				var rowH = "<tr> " + "<th>ID</th>" + "<th>Cidade</th>"
						+ "<th>Estado</th>" + "<th>Status</th>"
						+ "<th>Actions</th>" + "</tr>";
				$('#tableHead').append(rowH);

				$('#tabela').append(
						$('<tbody></tbody>').attr("id", "tabelaBody"));

				$
						.each(data,
								function(index, value) {

									var td = $("<tr id= 'row" + index + "'>"
											+ "</tr>");

									var row1 = $("<td>" + value.idCidade
											+ "</td>");

									var row2 = $("<td>" + value.nomeCidade
											+ "</td>");

									var row3 = $("<td>"
											+ value.idEstado.siglaEstado
											+ "</td>");

									var row4 = $("<td>" + value.statusCidade
											+ "</td>");

									// Efetua A Funcao Ao Clicar No Botao
									// btn-update
									var buttonUpdate = $(
											'<button>Modificar</button>').attr(
											"id", "btn-update-" + index).click(
											{
												p1 : this
											}, setInputData);

									var buttonRemove = $(
											'<button>Remover</button>').attr(
											"id", "btn-remove-" + index).click(
											{
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

									$('#set').append('</tbody></table>');
									// Preenche O Select Estado
									getEstados();

								})

			}
			;

			function getEstados() {
				$.ajax({
					url : 'ListarEstadoServlet',
					type : 'POST',
					success : function(response) {
						// alert(JSON.stringify(response));
						fillSelectEstados(response);

					}
				})
			}
			;

			function fillSelectEstados(estado) {

				// Percorre Cada Estado E Adiciona Ao Select
				$.each(estado, function(index, value) {
					// Faz O Preenchimento Do Select Estado
					var opt = $("<option value ='" + value.idEstado + "'>"
							+ value.siglaEstado + "</option>");
					$('#slc_Estado').append(opt);
				})

			}
			;

			// Efetua A Funcao Ao Clicar no Botao btn-operation
			$(function() {
				$("#btn-operation").click(function() {
					doOperation();
				});
			});

			// Efetua A Funcao Limpar Campos Ao Clicar No Botao LimparCampos
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

				var idCidade = $(row).find("td:eq(0)").text();

				var nomeCidade = $(row).find("td:eq(1)").text();

				var siglaEstado = $(row).find("td:eq(2)").text();

				var statusCidade = $(row).find("td:eq(3)").text();

				console.log("Status: " + statusCidade);

				$('#tf_Id').val(idCidade);
				$('#tf_Descricao').val(nomeCidade);

				// Define O Valor Do Status Como Numero Para
				if (statusCidade.toLowerCase() === "ativo") {
					$('#slc_Status').val(0);
				} else if (statusCidade.toLowerCase() === "inativo") {
					$('#slc_Status').val(1);
				}

				// Define O Valor Da Sigla No Select
				$.each($('#slc_Estado').children(),
						function(k, v) {
							// Define O Valor Do Option Para False Para Depois
							// Definir
							// Um Valor Como TRUE
							$(v).attr('selected', false);


							// Efetua A Comparação Para Ver Se O Valor Da Table
							// é igual
							// Ao valor do OPTION
							console.log("VText: " + $(v).text().toLowerCase() + " Sigla: " + siglaEstado.toLowerCase());
							console.log($(v));
							if ($(v).text().toLowerCase() === siglaEstado.toLowerCase()) {
								
								$(v).attr('selected', true);
								
							}
						});

				// Acessa O Componente Filho Do Select(OPTION)
				// roda a funcao para separar o valor(value) do conteudo do
				// OPTION
				$.each($('#slc_Status').children(), function(k, v) {
					// Define O Valor Do Option Para False Para Depois
					// Definir
					// Um Valor Como TRUE
					$(v).attr('selected', false);

					// Efetua A Comparação Para Ver Se O Valor Da Table
					// é igual
					// Ao valor do OPTION
					if ($(v).text().toLowerCase() === statusCidade
							.toLowerCase()) {
						$(v).attr('selected', true);
					}
				});

				var button = $('#btn-operation');
				if ($('#tf_Id').val() != "") {
					button.val("Atualizar Cidade");
				} else if ($('#tf_Id').val() == "") {
					button.val("Inserir Cidade");
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
				$('#slc_Estado').val(0);
				$('#slc_Status').val(0);
				$('#btn-operation').val("Criar Cidade");
			}
			;

			// Pega Os Valores Dos Campos Input
			function getInputData(operacao) {

				var nomeCidade = $('#tf_Descricao').val();
				// alert(nomeEstado);

				// Pega O Valor Do Option Sigla
				// var idEstado = $('#slc_Estado').attr('selected', true).val();

				var idEstado = $('#slc_Estado').children("option:selected")
						.val();
				console.log("IDESTADO: " + idEstado);

				// Pega O Valor Do Option Status Selecionado
				var statusCidade = $('#slc_Status').attr('selected', true)
						.val();
				// alert(statusEstado);

				if (operacao == "update") {
					var idCidade = $('#tf_Id').val();

					var cidade = {
						"idCidade" : idCidade,
						"nomeCidade" : nomeCidade,
						"idEstado" : idEstado,
						"statusCidade" : statusCidade
					};
				} else if (operacao == "insert") {
					var cidade = {
						"nomeCidade" : nomeCidade,
						"idEstado" : idEstado,
						"statusCidade" : statusCidade
					};
				}
				// console.log(estado.idEstado);
				return cidade;
			}
			;

			// Chama O Servlet Para Update
			function callUpdateServlet(cidade) {
				$.ajax({
					url : 'CRUDCidadeServlet',
					type : 'POST',
					data : {
						'operation' : 'update',
						'id-cidade' : cidade.idCidade,
						'nome-cidade' : cidade.nomeCidade,
						'id-estado' : cidade.idEstado,
						'status-cidade' : cidade.statusCidade
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
				var idCidade = $(row).find("td:eq(0)").text();
				var nomeCidade = $(row).find("td:eq(1)").text();

				var confirmacao = confirm("Deseja Excluir A Cidade: "
						+ nomeCidade + " ?");

				if (confirmacao == true) {
					$.ajax({
						url : 'CRUDCidadeServlet',
						type : 'POST',
						data : {
							'operation' : 'delete',
							'id-cidade' : idCidade,
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
			function callInsertServlet(cidade) {
				$.ajax({
					url : 'CRUDCidadeServlet',
					type : 'POST',
					data : {
						'operation' : 'insert',
						'nome-cidade' : cidade.nomeCidade,
						'id-estado' : cidade.idEstado,
						'status-cidade' : cidade.statusCidade
					},
					success : function(response) {
						alert("Cidade Cadastrada Com Sucesso!");
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
					// Passa o Valor Insert para O Método Decidir Qual Operacao
					// Será feita
					callInsertServlet(getInputData("insert"));
				} else if (inputId != "") {
					console.log("Update Servlet Call");
					callUpdateServlet(getInputData("update"));
				}

			}
			;

		});