@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.lgtm.android.common_ui"
}

dependencies {
    api(project(":domain"))
    api(libs.flexbox)
    api(libs.glide)

    implementation(libs.bundles.androidx.ui.foundation)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    implementation(libs.bundles.basic.test)
    implementation(libs.hilt)
    kapt(libs.hilt.kapt)
}