plugins {
    kotlin("jvm")
    id("com.google.devtools.ksp") version "1.5.30-1.0.0-beta08"
}

dependencies {
    kspTest(project(":processor"))
    testImplementation(project(":api"))
    testImplementation(kotlin("test-junit"))
}
