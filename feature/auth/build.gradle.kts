import org.jetbrains.kotlin.konan.properties.Properties

val lgtmPropertiesFile = rootProject.file("lgtm.properties")
val lgtmProperties = Properties()
lgtmProperties.load(lgtmPropertiesFile.inputStream())

plugins {
    kotlin("android")
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.lgtm.android.auth"

    defaultConfig {
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

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":common-ui"))

    implementation(libs.kotlin.coroutines)
    implementation(libs.material)
    implementation(libs.bundles.androidx.ui.foundation)
    implementation(libs.constraintlayout)
    implementation(libs.hilt)
    implementation(libs.bundles.navigation)
    kapt(libs.hilt.kapt)
    implementation(libs.bundles.basic.test)
    api(libs.bundles.gson)
}
