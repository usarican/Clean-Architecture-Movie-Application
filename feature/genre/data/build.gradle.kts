import com.iusarican.convention.implementation

plugins {
    alias(libs.plugins.cama.android.library)
    alias(libs.plugins.cama.hilt)
    alias(libs.plugins.cama.room)
    alias(libs.plugins.cama.retrofit)
}

android {
    namespace = "com.iusarican.data"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":core:database"))
    implementation(project(":feature:genre:domain"))
}