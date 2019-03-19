$(document)
		.ready(
				function() {

					$.ajax({
						url : 'ListarEstadoServlet',
						type : 'POST',
						success : function(response) {
							// alert(JSON.stringify(response));
							render(response);

						}
					})

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

						$
								.each(
										data,
										function(index, value) {

											var td = $("<tr id= 'row" + index
													+ "'>" + "</tr>");

											var row1 = $("<td>"
													+ value.idEstado + "</td>");

											var row2 = $("<td>"
													+ value.nomeEstado
													+ "</td>");

											var row3 = $("<td>"
													+ value.siglaEstado
													+ "</td>");

											var row4 = $("<td>"
													+ value.statusEstado
													+ "</td>");

											var button = $(
													'<button>Modificar</button>')
													.attr(
															"id",
															"btn-update-"
																	+ index)
													.click({
														p1 : this
													}, setInputData);

											var row5 = $("<td></td>");
											row5.append(button);

											td.append(row1);
											td.append(row2);
											td.append(row3);
											td.append(row4);
											td.append(row5);

											$('#tabela tbody').append(td);

											$('#set')
													.append('</tbody></table>');

										})

					}

					function setInputData(event) {

						console.log($('#row'));

						var row = "#" + $(this).parent().parent().attr('id');

						var idEstado = $(row).find("td:eq(0)").text();

						var nomeEstado = $(row).find("td:eq(1)").text();

						var siglaEstado = $(row).find("td:eq(2)").text();

						var statusEstado = $(row).find("td:eq(3)").text();

						// event.data.p1 =
						// $(row).find("td:eq(3)").children().text();


						var opt = $('#slc_status option').text();
						alert(opt);

						// alert($(this).parent().parent().attr('id'));

						clearFields();
						$('#tf_id').val(idEstado);
						$('#tf_estado').val(nomeEstado);
						$('#tf_uf').val(siglaEstado);
						$('#slc_status').val(statusEstado).attr('selected',
								'selected');

					}

					function clearFields() {
						$('#tf_id').val(null);
						$('#tf_estado').val(null);
						$('#tf_uf').val(null);
						$('#slc_status').val(null);
					}

				});