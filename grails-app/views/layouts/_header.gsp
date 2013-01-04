<style>
<!--
.header-sub {
	color: #7d7a7d;
	padding-left: 20px;
}

#header {
	position: relative; 
	z-index: 1; 
	padding-left: 90px;
	background-repeat: no-repeat; 
	background-size: contain;
	background-image: linear-gradient(right bottom, #81005F 0%, #DDDDDD 50%);
	background-image: -o-linear-gradient(right bottom, #81005F 0%, #DDDDDD 50%);
	background-image: -moz-linear-gradient(right bottom, #81005F 0%, #DDDDDD 50%);
	background-image: -webkit-linear-gradient(right bottom, #81005F 0%, #DDDDDD 50%);
	background-image: -ms-linear-gradient(right bottom, #81005F 0%, #DDDDDD 50%);
	
	background-image: -webkit-gradient(
		linear,
		right bottom,
		left top,
		color-stop(0, #81005F),
		color-stop(0.50, #DDDDDD)
	);
}

#header img {
	margin-top: -2em; 
	margin-left: -5.5em;
}

.entrar a {
	font-size: .75em;
}

@media screen and (max-width: 480px) {
	#header {
		padding-left: 10px;
	}
	
	#header img {
		margin-top: -.5em;
		margin-left: -.5em;
	}
}
-->
</style>
<div id="header" style="">
	<img src="${resource(dir: 'images', file: 'cmdt_logo.png')}" style="float: left;"/>
	<div>
	<span><a class="header-main" href="${resource(dir:'/')}"><b>C</b>ompartir<b>M</b>esa<b>D</b>e<b>T</b>ren</a></span>
	<span class="entrar">
		<sec:ifLoggedIn>
			<div class="bienvenida">Hola <sec:loggedInUserInfo field="username"/></div>
		</sec:ifLoggedIn>
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
