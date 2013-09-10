<div class="row">
	<div id="opciones" class="large-3 columns">
		<h4>Peticiones</h4>
		<g:each in="${compartirmesadetren.EstadoPeticion}" var="estado">
			<div class="opcion row">				
				<div class="small-9 large-10 columns"><span style="">&nbsp;&nbsp;&nbsp;&nbsp;</span><g:remoteLink action="porEstado" params="[estado: estado]" update="principal" before="\$('#spinner').fadeIn()" after="\$('#detalle').empty(); \$('#spinner').fadeOut()">${estado.name}<span data-tooltip data-options="disable-for-touch:true" class="has-tip" title="${estado.help}"><small> ? </small></span></g:remoteLink></div>
				<div id="${estado}" class="contador small-3 large-2 columns label">0</div>
			</div>
		</g:each>
		<hr/>
		<div class="opcion row">
			<div class="small-9 large-10 columns"><span style="">&nbsp;&nbsp;&nbsp;&nbsp;</span><g:remoteLink action="todas" update="principal" before="\$('#spinner').fadeIn()" after="\$('#detalle').empty(); \$('#spinner').fadeOut()">Todas</g:remoteLink></div>
			<div id="todas" class="contador small-3 large-2 columns"></div>
		</div>
		<div class="opcion row">
			<div class="small-9 large-10 columns"><span style="background: url(/css/img/play-mini.png) 0 5px no-repeat transparent;">&nbsp;&nbsp;&nbsp;&nbsp;</span><g:remoteLink id="actuales" action="pendientes" update="principal" before="\$('#spinner').fadeIn()" after="\$('#detalle').empty(); \$('#spinner').fadeOut()">Actuales</g:remoteLink></div>
			<div id="pendientes" class="contador small-3 large-2 columns">0</div>
		</div>
		<div class="opcion row">
			<div class="small-9 large-10 columns"><span style="">&nbsp;&nbsp;&nbsp;&nbsp;</span><g:remoteLink action="pasadas" update="principal" before="\$('#spinner').fadeIn()" after="\$('#detalle').empty(); \$('#spinner').fadeOut()">Historial</g:remoteLink></div>
			<div id="pasadas" class="contador small-3 large-2 columns">0</div>
		</div>
	</div>
	<a name="kk"></a>
	<div id="principal" name="principal" class="large-9 columns">
		<g:render template="peticiones" model="${[peticiones: peticiones]}"/>
	</div>
</div>
<hr/>
<div id="detalle" class="row">
</div>
<div id="editModal" class="reveal-modal"></div>
<script type="text/javascript">
	var last = null;
	var lastColor = null;
	var lastBGColor = null;
	var lastEstado = $('#actuales');
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
			$(id).click();; window.location.hash = 'principal'
		});

		$('.opcion div a').live('click', function() {
			if (lastEstado) {
				$(lastEstado).prev().css('background', '');
			}
			lastEstado = this;
			$(this).prev().css('background', 'url(/css/img/play-mini.png) 0 5px no-repeat transparent');
		});

	});
</script>