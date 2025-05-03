plugins {
    alias(libs.plugins.cama.android.library)
}

android {
    namespace = "com.iusarican.datastore"
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.androidx.datastore.preferences)
}