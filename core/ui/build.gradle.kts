import com.iusarican.convention.implementation

plugins {
    alias(libs.plugins.cama.compose.library)
    alias(libs.plugins.cama.android.library)
}

android {
    namespace = "com.iusarican.ui"
}

dependencies {
    // Modules
    implementation(project(":core:common"))
    implementation(libs.androidx.paging.compose.android)
    implementation(libs.coil.compose)
}