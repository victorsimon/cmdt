<style>
	#info * {
		font-size: 0.95em;
		color:#555555;
	}
	#info table tr {
		cursor:auto;
	}
	#info {
		height:20%;
		width:100%;
		float:left;
	}
	#info input {

	}
	#command {
		float:left;
	}
</style>
<span id="info">
	<table>
		<tr>
			<td><label for="salida">Salida</label></td>
			<td><input type="text" name="salida" id="salida" disabled="disabled" value="${peticion.salida.format('dd/MM/yyyy hh:mm')}"/></td>
		</tr>
		<tr>
			<td><label for="trayecto">Trayecto</label></td>
			<td><g:select name="trayecto" from="${compartirmesadetren.Trayecto.list()}" optionKey="id" value="${peticion.trayecto?.id}" disabled="disabled"/>
			</td>
		</tr>
		<tr>
			<td><label for="user">Usuario</label></td>
			<td><input type="text" name="user" value="${peticion.user.username}" disabled/></td>
		</tr>
		<tr>
			<td><label for="estado">Estado</label></td>
			<td><input type="text" name="estado" value="${peticion.estado.name}" disabled/></td>
		</tr>
	</table>
</span>
<span id="command">
	<input type="button"/>	
</span>
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
