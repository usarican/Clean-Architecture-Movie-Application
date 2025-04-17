plugins {
    alias(libs.plugins.cama.android.library)
    alias(libs.plugins.cama.hilt)
    alias(libs.plugins.cama.retrofit)
}

android {
    namespace = "com.iusarican.common"
}

dependencies {
    implementation(libs.coil.network.okhttp)
}

