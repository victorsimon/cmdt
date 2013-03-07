<div id="header">
	<img alt="tren-de-renfe-horarios-ave-tarifa-mesa-compartir-tarifa mesa-" src="${resource(dir: 'images', file: 'cmdt_logo.png')}" style="float: left;"/>
	<div>
		<h1 style="font-size: 1em; margin: 0;"><a class="header-main" href="${resource(dir:'/')}"><b>C</b>ompartir<b>M</b>esa<b>D</b>e<b>T</b>ren</a></h1>
	</div>
	<div>
	<span class="header-sub">Viajar ahorrando en AVE y ALVIA</span>
	<span class="entrar">
		<div class="icono_rrss" style="border-bottom: 1px solid #777777; border-left: 1px solid #777777; border-right: 1px solid #777777; border-radius: 30px; position: fixed; top: 0px; right: 0px; padding: .2em; background-color: #EEEEEE;">
		<a style="color: #81005F;" href="http://www.facebook.com/compartirtumesadetren" target="_blank">
			<img alt="tren-de-renfe-horarios-ave-tarifa-mesa-compartir-tarifa mesa-" style="width: 18px; height: 18px; margin: 0 .2em;" src="http://www.compartirmesadetren.com/images/Facebook.png" /></a>
		<a style="color: #81005F;" href="https://plus.google.com/u/0/b/112375915542570953380/112375915542570953380/posts" target="_blank">
			<img alt="tren-de-renfe-horarios-ave-tarifa-mesa-compartir-tarifa mesa-" style="width: 18px; height: 18px; margin: 0 .2em;" src="http://www.compartirmesadetren.com/images/Twitter.png" /></a>
		<a style="color: #81005F;" href="https://twitter.com/CompartirMesaDe" target="_blank">
			<img alt="tren-de-renfe-horarios-ave-tarifa-mesa-compartir-tarifa mesa-" style="width: 18px; height: 18px; margin: 0 .2em;" src="http://www.compartirmesadetren.com/images/Google+.png" /></a>
		</div>
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
	<g:link elementId="blog" url="http://compartirmesadetren.blogspot.com.es/">Blog</g:link> | 
	<g:link elementId="contacto" controller="contact">Contactar</g:link> 
</div>
<h2 style="display: none;">Compartir mesa de tren, ave, alvia, avant de renfe. Trayectos y horarios. Viaje en tren barato.</h2>
