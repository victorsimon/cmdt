<table class="responsive">
	<thead>
		<tr>
			<th>ID Paypal</th>
			<th>Plzs</th>
			<th>Salida</th>
			<th>Trayecto</th>
			<th>Estado</th>
			<th>Usuario</th>
			<th>email</th>
			<th></th>
			<th style="display: none;">HIDDEN</th>
		</tr>
	</thead>
	<tbody>
		<g:render template="peticion" collection="${peticiones}"/>
	</tbody>
</table>
<script type="text/javascript">
	var setTotal = function (id, total) {
		$(id).text(total);
		if (total > 0) {
			$(id).addClass('alert');
			$(id).removeClass('success');
		} else {
			$(id).addClass('success');
			$(id).removeClass('alert');
		}
	};

	var updateTotals = function(totales) {
		$('#todas').text(totales.todas);
		$('#pendientes').text(totales.pendientes);
		$('#pasadas').text(totales.pasadas);
		$('#IGNORAR').text(totales.ignorar);
		$('#A_LA_ESPERA').text(totales.enEspera);
		$('#BAJO_GESTION').text(totales.enProceso);
		$('#CANCELADA').text(totales.canceladas);
		$('#CANCELAR_FIANZA').text(totales.cancelarFianza);
		if (totales.cancelarFianza > 0) {
			$('#CANCELAR_FIANZA').addClass('alert');
			$('#CANCELAR_FIANZA').removeClass('success');
		} else {
			$('#CANCELAR_FIANZA').addClass('success');
			$('#CANCELAR_FIANZA').removeClass('alert');
		}
		$('#ANULADA').text(totales.anuladas);
		$('#COBRAR_FIANZA').text(totales.cobrarFianza);
		if (totales.cobrarFianza > 0) {
			$('#COBRAR_FIANZA').addClass('alert');
			$('#COBRAR_FIANZA').removeClass('success');
		} else {
			$('#COBRAR_FIANZA').addClass('success');
			$('#COBRAR_FIANZA').removeClass('alert');
		}
		$('#CERRADA').text(totales.cerradas);
		$('#VIGILAR').text(totales.vigilar);
		$('#RESERVAR').text(totales.reservar);
		if (totales.reservar > 0) {
			$('#RESERVAR').addClass('alert');
			$('#RESERVAR').removeClass('success');
		} else {
			$('#RESERVAR').addClass('success');
			$('#RESERVAR').removeClass('alert');
		}
		setTotal('#COBRAR', totales.cobrar);
		$('#COMPRAR').text(totales.comprar);
		if (totales.comprar > 0) {
			$('#COMPRAR').addClass('alert');
			$('#COMPRAR').removeClass('success');
		} else {
			$('#COMPRAR').addClass('success');
			$('#COMPRAR').removeClass('alert');
		}
		$('#ENVIAR').text(totales.enviar);
		if (totales.enviar > 0) {
			$('#ENVIAR').addClass('alert');
			$('#ENVIAR').removeClass('success');
		} else {
			$('#ENVIAR').addClass('success');
			$('#ENVIAR').removeClass('alert');
		}
		//$(window).trigger('redraw');
	};

	var callTotals = function() {
		$.post('/admin/totales', function(data) {
			updateTotals(data);
		});
	};

	$(document).ready( function() {
		setInterval('callTotals()', 5000);
		callTotals();
		$(window).trigger('redraw');
		});
</script>