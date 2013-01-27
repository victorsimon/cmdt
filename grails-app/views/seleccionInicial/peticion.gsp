<!DOCTYPE html>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<style type="text/css" media="screen">

			#page-body {
				margin: 1em 1em 1em 1em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 .1em .1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="page-body" role="main">
			<img alt="tren-de-renfe-horarios-ave-tarifa-mesa-compartir-tarifa mesa-" src="${resource (dir: 'images', file: 'SITO.png')}" style="float: left; margin-right: 20px;"/>
			<h3>¡Tu reserva se ha realizado con éxito!</h3><br/>
			<p>Recuerda que <b>el cobro</b> del billete <b>no se realizará</b> hasta cerrar el cupo de viajeros de las mesas y dependerá del número total de pasajeros.</p>
			<p>Podrás realizar el seguimiento de tu viaje desde el panel de <g:link controller="miCuenta" action="listRequest" style="color: #81005F;" >historial de tu cuenta.</g:link></p>
			<h2>¡Gracias por confiar en CMDT!</h2>
			<br/>
			<div class="fieldcontain" style="text-align: center; padding: .5em;">
					<g:link class="buttons" controller="seleccionInicial" ><g:message code="default.button.volver.label" default="Volver" /></g:link>
			</div>
		</div>
	</body>
</html>
