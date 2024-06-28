import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ktlint)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs()
    js {
        browser()
    }
    jvm()
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.datetime)
            implementation(libs.kotlinx.serialization)
        }
    }
}

ktlint {
    filter {
        exclude { entry ->
            entry.file.path.contains("/build/")
        }
    }
}