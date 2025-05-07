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
include(":core:ui")
include(":core:common")
include(":core:model")
include(":core:designsystem")
include(":feature:detail:data")
include(":feature:detail:domain")
include(":feature:detail:ui")
include(":feature:genre:data")
include(":feature:genre:domain")
include(":feature:genre:ui")
include(":feature:explore:data")
include(":feature:explore:domain")
include(":feature:explore:ui")
include(":feature:home:data")
include(":feature:home:domain")
include(":feature:home:ui")
include(":feature:mylist:data")
include(":feature:mylist:domain")
include(":feature:mylist:ui")
include(":feature:search:data")
include(":feature:search:domain")
include(":feature:search:ui")
include(":feature:seeall:data")
include(":feature:seeall:domain")
include(":feature:seeall:ui")
include(":feature:settings:data")
include(":feature:settings:domain")
include(":feature:settings:ui")
