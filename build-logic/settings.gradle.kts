dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
        }
        println("Loading version catalog from: ${file("../gradle/libs.versions.toml").absolutePath}")
    }
}

rootProject.name = "build-logic"
include(":convention")