plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-parcelize")
    alias(libs.plugins.hilt.android)  // Add this line
    kotlin("kapt")
}

android {
    namespace = "com.example.social_guard_dev"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.social_guard_dev"
        minSdk = 29
        targetSdk = 35
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
        viewBinding = true
    }
}

dependencies {
    // Core KTX
    implementation(libs.androidx.core.ktx)

    // UI
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    // Lifecycle components
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Fragment KTX (for viewModels delegate)
    implementation(libs.androidx.fragment.ktx)

    // Chart library
    implementation(libs.mpandroidchart)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Hilt for Dependency Injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    kapt("androidx.hilt:hilt-compiler:1.2.0")

    // ViewBinding
    implementation("androidx.databinding:viewbinding:8.4.0")

    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // For viewModels() delegate
    implementation("androidx.activity:activity-ktx:1.9.0")
    implementation("androidx.fragment:fragment-ktx:1.6.2")
}