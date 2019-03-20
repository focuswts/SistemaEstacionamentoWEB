$(document).ready(
		function() {

			fillTable();

			function fillTable() {
				clearTable();
				$.ajax({
					url : 'CRUDTipoVeiculoServlet',
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

					var row1 = $("<td>" + value.idTipoV + "</td>");

					var row2 = $("<td>" + value.descTipoV + "</td>");

					var row3 = $("<td>" + value.statusTipoV + "</td>");


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

				var idTipoV = $(row).find("td:eq(0)").text();

				var descTipoV = $(row).find("td:eq(1)").text();

				var statusTipoV = $(row).find("td:eq(2)").text();

				console.log("Status: " + statusTipoV);

				$('#tf_Id').val(idTipoV);
				$('#tf_Descricao').val(descTipoV);

				if (statusTipoV.toLowerCase() === "ativo") {
					$('#slc_Status').val(0);
				} else if (statusTipoV.toLowerCase() === "inativo") {
					$('#slc_Status').val(1);
				}

				// Acessa O Componente Filho Do Select(OPTION)
				// roda a funcao para separar o valor(value) do conteudo do
				// OPTION
				$.each($('#slc_status').children(), function(k, v) {
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

				var button = $('#btn-operation');
				if ($('#tf_Id').val() != null) {
					button.val("Atualizar Tipo Veículo");
				} else if ($('#tf_Id').val() == null) {
					button.val("Inserir Tipo Veículo");
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
				$('#btn-operation').val("Inserir Tipo Veículo");
			}
			;

			// Pega Os Valores Dos Campos Input
			function getInputData(operacao) {

				var descTipoV = $('#tf_Descricao').val();
				// alert(nomeEstado);

				// Pega O Valor Do Option Selecionado
				var statusTipoV = $('#slc_Status').attr('selected', true)
						.val();
				// alert(statusEstado);

				if (operacao == "update") {
					var idTipoV = $('#tf_Id').val();

					var tipoV = {
						"idTipoV" : idTipoV,
						"descTipoV" : descTipoV,
						"statusTipoV" : statusTipoV
					};
				} else if (operacao == "insert") {
					var tipoV = {
							"descTipoV" : descTipoV,
							"statusTipoV" : statusTipoV
					};
				}
				// console.log(estado.idEstado);
				return tipoV;
			}
			;

			// Chama O Servlet Para Update
			function callUpdateServlet(tipoV) {
				$.ajax({
					url : 'CRUDTipoVeiculoServlet',
					type : 'POST',
					data : {
						'operation' : 'update',
						'id-tipoV' : tipoV.idTipoV,
						'desc-tipoV' : tipoV.descTipoV,
						'status-tipoV' : tipoV.statusTipoV
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
				var idTipoV = $(row).find("td:eq(0)").text();
				var descTipoV = $(row).find("td:eq(1)").text();

				var confirmacao = confirm("Deseja Excluir O Tipo: "
						+ descTipoV + " ?");

				if (confirmacao == true) {
					$.ajax({
						url : 'CRUDTipoVeiculoServlet',
						type : 'POST',
						data : {
							'operation' : 'delete',
							'id-tipoV' : idTipoV,
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
			function callInsertServlet(tipoV) {
				$.ajax({
					url : 'CRUDTipoVeiculoServlet',
					type : 'POST',
					data : {
						'operation' : 'insert',
						'desc-tipoV' : tipoV.descTipoV,
						'status-tipoV' : tipoV.statusTipoV
					},
					success : function(response) {
						alert("Tipo Cadastrado Com Sucesso!");
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