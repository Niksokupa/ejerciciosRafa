<html>

<head>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<link rel="stylesheet" href="./css.css">
</head>

<body>
	<section>
		<div class="container">

			<div class="row">

				<%
					if (request.getParameter("ancho") == "" || request.getParameter("alto") == "") {
				%>
				<h1 id="textoError">Error inserta un ancho y un alto</h1>
				<a href="index.html">
					<button class="btn btn-primary">Volver</button>
				</a>
				<%
					}else{
					int ancho = Integer.parseInt(request.getParameter("ancho"));
					int alto = Integer.parseInt(request.getParameter("alto"));
					if (ancho < 0 || alto < 0) {
				%>
				<h1 id="textoError">Error inserta un ancho y un alto positivos</h1>
				<a href="index.html">
					<button class="btn btn-primary">Volver</button>
				</a>
				<%
					} else if (ancho == 0 || alto == 0) {
				%>
				<h1 id="textoError">Error inserta un ancho y un alto mayor que 0</h1>
				<a href="index.html">
					<button class="btn btn-primary">Volver</button>
				</a>
				<%
					} else {
				%>
				<table class="table">
					<tbody>
						<div class="row">
							<h2 style="margin-left: 30%; margin-top: 40px; margin-bottom: 40px;">Tabla
								de multiplicar</h2>
						</div>
						<%
							for (int i = 1; i <= alto; i++) {
						%>
						<tr style="text-align: center; font-weight: bold;">
							<%
								for (int j = 1; j <= ancho; j++) {
							%>
							<td>
								<%=j * i%>
							</td>
							<%
								}
							%>
						</tr>
						<%
							}
							}
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</section>
</body>

</html>