<html>

<head>
<meta name='layout' content='main'/>
</head>

<body>

<p/>

<div class="login" style='text-align:center;'>
	<div class="login-inner">
	<form action='${postUrl}' method='POST' id="loginForm" name="loginForm" autocomplete='off'>
	<div class="sign-in">
	<g:if test="${session['lastURL']}">
	<input name="spring-security-redirect" type="hidden" value="${session['lastURL']}" />
	</g:if>
	<table>
		<tr>
			<td><label for="username"><g:message code='cmdt.login.username' default="Usuario"/></label></td>
			<td><input name="j_username" id="username" size="20" /></td>
		</tr>
		<tr>
			<td><label for="password"><g:message code='cmdt.login.password' default="Clave"/></label></td>
			<td><input type="password" name="j_password" id="password" size="20" /></td>
		</tr>
		<tr>
			<td colspan='2'>
				<input type="checkbox" class="checkbox" name="${rememberMeParameter}" id="remember_me" checked="checked" />
				<label for='remember_me'><g:message code='cmdt.login.rememberme' default="Recuérdame"/></label> |
				<span class="forgot-link">
					<g:link controller='register' action='forgotPassword'><g:message code='cmdt.login.forgotPassword' default="Olvidé mi clave"/></g:link>
				</span>
			</td>
		</tr>
		<tr>
			<td colspan='2'>
				<g:link class="buttons" name='register' controller='register' >${message(code: 'cmdt.login.button.label', default: 'Soy nuevo')}</g:link>
				<g:submitButton class="buttons" name='loginButton' value="${message(code: 'cmdt.login.button.label', default: 'Login')}"/>
			</td>
		</tr>
	</table>
	<h4>O utiliza alguna de tus cuentas en ...</h4>
	<oauth:connect provider="twitter" id="twitter-connect-link"><img class="social-login" src="${resource(dir: 'images/social', file: 't_icon.png')}" alt="Sig in with Twitter" /></oauth:connect>
	<oauth:connect provider="facebook" id="facebook-connect-link"><img class="social-login" src="${resource(dir: 'images/social', file: 'f_icon.png')}" alt="Connect with Facebook" /></oauth:connect>
	<oauth:connect provider="google" id="google-connect-link"><img class="social-login" src="${resource(dir: 'images/social', file: 'g_icon.png')}" alt="Login with Google" /></oauth:connect>
	<h4>No cogeremos ni utilizaremos tus datos ni tu información personal.</h4>
	</div>
	</div>
	</form>
	</div>
</div>

<script>
$(document).ready(function() {
	$('#username').focus();
});

<s2ui:initCheckboxes/>

</script>

</body>
</html>
