import com.iusarican.convention.implementation

plugins {
    alias(libs.plugins.cama.android.library)
    alias(libs.plugins.cama.hilt)
    alias(libs.plugins.cama.room)
}

android {
    namespace = "com.iusarican.database"
}

dependencies {
    implementation(libs.moshi)
}