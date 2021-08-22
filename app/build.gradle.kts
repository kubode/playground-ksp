plugins {
    kotlin("jvm")
    id("com.google.devtools.ksp") version "1.5.21-1.0.0-beta07"
}

dependencies {
    ksp(project(":processor"))
    implementation(project(":api"))
    testImplementation(kotlin("test-junit"))
}
