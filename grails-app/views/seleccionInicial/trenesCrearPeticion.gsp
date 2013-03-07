<g:each in="${trenes}" var="ptren">
		<p><g:submitButton name="crearPeticion" value="${ptren.tren}" onclick="\$('#trenId').val(${ptren.tren.id});"/></p>
</g:each>
<input type="hidden" value="" id="trenId" name="trenId"/>
