import org.jetbrains.kotlin.konan.properties.Properties

val lgtmPropertiesFile = rootProject.file("lgtm.properties")
val lgtmProperties = Properties()
lgtmProperties.load(lgtmPropertiesFile.inputStream())

plugins {
    kotlin("android")
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    kotlin("kapt")
}

android {
    namespace = "com.lgtm.android"

    defaultConfig {
        applicationId = "com.lgtm.android"
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.appVersion.get()
        vectorDrawables.useSupportLibrary = true

        buildConfigField(
            "String",
            "LGTM_BASE_URL_DEBUG",
            lgtmProperties.getProperty("LGTM_BASE_URL_DEBUG")
        )
        buildConfigField(
            "String",
            "LGTM_BASE_URL_RELEASE",
            lgtmProperties.getProperty("LGTM_BASE_URL_RELEASE")
        )
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        dataBinding = true
        buildConfig = true
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
    implementation(libs.bundles.androidx.ui.foundation)
    implementation(libs.constraintlayout)

    implementation(libs.hilt)
    implementation(libs.firebase)
    kapt(libs.hilt.kapt)
    implementation(libs.bundles.basic.test)

}
