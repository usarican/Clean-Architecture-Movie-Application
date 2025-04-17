import com.iusarican.convention.implementation

plugins {
    alias(libs.plugins.cama.android.application)
    alias(libs.plugins.cama.retrofit)
    alias(libs.plugins.cama.hilt)
    alias(libs.plugins.cama.compose.application)
    alias(libs.plugins.android.room)
    alias(libs.plugins.google.mapsplatform)
    kotlin("plugin.serialization") version "2.1.0"
}

android {
    namespace = "com.ibrahimutkusarican.cleanarchitecturemovieapp"

    defaultConfig {
        applicationId = "com.ibrahimutkusarican.cleanarchitecturemovieapp"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }


    buildFeatures {
        compose = true
        buildConfig = true
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
    
}

dependencies {
    // Modules
    implementation(project(":core:common"))
    
    // Core AndroidX Libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.palette.ktx)

    // Navigation
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.hilt.navigation.compose)

    // Room Database
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    // Paging
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
    implementation(libs.room.paging)

    // Image Loading & Animations
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.dotlottie.android)

    // YouTube Player
    implementation(libs.youtube.player)

    // Testing Dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    // Debugging Tools
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}