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

<div class="page-body">
    <g:if test='${flash.error}'>
        <div class="errors">${flash.error}</div>
    </g:if>
    <g:hasErrors bean="${createAccountCommand}">
    <div class="errors">
        <g:renderErrors bean="${createAccountCommand}" as="list"/>
    </div>
    </g:hasErrors>

    <g:form action="createAccount" method="post" autocomplete="off">
        <fieldset class="property-list">
		    <h3><g:message code="compartirmesadetren.oauth.crear.cuenta" default="¡Bienvenido desde {0}, {1}!" args="[session.springSecurityOAuthToken.providerName, session.springSecurityOAuthToken.principal]"/></h3>
		    <br/>
			<h4>Como te hemos dicho, no usaremos ningún dato tuyo de tu cuenta externa...<br/> ¡por lo que tendremos que preguntarte algunas cosas!</h4>
			<br/>
			<p>Necesitamos tu email para poder comunicarnos contigo y enviarte el billete de tren.</p>
            <g:hiddenField name='username' value='${createAccountCommand?.username}'/>

            <div class="fieldcontain ${hasErrors(bean: createAccountCommand, field: 'email', 'error')} ">
                <label class="property-label" for='email'><g:message code="compartirmesadetren.oauth.crear.cuenta.email.label" default="Email"/><span class="required-indicator">*</span></label>
                <g:textField class="property-value" name='email' value='${createAccountCommand?.email}'/>
            </div>
            
            <div class="fieldcontain ${hasErrors(bean: createAccountCommand, field: 'email2', 'error')} ">
                <label class="property-label" for='email2'><g:message code="compartirmesadetren.oauth.crear.cuenta.email2.label" default="Repita su Email"/><span class="required-indicator">*</span></label>
                <g:textField class="property-value" name='email2' value='${createAccountCommand?.email2}'/>
            </div>
            
            <br/>
			<p>Y si quieres, puedes darnos tu teléfono.<br/> 
			Solo lo usaremos en casos <b>MUY</b> urgentes <b>¡Prometido!</b></p>
			
            <div class="fieldcontain ${hasErrors(bean: createAccountCommand, field: 'phoneNumber', 'error')} ">
                <label class="property-label" for='phoneNumber'><g:message code="compartirmesadetren.oauth.crear.cuenta.phoneNumber.label" default="Teléfono"/></label>
                <g:textField class="property-value" name='phoneNumber' value='${createAccountCommand?.phoneNumber}'/>
            </div>
            
            <div class="fieldcontain" style="text-align: center;">
			    <g:link class="buttons" controller="login" action="auth"><g:message code="compartirmesadetren.oauth.crear.cuenta.cancel" default="Cancelar"/></g:link>
	            <g:submitButton class="buttons" name="${message(code: 'compartirmesadetren.oauth.crear.cuenta.button', default: 'Entrar')}"/>
		    </div>
        </fieldset>
    </g:form>
</div>
<script>
$(document).ready(function() {
	$('#email').focus();
});
</script>
</body>
</html>