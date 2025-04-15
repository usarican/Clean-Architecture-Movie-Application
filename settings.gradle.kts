pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

rootProject.name = "CleanArchitectureMovieApp"
include(":app")
include(":core")

include(":feature:home")
include(":feature:settings")
include(":core:network")
include(":core:database")
include(":feature:detail")
include(":feature:mylist")
include(":feature:search")
include(":feature:seeall")
include(":feature:explore")
include(":feature:genre")
include(":core:datastore")
