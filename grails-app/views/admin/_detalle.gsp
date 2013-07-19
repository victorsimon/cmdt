<div id="command" class="large-12 columns">
	<g:each in="${acciones}" var="fila">
		<div class="row">
			<div class="large-8 large-centered columns">
				<g:each in="${fila}" var="col">
					<g:remoteLink class="small button large-5 columns" action="${col.accion}" id="${peticion.id}" update="principal" after="\$('#detalle').text('');">${col.texto}
					 <span data-tooltip data-options="disable-for-touch:true" class="has-tip" title="${col.help}"> ? </span></g:remoteLink>
				</g:each>
			</div>
		</div>
	</g:each>
</div>
<script type="text/javascript">
$(document).ready(function() {
});
</script>
<script src="js/jquery.ui.datepicker-es.js"></script>
<script>
  	$(function() {
    	$.datepicker.setDefaults( $.datepicker.regional[ "" ] );
    	$("#salida" ).datepicker( $.datepicker.regional[ "es" ] );	
	});
</script>
