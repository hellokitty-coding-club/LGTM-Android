@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = "com.lgtm.android.main"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        buildConfigField(
            "String", "versionName", "\"${libs.versions.appVersion.get()}\""
        )
    }
}

dependencies {
    implementation(project(":common-ui"))

    implementation(libs.core.ktx)
    implementation(platform(libs.kotlin.bom))
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.kotlin.coroutines)
    implementation(libs.material)
    implementation(libs.bundles.androidx.ui.foundation)
    implementation(libs.constraintlayout)
    implementation(libs.hilt)
    implementation(libs.bundles.navigation)
    implementation(libs.inappupdate)
    kapt(libs.hilt.kapt)
}