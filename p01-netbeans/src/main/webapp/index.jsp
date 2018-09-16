<html>

<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>

<body>
	<section>
		<div class="container">
			<div class="row" >
				<h2 style="margin-left: 30%;margin-top: 40px;margin-bottom: 40px;">Tabla de multiplicar</h2>
			</div>
			<div class="row">
				<table class="table">
					<tbody>
						<%
				for (int i  = 1; i < 9; i++){
					%>
							<tr style="text-align: center;font-weight: bold;">
								<%
					for(int j = 1; j < 9; j++){
						%>
									<td>
										<%= j * i %>
									</td>
									<%
					}
					%>
							</tr>
							<%
				}
				%>
					</tbody>
				</table>
			</div>
		</div>
	</section>
</body>

</html>