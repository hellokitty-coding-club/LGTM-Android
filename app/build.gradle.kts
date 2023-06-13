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

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":feature:auth"))
    implementation(project(":common-ui"))

    implementation("androidx.core:core-ktx:1.8.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.appcompat:appcompat:1.3.1")

    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-compiler:2.44")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
