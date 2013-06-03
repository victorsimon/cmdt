<div id="opciones">
	<div id="opciones-titulo">Peticiones</div>
	<div class="opcion">
		<g:remoteLink action="todas" update="principal">Todas</g:remoteLink>
		<div id="todas" class="contador"></div>
	</div>
	<div class="opcion">
		<g:remoteLink action="pendientes" update="principal">Actuales</g:remoteLink>
		<div id="pendientes" class="contador">0</div>
	</div>
	<div class="opcion">
		<g:remoteLink action="pasadas" update="principal">Historial</g:remoteLink>
		<div id="pasadas" class="contador">0</div>
	</div>
	<g:each in="${compartirmesadetren.EstadoPeticion}" var="estado">
		<div class="opcion">
			<g:remoteLink action="porEstado" params="[estado: estado]" update="principal">${estado.name}</g:remoteLink>
			<div id="${estado}" class="contador">0</div>
		</div>
	</g:each>
</div>
<div id="principal">
	<g:render template="peticiones" model="${[peticiones: peticiones]}"/>
</div>
<div id="detalle">
</div>
<script type="text/javascript">
	last = null;
	$(document).ready(function() {
		$('#principal table tr td.visible').live('click', function() {
			if (last) {
				$(last).parent().css('background-color', 'rgba(240, 240, 240, .85)');
				$(last).parent().css('color', '#000000');
			}
			last = this;
			$(this).parent().css('background-color', '#555555');
			$(this).parent().css('color', '#FFFFFF');
			id = '#' + $(this).parent().attr('peticion');
			$(id).click();
		});
	});
</script>