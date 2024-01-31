@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    namespace = "com.lgtm.android.common_ui"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
}

dependencies {
    api(project(":domain"))
    api(libs.flexbox)
    api(libs.glide)
    api(platform(libs.firebase))
    api(libs.bundles.firebase)

    implementation(libs.bundles.androidx.ui.foundation)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    implementation(libs.bundles.basic.test)
    implementation(libs.hilt)
    kapt(libs.hilt.kapt)

    //compose
    val composePlatform = platform(libs.compose.bom)
    implementation(composePlatform)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.preview)
    implementation(libs.compose.activity)
    implementation(libs.compose.material)
    implementation(libs.compose.foundation)
    implementation(libs.compose.constraintlayout)
    implementation(libs.compose.appcompat.theme)
}