<html class="no-js" lang="en">
<head>
	<style>
		/*{border: 1px solid black;}*/
		a:hover {
		}
		a {
		}
		a:visited {
		}
		#menu * {
		}
		#menu {
			background-color: #333;
		}
		#menu ul {
		}
		#menu ul li {
		}
		#contenedor {
		}
		#opciones {
		}
		#opciones-titulo {
		}
		div.opcion {
		}
		div.contador {
		}
		#principal {
			max-height: 65%;
			overflow-x: hidden;
			overflow-y: auto;
		}
		#principal table {
			font-size: 95%;
		}
		#principal tr {
		}
		#principal td {
		}
		#detalle {
		}
		#info table {
			width: 100%;
		}
		#command a {
		}
	</style>
	<meta charset="utf-8" />
  	<meta name="viewport" content="width=device-width" />
	<!-- If you are using CSS version, only link these 2 files, you may add app.css to use for your overrides if you like. -->
  	<link rel="stylesheet" href="css/normalize.css" />
  	<link rel="stylesheet" href="css/foundation.css" />	
	<link type="text/css" media="screen" rel="stylesheet" href="css/responsive-tables.css" />
  	<g:javascript library="jquery" />
	<g:javascript library="jquery-ui" />
	<script src="js/vendor/custom.modernizr.js"></script>
	<r:layoutResources />
</head>
<body>
	<div id="menu" class="row">
		<div class="large-12 columns">
			<ul class="inline-list"><li><g:remoteLink action="reservas" update="contenedor">Gestión de Reservas</g:remoteLink></li></ul>
		</div>
	</div>
	<div class="row">
		<div class="large-12 columns">
			<hr/>
		</div>
	</div>
	<div id="contenedor" class="row"><div class="large-12 columns">
		<g:render template="reservas" model="${[peticiones: peticiones]}"/>
	</div></div>
	<script>
	document.write('<script src=' +
	  ('__proto__' in {} ? 'js/vendor/zepto' : 'js/vendor/jquery') +
	  '.js><\/script>')
	</script>
	<script src="js/foundation/foundation.js"></script>
	<script src="js/foundation/foundation.alerts.js"></script>
	<script src="js/foundation/foundation.clearing.js"></script>
	<script src="js/foundation/foundation.cookie.js"></script>
	<script src="js/foundation/foundation.dropdown.js"></script>
	<script src="js/foundation/foundation.forms.js"></script>
	<script src="js/foundation/foundation.joyride.js"></script>
	<script src="js/foundation/foundation.magellan.js"></script>
	<script src="js/foundation/foundation.orbit.js"></script>
	<script src="js/foundation/foundation.placeholder.js"></script>
	<script src="js/foundation/foundation.reveal.js"></script>
	<script src="js/foundation/foundation.section.js"></script>
	<script src="js/foundation/foundation.tooltips.js"></script>
	<script src="js/foundation/foundation.topbar.js"></script>
	<script src="js/foundation/foundation.interchange.js"></script>
	<script src="js/foundation/responsive-tables.js"></script>
	<script>
	  $(document).foundation();
	</script>	
	<script type="text/javascript">
	$(document).ready(function() {
	});
	</script>
</body>
</html>