plugins {
    java
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
    id("nu.studer.jooq") version "9.0"
}

group = "dev.gyeoul"
version = "0.0.3-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

sourceSets {
    main {
        java {
            srcDir(listOf("src/main/java", "src/generated"))
        }
    }
}

dependencies {
    // dependencies
    val logbackClassic = "1.5.8"
    val assertjCore = "3.26.3"
    val jda = "5.1.2"
    val reflection = "0.10.2"

    // implementation
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("net.dv8tion:JDA:$jda")
    implementation("org.reflections:reflections:$reflection")


    // compileOnly
    compileOnly("org.projectlombok:lombok")

    // runtimeOnly
    runtimeOnly("org.postgresql:postgresql")

    // annotationProcessor
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")

    // jooqGenerator
    jooqGenerator(project(":jooq-custom"))
    jooqGenerator("org.jooq:jooq")
    jooqGenerator("org.jooq:jooq-meta")

    // developmentOnly
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // testImplementation
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("ch.qos.logback:logback-classic:$logbackClassic")
    testImplementation("org.assertj:assertj-core:$assertjCore")

    // testRuntimeOnly
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}


val dbUser: String = System.getProperty("db-user") ?: "postgres"
val dbPassword: String = System.getProperty("db-passwd") ?: "postgres"

jooq { //    version.set(jooqVersion)
    configurations {
        create("portal") {
            generateSchemaSourceOnCompilation.set(false)

            jooqConfiguration.apply {
                jdbc.apply {
                    driver = "org.postgresql.Driver"
                    url = "jdbc:postgresql://192.168.50.200:5432/postgres"
                    user = dbUser
                    password = dbPassword
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "org.jooq.meta.postgres.PostgresDatabase"
                        inputSchema = "ingress"
                    }
                    generate.apply {
                        isDaos = true
                        isRecords = true
                        isFluentSetters = true
                        isJavaTimeTypes = true
                        isDeprecated = false
                        isValidationAnnotations = true
                        //isSpringAnnotations = true
                        //isSpringDao = true
                    }
                    target.apply {
                        directory = "src/generated"
                    }
                    strategy.name = "org.jooq.generator.JPrefixGeneratorStrategy"
                }
            }
        }
    }
}
