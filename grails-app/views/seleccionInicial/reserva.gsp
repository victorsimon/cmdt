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
				<fieldset class="property-list" >
					<input type="hidden" name="id" value="${peticionesTren.tren.id}" />
					<h2>${peticionesTren.tren.nombre}, ${peticionesTren.tren.trayecto}</h2>
					<h2>Salida
					<input size="13" disabled="disabled" value="${peticionesTren.tren.salida.format('dd-MM-yyyy HH:mm')}" /></h2>
					<h2>Llegada
					<input size="13" disabled="disabled" value="${peticionesTren.tren.llegada.format('dd-MM-yyyy HH:mm')}" /></h2>
					<g:if test="${peticionesTren.meApuntoYViajoSeguro}">
						<h2>¡Si te apuntas viajas!</h2>
					</g:if>
					<g:if test="${doPayment}">
						<h2><paypal:button itemName="${peticionesTren.tren}, ${peticionesTren.tren.trayecto}" 
							itemNumber="${peticionesTren.tren.id}" amount="37,99" buyerId="${user.id}" 
							discountAmount="0" params="[paymentaction: 'authorization']" 
							buttonSrc="https://www.paypalobjects.com/es_ES/ES/i/btn/btn_buynowCC_LG.gif"
							buttonAlt="https://www.paypalobjects.com/es_ES/i/scr/pixel.gif"/></h2>
						<p style="border: 1px solid black;">Datos de prueba para realizar la compra.<br/>
						Credit card:<br/>
						- Visa 4073774447610449<br />
						- Exp. date 11/2017<br />
						- CVV2 123<br />
						</p>
					</g:if>
					<g:if test="${!doPayment}">
						<h2><g:link controller="seleccionInicial" action="peticion" id="${peticionesTren.tren.id}"><img src="https://www.paypalobjects.com/es_ES/ES/i/btn/btn_buynowCC_LG.gif"/></g:link></h2>
						<p style="border: 1px solid black;">Datos de prueba para realizar la compra.<br/>
						Credit card:<br/>
						- Visa 4073774447610449<br />
						- Exp. date 11/2017<br />
						- CVV2 123<br />
						</p>
					</g:if>
				</fieldset>
				<fieldset class="buttons">
					<g:if test="${sugeridos}">
						<h2>Puede interesarte...</h2>
					</g:if>
					<lu>
						<g:each in="${sugeridos}" var="ptren">
							<li style="margin: .75em;"><g:link action="reserva" id="${ptren.tren.id}">${ptren.tren} ${ptren.oportunidad}</g:link></li>
						</g:each>
					</lu>
					<div class="fieldcontain" style="text-align: center; padding: .5em;">
							<g:link class="buttons" controller="seleccionInicial" ><g:message code="default.button.volver.label" default="Volver" /></g:link>
					</div>
				</fieldset>
				<div id="page-info" style="float:right; width: 45%; top: -370px; position: relative;">
					<h3>¡ATENCIÓN!</h3>
					<p><b>Comprueba</b> que los datos de <b>fecha y hora de salida</b> son correctos antes de aceptar la reserva.</p>
					<p>Recuerda que <b>SITO</b> no hará NUNCA <b>ningún cargo</b> en tu tarjeta de crédito <b>hasta completar las mesas</b>. <b>Este paso es una comprobación de que tu tarjeta es correcta</b>.</p>
					<p>Comparte la información de tu viaje en tus redes sociales preferidas al terminar la gestión. A más gente, más probabilidad de pagar menos:</p>
					
					<p>Por ello, el <b>precio que SITO te ofrece</b> será de tan sólo <b>29,99€, 34,99€ o 37,99€</b>.</p>
					
					<p>El <b>ahorro</b> con el precio original oscila <b>entre los 20 y 30€*</b></p>
					<p><i>(Precio oficial por trayecto: 58,90€)</i></p>
				</div>
		</div>
	</body>
</html>
