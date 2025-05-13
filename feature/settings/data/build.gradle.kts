plugins {
    alias(libs.plugins.cama.android.library)
    alias(libs.plugins.cama.hilt)
}

android {
    namespace = "com.iusarican.data"
}

dependencies {
    implementation(project(":core:datastore"))
    implementation(project(":core:common"))
    implementation(project(":feature:settings:domain"))
}