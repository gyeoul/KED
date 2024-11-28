plugins {
    id("java")
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "org.jooq"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jooq:jooq-codegen")
    runtimeOnly("org.postgresql:postgresql")
}

tasks.named("bootJar") {
    enabled = false
}