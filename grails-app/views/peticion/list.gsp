
<%@ page import="compartirmesadetren.Peticion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'peticion.label', default: 'Peticion')}" />
	</head>
	<body>
		<a href="#list-peticion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="search">
			<g:form controller="Peticion" action="list"	name="searchForm">
				<g:textField name="q" value="${params.q}" />
				<input type="submit" value="Buscar" />
			</g:form>
		</div>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/mantenimiento')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
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
						
						<g:sortableColumn property="user" title="${message(code: 'peticion.user.label', default: 'Usuario')}" />
						<!-- th><g:message code="peticion.user.label" default="User" /></th -->
						<g:sortableColumn property="estado" title="${message(code: 'peticion.estado.label', default: 'Estado')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${peticionInstanceList}" status="i" var="peticionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${peticionInstance.id}">${fieldValue(bean: peticionInstance, field: "salida")}</g:link></td>
					
						<td><g:link action="show" id="${peticionInstance.id}">${fieldValue(bean: peticionInstance, field: "trayecto")}</g:link></td>
					
						<td><g:link action="show" id="${peticionInstance.id}">${fieldValue(bean: peticionInstance, field: "user")}</g:link></td>

						<td><g:link action="show" id="${peticionInstance.id}">${fieldValue(bean: peticionInstance, field: "estado")}</g:link></td>
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
