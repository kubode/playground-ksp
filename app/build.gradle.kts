plugins {
    kotlin("multiplatform")
    id("com.google.devtools.ksp") version "1.4.20-dev-experimental-20210120"
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
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }
}

dependencies {
    ksp(project(":processor"))
}
