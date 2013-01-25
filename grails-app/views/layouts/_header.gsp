<div id="header">
	<img src="${resource(dir: 'images', file: 'cmdt_logo.png')}" style="float: left;"/>
	<div>
	<span><h1 style="line-height: 1; margin: 0;"><a class="header-main" href="${resource(dir:'/')}"><b>C</b>ompartir<b>M</b>esa<b>D</b>e<b>T</b>ren</a></h1></span>
	<span class="entrar">
	</span>
	</div>
	<div>
	<span class="header-sub">Viajar ahorrando</span>
	<span class="entrar">
		<sec:ifNotLoggedIn>
			<g:link class="buttons" controller='login' action='auth'>Entrar</g:link>
		</sec:ifNotLoggedIn>
		<sec:ifLoggedIn>
			<g:link class="buttons" controller='miCuenta' action='show'>Mi cuenta</g:link>
			<g:link class="buttons" controller='logout'>Salir</g:link>
		</sec:ifLoggedIn>
	</span>
	</div>
</div>
<div id="menu-principal">
	<g:link elementId="inicio" controller="seleccionInicial">Inicio</g:link> |
	<g:link elementId="queycomo" controller="queycomo">Qué es y cómo funciona</g:link> |
	<g:link elementId="preguntasfrecuentes" controller="preguntasfrecuentes">Preguntas frecuentes</g:link> |
	<g:link elementId="contacto" controller="contact">Contactar</g:link> 
</div>
