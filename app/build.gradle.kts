plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
//    id ("kotlin-kapt") // For Kotlin projects
    id ("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.example.pagginationapp"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.pagginationapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
// Or the latest version
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
// For JSON conversion using Gson
    implementation("com.google.code.gson:gson:2.9.1")
// Gson library itself
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
// For network request logging (optional)

    implementation ("com.google.dagger:hilt-android:2.49") // Use the latest version
    kapt ("com.google.dagger:hilt-compiler:2.49") // Use the latest version

    // Optional: For Hilt integration with Jetpack Compose
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0") // Use the latest version
}