plugins {
    java
    idea
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.6"
}

allprojects {
    group = "demo.microservice"
    version = "0.0.1"
    repositories.mavenCentral()
}

idea.module.isDownloadJavadoc = true

extra["springCloudVersion"] = "2023.0.2"

subprojects {
    apply(plugin = "java")
    apply(plugin = "idea")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    java.toolchain.languageVersion = JavaLanguageVersion.of(21)
    idea.module.isDownloadJavadoc = true

    dependencies {
        compileOnly("org.projectlombok:lombok")

        developmentOnly("org.springframework.boot:spring-boot-devtools")

        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-actuator")

        implementation("org.mapstruct:mapstruct:1.5.5.Final")

        testImplementation("org.springframework.boot:spring-boot-starter-test")

        testImplementation("org.projectlombok:lombok")

        testRuntimeOnly("org.junit.platform:junit-platform-launcher")

        annotationProcessor("org.projectlombok:lombok")
        annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
        annotationProcessor("org.projectlombok:lombok-mapstruct-binding:0.2.0")
    }

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    tasks.withType<Test> { useJUnitPlatform() }

    tasks.withType<JavaCompile> { options.compilerArgs.add("-parameters") }
}

tasks.register("bootBuildImages") {
    dependsOn(subprojects.map { it.tasks.named("bootBuildImage") })
}
