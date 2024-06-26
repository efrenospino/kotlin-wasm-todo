plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.ktlint)
}

group = "dev.efrenospino.kwtodo"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation(project(":shared"))
    implementation(libs.sqldelight.driver)
    testImplementation(kotlin("test"))
}

ktlint {
    filter {
        exclude { entry ->
            entry.file.path.contains("/generated/")
        }
    }
}

sqldelight {
    databases {
        create("TasksDatabase") {
            packageName.set("dev.efrenospino.kwtodo.database")
        }
    }
}

tasks.test {
    useJUnitPlatform()
}