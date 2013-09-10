<tr peticion="${it.id}">
	<td class="visible"><a class="" href='https://www.paypal.com/es/cgi-bin/webscr?cmd=_view-a-trans&id=${it.paypalTren?.payment?.paypalTransactionId}' target='_blank'>${it.paypalTren?.payment?.paypalTransactionId}</a></td>
	<td class="visible">${it.plazas}</td>
	<td class="visible">${it.salida.format('dd/MM/yyyy HH:mm')}</td>
	<td class="visible">${it.trayecto}</td>
	<td class="visible">${it.estado.name}</td>
	<td class="visible">
		<span data-tooltip data-options="disable-for-touch:false" class="has-tip" title="${it.user.phoneNumber}">${it.user.username.length() > 7? it.user.username[0..7] + '...': it.user.username}</span>
	</td>
	<td class="visible"><a href="mailto:${it.user.email}" target="_blank">${it.user.email.length() > 7? it.user.email[0..7] + '...': it.user.email}</a></td>
	<td class="visible"> 
		<!-- Modal div esta en _reservas.gsp -->
		<a class="" data-reveal-id="editModal" data-reveal-ajax="/admin/edit/${it.id}">Editar</a>
	</td>
	<td style="display:none;">
		<g:remoteLink action="detalle" id="${it.id}" update="detalle" before="\$('#spinner').fadeIn()" after="\$('#spinner').fadeOut()" />
	</td>
</tr>
<script type="text/javascript">
	$(document).ready( function() {
		window.location.hash = "principal";
	});
</script>