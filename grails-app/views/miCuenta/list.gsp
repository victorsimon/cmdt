
<%@ page import="compartirmesadetren.Peticion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'peticion.label', default: 'Peticion')}" />
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-peticion" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="salida" title="${message(code: 'peticion.salida.label', default: 'Salida')}" />

						<g:sortableColumn property="trayecto" title="${message(code: 'peticion.trayecto.label', default: 'Trayecto')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${peticionInstanceList}" status="i" var="peticionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:formatDate date="${peticionInstance.salida}" format="dd-MM-yyyy hh:mm" /></td>
					
						<td>${peticionInstance.trayecto}</td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${peticionInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
