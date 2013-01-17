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
		
		.captcha {
			width: 33%; 
			margin: 1em 33% 0 33%;
		}

		@media screen and (max-width: 680px) {
			.captcha {
				width: auto; 
				margin: 1em .5em 0 .5em;
			}
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

<g:form action='form' name='form'>
<fieldset class="property-list" >
	<g:if test='${emailSent}'>
	<div>
		<br/>
		<br/>
		<g:message code='cmdt.contact.sent' default="Gracias por contactar con nosotros. Encuanto revisemos tu cuestión nos pondremos en contacto contigo."/>
		<div class="fieldcontain" style="text-align: center; padding: .5em;">
			<g:link class="buttons" controller="seleccionInicial"><g:message code="cmdt.contact.salir" default="Salir"/></g:link>
		</div>
	</div>
	</g:if>
	<g:else>
	<br/>
	<h4>Solo tienes rellenar el formulario para contactar con nosotros.</h4>
	<recaptcha:ifFailed><div class="errors">CAPTCHA Failed</div></recaptcha:ifFailed>
	<g:hasErrors bean="${command}">
		<ul class="errors" role="alert">
			<g:eachError bean="${command}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
			</g:eachError>
		</ul>
	</g:hasErrors>
	<br/>
	<div class="fieldcontain ${hasErrors(bean: command, field: 'subject', 'error')}">
		<label class="property-label" for="subject"><g:message code='cmdt.contact.username' default="Asunto"/><span class="required-indicator">*</span></label>
		<input class="property-value" style="float:left; width: 17em;" name="subject" id="subject" value="${command?.subject}" <g:if test="${lock}">readonly</g:if> />
		<img id="info1" class="property-info" src="${resource(dir: 'images', file: 'info.gif')}" alt="info" />
	</div>
	<div class="fieldcontain ${hasErrors(bean: command, field: 'responseEmail', 'error')}">
		<label class="property-label" for="responseEmail"><g:message code='cmdt.contact.responseemail' default="E-mail"/><span class="required-indicator">*</span></label>
		<input class="property-value" style="float:left; width: 17em;" name="responseEmail" id="responseEmail" value="${command?.responseEmail}" />
		<img id="info2" class="property-info" src="${resource(dir: 'images', file: 'info.gif')}" alt="info" />
	</div>
	<div class="fieldcontain ${hasErrors(bean: command, field: 'body', 'error')}">
		<label class="property-label" for="body"><g:message code='cmdt.contact.body' default="Detalle"/><span class="required-indicator">*</span></label>
		<g:textArea class="property-value" style="float:left; width: 17em;" name="body" id="body" value="${command?.body}" />
		<img id="info3" class="property-info" src="${resource(dir: 'images', file: 'info.gif')}" alt="info" />
	</div>
	<div class="captcha">
		<recaptcha:ifEnabled>
			<recaptcha:recaptcha theme="white"  lang="es"/>
		</recaptcha:ifEnabled>
	</div>
	<div class="fieldcontain">
	    <g:link class="buttons" controller="seleccionInicial"><g:message code="cmdt.contact.cancel" default="Cancelar"/></g:link>
		<g:submitButton class="buttons" value="${message(code: 'cmdt.contact.submit', default: 'Enviar')}" name="create"/>
	</div>
	</g:else>

</fieldset>
</g:form>

</div>

<script type="text/javascript">
var info1 = "<p>¿Qué nos quieres comunicar?</p>";
var info2 = "<p>Dirección de e-mail a la que deseas que te respondamos.</p>";
var info3 = "<p>Descripción detallada de la consulta.</p>";
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
	if (screenWidth > 680) {
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
})

$(document).ready(function() {
	<g:if test="${lock}">
		$('#body').focus();
	</g:if>
	<g:if test="${!lock}">
		$('#subject').focus();
	</g:if>
});
</script>
</body>
</html>
