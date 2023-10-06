plugins {
    id("java-library")
    kotlin("jvm")
    kotlin("kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    api(libs.bundles.gson)
    api(project(":swm-logging"))
    implementation(libs.javax.inject)
    implementation(libs.kotlin.coroutines)
}