@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = "com.lgtm.android.mission_suggestion"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compiler.get()
    }
}

dependencies {
    implementation(project(":common-ui"))

    implementation(libs.core.ktx)
    implementation(platform(libs.kotlin.bom))
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.kotlin.coroutines)
    implementation(libs.bundles.androidx.ui.foundation)
    implementation(libs.constraintlayout)
    implementation(libs.hilt)
    implementation(libs.bundles.navigation)
    implementation(libs.bundles.basic.test)
    kapt(libs.hilt.kapt)
}