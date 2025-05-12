plugins {
    alias(libs.plugins.cama.android.library)
    alias(libs.plugins.cama.hilt)
}

android {
    namespace = "com.iusarican.domain"
}

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:common"))
    implementation(project(":feature:genre:data"))
    implementation(project(":feature:genre:domain"))
    implementation(project(":feature:home:data"))
}

