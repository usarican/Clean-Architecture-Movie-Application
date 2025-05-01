plugins {
    alias(libs.plugins.cama.android.library)
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
    implementation(libs.coil.network.okhttp)
}

