<div id="footer" style="padding-bottom: .5em;">
<hr>
	<div style="display: block;">
		<!-- Boton follow twitter -->
		<div style="heght: 23px; wdth: 183px; margin: 5px; float: left;"><a href="https://twitter.com/CompartirMesaDe" class="twitter-follow-button" data-show-count="false" data-lang="es">Seguir a @CompartirMesaDe</a></div>
		<!-- Boton de me gusta para facebook -->
		<div style="heght: 20px; wdth: 97px; margin: 5px;" class="fb-like" data-href="http://www.compartirmesadetren.com" data-send="false" data-layout="button_count" data-width="450" data-show-faces="false"></div>
		<!-- Boton +1 de google+ -->
		<div style="heght: 24px; wdth: 300px; margin: 5px; top: 10px;" class="g-plusone" data-annotation="inline" data-width="450" ></div>
		<script type="text/javascript" src="https://apis.google.com/js/plusone.js"></script>
		<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="//platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
		<div style="float: left; margin: 5px;" id="fb-root"></div><script>(function(d, s, id) {  var js, fjs = d.getElementsByTagName(s)[0];  if (d.getElementById(id)) return;  js = d.createElement(s); js.id = id;  js.src = "//connect.facebook.net/es_ES/all.js#xfbml=1";  fjs.parentNode.insertBefore(js, fjs);}(document, 'script', 'facebook-jssdk'));</script>
	</div>
</div>
<g:form style="border: 1px solid #81005F; margin: 0 .5em; padding: 1em;">
<h3 style="margin: 0 1em 1em 1em;"><font size="5">¡Exprésate! ¡Coméntanos lo que quieras!</font><br/><font size="3">(Sugerencias, críticas, opiniones, etc. Sientete libre ;-) )</font></h3>
<g:textArea class="property-value" style="float:left; width: 85%; height: 1.5em; margin: 0 1em 0 1em;" name="texto" id="texto" value="" />
<g:submitToRemote class="property-value" name="comentario"	url="[controller: 'comentario', action: 'enviar']" update="comentario" value="Enviar" />
<div id="comentario"></div>
</g:form>
<div id="footer">
<div style="margin-left:auto; margin-right:auto; text-align: center;">
<g:link controller="contact" style="font-size: 1.2em; color: #81005E; font-style: normal;">Contactar</g:link> | 
<g:link controller="contact" style="font-size: 1.2em; color: #81005E; font-style: normal;">Cómo funciona</g:link> |
<g:link controller="contact" style="font-size: 1.2em; color: #81005E; font-style: normal;">Preguntas frecuentes</g:link>
</div>
<hr />
<g:copyrigth startYear="2010">CSMovil, S.L.</g:copyrigth>
</div>
