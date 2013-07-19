
<%@ page import="compartirmesadetren.Peticion" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<script type="text/javascript">
			doClick = function (row) {
				salida = row.cells[0].innerText; 
				trayecto = row.cells[1].innerText;
				subject = escape(trayecto + " - " + salida)
				window.location.assign("../../contact?subject=" + subject);	
			}
		</script>
	</head>
	<body>
		<div id="list-peticion" class="content scaffold-list" role="main">
			<h1>${peticionesTittle}</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="salida" title="${message(code: 'peticion.salida.label', default: 'Salida')}" />

						<g:sortableColumn property="trayecto" title="${message(code: 'peticion.trayecto.label', default: 'Trayecto')}" />
					
						<g:sortableColumn property="plazas" title="${message(code: 'peticion.plazas.label', default: 'Plazas')}" />
					
						<g:sortableColumn property="Estado" title="${message(code: 'peticion.estado.label', default: 'Estado')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${peticionInstanceList}" status="i" var="peticionInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}" onclick="doClick(this);">
					
						<td><g:formatDate date="${peticionInstance.salida}" format="dd-MM-yyyy HH:mm" /></td>
					
						<td>${peticionInstance.trayecto}</td>

						<td>${peticionInstance.plazas}</td>

						<td>${peticionInstance.estado}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${peticionInstanceTotal}" />
			</div>
		</div>
		<div class="fieldcontain" style="text-align: center; padding: .5em;">
				<g:link class="buttons" controller='miCuenta' action='show'><g:message code="default.button.volver.label" default="Volver" /></g:link>
		</div>
	</body>
</html>
