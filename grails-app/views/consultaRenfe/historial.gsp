<% def last %>
<g:each var="linea" in="${datos}">
	<% 
	if(linea.timestamp != last) { 
	%>
		<hr/>
	<% 
		last = linea.timestamp
	}
	%>
	${"${linea.timestamp} - ${linea.rawData}"} <br/>
</g:each>
