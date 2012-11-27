<%@ page import="compartirmesadetren.Peticion" %>



<div class="fieldcontain ${hasErrors(bean: peticionInstance, field: 'salida', 'error')} required">
	<label for="salida">
		<g:message code="peticion.salida.label" default="Salida" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="salida" precision="day"  value="${peticionInstance?.salida}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: peticionInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="peticion.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="user" name="user.id" from="${compartirmesadetren.User.list()}" optionKey="id" required="" value="${peticionInstance?.user?.id}" class="many-to-one"/>
</div>

