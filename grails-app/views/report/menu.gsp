<html>

<head>
<meta name='layout' content='main'/>
<style type="text/css" media="screen">
	* {
		/*border: 1px solid black;*/
	}
	
	fieldset.property-list {
		width: auto;
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
		text-align: center;
		vertical-align: top;
	}
	tr:hover {
		background: white;
	}
</style>
</head>

<body>
	<div class="page-body">
		<ul style="margin: 1em 3em;">
			<g:each var="r" in="${reports}">
				<li><g:jasperReport
				          jasper="${r.jasper}"
				          format="${r.format}"
				          name="${r.name}"
				          description="${r.desc}">
				          	<ul style="margin: 1em 3em;">
					        	<g:each var="p" in="${r.parameters}">
					        		<li>${p.name}: <input type="text" name="${p.name}" value="0"/></li>
					    		</g:each>
					    	</ul>
				</g:jasperReport></li>
			</g:each>
		</ul>
	</div>
</body>