import com.codingfeline.buildkonfig.compiler.FieldSpec
import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import java.util.*

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.buildKonfig)
    alias(libs.plugins.kotlinSerialization)
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

buildkonfig {
    packageName = "dev.efrenospino.kwtodo"

    val localPropsFile = rootProject.file("local.properties")
    val localProperties = Properties()
    if (localPropsFile.exists()) {
        runCatching {
            localProperties.load(localPropsFile.inputStream())
        }.getOrElse {
            it.printStackTrace()
        }
    }
    defaultConfigs {
        buildConfigField(
            FieldSpec.Type.STRING,
            "SERVER_SCHEMA",
            System.getenv("SERVER_SCHEMA") ?: localProperties["server.schema"]?.toString()
        )
        buildConfigField(
            FieldSpec.Type.STRING,
            "SERVER_HOST",
            System.getenv("SERVER_HOST") ?: localProperties["server.host"]?.toString()
        )
        buildConfigField(
            FieldSpec.Type.INT,
            "SERVER_PORT",
            System.getenv("SERVER_PORT") ?: localProperties["server.port"]?.toString()
        )
    }

}