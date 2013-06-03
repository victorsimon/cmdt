<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<script type="text/javascript" src="${resource(dir: 'js', file: 'bannerRotator.js')}"></script>
  		<link href="${resource(dir: 'css', file: 'style.css')}" rel="stylesheet" type="text/css">
		<style type="text/css" media="screen">

			#page-body {
				margin: 1em 1em 1em 1em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}
			
			h2 div {
				display: inline;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			.spinner {
				background: url("${resource(dir: 'images', file: 'spinner.gif')}") 50% 50% no-repeat transparent;
			    position: fixed;
			    left: 50%;
				top: 200px;
				margin-left: -50px; /* half width of the spinner gif */
			    margin-top: -50px; /* half height of the spinner gif */
				text-align: center;
				overflow: auto;
				display: none;
			}
						
			div.balloonTip {
				font-size: medium;
				width: 300px;
			}

			.banners {
				float: right;
				width: 47%;
			}

			a.mas-info {
				color: #81005F;
				font-size: 1.2em;
				font-weight: bold;
			}
			a.mas-info:link {
				text-decoration:underline;
			}
			a.mas-info:hover {
				text-decoration:underline;
			}
			a.mas-info:active {
				text-decoration:underline;
			}
			a.mas-info:visited {
				text-decoration:underline;
			}

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 .1em .1em;
				}

				#page-body h1 {
					margin-top: 0;
				}				
				
				div.balloonTip {
					font-size: small;
					width: 200px;
				}
			}
		</style>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="page-body" role="main" id="page-body">
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:form action="crearPeticion" method="POST">
				<fieldset class="form property-list">
					<div class="fieldcontain">
						<label class="property-label" for="notificar">Enviar notificaciones</label>
						<g:checkBox name="notificar" value="${notificar}" checked="${true}"/>
					</div>

					<div class="fieldcontain">
						<label class="property-label" for="noPeticion">Solo notificar</label>
						<g:checkBox name="noPeticion" value="${noPeticion}" checked="${true}"/>
					</div>

					<div class="fieldcontain">
						<label class="property-label" for="user">Selecciona el usuario</label>
						<g:select class="property-value" name="user" from="${users}" optionKey="id" value="${user?.id}"/>
					</div>

					<div class="fieldcontain">
						<label class="property-label" for="trayecto">Selecciona el trayecto</label>
						<g:select class="property-value" name="trayecto" from="${trayectos}" optionKey="id" value="${trayecto?.id}" onchange="changeBannerPrecio(); b();"/>
					</div>
					 
					<div class="fieldcontain">
						<label class="property-label" for="fecha">Buscar para el día</label>
						<input class="property-value" type="text" id="fecha" name="fecha" readonly value="${fecha ? fecha: new Date().format('dd/MM/yyyy')}" onchange="b();" size="10" maxLength="10"/>
					</div>
					<div  style="display: none;"><g:submitToRemote id="buscar" url="[action: 'trenesCrearPeticion']" update="panel" name="buscar" value="fecha" /></div>
					
				</fieldset>
				<fieldset id="panel" class="buttons">
				</fieldset>
			</g:form>
		</div>
	    <script type="text/javascript">
        b = function() {
        	$("#buscar").click();
        }
		setDatePicker = function() {
            $.datepicker.regional['es'] = {closeText: 'Cerrar',prevText: '&#x3C;Ant',nextText: 'Sig&#x3E;',currentText: 'Hoy',monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic'],dayNames: ['Domingo','Lunes','Martes','Mi&#xE9;rcoles','Jueves','Viernes','S&#xE1;bado'],dayNamesShort: ['Dom','Lun','Mar','Mi&#xE9;','Juv','Vie','S&#xE1;b'],dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S&#xE1;'],weekHeader: 'Sm',dateFormat: 'dd/mm/yy',firstDay: 1,isRTL: false,showMonthAfterYear: false,yearSuffix: ''};$.datepicker.setDefaults($.datepicker.regional['es']);
		}
        $(document).ready(function() { 
        	setDatePicker();
        	$("#fecha").datepicker($.datepicker.regional[ "es" ]);
			screenWidth = $(window).width();
			$("#buscar").click();
        })
	    </script>
	</body>
</html>
