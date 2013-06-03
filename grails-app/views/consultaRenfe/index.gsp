<html>
<head>
	<g:javascript library="jquery" />
	<g:javascript library="jquery-ui" />
	<r:layoutResources />
</head>
<body>
	<g:form action="">
		<h1>Consultar historial de los trenes de un día</h1>
		<g:jqDatePicker name="fecha" value="${fecha}" precision="day"/><br/>
		<g:select name="trayecto" from="${trayectos}" optionKey="id"/><br/>
		<g:submitToRemote url="[action: 'historial']" update="datos" value="Historial"/>
		<g:submitToRemote url="[action: 'buscar']" update="datos" value="Estado actual"/>
	</g:form>
	<div id="datos">
	</div>
	<div id="spinner" class="spinner" style="display: none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
	<g:javascript src="application.js"/>
</body>
</html>