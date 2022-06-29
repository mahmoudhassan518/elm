// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
    dependencies {
        classpath(Google.playServicesGradlePlugin)
        classpath(Google.dagger.hilt.android.gradlePlugin)
        classpath(AndroidX.navigation.safeArgsGradlePlugin)
        classpath(kotlin("gradle-plugin", version = "1.5.20"))
        classpath("com.android.tools.build:gradle:7.0.3")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
