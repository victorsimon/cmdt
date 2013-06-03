<table>
	<colgroup></colgroup>
	<tbody>
		<g:render template="peticion" collection="${peticiones}"/>
	</tbody>
</table>
<script type="text/javascript">
	$(document).ready(function() {
		$('#todas').text(${totales?.todas});
		$('#pendientes').text(${totales?.pendientes});
		$('#pasadas').text(${totales?.pasadas});
		$('#A_LA_ESPERA').text(${totales?.enEspera});
		$('#BAJO_GESTION').text(${totales?.enProceso});
		$('#CANCELADA').text(${totales?.canceladas});
		$('#ANULADA').text(${totales?.anuladas});
		$('#CERRADA').text(${totales?.cerradas});
	});
</script>