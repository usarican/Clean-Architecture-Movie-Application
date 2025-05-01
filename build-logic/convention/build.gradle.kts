import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.example.starter.build_logic.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "cama.android.library"
            implementationClass = "com.iusarican.AndroidLibraryConventionPlugin"
        }

        register("androidApplication") {
            id = "cama.android.application"
            implementationClass = "com.iusarican.AndroidApplicationConventionPlugin"
        }

        register("retrofit") {
            id = "cama.retrofit"
            implementationClass = "com.iusarican.RetrofitConventionPlugin"
        }

        register("composeApplicaiton") {
            id = "cama.compose.application"
            implementationClass = "com.iusarican.AndroidApplicationComposeConventionPlugin"
        }

        register("composeLibrary") {
            id = "cama.compose.library"
            implementationClass = "com.iusarican.AndroidLibraryComposeConventionPlugin"
        }

        register("hilt"){
            id = "cama.hilt"
            implementationClass = "com.iusarican.HiltConventionPlugin"
        }
        register("secret"){
            id = "cama.secret"
            implementationClass = "com.iusarican.SecretsConventionPlugin"
        }
    }
}