<html> 
	<head>
		<title>Preguntas frecuentes - Compartir Mesa De Tren</title>
		<meta name="layout" content="main"/>
		<style type="text/css">
			#faq {
				margin: 1.5em;
			}

			#faq h1 {
				color: #333333;
				font-size: 1.5em;
				font-weight: bold;
			}

			#faq h2 {
				color: #555555;
				font-size: 1em;
				font-weight: bold;
				margin: .5em;
			}

			#faq h3 {
				color: #81005F;
				font-size: 1.2em;
			}

			#faq hr {
				margin-bottom: 1em;
			}

			#faq li {
				color: #81005F;
				margin: .5em 1.5em;
			}

			#faq a {
				color: #81005F;
			}

			#faq a:hover {
				text-decoration: underline;
				cursor: pointer;
			}

			#faq h3 a:hover {
				text-decoration: none;
				cursor: text;
			}

			#faq p {
				margin: 1em;
			}


		</style>
	</head>
	<body>
		<div id="faq">
			<h1>Preguntas frecuentes</h1>
			<h2>SITO responde tus preguntas más frecuentes y te invita a que le mandes tus sugerencias. También puedes enviarle una consulta si no encuentras la respuesta que buscas.</h2>
			<br/>
			<g:set var="faqs" value="${compartirmesadetren.FAQ.findAll()}"/>
			<h3>Indice</h3>
			<ol>
				<g:each in="${faqs}" status="i" var="faq">
					<li><a href="#faq${i}">${faq.pregunta}</a></li>
				</g:each>
				<li><a href="#sug">Envíanos tus sugerencias y/o dudas</a></li>
			</ol>
			<g:each in="${faqs}" status="i" var="faq">
				<hr/>
				<h3><a id="faq${i}">${faq.pregunta}</a></h3>
				<p><%=faq.respuesta%></p>
				<a href="#">Volver arriba</a>
			</g:each>
			<hr/>
			<h3><a id="sug">No dudes en enviarnos tus sugerencias, consultas, críticas, lo que sea en este apartado, intentaremos contestarte con la mayor brevedad posible:</a></h3>
			<g:form>
				<g:textArea style="width: 100%; height: 6em; margin: 1em 0 1em 0;" name="texto" id="texto" value="" />
				<g:submitToRemote name="comentario"	url="[controller: 'comentario', action: 'enviar']" update="comentario" value="Enviar" after="\$('#texto').val('')"/>
			</g:form>
		</div>
	</body>
</html>