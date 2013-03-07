grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
        mavenRepo "http://repository.springsource.com/maven/bundles/release/"
        mavenRepo "http://repository.springsource.com/maven/bundles/external/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        runtime 'mysql:mysql-connector-java:5.1.22',
                'org.tuckey:urlrewritefilter:4.0.3'
		compile 'xerces:xercesImpl:2.9.1',
				'net.sourceforge.nekohtml:nekohtml:1.9.15',
                "org.grails.plugins:geb:0.9.0-RC-1"
        test "org.gebish:geb-spock:0.9.0-RC-1"    
        test 'org.seleniumhq.selenium:selenium-firefox-driver:latest.release'
        test 'org.seleniumhq.selenium:selenium-chrome-driver:latest.release'
        test 'org.seleniumhq.selenium:selenium-ie-driver:latest.release'
        test('org.seleniumhq.selenium:selenium-htmlunit-driver:latest.release') {
                exclude 'xml-apis'
        }
    }

    plugins {
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.8.0"
        runtime ":resources:1.1.6"

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

        build ":tomcat:$grailsVersion"

        runtime ":database-migration:1.1"

        compile ':cache:1.0.0'
		test ":spock:0.7"
        test "org.grails.plugins:geb:0.9.0-RC-1"
    }
}
