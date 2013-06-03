<!DOCTYPE html>
<html>
	<head>
		<title>Reserva de plaza - Compartir Mesa De Tren</title>
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
				font-size: .98em;
				line-height: 1;
				margin: 0.75em 0;
				text-indent: -1em;
			}

			b {
				font-size: 1em;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}

			a.enlace {
				color: #81005F;
				font-weight: bold;
				text-decoration: underline;
			}

			input[type='image'] {
				border: 0;
			}
			@media screen and (max-width: 680px) {
				#page-info {
					display: none;
				}
			}
			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 .1em .1em;
				}
				
				#page-info {
					display: none;
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
			<div id="page-info" style="float:right; width: 45%;">
				<img src='${resource(dir: 'images', file: 'SITO.png')}' style='float: right; width: 68px; height: 80px; margin: 1em;' />
				<h3  style="color:#81006F;">¡ATENCIÓN!</h3>
				<p><b>Comprueba</b> que los datos de fecha y hora de salida son correctos antes de aceptar la reserva.</p>
				<p><b>Recuerda</b> que sólo pagarás uno de estos tres precios: <b style="color:#81006F;"><g:formatNumber number="${precio.precioCmdtMin}" type="currency" currencyCode="EUR" />, <g:formatNumber number="${precio.precioCmdtMed}" type="currency" currencyCode="EUR" /> o <g:formatNumber number="${precio.precioCmdtMax}" type="currency" currencyCode="EUR" /></b>. <b>Precio final.</b> Sin más comisiones.</p>
				<p><b>No realizaré ningún cargo</b> en tu tarjeta de crédito hasta tener un mínimo de viajeros.</p>
				<p><b>Este paso es una comprobación</b> de que tu tarjeta es correcta, y de que podriás pagar el precio máximo del billete <b style="color:#81006F;"><g:formatNumber number="${precio.precioCmdtMax}" type="currency" currencyCode="EUR" /></b>. PayPal no realiza ninguna otra acción. Es el método más seguro de pago por Internet</p>
				<p><b>Comparte la información</b> de tu viaje en tus redes sociales para que tu trayecto sea más barato.</p>
				<p><b>El ahorro</b> con el precio original es de <b style="color:#81006F;"><g:formatNumber number="${precio.precioRenfe - precio.precioCmdtMax}" type="currency" currencyCode="EUR" /> a <g:formatNumber number="${precio.precioRenfe - precio.precioCmdtMin}" type="currency" currencyCode="EUR" />*</b></p>
				<p><i>*(Precio oficial por trayecto: <g:formatNumber number="${precio.precioRenfe}" type="currency" currencyCode="EUR" />)</i></p>
				<p><b>Si tienes cualquier duda</b>, pincha en <g:link class="enlace" url="/tarifa-mesa-renfe-como-funciona" >¿cómo funciona?</g:link> o revisa las <g:link class="enlace" url="/billetes-ave-preguntas-frecuentes">preguntas frecuentes</g:link>. También puedes <g:link class="enlace" controller="contact">escríbeme</g:link>.</p>
			</div>
			<fieldset class="property-list">
				<input type="hidden" name="id" value="${peticionesTren.tren.id}" />
				<div style="text-align: center;">
					<h2>${peticionesTren.tren.nombre}, ${peticionesTren.tren.trayecto}</h2>
				</div>
				<div class="fieldcontain">
					<label>Salida</label>
					<input disabled="disabled" value="${peticionesTren.tren.salida.format('dd-MM-yyyy HH:mm')}" />
				</div>
				<div class="fieldcontain">
					<label>Llegada</label>
					<input disabled="disabled" value="${peticionesTren.tren.llegada.format('dd-MM-yyyy HH:mm')}" />
				</div>
				<div style="text-align: center;">
					<g:if test="${doPayment}">
						<h2><paypal:button itemName="${peticionesTren.tren}, ${peticionesTren.tren.trayecto}" 
							itemNumber="${peticionesTren.tren.id}" amount="4" buyerId="${user.id}" 
							discountAmount="0" params="[paymentaction: 'authorization', currency_code: 'EUR', lc: 'es', country: 'ES']"
							buttonSrc="${resource(dir: 'images', file: 'reservar.png')}"
							buttonAlt="https://www.paypalobjects.com/es_ES/i/scr/pixel.gif"/></h2>
					</g:if>
					<g:if test="${!doPayment}">
						<h2><g:link controller="seleccionInicial" action="peticion" id="${peticionesTren.tren.id}"><img src="${resource(dir: 'images', file: 'reservar.png')}"/></g:link></h2>
					</g:if>
				</div>
			</fieldset>
			<fieldset class="buttons">
				<g:if test="${sugeridos}">
					<h2>Los siguientes ya tienen reservas, por lo que podría interesarte ...</h2>
				</g:if>
				<lu>
					<g:each in="${sugeridos}" var="ptren">
						<li style="margin: .75em;"><g:link action="reserva" id="${ptren.tren.id}">${ptren.tren}</g:link></li>
					</g:each>
				</lu>
				<div class="fieldcontain" style="text-align: center; padding: .5em;">
						<g:link class="buttons" controller="seleccionInicial" ><g:message code="default.button.volver.label" default="Volver" /></g:link>
				</div>
			</fieldset>
		</div>
	</body>
</html>
