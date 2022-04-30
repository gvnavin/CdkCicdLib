import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("java-library")
    id("maven-publish")
}

group = "com.company.infra.common"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://beta-com-company-codeartifacts-134514717601.d.codeartifact.ap-south-1.amazonaws.com/maven/beta-com-company-codeartifacts-repository/")
        credentials {
            username = "aws"
            password = System.getenv()["CODEARTIFACT_AUTH_TOKEN"]
        }
    }
}

dependencies {

    implementation("software.amazon.awscdk:aws-cdk-lib:2.19.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")
    implementation("com.beust:klaxon:5.5")

    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.6.10")

    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")
    testImplementation("org.assertj:assertj-core:3.22.0")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "com.company.infra.common"
            artifactId = "CdkCicdLib"
            version = version

            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("https://beta-com-company-codeartifacts-134514717601.d.codeartifact.ap-south-1.amazonaws.com/maven/beta-com-company-codeartifacts-repository/")
            credentials {
                username = "aws"
                password = System.getenv()["CODEARTIFACT_AUTH_TOKEN"]
            }
        }
    }
}