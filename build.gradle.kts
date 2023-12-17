import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose")
}

group = "net.integr"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}

dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    implementation(compose.desktop.currentOs)
    testImplementation(kotlin("test"))
    implementation("net.harawata:appdirs:1.2.2")
    implementation("com.google.code.gson:gson:2.10.1")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)

            packageName = "RegistryTracker"
            packageVersion = "1.0.0"
            vendor = "Integr"
            description = "Track entries in JSON format"

            windows {
                menu = true
                dirChooser = true
                iconFile = File("src/main/resources/Tracker.ico")
            }

            linux {
                iconFile = File("src/main/resources/Tracker.ico")
            }

            macOS {
                // Now willing to do this (Signing)
                iconFile = File("src/main/resources/Tracker.ico")
            }
        }
    }
}
