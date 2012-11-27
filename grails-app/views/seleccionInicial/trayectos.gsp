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
					<h2>Selecciona el trayecto <g:select name="trayecto" from="${trayectos}" optionKey="id" value="${trayecto?.id}" /></h2>
					<h2><g:datePicker name="fecha" value="${fecha}" precision="day" default="${new Date() + 2}" /></h2>
					<h2>Selecciona por fecha <g:actionSubmit value="Buscar" action="trayectos" /></h2>
					<h2>O vea los <g:actionSubmit value="proximos disponibles" action="proximos" /></h2>
				</fieldset>
				<fieldset class="buttons">
					<g:if test="${trenes}">
						<h2>Trenes disponibles</h2>
					</g:if>
					<lu>
						<g:each in="${trenes}" var="ptren">
							<li style="margin: .75em;"><g:link action="detalle" id="${ptren.tren.id}">${ptren.tren} ${ptren.oportunidad} ${ptren.cuantosFaltan}</g:link></li>
						</g:each>
					</lu>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
