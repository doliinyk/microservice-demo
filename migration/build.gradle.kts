plugins {
    id("org.springframework.boot") version "3.3.1" apply false
    id("io.spring.dependency-management") version "1.1.6" apply false
    id("org.liquibase.gradle") version "2.2.2"
}

dependencies {
    liquibaseRuntime("org.liquibase:liquibase-core:4.28.0")
    liquibaseRuntime("info.picocli:picocli:4.7.6")
    liquibaseRuntime("org.postgresql:postgresql")
}

liquibase {
    activities.register("main") {
        val dbUrl: String? = System.getenv("DB_URL")
        val dbUsername: String? = System.getenv("DB_USERNAME")
        val dbPassword: String? = System.getenv("DB_PASSWORD")
        val changelogFile: String? = System.getenv("CHANGELOG_FILE")

        arguments = mapOf(
            "url" to (dbUrl ?: "jdbc:postgresql://localhost:5432/microservice-demo"),
            "username" to (dbUsername ?: "test"),
            "password" to (dbPassword ?: "test"),
            "changelogFile" to (changelogFile ?: "migration/changelogs/db.changelog-master.xml")
        )
    }

    runList = "main"
}