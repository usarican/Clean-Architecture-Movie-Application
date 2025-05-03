plugins {
    alias(libs.plugins.cama.android.library)
    alias(libs.plugins.cama.compose.library)
    alias(libs.plugins.cama.hilt)
    alias(libs.plugins.cama.retrofit)
    alias(libs.plugins.cama.secrets)
}




android {
    namespace = "com.iusarican.common"

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:datastore"))
    implementation(libs.coil.network.okhttp)
}

