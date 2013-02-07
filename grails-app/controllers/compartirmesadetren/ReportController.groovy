package compartirmesadetren

class ReportController {
	def scaffold = true

	def menu () {
		[reports: Report.findAll()]
	}
}
