import com.iusarican.convention.implementation

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
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)
    implementation(libs.room.paging)
    implementation(libs.coil.network.okhttp)
}

