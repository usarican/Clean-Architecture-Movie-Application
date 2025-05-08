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
    implementation(project(":core:model"))
    implementation(project(":feature:search:data"))
    implementation(project(":feature:mylist:data"))
    implementation(project(":feature:home:data"))
    implementation(project(":feature:genre:data"))
    implementation(project(":feature:detail:data"))
    implementation(libs.moshi)
}