plugins {
    kotlin("jvm") version "2.0.0"
    id("com.gradleup.shadow") version "9.0.0-beta10"
}

group = "com.github.moun"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()

    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

tasks {
    build {
        dependsOn(shadowJar)
    }

    shadowJar {
        archiveClassifier.set("")
    }
}
