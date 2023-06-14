@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.lgtm.android.common_ui"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.bundles.androidx.ui.foundatation)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    implementation(libs.bundles.basic.test)
}