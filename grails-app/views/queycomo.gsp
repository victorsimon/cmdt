<html> 
	<head>
		<meta name="layout" content="main"/>
		<style type="text/css">
			h1 {color: #81005F;}
			div .seccion {
				padding: 1em;
			}
			.seccion a {
				font-weight: bold;
				text-decoration: underline;
			}
			.seccion a:hover {color: #81005F;}
			.seccion a:link {color: #81005F;}
			.seccion a:visited {color: #81005F;}
			.seccion a:active {color: #81005F;}
			div .contenido {
				margin: 1em;
			}
			div .contenido h2 {
				font-size: 1em;
			}
			div .contenido p {
				padding: .6em;
			}
			div .contenido img {
				margin: 1em;
			}
			table {
				margin: 0;
				border: 0;
				border-collapse: collapse;
				width: 100%;
				margin-bottom: 0;
			}
			tr {
				border: 0;
			}
			tr>td:first-child {
				padding-left: 0;
			}
			tr>td:last-child {
				padding-right: 0;
			}
			td {
				line-height: 1em;
				padding: 0;
				text-align: left;
				vertical-align: top;
			}
			tr:hover {
				background: white;
			}
		</style>
	</head>
	<body>
		<div class="seccion" id="s1" style="display: inline;">
			<div class="contenido">
				<h1>¿Qué es CompartirMesaDeTren?</h1>
				<p>¡Hola! Mi nombre es SITO ...<img alt="tren-de-renfe-horarios-ave-tarifa-mesa-compartir-tarifa mesa-" src='${resource(dir: 'images', file: 'SITO.png')}' style='float: left; width: 68px; height: 80px;' /></p>
				<p>... CompartirMesaDeTren es el portal en el que gestionaré (de forma integral e individual) la venta de billetes con dos días de antelación. Está basada en el descuento que Renfe ofrece; aquel en el que compartiendo una mesa de 4 personas, <b>podéis conseguir un precio inmejorable entre los 3 precios que os ofrecemos</b>.</p>
				<p><b>Recordad: contra más gente viajéis, más barato os puede salir.</b>
				</p>
				<h1>¿Cómo funciona?</h1>
				<p><b>Lo primero de todo, debes recordar que gestiono la venta de billetes siempre que sea con dos días de antelación. (Para poder gestionar el billete y conseguirte el mejor precio posible).</b></p>
				<p>Según se apunte gente para viajar en un tren en concreto, iré comprando las plazas necesarias; por lo que a partir de tres compañeros, tendrás plaza segura en el tren deseado.</p>
				<p>Una vez se cierre el tiempo de reservas (dos días antes de la salida del tren) distribuiré los billetes que he adquirido y procederé a cobrar a cada pasajero el importe correspondiente.</p>
				<p>Por supuesto, te mantendré informado en todo momento de el estado de tu reserva.</p>
				<p>Si por algún casual, no consigo juntar la gente suficiente en el tren que has escogido, te avisaré con tiempo de sobra, ofreciendote alternativas que te puedan interesar. Si estas alternativas no encajan con tus necesidades, anularemos tu reserva sin ningún tipo de cargo.</p>
			</div>
			<center><a href="#" onclick="$('#s1').css('display', 'none');$('#s2').css('display','inline');">¿Quiéres saber cómo se usa? pincha AQUI..</a></center>
		</div>
		<div class="seccion" id="s2" style="display: none;">
			<div class="contenido">
				<img alt="tren-de-renfe-horarios-ave-tarifa-mesa-compartir-tarifa mesa-" src='${resource(dir: 'images', file: 'como_1.png')}' style='float: right; width: 40%;' />
				<h1>¿Cómo se usa?</h1>
				<h2>Simulemos una venta...</h2>
				<p>Supón que quieres viajar el en una fecha concreta, por ejemplo, el 22 de Enero de 2013, desde Madrid a Pamplona:</p>
				<p><b>1º Elijo la fecha y/o veo las oportunidades* disponibles. ¡Me apunto!</b></p>
				<p><i>*No dejes de ver las ofertas disponibles, podrás obtener el billete asegurado</i></p>
				<img alt="tren-de-renfe-horarios-ave-tarifa-mesa-compartir-tarifa mesa-" src='${resource(dir: 'images', file: 'como_2.png')}' style='float: left; width: 40%;' />
				<br/>
				<p><b>2º Si aún no lo he hecho, introduzco mi usuario y clave. Si todavía no soy un usuario registrado, creo una nueva cuenta en Compartir Mesa de Tren.</b></p>
				<p>También puedo entrar con alguna de mis cuentas* en Twitter, Facebook o Google+. </p>
				<p><i>*Jamás extraeremos ni utilizaremos tus datos ni tu información personal. ¡Prometido!</i></p>
			</div>
			<br/>
			<center>
				<a href="#" onclick="$('#s1').css('display','inline');$('#s2').css('display','none');"><< Anterior</a>
				<a href="#" onclick="$('#s2').css('display','none');$('#s3').css('display','inline');">Siguiente >></a>
			</center>
		</div>
		<div class="seccion" id="s3" style="display: none;">
			<div class="contenido" style="width: 100%; padding: 0; margin: 0;">
				<table>
					<tr>
						<td>
							<div style="padding: 1em; margin: 0;">
								<p><b>3º a) Si decidimos crear una cuenta nueva, veremos la siguiente pantalla en la que rellenaremos el usuario, email y una contraseña.</b></p> 
								<p>El teléfono lo puedes poner para que nos comuniquemos o enviemos el billete si así lo deseas, pero no es obligatorio.</p>
								<img alt="tren-de-renfe-horarios-ave-tarifa-mesa-compartir-tarifa mesa-" src='${resource(dir: 'images', file: 'como_3.png')}' style="width: 90%;"/>
								<p>Después de completar el formulario y crear la cuenta, te enviaremos un e-mail que deberás confirmar.</p>
							</div>
						</td>
						<td>
							<div style="padding: 1em; margin: 0;">
								<p><b>3º b) Si entramos por medio de una de nuestras cuentas de usuario en redes sociales, veremos la siguiente pantalla.</b></p> 
								<p>Te pediremos el email que deseas que usemos para comunicarnos contigo.</p>
								<p>El teléfono lo puedes poner para que nos comuniquemos o enviemos el billete si así lo deseas, pero no es obligatorio.</p>
								<img alt="tren-de-renfe-horarios-ave-tarifa-mesa-compartir-tarifa mesa-" src='${resource(dir: 'images', file: 'como_4.png')}' style="width: 90%;" />
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div style="width: 100%;"><center>
				<a href="#" onclick="$('#s2').css('display','inline');$('#s3').css('display','none');"><< Anterior</a>
				<a href="#" onclick="$('#s3').css('display','none');$('#s4').css('display','inline');">Siguiente >></a>
			</center></div>
		</div>
		<div class="seccion" id="s4" style="display: none;">
			<div class="contenido">
				<img alt="tren-de-renfe-horarios-ave-tarifa-mesa-compartir-tarifa mesa-" src='${resource(dir: 'images', file: 'como_5.png')}' style='float: right; width: 60%;' />
				<p><b>4º Si ya disponias de usuario o te has podido crear una cuenta, llegarás a la pantalla de reserva. Comprueba que es el tren deseado antes de proceder a reservar.</b></p>
				<p><i>Recuerda que <b>LA RESERVA NO IMPLICA EL CARGO EN TU CUENTA</b>. Tan solo se comprueba la disponibilidad de fondos.</i></p>
				<p>Este paso es una confirmación de  tu interés por el billete y por los tres tipos de precios que te ofrecemos. El cargo final no será ni más ni menos. Son precios finales.</p>
				<p>Cuando pulses en 'Reservar ahora' entrarás en un formulario de pago con tarjeta que debes cumplimentar. Cuando termines los pasos necesarios, si la comprobación de la tarjeta a sido satisfactoria, volveras a Compartir Mesa De Tren y llegará a tu buzón una confirmación de tu reserva</p>
				<p>Desde este punto, te notificaré el estado de tu reserva en todo momento. También podrás verlo en cualquier momento accediendo a 'Mi cuenta' -> 'Solicitudes'.</p>
			</div>
			<br/>
			<center>
				<a href="#" onclick="$('#s3').css('display','inline');$('#s4').css('display','none');"><< Anterior</a>
				<a href="#" onclick="$('#s4').css('display','none');$('#s5').css('display','inline');">Siguiente >></a>
			</center>
		</div>
		<div class="seccion" id="s5" style="display: none;">
			<div class="contenido">
				<img alt="tren-de-renfe-horarios-ave-tarifa-mesa-compartir-tarifa mesa-" src='${resource(dir: 'images', file: 'SITO.png')}' style='float: right; width: 88px; height:100px;' />
				<p><b>Deja que te gestione tu billete en el mínimo tiempo posible. Intentaré conseguirte el mejor de los tres precios. A más pasajeros, más posibilidades.</b></p>
				<p>Por ello, no dejes de compartir tu trayecto en tus redes sociales preferidas.</p>
				<p>YA PODÉIS COMPARTIR LA EXPERIENCIA DE AHORRAR VIAJANDO EN UNO DE LOS MEDIOS MÁS RÁPIDOS Y CÓMODOS.</p>
				<p><i>*La gestión y compra/venta se realizará hasta 48 hrs. antes de la salida, siempre sujeto a disponibilidad. Si no se consigue agrupar usuarios a una mesa se avisará con suficiente tiempo y/o ofrecerán otras posibilidades.</i></p>
			</div>
			<br/>
			<center><a href="#" onclick="$('#s4').css('display','inline');$('#s5').css('display','none');"><< Anterior</a></center>
		</div>
	</body>
</html>