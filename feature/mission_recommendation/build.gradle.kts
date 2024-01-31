@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
}

android {
    namespace = "com.lgtm.android.mission_recommendation"

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
    kapt(libs.hilt.kapt)

    //compose
    val composePlatform = platform(libs.compose.bom)
    implementation(composePlatform)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.preview)
    implementation(libs.compose.activity)
    implementation(libs.compose.material)
    implementation(libs.compose.foundation)
    implementation(libs.compose.constraintlayout)
    implementation(libs.compose.appcompat.theme)
}