plugins {
    kotlin("jvm")
    id("com.google.devtools.ksp") version "1.4.30-1.0.0-alpha02"
}

dependencies {
    kspTest(project(":processor"))
    testImplementation(project(":annotation"))
    testImplementation(kotlin("test-junit"))
}

sourceSets.getByName("test").java.srcDirs("build/generated/ksp/test/kotlin")
