buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("com.google.gms:google-services:4.3.15")
    }
}

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.dagger.hilt) apply false
    alias(libs.plugins.kotlin.jvm) apply false
    alias(libs.plugins.crashlytics) apply false
    alias(libs.plugins.sonarqube) apply false
}

subprojects {
    afterEvaluate {
        project.apply("$rootDir/gradle/common.gradle")
    }
}
