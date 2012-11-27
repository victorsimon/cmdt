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
			<g:form action="trayectos" method="post">
				<fieldset class="form">
					<input type="hidden" name="id" value="${peticionesTren.tren.id}" />
					<h2>${peticionesTren.tren.nombre}, ${peticionesTren.tren.trayecto}</h2>
					<h2>Salida
					<input size="13" disabled="disabled" value="${peticionesTren.tren.salida.format('dd-MM-yyyy HH:mm')}" /></h2>
					<h2>Llegada
					<input size="13" disabled="disabled" value="${peticionesTren.tren.llegada.format('dd-MM-yyyy HH:mm')}" /></h2>
					<g:if test="${peticionesTren.meApuntoYViajoSeguro}">
						<h2>¡Si te apuntas viajas!</h2>
					</g:if>
					<g:if test="${!peticionesTren.meApuntoYViajoSeguro && !(peticionesTren.cuantosFaltan == 0)}">
						<h2>¡Faltan ${peticionesTren.cuantosFaltan} para viajar!</h2>
					</g:if>
					<h2><g:actionSubmit value="¡Me apunto!" action="peticion" /></h2>
				</fieldset>
				<fieldset class="buttons">
					<g:if test="${sugeridos}">
						<h2>Puede interesarte...</h2>
					</g:if>
					<lu>
						<g:each in="${sugeridos}" var="ptren">
							<li style="margin: .75em;"><g:link action="detalle" id="${ptren.tren.id}">${ptren.tren} ${ptren.oportunidad}</g:link></li>
						</g:each>
					</lu>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
