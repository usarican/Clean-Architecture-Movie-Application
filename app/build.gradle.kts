plugins {
    alias(libs.plugins.android.application)
    kotlin("plugin.serialization") version "2.1.0"
}

android {
    namespace = "com.ibrahimutkusarican.cleanarchitecturemovieapp"
}

apply(from = "$rootDir/scripts/common.gradle")

dependencies {
    implementation(project(":core"))
}