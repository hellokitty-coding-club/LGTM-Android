pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "LGTM-Android"
include(":app")
include(":domain")
include(":data")
include(":feature:auth")
include(":shared")
include(":common-ui")
include(":feature:splash")
