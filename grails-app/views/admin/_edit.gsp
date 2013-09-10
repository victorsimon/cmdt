<a id="closeModal" class="close-reveal-modal">x</a>
<g:eachError bean="${peticion}">
    <li>${it}</li>
</g:eachError>
<g:formRemote name="peticionEdit" update="editModal" url="[controller: 'admin', action: 'save']" method="post" after="\$('#closeModal').trigger('click')">
	<fieldset>
		<input name="id" id="id" type="hidden" value="${peticion.id}"/>
		<div class="row">
			<div class="large-6 columns">
				<label for="trayecto">Trayecto</label>
				<g:select name="trayecto" id="trayecto" from="${trayectos}" optionKey="id" value="${peticion.trayecto}"/>
			</div>
			<div class="large-6 columns">
				<label for="estado">Estado</label>
				<g:select name="estado" id="estado" from="${compartirmesadetren.EstadoPeticion}" value="${peticion.estado}"/>
			</div>
		</div>
		<div class="row">
			<div class="large-6 columns">
				<label for="fecha">Salida</label>
				<input type="text" id="fecha" name="fecha" value="${peticion.salida.format('dd/MM/yyyy')}"/>
			</div>
			<div class="large-6 columns">
				<label for="hora">Hora</label>
				<input type="time" id="hora" name="hora" value="${peticion.salida.format('HH:mm')}"/>
			</div>
		</div>
	</fieldset>
	<fieldset>
		<legend>Valores originales</legend>
		<div class="row">
			<div class="large-3 columns">
				<label>${peticion.trayecto}</label>
			</div>
			<div class="large-3 columns">
				<label>${peticion.estado}</label>
			</div>
			<div class="large-3 columns">
				<label>${peticion.salida.format('dd/MM/yyyy')}</label>
			</div>
			<div class="large-3 columns">
				<label>${peticion.salida.format('HH:mm')}</label>
			</div>
		</div>
	</fieldset>
	<div class="row">
		<div class="large-4 push-8 columns">
			<input type="submit" value="Guardar" class="small button" />
			<button class="small button secondary" onclick="$('#closeModal').trigger('click');">Cancelar</button>
		</div>
	</div>
</g:formRemote>
