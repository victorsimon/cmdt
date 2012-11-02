package compartirmesadetren

class CSMovilTagLib {

	def thisYear = {
		out << new Date().format("yyyy")
	}
	
	def copyrigth = {attrs, body->
		out << "&copy;" + attrs.startYear + "-"
		out << thisYear() + " " + body()
	}
	
}
