dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
	properties {
		maxActive = 50
		maxIdle = 25
		minIdle = 5
		initialSize = 5
		minEvictableIdleTimeMillis = 1000 * 60 * 10
		timeBetweenEvictionRunsMillis = 1000 * 60 * 10
		maxWait = 10000
		validationQuery = "SELECT 1"
	}
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    production {
        dataSource {
		    driverClassName = "com.mysql.jdbc.Driver"
		    username = "vsimon_cmdt"
		    password = "sk3514"
            dbCreate = "update"
            url = "jdbc:mysql://compartirmesadetren.com:3306/vsimon_cmdt?autoreconnect=true"
			dialect=org.hibernate.dialect.MySQLInnoDBDialect
			autoreconnect = true
            pooled = true
        }
    }
    sandbox {
        dataSource {
		    driverClassName = "com.mysql.jdbc.Driver"
		    username = "vsimon_cmdt"
		    password = "sk3514"
            dbCreate = "update"
            url = "jdbc:mysql://compartirmesadetren.com:3306/vsimon_cmdt_sandbox?autoreconnect=true"
			dialect=org.hibernate.dialect.MySQLInnoDBDialect
			autoreconnect = true
            pooled = true
        }
    }
}
