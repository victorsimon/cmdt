// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }
grails.app.context = '/'
grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "html" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = false
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true
        grails.serverURL = "http://localhost:8080"
		grails.compartirmesadetren.oauth.debug = true
		grails.paypal.server = "https://www.sandbox.paypal.com/cgi-bin/webscr"
		grails.paypal.email = "vsimon_1354244358_biz@gmail.com"
		grails.mail.contact = "vsimon.batanero@gmail.com"
		cmdt.dopayment = false
    }
    production {
        grails.logging.jul.usebridge = false
        grails.serverURL = "http://www.compartirmesadetren.com"
		grails.compartirmesadetren.oauth.debug = false
		grails.paypal.server = "https://www.paypal.com/cgi-bin/webscr"
		grails.paypal.email = "vsimon.batanero@gmail.com"    
		grails.mail.contact = "contacto@compartirmesadetren.com"
		cmdt.dopayment = true
	}
    sandbox {
        grails.logging.jul.usebridge = false
        grails.serverURL = "http://www.compartirmesadetren.com/sandbox"
		grails.compartirmesadetren.oauth.debug = false
		grails.paypal.server = "https://www.sandbox.paypal.com/cgi-bin/webscr"
		grails.paypal.email = "vsimon_1354244358_biz@gmail.com"
		grails.mail.contact = "contacto@compartirmesadetren.com"
		cmdt.dopayment = true
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
		   
	info   'grails.app'
}

grails {
   mail {
     host = "smtp.gmail.com"
     port = 465
     username = "noreply.compartirmesadetren"
     password = "#ks3514!"
	 from = "noreply@compartirmesadetren.com"
     props = ["mail.smtp.auth":"true",
              "mail.smtp.socketFactory.port":"465",
              "mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
              "mail.smtp.socketFactory.fallback":"false"]
   }
}
   
// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'compartirmesadetren.User'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'compartirmesadetren.UserRole'
grails.plugins.springsecurity.authority.className = 'compartirmesadetren.Role'
grails.plugins.springsecurity.ui.password.validationRegex = '^.*$'
grails.plugins.springsecurity.ui.password.minLength = 4
grails.plugins.springsecurity.ui.encodePassword = false

// Added by the Spring Security OAuth plugin:
grails.plugins.springsecurity.oauth.domainClass = 'compartirmesadetren.OAuthID'

oauth {
	providers {
		google {
			api = org.scribe.builder.api.GoogleApi
			key = '273808781997.apps.googleusercontent.com'
			secret = 'sqLSQjpsIbsqJEOmc5l70F08'
			scope = "https://www.googleapis.com/auth/userinfo.email"
			callback = grails.serverURL + "/oauth/google/callback"
			successUri = grails.serverURL + "/oauth/google/success"
			failureUri = grails.serverURL + "/oauth/google/failure"
		}
		twitter {
			api = org.scribe.builder.api.TwitterApi
			key = 'cjAf40dFHzj0Gk0m1vy6Kw'
			secret = 'Aclu3EkYqJRwVaxgXiv8L688UEG207Xqd0O3usIq0W4'
			callback = grails.serverURL + "/oauth/twitter/callback"
			successUri = grails.serverURL + "/oauth/twitter/success"
			failureUri = grails.serverURL + "/oauth/twitter/failure"
		}
		facebook {
			api = org.scribe.builder.api.FacebookApi
			key = '130052670479745'
			secret = 'e0fceabaa2121ade42939fdaab135fac'
			callback = grails.serverURL + "/oauth/facebook/callback"
			successUri = grails.serverURL + "/oauth/facebook/success"
			failureUri = grails.serverURL + "/oauth/facebook/failure"
		}
	}
	debug = true
	registration.roleNames = ['ROLE_USER']
}

grails.resources.mappers.googleclosurecompiler.compilation_level = 'SIMPLE_OPTIMIZATIONS'

grails {
	doc {
		title = 'Compartir Mesa De Tren'
		subtitle = 'Documentacion de proyecto'
		authors = 'Victor Simon Batanero "victor.simon@compartirmesadetren.com"'
		license = '-'
		copyright = 'Contenido y soluciones para moviles, S.L.'
		footer = ''
	}
}

grails.plugins.springsecurity.ui.register.emailSubject = "Confirme su cuenta en www.compartirmesadetren.com"
grails.plugins.springsecurity.ui.register.emailFrom = "victor.simon@compartirmesadetren.com"
grails.plugins.springsecurity.ui.register.emailBody = '''\
<div style="padding-top: 1em; margin: 0; border: 0; background-color: #EEEEEE; min-width: 100%; min-height: 100%;">
	<div style=" margin-left: auto; margin-right: auto; width: 95%; font-family: 'HelveticaNeue-Light', 'Helvetica Neue Light', 'Helvetica Neue', Helvetica, Arial, 'Lucida Grande', sans-serif; background-color: #EEEEEE;">			
		<div style="width: 100%; height: 15px; background-color: #ffffff;"></div>
		<div style="height: 120px; background-color: #ffffff;width: 100%; margin: 0;">
			<img alt="www.compartirmesadetren.com" style="border: 0; margin-left: 1em; height: 90px;" src="http://www.compartirmesadetren.com/images/logo_mail.png"/>
			<div style="text-align: center; float: right; ">
				<h1 style="margin: .5em; font-size: 1.2em; color: #777777;">¡Síguenos en!</h1>
				<a style="color: #81005F;" href="http://www.facebook.com/compartirtumesadetren" target="_blank">
					<img alt="Facabook" style="width: 32px; height: 32px; margin: 0 .5em;" src="http://www.compartirmesadetren.com/images/Facebook.png" /></a>
				<a style="color: #81005F;" href="https://plus.google.com/u/0/b/112375915542570953380/112375915542570953380/posts" target="_blank">
					<img alt="Google+" style="width: 32px; height: 32px; margin: 0 .5em;" src="http://www.compartirmesadetren.com/images/Twitter.png" /></a>
				<a style="color: #81005F;" href="https://twitter.com/CompartirMesaDe" target="_blank">
					<img alt="Twitter" style="width: 32px; height: 32px; margin: 0 .5em;" src="http://www.compartirmesadetren.com/images/Google+.png" /></a>
			</div>
		</div>
		<div style="width: 100%; margin: 0;">
			<div style="margin: 0; height: 1px; width: 100%; color: #777777; background-color: #777777;"></div>
			<img alt="SITO" style="height: 120px; margin: 1em; float: left;" src="http://www.compartirmesadetren.com/images/sito_mail.png"/>
			<div style="padding: 1em 0; width: 100%; font-size: .9em; color: #81005F; min-height: 160px; border: 0; background-color: #ffffff;">
				<p style="margin: 0; padding: .5em;">¡Hola $user.username! Soy <strong>SITO</strong>, </p>
				<p style="margin: 0; padding: .5em;">Y quiero darte la Bienvenida a CompartirMesaDeTren.com, la web donde podrás adquirir billetes de Renfe mucho más equonómicos.</p>
				<p style="margin: 0; padding: .5em;">Viaja en tren ahorrando y vive la experiencia de compartir mesa con otros usuarios.</p>
				<p style="margin: 0; padding: .5em;">Pero primero tienes que confirmar tu registro haciendo click en ...</p>
				<div style="width: 90%; text-align: right;">
					<a href="$url"><img alt="Confirmar cuenta" style="border: 0; width: 120px; float: right;" src="http://www.compartirmesadetren.com/images/boton_confirmar_cuenta.png"/></a>
				</div>
			</div>
			<div style="margin: 0; height: 1px; width: 100%; color: #777777; background-color: #777777;"></div>
		</div>
		<div style="width: 100%; margin: 0; padding: 1em 0;	text-align: center;	height: 6em; background-color: #fff; color: #777777; font-size: .7em;">
			<p style="margin: 0;">No respondas a este mensaje, es un mail generado automáticamente y enviado. Para contactar con nosotros hazlo a través de <a style="color: #81005F;" href="mailto:info@compartirmesadetren.com">info@compartirmesadetren.com</a></p>
			<p style="text-align: center; margin-top: 1em; padding: .5em; font-size: .8em;">www.compartirmesadetren.com cumple con la Ley orgánica 15/99 de 14 de Diciembre de protección de datos de carácter personal.</p>
			<br/>
		</div>
		<div style="width: 100%; height: 15px; background-color: #ffffff;"></div>
		<img border="0" src="http://www.compartirmesadetren.com/images/sombra.jpg" style="display: block; vertical-align: bottom; width: 100%; height: 20px;">	</div>
</div>
'''

grails.plugins.springsecurity.ui.forgotPassword.emailSubject = "Reestablecer clave de su cuenta en www.compartirmesadetren.com"
grails.plugins.springsecurity.ui.forgotPassword.emailBody = '''\
<div style="padding-top: 1em; margin: 0; border: 0; background-color: #EEEEEE; min-width: 100%; min-height: 100%;">
	<div style=" margin-left: auto; margin-right: auto; width: 95%; font-family: 'HelveticaNeue-Light', 'Helvetica Neue Light', 'Helvetica Neue', Helvetica, Arial, 'Lucida Grande', sans-serif; background-color: #EEEEEE;">			
		<div style="width: 100%; height: 15px; background-color: #ffffff;"></div>
		<div style="height: 120px; background-color: #ffffff;width: 100%; margin: 0;">
			<img alt="www.compartirmesadetren.com" style="border: 0; margin-left: 1em; height: 90px;" src="http://www.compartirmesadetren.com/images/logo_mail.png"/>
			<div style="text-align: center; float: right; ">
				<h1 style="margin: .5em; font-size: 1.2em; color: #777777;">¡Síguenos en!</h1>
				<a style="color: #81005F;" href="http://www.facebook.com/compartirtumesadetren" target="_blank">
					<img alt="Facabook" style="width: 32px; height: 32px; margin: 0 .5em;" src="http://www.compartirmesadetren.com/images/Facebook.png" /></a>
				<a style="color: #81005F;" href="https://plus.google.com/u/0/b/112375915542570953380/112375915542570953380/posts" target="_blank">
					<img alt="Google+" style="width: 32px; height: 32px; margin: 0 .5em;" src="http://www.compartirmesadetren.com/images/Twitter.png" /></a>
				<a style="color: #81005F;" href="https://twitter.com/CompartirMesaDe" target="_blank">
					<img alt="Twitter" style="width: 32px; height: 32px; margin: 0 .5em;" src="http://www.compartirmesadetren.com/images/Google+.png" /></a>
			</div>
		</div>
		<div style="width: 100%; margin: 0;">
			<div style="margin: 0; height: 1px; width: 100%; color: #777777; background-color: #777777;"></div>
			<img alt="SITO" style="height: 120px; margin: 1em; float: left;" src="http://www.compartirmesadetren.com/images/sito_mail.png"/>
			<div style="padding: 1em 0; width: 100%; font-size: .9em; color: #81005F; min-height: 160px; border: 0; background-color: #ffffff;">
				<p style="margin: 0; padding: .5em;">¡Hola $user.username! Soy <strong>SITO</strong>, </p>
				<p style="margin: 0; padding: .5em;">Quería avisarte que se está intentando reestablecer tu contraseña de la cuenta de usuario que tienes en <a style="color: #81005F;" href="http://www.compartirmesadetren.com">www.compartirmesadetren.com</a>.</p>
				<p style="margin: 0; padding: .5em;">Si no has hecho esta petición, ignora el e-mail.</p>
				<p style="margin: 0; padding: .5em;">Si eres tú quien lo ha solicitado, haz click aquí para reestablecerla...</p>
				<div style="width: 90%; text-align: right;">
					<a href="$url"><img alt="Restablecer clave" style="border: 0; width: 120px; float: right;" src="http://www.compartirmesadetren.com/images/boton_reestablecer.png"/></a>
				</div>
			</div>
			<div style="margin: 0; height: 1px; width: 100%; color: #777777; background-color: #777777;"></div>
		</div>
		<div style="width: 100%; margin: 0; padding: 1em 0;	text-align: center;	height: 6em; background-color: #fff; color: #777777; font-size: .7em;">
			<p style="margin: 0;">No respondas a este mensaje, es un mail generado automáticamente y enviado. Para contactar con nosotros hazlo a través de <a style="color: #81005F;" href="mailto:info@compartirmesadetren.com">info@compartirmesadetren.com</a></p>
			<p style="text-align: center; margin-top: 1em; padding: .5em; font-size: .8em;">
				<a style="color: #81005F;" href="http://compartirmesadetren.com">www.compartirmesadetren.com</a> cumple con la Ley orgánica 15/99 de 14 de Diciembre de protección de datos de carácter personal.</p>
			<br/>
		</div>
		<div style="width: 100%; height: 15px; background-color: #ffffff;"></div>
		<img border="0" src="http://www.compartirmesadetren.com/images/sombra.jpg" style="display: block; vertical-align: bottom; width: 100%; height: 20px;">
	</div>
</div>
'''
