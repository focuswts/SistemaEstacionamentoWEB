$(document).ready(
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
				$('#slc_Marca').children().remove();
				$('#slc_TipoVeiculo').children().remove();
				var tabela = $('#tableDiv').append(	$('<table></table>').attr("id", "tabela"));
				var tHead = "<thead id='tableHead'></thead>";
				$('#tabela').append(tHead);
				var rowH = "<tr> " + "<th>ID</th>" + "<th>Marca</th>"
						+ "<th>Tipo Veiculo</th>" + "<th>Modelo</th>"
						+ "<th>Status</th>" + "<th>Actions</th>" + "</tr>";
				$('#tableHead').append(rowH);

				$('#tabela').append(
						$('<tbody></tbody>').attr("id", "tabelaBody"));

				$.each(data, function(index, value) {

					var td = $("<tr id= 'row" + index + "'>" + "</tr>");

					var row1 = $("<td>" + value.idModelo + "</td>");

					var row2 = $("<td>" + value.idMarca.descMarca + "</td>");

					var row3 = $("<td>" + value.idTipoV.descTipoV + "</td>");

					var row4 = $("<td>" + value.descModelo + "</td>");

					var row5 = $("<td>" + value.statusModelo + "</td>");

					// Efetua A Funcao Ao Clicar No
					// Botao
					// btn-update
					var buttonUpdate = $('<button>Modificar</button>').attr(
							"id", "btn-update-" + index).click({
						p1 : this
					}, setInputData);

					var buttonRemove = $('<button>Remover</button>').attr("id",
							"btn-remove-" + index).click({
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

					$('#set').append('</tbody></table>');

				})

				// Preenche O Select Marca
				getMarcas();

				// Preenche O Select TipoVeiculo
				getTiposVeiculos();

			}
			;

		});