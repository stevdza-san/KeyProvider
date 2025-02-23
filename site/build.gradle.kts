import com.varabyte.kobweb.gradle.application.util.configAsKobwebApplication

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.kobweb.application)
    alias(libs.plugins.serialization.plugin)
    alias(libs.plugins.compose.compiler)
    // alias(libs.plugins.kobwebx.markdown)
}

group = "com.stevdza_san.keyproviderdemo"
version = "1.0-SNAPSHOT"

kobweb {
    app {
        index {
            description.set("Powered by Kobweb")
        }
    }
}

kotlin {
    configAsKobwebApplication("keyproviderdemo", includeServer = true)

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(libs.kobweb.core)
                // implementation(libs.kobweb.silk)
                // implementation(libs.silk.icons.fa)
                // implementation(libs.kobwebx.markdown)
            }
        }
        val jvmMain by getting {
            dependencies {
                compileOnly(libs.kobweb.api) // Provided by Kobweb backend at runtime
                implementation(libs.kotlinx.serialization)
            }
        }
    }
}
