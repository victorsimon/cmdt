dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
	properties {
		validationQuery = "SELECT 1"
        testOnBorrow=true
        testOnReturn=true
        testWhileIdle=true
        initialSize=0
        maxActive=10
        maxIdle=10
        minIdle=0
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
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            autoreconnect = true
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
