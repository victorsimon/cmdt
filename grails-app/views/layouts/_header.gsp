<div id="header">
	<img src="${resource(dir: 'images', file: 'cmdt_logo.png')}" style="float: left;"/>
	<div>
	<span><a class="header-main" href="${resource(dir:'/')}"><b>C</b>ompartir<b>M</b>esa<b>D</b>e<b>T</b>ren</a></span>
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
