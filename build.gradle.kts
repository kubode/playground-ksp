buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.4.21"))
    }
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }

    group = "org.github.kubode"
    version = "1.0.0-SNAPSHOT"
}
