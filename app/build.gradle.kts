plugins {
    kotlin("multiplatform")
    id("com.google.devtools.ksp") version "1.4.30-1.0.0-alpha02"
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(project(":annotation"))
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
    }
}

dependencies {
    ksp(project(":processor"))
}
