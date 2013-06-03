<tr peticion="${it.id}">
	<td style="display:none;">
		<g:remoteLink action="detalle" id="${it.id}" update="detalle" />
	</td>
	<td class="visible">${it.paypalTren?.payment?.paypalTransactionId}</td>
	<td class="visible">${it.salida.format('dd/MM/yyyy hh:mm')}</td>
	<td class="visible">${it.trayecto}</td>
	<td class="visible">${it.estado.name}</td>
	<td class="visible">${it.user.username}</td>
	<td class="visible">${it.user.email}</td>
</tr>