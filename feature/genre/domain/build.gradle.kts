import com.iusarican.convention.implementation

plugins {
    alias(libs.plugins.cama.android.library)
    alias(libs.plugins.cama.hilt)
}

android {
    namespace = "com.iusarican.domain"
}

dependencies {
    implementation(project(":core:common"))
}