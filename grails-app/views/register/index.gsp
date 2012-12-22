<html>

<head>
	<meta name='layout' content='main'/>
	<style type="text/css" media="screen">
		* {
			/*border: 1px solid black;*/
		}
		
		fieldset.property-list {
			width: auto;
		}
		
		.fieldcontain .property-label {
			float: left;
		}
		
		.fieldcontain .property-value {
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
<div class="page-body" style='text-align:center;'>

<g:form action='register' name='registerForm'>
<fieldset class="property-list" >
	<g:if test='${emailSent}'>
	<br/>
	<g:message code='cmdt.newuser.sent' default="Te hemos eviado un email con un link para asegurarnos que la dirección es correcta. Pulsa en el para completar el registro."/>
	<br/>
	</g:if>
	<g:else>
	<br/>
	<h4>Solo tienes rellenar el formulario para registrarte como nuevo usuario.</h4>
	<g:hasErrors bean="${command}">
		<ul class="errors" role="alert">
			<g:eachError bean="${command}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
			</g:eachError>
		</ul>
	</g:hasErrors>
	<br/>
	<div class="fieldcontain ${hasErrors(bean: command, field: 'username', 'error')}">
		<label class="property-label" for="username"><g:message code='cmdt.newuser.username' default="Usuario"/><span class="required-indicator">*</span></label>
		<input class="property-value" style="float:left;" name="username" id="username" value="${command.username}" size="20" />
		<img id="info1" class="property-info" src="${resource(dir: 'images', file: 'info.png')}" alt="info" />
	</div>
	<div class="fieldcontain ${hasErrors(bean: command, field: 'email', 'error')}">
		<label class="property-label" for="email"><g:message code='cmdt.newuser.email' default="E-mail"/><span class="required-indicator">*</span></label>
		<input class="property-value" style="float:left;" name="email" id="email" value="${command.email}" size="20" />
		<img id="info2" class="property-info" src="${resource(dir: 'images', file: 'info.png')}" alt="info" />
	</div>
	<div class="fieldcontain ${hasErrors(bean: command, field: 'phoneNumber', 'error')}">
		<label class="property-label" for="phoneNumber"><g:message code='cmdt.newuser.phoneNumber' default="Tel&eacute;fono"/></label>
		<input class="property-value" style="float:left;" name="phoneNumber" id="phoneNumber" value="${command.email}" size="20" />
		<img id="info3" class="property-info" src="${resource(dir: 'images', file: 'info.png')}" alt="info" />
	</div>
	<div class="fieldcontain ${hasErrors(bean: command, field: 'password', 'error')}">
		<label class="property-label" for="password"><g:message code='cmdt.newuser.password' default="Clave"/><span class="required-indicator">*</span></label>
		<input class="property-value" style="float:left;" name="password" id="password" type="password" size="20" />
		<img id="info4" class="property-info" src="${resource(dir: 'images', file: 'info.png')}" alt="info" />
	</div>
	<div class="fieldcontain ${hasErrors(bean: command, field: 'password2', 'error')}">
		<label class="property-label" for="password2"><g:message code='cmdt.newuser.password2' default="Repita la clave"/><span class="required-indicator">*</span></label>
		<input class="property-value" style="float:left;" name="password2" id="password2" type="password" size="20" />
		<img id="info5" class="property-info" src="${resource(dir: 'images', file: 'info.png')}" alt="info" />
	</div>
	<div class="fieldcontain">
	    <g:link class="buttons" controller="login" action="auth"><g:message code="cmdt.newuser.cancel" default="Cancelar"/></g:link>
		<g:submitButton class="buttons" value="${message(code: 'cmdt.newuser.submit', default: 'Crea tu cuenta')}" name="create"/>
	</div>
	</g:else>

</fieldset>
</g:form>

</div>

<script type="text/javascript">
var info1 = "<p>Este será el nombre de usuario que utilizaras para entrar.</p>";
var info2 = "<p>La dirección de email con la que podremos comunicarnos contigo. Imprescindible para gestionarte el billete.</p>";
var info3 = "<p>Y si quieres, puedes darnos tu teléfono.<br/> Solo lo usaremos en casos <b>MUY</b> urgentes <b>¡Prometido!</b></p>";
var info4 = "<p>Tu clave de acceso.</p>";
var info5 = "<p>Necesitamos que repitas la clave para asegurarnos.</p>";
prepareBalloon = function(id, text) {
    $(id).click(function(e) {
			$(this).showBalloon({contents: text});
		});
	$(id).mouseleave(function(e) {
			$(this).hideBalloon();
		});
}
$(document).ready(function() { 
	$('#username').focus();
    $.balloon.defaults.classname = 'balloonTip';
	screenWidth = $(window).width();
	if (screenWidth > 480) {
		$.balloon.defaults.position = 'right';
	} else {
		$.balloon.defaults.position = 'left';
	}
    $.balloon.defaults.css = {
    	  minWidth: "20px",
    	  padding: "5px",
    	  borderRadius: "6px",
    	  border: "solid 1px #666666",
    	  boxShadow: "4px 4px 4px #555",
    	  color: "#666666",
    	  backgroundColor: "#fff",
    	  opacity: "1",
    	  zIndex: "32767",
    	  textAlign: "left"
    };
	prepareBalloon('#info1', info1);
	prepareBalloon('#info2', info2);
	prepareBalloon('#info3', info3);
	prepareBalloon('#info4', info4);
	prepareBalloon('#info5', info5);
})

$(document).ready(function() {
});
</script>
</body>
</html>
