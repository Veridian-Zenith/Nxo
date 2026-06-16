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

rootProject.name = "Nxo"

include(":app")
include(":core:design-system")
include(":core:common")
include(":features:launcher")
include(":features:ai-engine")
include(":features:settings")
