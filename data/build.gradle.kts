plugins {
    kotlin("android")
    id("com.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.lgtm.android.data"

}

dependencies {
    implementation(project(":domain"))

    implementation(libs.hilt)
    kapt(libs.hilt.kapt)
    implementation(libs.bundles.basic.test)

    // app 모듈에 전달 (NetworkModule)
    api(libs.bundles.retrofit)
    api(libs.bundles.okhttp)
    api(libs.bundles.gson)
}
