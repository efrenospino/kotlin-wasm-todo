plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "dev.efrenospino.kwtodo"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation(libs.ktor.json)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.cors)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.json)
    implementation(libs.logback)
    testImplementation(kotlin("test"))
}

application {
    mainClass.set("dev.efrenospino.kwtodo.server.MainKt")
}

tasks.test {
    useJUnitPlatform()
}
