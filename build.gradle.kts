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
                url.set("https://github.com/mounismoun/${rootProject.name}")

                licenses {
                    license {
                        name.set("GNU General Public License version 3")
                        url.set("https://opensource.org/licenses/GPL-3.0")
                    }
                }

                developers {
                    developer {
                        id.set("mounismoun")
                        name.set("mounismoun")
                        email.set("mounismoun@gmail.com")
                        url.set("https://github.com/mounismoun")
                        roles.addAll("developer")
                        timezone.set("Asia/Seoul")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/mounismoun/${rootProject.name}.git")
                    developerConnection.set("scm:git:ssh://github.com/mounismoun/${rootProject.name}.git")
                    url.set("https://github.com/mounismoun/${rootProject.name}")
                }
            }
        }
    }

    repositories {
        maven {
            name = "staging"
            url = uri(layout.buildDirectory.dir("staging-repo"))
        }
    }
}

signing {
    isRequired = true
    useInMemoryPgpKeys(
        file(System.getenv("SIGNING_KEY")).readText(),
        System.getenv("SIGNING_PASSWORD")
    )
    sign(publishing.publications["maven"])
}