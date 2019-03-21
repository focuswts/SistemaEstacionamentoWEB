$(document).ready(
		function() {

			fillTable();

			function fillTable() {
				clearTable();
				$.ajax({
					url : 'CRUDVagaServlet',
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
				var rowH = "<tr> " + "<th>ID</th>" + "<th>Descrição</th>"
						+ "<th>Status</th>" + "<th>Actions</th>" + "</tr>";
				$('#tableHead').append(rowH);

				$('#tabela').append(
						$('<tbody></tbody>').attr("id", "tabelaBody"));

				$.each(data, function(index, value) {

					var td = $("<tr id= 'row" + index + "'>" + "</tr>");

					var row1 = $("<td>" + value.idVaga + "</td>");

					var row2 = $("<td>" + value.descVaga + "</td>");

					var row3 = $("<td>" + value.statusVaga + "</td>");


					// Efetua A Funcao Ao Clicar No Botao btn-update
					var buttonUpdate = $('<button>Modificar</button>').attr(
							"id", "btn-update-" + index).click({
						p1 : this
					}, setInputData);

					var buttonRemove = $('<button>Remover</button>').attr("id",
							"btn-remove-" + index).click({
						p1 : this
					}, callRemoveServlet);

					var row4 = $("<td></td>");
					row4.append(buttonUpdate);
					row4.append(buttonRemove);

					td.append(row1);
					td.append(row2);
					td.append(row3);
					td.append(row4);

					$('#tabela tbody').append(td);

					$('#set').append('</tbody></table>');

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

			// Define Os Valores Do Input Para Update
			function setInputData(event) {

				// console.log($('#row'));
				clearFields();
				var row = "#" + $(this).parent().parent().attr('id');

				var idVaga = $(row).find("td:eq(0)").text();

				var descVaga = $(row).find("td:eq(1)").text();

				var statusVaga = $(row).find("td:eq(2)").text();

				console.log("Status: " + statusVaga);

				$('#tf_Id').val(idVaga);
				$('#tf_Descricao').val(descVaga);

				if (statusVaga.toLowerCase() === "ativo") {
					$('#slc_Status').val(0);
				} else if (statusVaga.toLowerCase() === "inativo") {
					$('#slc_Status').val(1);
				}

				// Acessa O Componente Filho Do Select(OPTION)
				// roda a funcao para separar o valor(value) do conteudo do
				// OPTION
				$.each($('#slc_Status').children(), function(k, v) {
					// Define O Valor Do Option Para False Para Depois Definir
					// Um Valor Como TRUE
					$(v).attr('selected', false);

					// Efetua A Comparação Para Ver Se O Valor Da Table é igual
					// Ao valor do OPTION
					if ($(v).text().toLowerCase() === statusVaga
							.toLowerCase()) {
						$(v).attr('selected', true);
					}
				});

				var button = $('#btn-operation');
				if ($('#tf_Id').val() != null) {
					button.val("Atualizar Vaga");
				} else if ($('#tf_Id').val() == null) {
					button.val("Inserir Vaga");
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
				$('#slc_Status').val(0);
				$('#btn-operation').val("Inserir Vaga");
			}
			;

			// Pega Os Valores Dos Campos Input
			function getInputData(operacao) {

				var descVaga = $('#tf_Descricao').val();
				// alert(nomeEstado);

				// Pega O Valor Do Option Selecionado
				var statusVaga = $('#slc_Status').attr('selected', true)
						.val();
				// alert(statusVaga);

				if (operacao == "update") {
					var idVaga = $('#tf_Id').val();

					var vaga = {
						"idVaga" : idVaga,
						"descVaga" : descVaga,
						"statusVaga" : statusVaga
					};
				} else if (operacao == "insert") {
					var vaga = {
							"descVaga" : descVaga,
							"statusVaga" : statusVaga
					};
				}
				// console.log(estado.idEstado);
				return vaga;
			}
			;

			// Chama O Servlet Para Update
			function callUpdateServlet(vaga) {
				$.ajax({
					url : 'CRUDVagaServlet',
					type : 'POST',
					data : {
						'operation' : 'update',
						'id-vaga' : vaga.idVaga,
						'desc-vaga' : vaga.descVaga,
						'status-vaga' : vaga.statusVaga
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
				var idVaga = $(row).find("td:eq(0)").text();
				var descVaga = $(row).find("td:eq(1)").text();

				var confirmacao = confirm("Deseja Excluir A Vaga: "
						+ descVaga + " ?");

				if (confirmacao == true) {
					$.ajax({
						url : 'CRUDVagaServlet',
						type : 'POST',
						data : {
							'operation' : 'delete',
							'id-vaga' : idVaga,
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
			function callInsertServlet(vaga) {
				$.ajax({
					url : 'CRUDVagaServlet',
					type : 'POST',
					data : {
						'operation' : 'insert',
						'desc-vaga' : vaga.descVaga,
						'status-vaga' : vaga.statusVaga
					},
					success : function(response) {
						alert("Vaga Cadastrada Com Sucesso!");
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
					// Passa o Valor Insert para O Método Decidir Qual Operacao
					// Será feita
					callInsertServlet(getInputData("insert"));
				} else if (inputId != "") {
					console.log("Update Servlet Call");
					callUpdateServlet(getInputData("update"));
				}

			}

		});