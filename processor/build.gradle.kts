plugins {
    kotlin("jvm")
    kotlin("kapt")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:1.5.21-1.0.0-beta07")
    implementation("com.google.auto.service:auto-service-annotations:1.0-rc7")
    implementation("com.squareup:kotlinpoet:1.7.2")
    implementation(project(":annotation"))
    kapt("com.google.auto.service:auto-service:1.0-rc7")
}
