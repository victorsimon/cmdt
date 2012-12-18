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

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
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
			
			div.info img {
				display:inline;
  				height: 2em;
				width: 2em;
				vertical-align: bottom;
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
			}
		</style>
		<g:javascript library="jquery" />
		<g:javascript library="jquery-ui" />
		<g:javascript library="jquery-balloon" />
	    <script type="text/javascript">
        b = function() {
        	$("#buscar").click();
        }
	    </script>
	</head>
	<body>
		<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div id="page-body" role="main">
			<g:form action="trenes" method="POST">
				<fieldset class="form">
					<h2><div id="info1" class="info">Selecciona el trayecto 
					<g:select name="trayecto" from="${trayectos}" optionKey="id" value="${trayecto?.id}" />
					<img src="images/info.png" alt="info"/>
					</div></h2>
					<h2><div id="info2" class="info">Buscar para el día 
					<input type="text" id="fecha" name="fecha" readonly value="${fecha ? fecha: new Date().plus(2).format('dd/MM/yyyy')}" onchange="b();">
					<img src="images/info.png" alt="info"/>
					</div></h2>
					<div  style="display: none;"><g:submitToRemote id="buscar" url="[action: 'trenes']" update="panel" name="buscar" value="fecha" /></div>
					<h2><div id="info3" class="info">O vea 
					<g:submitToRemote url="[action: 'proximos']" update="panel" value="las ofertas disponibles" />
					<img src="images/info.png" alt="info"/>
					</div></h2>
				</fieldset>
				<fieldset id="panel" class="buttons">
				</fieldset>
			</g:form>
		</div>
	    <script type="text/javascript">
	    var info1 = "<p>¡Hola! Mi nombre es SITO ...<br/>... y quiero ayudarte " + 
	    	"a encontrar una mesa para que<br/>puedas <b>beneficiarte</b> e del descuento que Renfe<br/>" + 
	    	"ofrece para compartir una mesa. Yo te lo<br/>" + 
	    	"<b>gestionaré y venderé</b> de forma <b>individual</b>.</p>";
	    var info2 = "Elige la fecha que más te convenga.<br/>" +  
	    	"Recuerda realizarlo con cierta antelación, así…<br/>" +
	    	"<b>¡Te ofreceré MEJOR servicio y MEJOR precio!</b>";
	    var info3 = "Si tienes flexibilidad de fechas…<br/>" +
	    	"¡este es tu apartado! No dejes de mirar las<br/>" + 
	    	"<b>OPORTUNIDADES YA DISPONIBLES</b>.";
        $(document).ready(function() { 
            $.datepicker.regional['es'] = {closeText: 'Cerrar',prevText: '&#x3C;Ant',nextText: 'Sig&#x3E;',currentText: 'Hoy',monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic'],dayNames: ['Domingo','Lunes','Martes','Mi&#xE9;rcoles','Jueves','Viernes','S&#xE1;bado'],dayNamesShort: ['Dom','Lun','Mar','Mi&#xE9;','Juv','Vie','S&#xE1;b'],dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S&#xE1;'],weekHeader: 'Sm',dateFormat: 'dd/mm/yy',firstDay: 1,isRTL: false,showMonthAfterYear: false,yearSuffix: ''};$.datepicker.setDefaults($.datepicker.regional['es']);$("#fecha").datepicker($.datepicker.regional[ "es" ]);
            $.balloon.defaults.position = 'top';
            $.balloon.defaults.css = {
            	  minWidth: "20px",
            	  padding: "5px",
            	  borderRadius: "6px",
            	  border: "solid 1px #777",
            	  boxShadow: "4px 4px 4px #555",
            	  color: "#000",
            	  backgroundColor: "#fff",
            	  opacity: "1",
            	  zIndex: "32767",
            	  textAlign: "left"
            };
	        $('#info1 img').balloon({contents: info1});
	        $('#info2 img').balloon({contents: info2});
	        $('#info3 img').balloon({contents: info3});
        })
	    </script>
	</body>
</html>
