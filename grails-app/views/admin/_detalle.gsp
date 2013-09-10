<div id="command" class="small-12 large-12 columns">
	<g:each in="${acciones}" var="fila">
		<div class="row">
			<div class="small-8 large-8 small-centered large-centered columns">
				<g:each in="${fila}" var="col">
					<g:remoteLink class="small button small-5 large-5 columns" action="${col.accion}" id="${peticion.id}" update="principal" before="if (!confirm('¿Esta seguro?')) {return false;}" after="\$('#detalle').text('');">${col.texto}
					 <span data-tooltip data-options="disable-for-touch:true" class="has-tip" title="${col.help}"> ? </span></g:remoteLink>
				</g:each>
			</div>
		</div>
	</g:each>
</div>
<script type="text/javascript">
$(document).ready(function() {
	window.location.hash = "detalle";
});
</script>
<script src="js/jquery.ui.datepicker-es.js"></script>
<script>
  	$(function() {
    	$.datepicker.setDefaults( $.datepicker.regional[ "" ] );
    	$("#salida" ).datepicker( $.datepicker.regional[ "es" ] );	
	});
</script>
