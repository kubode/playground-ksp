plugins {
    kotlin("jvm")
    id("com.google.devtools.ksp")
}

dependencies {
    kspTest(project(":processor"))
    testImplementation(project(":api"))
    testImplementation(kotlin("test-junit"))
}
