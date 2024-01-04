
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.21"
    application
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.21"
}

group = "com.sk"
version = "0.0.1"

application {
    mainClass.set("com.sk.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

val ktorm_version: String by project
val postgresql_driver_version: String by project

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")

    implementation("org.ktorm:ktorm-core:$ktorm_version")
    implementation("org.ktorm:ktorm-support-postgresql:$ktorm_version")
    implementation("org.postgresql:postgresql:$postgresql_driver_version")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.5.0")
}
