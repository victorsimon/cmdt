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
	<g:form action='forgotPassword' name="forgotPasswordForm" autocomplete='off'>
        <fieldset class="property-list">

		<g:if test='${emailSent}'>
		<br/>
		<g:message code='compartirmesadetren.oauth.resetpassword.sent' default="Te hemos enviado un email con un link. Pulsa en el para restablecer tu clave."/>
		<br/>
		</g:if>
	
		<g:else>
	    <g:if test='${flash.error}'>
	        <div class="errors">${flash.error}</div>
	    </g:if>
		<br/>
		<h4><g:message code='compartirmesadetren.oauth.resetpassword.description' default="Dinos tu usuario y te enviaremos un link a tu email para que puedas restablecer tu clave."/></h4>
		<br/>
        <div class="fieldcontain" >
			<label class="property-label" for="username"><g:message code='compartirmesadetren.oauth.resetpassword.username' default="Usuario"/></label>
			<g:textField class="property-value" name="username" size="25" />
		</div>
	
        <div class="fieldcontain" style="text-align: center;">
		    <g:link class="buttons" controller="login" action="auth"><g:message code="compartirmesadetren.oauth.resetpassword.cancel" default="Cancelar"/></g:link>
            <g:submitButton class="buttons" id="reset" name="${message(code: 'compartirmesadetren.oauth.resetpassword.button', default: 'Restablecer mi clave')}"/>
	    </div>
		<br/>
		</g:else>
        </fieldset>
	</g:form>
</div>
<script>
$(document).ready(function() {
	$('#username').focus();
});
</script>

</body>
</html>
