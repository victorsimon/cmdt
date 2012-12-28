<div id="header">
	<div>
	<span><a class="header-main" href="${resource(dir:'/')}">Compartir Mesa de Tren</a></span>
	<span class="entrar">
		<sec:ifLoggedIn>
			<div class="bienvenida">Hola <sec:loggedInUserInfo field="username"/></div>
		</sec:ifLoggedIn>
	</span>
	</div>
	<div>
	<span class="header-sub">... para viajar más barato</span>
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