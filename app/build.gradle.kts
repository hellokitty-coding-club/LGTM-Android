plugins {
    kotlin("kapt")
    kotlin("android")
    id("com.android.application")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.lgtm.android"

    defaultConfig {
        applicationId = "com.lgtm.android"
        vectorDrawables.useSupportLibrary = true
    }
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

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":feature:auth"))
    implementation(project(":common-ui"))

    // todo) MainActivity Feature 모듈로 옮기고 나면 삭제
    implementation(libs.bundles.androidx.ui.foundatation)
    implementation(libs.constraintlayout)

    implementation(libs.bundles.hilt)
    implementation(libs.bundles.basic.test)
}
