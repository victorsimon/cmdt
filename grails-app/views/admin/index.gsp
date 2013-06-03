<html>
<head>
	<style>
		/*{border: 1px solid black;}*/
		body {
			font-family: Arial;
			margin:0;
			padding:0;
		}
		a:hover {
			margin-bottom: 0;
			border-bottom: 2px solid #FF8800;
			color: #777777;
		}
		a {
			text-decoration: none;
			color: #777777;
		}
		a:visited {
			color: #777777;
		}
		#menu * {
			color: #FFFFFF;
		}
		#menu {
			color: #FFFFFF;
			font-size: .9em;
			border-bottom: 1px solid #AAAAAA;
			box-shadow: 0px 2px 3px #888888;
			margin: 0;
			padding: 10px 0 10px 0;
			background-color: rgba(0,0,0,.70);
		}
		#menu ul {
			margin:0;
			padding: 0px 5px;
			display: block;
			list-style: none;
		}
		#menu ul li {
			display: inline-block;
			list-style: none;
		}
		#contenedor {
			display: block;
			width: 100%;
			height: 90% ;
			margin: 0;
			padding: 0;
		}
		#opciones {
			float: left;
			width: 20%;
		}
		#opciones-titulo {
			margin: 5px;
			padding: 5px 15px;
			color: #eee;
			font-weight: bold;
			background-color: rgba(256,88,00,.70);
			border-radius: 5px 5px 0 0;
		}
		div.opcion {
			padding: 0 10px 0 10px;
			margin-bottom: 2px;
			font-size: 1em;
		}
		div.contador {
			float: right;
			padding-right: .9em;
			color: #777777;
		}
		#principal {
			width: 80%;
			height: 450px;
			float: left;
			margin: 0;
			overflow-x: hidden;
			overflow-y: scroll;
			box-shadow: -2px 0px 3px #888888;
		}
		#principal table {
			border-collapse: collapse;
			width: 100%;
			font-size: 0.9em;
		}
		#principal tr {
			width: 100%;
			cursor: pointer;
			background-color: rgba(240, 240, 240, .85);
		}
		#principal td {
			height: 30px;
			border-top: 1px solid #AAAAAA;
			padding: 2.5px 10px;
		}
		#detalle {
			border-top: 1px solid #AAAAAA;
			box-shadow: 0px -2px 3px #888888;
			width:100%;
			float:left;
			overflow-x: hidden;
			overflow-y: scroll;
		}
	</style>
	<g:javascript library="jquery" />
	<g:javascript library="jquery-ui" />
	<r:layoutResources />
</head>
<body>
	<div id="menu">
		<ul><li><g:remoteLink action="reservas" update="contenedor">Gestión de Reservas</g:remoteLink></li></ul>
	</div>
	<div id="contenedor">
		<g:render template="reservas" model="${[peticiones: peticiones]}"/>
	</div>
	<script type="text/javascript">
	$(document).ready(function() {
	});
	</script>
</body>
</html>