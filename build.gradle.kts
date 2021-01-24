buildscript {
    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.4.21"))
    }
}

plugins {
    kotlin("multiplatform") version "1.4.21"
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }

    group = "org.github.kubode"
    version = "1.0.0-SNAPSHOT"
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
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
