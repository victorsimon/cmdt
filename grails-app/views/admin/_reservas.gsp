<div class="row">
	<div id="opciones" class="large-3 columns">
		<h4>Peticiones</h4>
		<g:each in="${compartirmesadetren.EstadoPeticion}" var="estado">
			<div class="opcion row">
				<div class="large-10 columns"><g:remoteLink action="porEstado" params="[estado: estado]" update="principal"after="\$('#detalle').empty()">${estado.name}<span data-tooltip data-options="disable-for-touch:true" class="has-tip" title="${estado.help}"><small> ? </small></span></g:remoteLink></div>
				<div id="${estado}" class="contador large-2 columns label">0</div>
			</div>
		</g:each>
		<hr/>
		<div class="opcion row">
			<div class="large-10 columns"><g:remoteLink action="todas" update="principal">Todas</g:remoteLink></div>
			<div id="todas" class="contador large-2 columns"></div>
		</div>
		<div class="opcion row">
			<div class="large-10 columns"><g:remoteLink action="pendientes" update="principal">Actuales</g:remoteLink></div>
			<div id="pendientes" class="contador large-2 columns">0</div>
		</div>
		<div class="opcion row">
			<div class="large-10 columns"><g:remoteLink action="pasadas" update="principal">Historial</g:remoteLink></div>
			<div id="pasadas" class="contador large-2 columns">0</div>
		</div>
	</div>
	<div id="principal" class="large-9 columns">
		<g:render template="peticiones" model="${[peticiones: peticiones]}"/>
	</div>
</div>
<hr/>
<div id="detalle" class="row">
</div>
<div id="editModal" class="reveal-modal"></div>
<script type="text/javascript">
	last = null;
	lastColor = null;
	lastBGColor = null;
	$(document).ready(function() {
		$('#principal table tr td.visible').live('click', function() {
			if (last) {
				$(last).parent().css('background-color', lastBGColor);
				$(last).parent().children().each(function(){$(this).css('color', lastColor)});
			}
			last = this;
			lastBGColor = $(this).parent().css('background-color');
			lastColor = $(this).parent().css('color');
			$(this).parent().css('background-color', '#555555');
			$(this).parent().children().each(function(){$(this).css('color', '#FFFFFF')});
			id = '#' + $(this).parent().attr('peticion');
			$(id).click();
		});
	});
</script>