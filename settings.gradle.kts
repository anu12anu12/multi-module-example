pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter() // Warning: this repository is going to shut down soon
    }
}
rootProject.name = "InterviewTest"
include(":app")
include(":core:database")
include(":core:network")
include(":core:ui")
include(":core:common")
include(":features:reviews")
include(":features:wishlist")
include(":features:reviews")
include(":features:public:wishlist")
include(":core:sharedTests")
