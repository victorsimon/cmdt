
<%@ page import="compartirmesadetren.User" %>
<!DOCTYPE html>
<html>
	<head>
    <meta name='layout' content='main'/>
    <style type="text/css" media="screen">
		h3, h4, p {
			text-align: center;
		}
		
		fieldset.property-list {
			width: auto;
		}

		.fieldcontain .property-label {
			float: left;
		}
		
		.fieldcontain .property-value {
			margin: 1px 0 0 0;
			float: none;
		}

		.fieldcontain img.property-info {
			float: left;
			margin: 4px 0 0 0;
		}

		.fieldcontain .buttons {
			margin: 1em 0 0 0;
		}

		div.balloonTip {
			font-size: medium;
			width: 300px;
		}

		@media screen and (max-width: 480px) {
			div.balloonTip {
				font-size: small;
				width: 200px;
			}
		}
	</style>
	</head>
	<body>
		<div class="page-body" role="main">
		<form action="cambiar">
			<fieldset class="property-list">
				<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
				</g:if>
			
				<div class="fieldcontain">
					<label class="property-label"><g:message code="user.username.label" default="Nombre" /></label>
					<input class="property-value" value="<g:fieldValue bean="${userInstance}" field="username"/>" disabled="disabled" />
				</div>
			
				<div class="fieldcontain">
					<label class="property-label"><g:message code="user.email.label" default="E-mail" /></label>
					<input class="property-value" value="<g:fieldValue bean="${userInstance}" field="email"/>" disabled="disabled" />
				</div>
			
				<div class="fieldcontain">
					<label class="property-label"><g:message code="user.phoneNumber.label" default="Teléfono" /></label>
					<input class="property-value" value="<g:fieldValue bean="${userInstance}" field="phoneNumber"/>-" disabled="disabled" />
				</div>
			
				<div class="fieldcontain" style="text-align: center; padding: .5em;">
						<g:link class="buttons" style="margin-right: 1em;" controller="cambiarPassword" ><g:message code="default.button.changepassword.label" default="Cambiar clave" /></g:link>
						<g:link class="buttons" action="cambiarEmail" ><g:message code="default.button.changeemail.label" default="Cambiar email" /></g:link>
				</div>
			
				<div class="fieldcontain" style="text-align: center; padding: .5em;">
						<g:link class="buttons" style="margin-left: 1em; margin-right: 1em;" action="listClosed" id="${userInstance?.id}"><g:message code="default.button.listclosed.label" default="Historial" /></g:link>
						<g:link class="buttons" action="listRequest" id="${userInstance?.id}"><g:message code="default.button.listrequest.label" default="Solicitudes" /></g:link>
				</div>

				<div class="fieldcontain" style="text-align: center; padding: .5em;">
						<g:link class="buttons" controller="seleccionInicial" ><g:message code="default.button.volver.label" default="Volver" /></g:link>
				</div>
			</fieldset>
		</form>
		</div>
	</body>
</html>
