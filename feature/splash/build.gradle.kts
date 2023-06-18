plugins {
    kotlin("android")
    id("com.android.library")
    kotlin("kapt")
}

android {
    namespace = "com.lgtm.android.splash"

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
    implementation(project(":common-ui"))

    implementation(libs.bundles.androidx.ui.foundation)
    implementation(libs.constraintlayout)
    implementation(libs.hilt)
    kapt(libs.hilt.kapt)
    implementation(libs.bundles.basic.test)
}