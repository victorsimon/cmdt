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
</style>
</head>

<body>

<div class="page-body" style='text-align:center;'>
    <g:if test='${flash.message}'>
        <div class="message">${flash.message}</div>
    </g:if>
	<form action='${postUrl}' method='POST' id="loginForm" name="loginForm" autocomplete='off'>
	<g:if test="${session['lastURL']}">
	<input name="spring-security-redirect" type="hidden" value="${session['lastURL']}" />
	</g:if>
	<fieldset class="property-list">
		<br/>
		<h4>Introduce tus datos de usuario si ya estas registrado.</h4>
		<br/>
		<div class="fieldcontain">
			<label class="property-label" for="username"><g:message code='cmdt.login.username' default="Usuario"/></label>
			<input class="property-value" style="float:left;" name="j_username" id="username" size="20" />
		</div>
		<div class="fieldcontain">
			<label class="property-label" for="password"><g:message code='cmdt.login.password' default="Clave"/></label>
			<input class="property-value" style="float:left;" type="password" name="j_password" id="password" size="20" />
		</div>
		<div style="margin-top: 0.6em;">
			<input type="checkbox" class="checkbox" name="${rememberMeParameter}" id="remember_me" checked="checked" />
			<label for='remember_me'><g:message code='cmdt.login.rememberme' default="Recuérdame"/></label> |
			<span class="forgot-link">
				<g:link controller='register' action='forgotPassword'><g:message code='cmdt.login.forgotPassword' default="Olvidé mi clave"/></g:link>
			</span>
		</div>
		<div class="fieldcontain">
			<g:link class="buttons" name='register' controller='register' >${message(code: 'cmdt.login.button.label', default: 'Soy nuevo')}</g:link>
			<g:submitButton class="buttons" action='${postUrl}'  name='loginButton' value="${message(code: 'cmdt.login.button.label', default: 'Login')}"/>
		</div>
		<br/><br/>
	<h4>O utiliza alguna de tus cuentas en ...</h4>
	<oauth:connect provider="twitter" id="twitter-connect-link"><img class="social-login" src="${resource(dir: 'images/social', file: 't_icon.png')}" alt="Sig in with Twitter" /></oauth:connect>
	<oauth:connect provider="facebook" id="facebook-connect-link"><img class="social-login" src="${resource(dir: 'images/social', file: 'f_icon.png')}" alt="Connect with Facebook" /></oauth:connect>
	<oauth:connect provider="google" id="google-connect-link"><img class="social-login" src="${resource(dir: 'images/social', file: 'g_icon.png')}" alt="Login with Google" /></oauth:connect>
	<h4>No cogeremos ni utilizaremos tus datos ni tu información personal.</h4>
	<div class="fieldcontain" style="text-align: center; padding: .5em;">
		<g:link class="buttons" controller="seleccionInicial" ><g:message code="default.button.volver.label" default="Volver" /></g:link>
	</div>
	</fieldset>
	</form>
</div>

<script>
$(document).ready(function() {
	$('#username').focus();
});

<s2ui:initCheckboxes/>

</script>

</body>
</html>
