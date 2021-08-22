plugins {
    kotlin("jvm")
    id("com.google.devtools.ksp") version "1.5.21-1.0.0-beta07"
}

dependencies {
    kspTest(project(":processor"))
    testImplementation(project(":annotation"))
    testImplementation(kotlin("test-junit"))
}
