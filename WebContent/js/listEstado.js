$(document).ready(

		function() {
			// Declara O Metodo Servlet

			fillTable();

			function fillTable() {
				clearTable();
				$.ajax({
					url : 'ListarEstadoServlet',
					type : 'POST',
					success : function(response) {
						// alert(JSON.stringify(response));
						render(response);

					}
				})
			}

			// Renderiza A Table Com Os Estados
			function render(data) {
				var tabela = $('#set').append(
						$('<table></table>').attr("id", "tabela"));
				var tHead = "<thead id='tableHead'></thead>";
				$('#tabela').append(tHead);
				var rowH = "<tr> " + "<th>ID</th>" + "<th>Nome</th>"
						+ "<th>Sigla</th>" + "<th>Status</th>"
						+ "<th>Actions</th>" + "</tr>";
				$('#tableHead').append(rowH);

				$('#tabela').append(
						$('<tbody></tbody>').attr("id", "tabelaBody"));

				$.each(data, function(index, value) {

					var td = $("<tr id= 'row" + index + "'>" + "</tr>");

					var row1 = $("<td>" + value.idEstado + "</td>");

					var row2 = $("<td>" + value.nomeEstado + "</td>");

					var row3 = $("<td>" + value.siglaEstado + "</td>");

					var row4 = $("<td>" + value.statusEstado + "</td>");

					// Efetua A Funcao Ao Clicar No Botao btn-update
					var buttonUpdate = $('<button>Modificar</button>').attr(
							"id", "btn-update-" + index).click({
						p1 : this
					}, setInputData);

					var buttonRemove = $('<button>Remover</button>').attr("id",
							"btn-remove-" + index).click({
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

				})

			}
			;

			// Efetua A Funcao Ao Clicar no Botao btn-update-db
			$(function() {
				$("#btn-update-db").click(function() {
					doOperation();
				});
			});

			// Efetua A Funcao Limpar Campos Ao Clicar No Botao LimparCampos
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

				var idEstado = $(row).find("td:eq(0)").text();

				var nomeEstado = $(row).find("td:eq(1)").text();

				var siglaEstado = $(row).find("td:eq(2)").text();

				var statusEstado = $(row).find("td:eq(3)").text();

				console.log("Status: " + statusEstado);

				$('#tf_id').val(idEstado);
				$('#tf_estado').val(nomeEstado);
				$('#tf_uf').val(siglaEstado);

				if (statusEstado.toLowerCase() === "ativo") {
					$('#slc_status_update').val(0);
				} else if (statusEstado.toLowerCase() === "inativo") {
					$('#slc_status_update').val(1);
				}

				// Acessa O Componente Filho Do Select(OPTION)
				// roda a funcao para separar o valor(value) do conteudo do
				// OPTION
				$.each($('#slc_status_update').children(), function(k, v) {
					// Define O Valor Do Option Para False Para Depois Definir
					// Um Valor Como TRUE
					$(v).attr('selected', false);

					// Efetua A Comparação Para Ver Se O Valor Da Table é igual
					// Ao valor do OPTION
					if ($(v).text().toLowerCase() === statusEstado
							.toLowerCase()) {
						$(v).attr('selected', true);
					}
				});

				var button = $('#btn-update-db');
				if ($('#tf_id').val() != null) {
					button.val("Atualizar Estado");
				} else if ($('#tf_id').val() == null) {
					button.val("Inserir Estado");
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
				$('#tf_id').val(null);
				$('#tf_estado').val(null);
				$('#tf_uf').val(null);
				$('#slc_status_update').val(0);
				$('#btn-update-db').val("Inserir Estado");
			}
			;

			// Pega Os Valores Dos Campos Input
			function getInputData(operacao) {

				var nomeEstado = $('#tf_estado').val();
				// alert(nomeEstado);

				var siglaEstado = $('#tf_uf').val();
				// alert(siglaEstado);

				// Pega O Valor Do Option Selecionado
				var statusEstado = $('#slc_status_update').attr('selected',
						true).val();
				// alert(statusEstado);

				if (operacao == "update") {
					var idEstado = $('#tf_id').val();
					
					var estado = {
						"idEstado" : idEstado,
						"nomeEstado" : nomeEstado,
						"siglaEstado" : siglaEstado,
						"statusEstado" : statusEstado
					};
				} else if (operacao == "insert") {
					var estado = {
						"nomeEstado" : nomeEstado,
						"siglaEstado" : siglaEstado,
						"statusEstado" : statusEstado
					};
				}
				// console.log(estado.idEstado);
				return estado;
			}
			;

			// Chama O Servlet Para Update
			function callUpdateServlet(estado) {
				$.ajax({
					url : 'AtualizarEstadoServlet',
					type : 'POST',
					data : {
						'id-estado' : estado.idEstado,
						'nome-estado' : estado.nomeEstado,
						'sigla-estado' : estado.siglaEstado,
						'status-estado' : estado.statusEstado
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
				var idEstado = $(row).find("td:eq(0)").text();
				var nomeEstado = $(row).find("td:eq(1)").text();

				var confirmacao = confirm("Deseja Excluir O Estado: "
						+ nomeEstado + " ?");

				if (confirmacao == true) {
					$.ajax({
						url : 'RemoverEstadoServlet',
						type : 'POST',
						data : {
							'id-estado' : idEstado,
						},
						success : function(response) {
							alert("Removido Com Sucesso");
							clearFields();
							fillTable();
						}
					})
				}

			}

			// Chama O Servlet De Inserção
			function callInsertServlet(estado) {
				$.ajax({
					url : 'InserirEstadoServlet',
					type : 'POST',
					data : {
						'nome-estado' : estado.nomeEstado,
						'sigla-estado' : estado.siglaEstado,
						'status-estado' : estado.statusEstado
					},
					success : function(response) {
						alert("Estado Cadastrado Com Sucesso");
						clearFields();
						fillTable();
					}
				})
			}

			// verifica Qual Operação Será Necessária E Efetua
			function doOperation() {
				var inputId = $('#tf_id').val();

				//Verifica Se O Campo InputID é Vazio
				if (inputId == "") {
					console.log("Insert Servlet Call");
					callInsertServlet(getInputData("insert"));
				} else if (inputId != "") {
					console.log("Update Servlet Call");
					callUpdateServlet(getInputData("update"));
				}

			}

		});