import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import org.gradle.internal.impldep.org.junit.platform.launcher.EngineFilter.includeEngines
import org.gradle.kotlin.dsl.version
import org.jetbrains.kotlin.contracts.model.structure.UNKNOWN_COMPUTATION.type
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    application
    val kotlinVersion = "1.3.10"
    kotlin("jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("com.github.johnrengelman.shadow") version "4.0.2"
    id("io.spring.dependency-management") version "1.0.6.RELEASE"
    id("org.springframework.boot") version "2.1.0.RELEASE"
    id("com.github.ben-manes.versions") version "0.20.0"
//    id("org.junit.platform.gradle.plugin") version "1.2.0"
}

application {
    mainClassName = "functional.Application.kt"
}


tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            freeCompilerArgs = listOf("-Xjsr305=strict")
        }
    }
    getByName<Test>("test") {
        useJUnitPlatform {
            includeEngines("junit-jupiter")
            excludeEngines("junit-vintage")
        }
    }

}

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:2.1.0.RELEASE"){
            bomProperty("kotlin.version", "1.3.10")
        }
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
    maven("https://repo.spring.io/snapshot")
    maven("https://repo.spring.io/milestone")
}

dependencies {
    //spring webflux
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework:spring-webflux")
    implementation("org.springframework:spring-context") {
        exclude(module = "spring-aop")
    }
//    compileOnly("org.springframework.boot:spring-boot-configuration-processor")
//    compileOnly("org.tuxdude.logback.extensions:logback-colorizer:1.0.1")
//    compileOnly("io.github.benas:random-beans:3.7.0")

//    implementation("org.springframework.boot:spring-boot-starter-reactor-netty")
//    implementation("io.netty:netty-all:5.0.0.Alpha2")
    implementation("io.projectreactor.netty:reactor-netty")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

    implementation("com.github.ben-manes:gradle-versions-plugin:0.20.0")
    implementation ("io.github.cdimascio:java-dotenv:3.1.3")
    implementation ("io.github.cdimascio:openapi-spring-webflux-validator:1.0.1")

    //kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.1")

    //slf4j and logback
    implementation("org.slf4j:slf4j-api")
    implementation("ch.qos.logback:logback-classic")

    //TEST
//    testImplementation("org.springframework:spring-test") {
//        exclude(module = "junit")
//    }
//    testImplementation("org.junit.platform:junit-platform-engine")
//    testImplementation("org.junit.jupiter:junit-jupiter-params")
//    testImplementation("org.assertj:assertj-core")
//    testImplementation("io.github.glytching:junit-extensions:2.3.0") // glytching.github.io/junit-extensions/index
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("io.projectreactor:reactor-test")
    testRuntime("org.junit.platform:junit-platform-launcher")
    testRuntime("org.junit.jupiter:junit-jupiter-engine")

    //DataBases
//    runtimeOnly("org.hsqldb:hsqldb")
//    runtimeOnly("mysql:mysql-connector-java")
//    runtimeOnly("com.h2database:h2:1.4.197")
//    runtimeOnly("org.postgresql:postgresql:42.2.2")
//    implementation("com.zaxxer:HikariCP:3.1.0")
}
