dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
	properties {
        maxActive = 20
        maxIdle = 1
        minIdle = 1
        initialSize = 1
        minEvictableIdleTimeMillis = 10000
        timeBetweenEvictionRunsMillis = 10000
        validationQuery = "SELECT 1"
        testOnBorrow=true
        testOnReturn=true
        testWhileIdle=true
        maxWait=-1
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
        /**/
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE;"
        }
        /**/
        /*
        dataSource {
            driverClassName = "com.mysql.jdbc.Driver"
            dbCreate = "update"
            url = "jdbc:mysql://localhost/vsimon_cmdt"
            username = "root"
            password = "sk3514"
            autoreconnect = true
            pooled = true
        }
        */
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE;"
        }
    }
    production {
        dataSource {
		    driverClassName = "com.mysql.jdbc.Driver"
		    username = "vsimon_cmdt"
		    password = "sk3514"
            dbCreate = "update"
            url = "jdbc:mysql://compartirmesadetren.com:3306/vsimon_cmdt?autoreconnect=true"
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
			autoreconnect = true
            pooled = true
        }
    }
}
