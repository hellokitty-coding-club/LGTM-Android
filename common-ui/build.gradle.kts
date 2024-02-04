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
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compiler.get()
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
    api(composePlatform)
    api(libs.compose.ui)
    api(libs.compose.ui.tooling)
    api(libs.compose.preview)
    api(libs.compose.activity)
    api(libs.compose.material)
    api(libs.compose.foundation)
    api(libs.compose.constraintlayout)
    api(libs.compose.appcompat.theme)
}