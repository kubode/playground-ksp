enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    val kotlinVersion: String by settings
    val kspVersion: String by settings
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        kotlin("gradle-plugin")
        id("com.google.devtools.ksp") version kspVersion
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("org.jetbrains.kotlin.")) {
                useVersion(kotlinVersion)
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
    versionCatalogs {
        create("libs") {
            val kspVersion: String by settings
            version("ksp", kspVersion)
            version("autoService", "1.0")
            version("kotlinPoet", "1.9.0")
            alias("ksp.symbolProcessingApi").to("com.google.devtools.ksp", "symbol-processing-api").versionRef("ksp")
            alias("autoService.annotations").to("com.google.auto.service", "auto-service-annotations").versionRef("autoService")
            alias("autoService.compiler").to("com.google.auto.service", "auto-service").versionRef("autoService")
            alias("kotlinPoet").to("com.squareup", "kotlinpoet").versionRef("kotlinPoet")
        }
    }
}

rootProject.name = "playground-ksp"

include("api")
include("app")
include("processor")
