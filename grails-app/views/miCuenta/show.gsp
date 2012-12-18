
<%@ page import="compartirmesadetren.User" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'user.label', default: 'User')}" />
	</head>
	<body>
		<a href="#show-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="show-user" class="content scaffold-show" role="main">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list user">
			
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="user.username.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${userInstance}" field="username"/></span>
					
				</li>
			
				<li class="fieldcontain">
					<span class="property-label buttons">
						<g:link class="edit" action="changePassword" id="${userInstance?.id}"><g:message code="default.button.changepassword.label" default="Cambiar clave" /></g:link>
					</span>
				</li>
			
				<li class="fieldcontain">
					<span class="property-label buttons">
						<g:link class="edit" action="changeEmail" id="${userInstance?.id}"><g:message code="default.button.changeemail.label" default="Cambiar email" /></g:link>
					</span>
				
				</li>
			
				<li class="fieldcontain">
					<span class="property-label buttons">
						<g:link class="list" action="listRequest" id="${userInstance?.id}"><g:message code="default.button.listrequest.label" default="Peticiones pendientes" /></g:link>
					</span>
				
				</li>

				<li class="fieldcontain">
					<span class="property-label buttons">
						<g:link class="list" action="listClosed" id="${userInstance?.id}"><g:message code="default.button.listclosed.label" default="Peticiones anteriores" /></g:link>
					</span>
				
				</li>

			</ol>
		</div>
	</body>
</html>
