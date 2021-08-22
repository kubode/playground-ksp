plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:1.5.21-1.0.0-beta07")
    implementation("com.google.auto.service:auto-service-annotations:1.0")
    implementation("com.squareup:kotlinpoet:1.9.0")
    implementation(project(":api"))
    kapt("com.google.auto.service:auto-service:1.0")
}
