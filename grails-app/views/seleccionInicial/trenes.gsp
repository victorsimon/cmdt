<g:if test="${trenes}">
	<h2>Trenes disponibles</h2>
</g:if>
<g:if test="${!trenes}">
	<h2>Noy hay trenes disponibles</h2>
</g:if>
<g:each in="${trenes}" var="ptren">
	<g:if test="${!ptren.tren.noValido}">
		<p><g:link action="detalle" id="${ptren.tren.id}">
				${ptren.tren} <g:if test="${ptren.oportunidad}">
									<img src="${resource(dir: 'images', file: 'destacado.png')}" 
										alt="Oportunidad" height="16" width="16" /> 
							</g:if> 
		</g:link></p>
	</g:if>
	<g:if test="${ptren.tren.noValido}">
		<p>${ptren.tren} NO DISPONIBLE</p>
	</g:if>
</g:each>
