pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        kotlin("gradle-plugin")
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace in setOf("org.jetbrains.kotlin", "org.jetbrains.kotlin.plugin")) {
                useVersion("1.5.30")
            }
        }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "playground-ksp"

include("api")
include("app")
include("processor")
