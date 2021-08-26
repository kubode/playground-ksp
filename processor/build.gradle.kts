plugins {
    kotlin("jvm")
    kotlin("kapt")
}

dependencies {
    implementation(libs.ksp.symbolProcessingApi)
    implementation(libs.autoService.annotations)
    implementation(libs.kotlinPoet)
    implementation(project(":api"))
    kapt(libs.autoService.compiler)
}
