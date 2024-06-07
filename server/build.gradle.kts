plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.sqldelight)
    application
}

group = "dev.efrenospino.kwtodo"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation(project(":shared"))
    implementation(libs.ktor.json)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.server.cors)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.json)
    implementation(libs.logback)
    implementation(libs.kotlinx.datetime)
    implementation(libs.sqldelight.driver)
    testImplementation(kotlin("test"))
}

sqldelight {
    databases {
        create("TasksDB") {
            packageName.set("dev.efrenospino.kwtodo.server.db")
        }
    }
}

application {
    mainClass.set("dev.efrenospino.kwtodo.server.MainKt")
}

tasks.test {
    useJUnitPlatform()
}
