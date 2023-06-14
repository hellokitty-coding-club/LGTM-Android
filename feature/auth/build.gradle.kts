plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.lgtm.android.auth"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":common-ui"))

    implementation(libs.bundles.androidx.ui.foundatation)
    implementation(libs.constraintlayout)
    implementation(libs.bundles.hilt)
    implementation(libs.bundles.basic.test)
}
