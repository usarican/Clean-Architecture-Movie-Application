plugins {
    alias(libs.plugins.cama.android.library)
}

android {
    namespace = "com.iusarican.datastore"
}

dependencies {
    implementation(libs.androidx.datastore.preferences)
}