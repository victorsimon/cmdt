class JqueryDatePickerTagLib {

	def jqDatePicker = {attrs, body ->
		def out = out
		def name = attrs.name    //The name attribute is required for the tag to work seamlessly with grails
		def id = attrs.id ?: name
		def minDate = attrs.minDate
		def showDay = attrs.showDay

		//Create date text field and supporting hidden text fields need by grails
		out.println "<input type=\"text\" name=\"${name}\" id=\"${id}\" />"
		out.println "<input type=\"hidden\" name=\"${name}_day\" id=\"${id}_day\" />"
		out.println "<input type=\"hidden\" name=\"${name}_month\" id=\"${id}_month\" />"
		out.println "<input type=\"hidden\" name=\"${name}_year\" id=\"${id}_year\" />"

		//Code to parse selected date into hidden fields required by grails
		out.println "<script type=\"text/javascript\"> \$(document).ready(function(){"

		out.println """
\$.datepicker.regional['es'] = {closeText: 'Cerrar',prevText: '&#x3C;Ant',nextText: 'Sig&#x3E;',currentText: 'Hoy',monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic'],dayNames: ['Domingo','Lunes','Martes','Mi&#xE9;rcoles','Jueves','Viernes','S&#xE1;bado'],dayNamesShort: ['Dom','Lun','Mar','Mi&#xE9;','Juv','Vie','S&#xE1;b'],dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S&#xE1;'],weekHeader: 'Sm',dateFormat: 'dd/mm/yy',firstDay: 1,isRTL: false,showMonthAfterYear: false,yearSuffix: ''};\$.datepicker.setDefaults(\$.datepicker.regional['es']);		
"""
		out.println "\$(\"#${name}\").datepicker({"
		out.println "onClose: function(dateText, inst) {"
		out.println "\$(\"#${name}_month\").attr(\"value\",new Date(dateText).getMonth() +1);"
		out.println "\$(\"#${name}_day\").attr(\"value\",new Date(dateText).getDate());"
		out.println "\$(\"#${name}_year\").attr(\"value\",new Date(dateText).getFullYear());"
		out.println "}"

		//If you want to customize using the jQuery UI events add an if block an attribute as follows
		if(minDate != null){
			out.println ","
			out.println "minDate: ${minDate}"
		}

		if(showDay != null){
			out.println ","
			out.println "beforeShowDay: function(date){"
			out.println	"var day = date.getDay();"
			out.println	"return [day == ${showDay},\"\"];"
			out.println "}"
		}

		out.println "});"
		out.println "})</script>"

	}
}