rootProject.name = "playground-ksp"

include("api")
include("app")
include("processor")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
    }
}
