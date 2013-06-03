class RedirectTagLib {
	def redirectMainPage = {
		response.sendRedirect("${request.contextPath}/madrid-pamplona-mesa-renfe")
	}
}