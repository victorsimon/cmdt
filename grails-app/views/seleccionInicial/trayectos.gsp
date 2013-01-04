<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
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
				background: url(../images/spinner.gif) 50% 50% no-repeat transparent;
			    position: fixed;
			    left: 50%;
				top: 50%;
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
	    <script type="text/javascript">
        b = function() {
        	$("#buscar").click();
        }
	    </script>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="page-body" role="main" id="page-body">
			<g:form action="trenes" method="POST">
				<fieldset class="form property-list">
					<div class="fieldcontain">
						<label class="property-label" for="trayecto">Selecciona el trayecto</label>
						<g:select class="property-value" name="trayecto" from="${trayectos}" optionKey="id" value="${trayecto?.id}" />
						<img id="info1" class="property-info" src="${resource(dir: 'images', file: 'info.gif')}" alt="info" />
					</div>
					 
					<div class="fieldcontain">
						<label class="property-label" for="fecha">Buscar para el día</label>
						<input class="property-value" type="text" id="fecha" name="fecha" readonly value="${fecha ? fecha: new Date().plus(2).format('dd/MM/yyyy')}" onchange="b();" size="10" maxLength="10"/>
						<img id="info2"  class="property-info" src="${resource(dir: 'images', file: 'info.gif')}" alt="info"/>
					</div>
					<div  style="display: none;"><g:submitToRemote id="buscar" url="[action: 'trenes']" update="panel" name="buscar" value="fecha" /></div>
					
					<div class="fieldcontain">
						<label class="property-label" for="ofertas">O vea </label>
						<g:submitToRemote class="property-value" name="ofertas" url="[action: 'proximos']" update="panel" value="las ofertas disponibles" />
						<img id="info3" class="property-info" src="${resource(dir: 'images', file: 'info.gif')}" alt="info"/>
					</div>
				</fieldset>
				<fieldset id="panel" class="buttons">
				</fieldset>
			</g:form>
		</div>
	    <script type="text/javascript">
	    var info1 = "<p>¡Hola! Mi nombre es SITO ...<img src='images/SITO.png' style='float: right; width: 48px; height: 60px;' /><br/>... y quiero ayudarte " + 
	    	"a encontrar una mesa para que puedas <b>beneficiarte</b> e del descuento que Renfe" + 
	    	"ofrece para compartir una mesa. Yo te lo " + 
	    	"<b>gestionaré y venderé</b> de forma <b>individual</b>.</p>";
	    var info2 = "Elige la fecha que más te convenga.<img src='images/SITO.png' style='float: right; width: 48px; height: 60px;' /><br/>" +  
	    	"Recuerda realizarlo con cierta antelación, así…<br/>" +
	    	"<b>¡Te ofreceré MEJOR servicio y MEJOR precio!</b>";
	    var info3 = "Si tienes flexibilidad de fechas…<img src='images/SITO.png' style='float: right; width: 48px; height: 60px;' /><br/>" +
	    	"¡este es tu apartado! No dejes de mirar las<br/>" + 
	    	"<b>OPORTUNIDADES YA DISPONIBLES</b>.";
		prepareBalloon = function(id, text) {
	        $(id).click(function(e) {
					$(this).showBalloon({contents: text});
				});
			$(id).mouseleave(function(e) {
					$(this).hideBalloon();
				});
		}
        $(document).ready(function() { 
            $.datepicker.regional['es'] = {closeText: 'Cerrar',prevText: '&#x3C;Ant',nextText: 'Sig&#x3E;',currentText: 'Hoy',monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic'],dayNames: ['Domingo','Lunes','Martes','Mi&#xE9;rcoles','Jueves','Viernes','S&#xE1;bado'],dayNamesShort: ['Dom','Lun','Mar','Mi&#xE9;','Juv','Vie','S&#xE1;b'],dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S&#xE1;'],weekHeader: 'Sm',dateFormat: 'dd/mm/yy',firstDay: 1,isRTL: false,showMonthAfterYear: false,yearSuffix: ''};$.datepicker.setDefaults($.datepicker.regional['es']);$("#fecha").datepicker($.datepicker.regional[ "es" ]);
            $.balloon.defaults.classname = 'balloonTip';
			screenWidth = $(window).width();
			if (screenWidth > 480) {
				$.balloon.defaults.position = 'right';
			} else {
				$.balloon.defaults.position = 'left';
			}
            $.balloon.defaults.css = {
            	  minWidth: "20px",
            	  padding: "5px",
            	  borderRadius: "6px",
            	  border: "solid 1px #666666",
            	  boxShadow: "4px 4px 4px #555",
            	  color: "#666666",
            	  backgroundColor: "#fff",
            	  opacity: "1",
            	  zIndex: "32767",
            	  textAlign: "left"
            };
			prepareBalloon('#info1', info1);
			prepareBalloon('#info2', info2);
			prepareBalloon('#info3', info3);
        })
	    </script>
	</body>
</html>
