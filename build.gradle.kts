plugins {
    java
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.6"
}

allprojects {
    group = "demo.microservice"
    version = "0.0.1"
    repositories.mavenCentral()
}

extra["springCloudVersion"] = "2023.0.2"

subprojects {
    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    java { toolchain { languageVersion = JavaLanguageVersion.of(21) } }

    dependencies {
        developmentOnly("org.springframework.boot:spring-boot-devtools")

        implementation("org.springframework.boot:spring-boot-starter")

        testImplementation("org.springframework.boot:spring-boot-starter-test")

        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    tasks.withType<Test> { useJUnitPlatform() }
}

tasks.register("bootBuildImages") {
    dependsOn(subprojects.map { it.tasks.named("bootBuildImage") })
}
