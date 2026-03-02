plugins {
    id("org.jetbrains.dokka") version "2.0.0"
    kotlin("jvm") version "2.0.0"
    `maven-publish`
    signing
}

group = "io.github.mounismoun"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar()
}

tasks.register<Jar>("javadocJar") {
    dependsOn(tasks.dokkaHtml)
    archiveClassifier.set("javadoc")
    from(tasks.dokkaHtml.get().outputDirectory)
}

publishing {
    publications {
        create<MavenPublication>("maven") {

            from(components["java"])

            artifact(tasks["javadocJar"])

            pom {
                name.set("inv-gui")
                description.set("A framework that helps easily create inventory GUIs when developing Minecraft plugins.")
                url.set("https://github.com/moun/my-library")

                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("moun")
                        name.set("moun")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/moun/my-library.git")
                    developerConnection.set("scm:git:ssh://github.com/moun/my-library.git")
                    url.set("https://github.com/moun/my-library")
                }
            }
        }
    }
}