plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:1.4.20-dev-experimental-20210120")
    implementation("com.google.auto.service:auto-service-annotations:1.0-rc7")
    kapt("com.google.auto.service:auto-service:1.0-rc7")
}
