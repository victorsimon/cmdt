<g:if test="${trenes}">
	<h2>Trenes disponibles ${trayecto}</h2>
</g:if>
<g:if test="${!trenes}">
	<g:if test="${ofertas}">
		<div style="text-align: center; float: right; ">
			<h2>Lo siento, actualmente no tenemos ofertas disponibles</h2>
			<h2 style="color: #81005F;">Si quieres estar al día de las ofertas de billetes en trenes, síguenos en</h2>
			<a style="color: #81005F;" href="http://www.facebook.com/compartirtumesadetren" target="_blank">
				<img alt='tren-de-renfe-horarios-ave-tarifa-mesa-compartir-tarifa mesa-' style="width: 32px; height: 32px; margin: 0 .5em;" src="http://www.compartirmesadetren.com/images/Facebook.png" /></a>
			<a style="color: #81005F;" href="https://plus.google.com/u/0/b/112375915542570953380/112375915542570953380/posts" target="_blank">
				<img alt='tren-de-renfe-horarios-ave-tarifa-mesa-compartir-tarifa mesa-' style="width: 32px; height: 32px; margin: 0 .5em;" src="http://www.compartirmesadetren.com/images/Twitter.png" /></a>
			<a style="color: #81005F;" href="https://twitter.com/CompartirMesaDe" target="_blank">
				<img alt='tren-de-renfe-horarios-ave-tarifa-mesa-compartir-tarifa mesa-' style="width: 32px; height: 32px; margin: 0 .5em;" src="http://www.compartirmesadetren.com/images/Google+.png" /></a>
		</div>
	</g:if>
	<g:if test="${!ofertas}"><h2>Lo siento, noy hay trenes disponibles para el día selecionado.</h2></g:if>
</g:if>
<g:each in="${trenes}" var="ptren">
	<g:if test="${!ptren.tren.noValido}">
		<p><g:link action="detalle" id="${ptren.tren.id}">
				${ptren.tren} <g:if test="${ptren.oportunidad}">
									<img alt='tren-de-renfe-horarios-ave-tarifa-mesa-compartir-tarifa mesa-' src="${resource(dir: 'images', file: 'destacado.png')}" 
										alt="Oportunidad" height="16" width="16" /> 
							</g:if> 
		</g:link></p>
	</g:if>
	<g:if test="${ptren.tren.noValido}">
		<p><g:link action="detalle" id="${ptren.tren.id}">
				${ptren.tren} <g:if test="${ptren.oportunidad}">
									<img alt='tren-de-renfe-horarios-ave-tarifa-mesa-compartir-tarifa mesa-' src="${resource(dir: 'images', file: 'destacado.png')}" 
										alt="Oportunidad" height="16" width="16" /> 
							</g:if> 
		<small> *Plazas limitadas</small></g:link></p>
	</g:if>
</g:each>
