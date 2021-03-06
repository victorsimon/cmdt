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

<p/>
<div class="page-body">
	<g:form action='index' method="post" >
	<g:hiddenField name='username' value='${command.username}'/>
	<fieldset class="property-list">

	<br/>
	<h4><g:message code='compartirmesadetren.oauth.resetpassword.update.description' default="Ya solo te queda un paso más. Introduce tu nueva clave."/></h4>

		<g:hasErrors bean="${command}">
			<ul class="errors" role="alert">
				<g:eachError bean="${command}" var="error">
					<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
		</g:hasErrors>

        <div class="fieldcontain ${hasErrors(bean: command, field: 'password', 'error')}" >
			<label class="property-label" for="password"><g:message code='compartirmesadetren.oauth.resetpassword.password' default="Clave"/></label>
			<g:passwordField class="property-value" name="password" size="25" />
		</div>
        <div class="fieldcontain ${hasErrors(bean: command, field: 'password2', 'error')}" >
			<label class="property-label" for="password2"><g:message code='compartirmesadetren.oauth.resetpassword.password' default="Repita la clave"/></label>
			<g:passwordField class="property-value" name="password2" size="25" />
		</div>

	    <div class="fieldcontain" style="text-align: center;">
		    <g:link class="buttons" controller="miCuenta" action="show"><g:message code="compartirmesadetren.oauth.resetpassword.cancel" default="Cancelar"/></g:link>
	        <g:submitButton class="buttons" name="${message(code: 'compartirmesadetren.oauth.resetpassword.update', default: 'Actualiza mi clave')}"/>
	    </div>

	</fieldset>
	</g:form>
</div>

<script>
$(document).ready(function() {
	$('#password').focus();
});
</script>

</body>
</html>
