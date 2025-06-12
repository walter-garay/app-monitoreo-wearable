plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)


    // Plugins agregados por mi
    id("org.jetbrains.kotlin.kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.wgaray.appmonitoreo"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.wgaray.appmonitoreo"
        minSdk = 26
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
        compose = true
    }
}

dependencies {

    // Core y Compose
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Librerías agregadas
    implementation("androidx.navigation:navigation-compose:2.9.0")
    implementation("androidx.compose.material:material-icons-extended:1.7.8")

    // ViewModel y Lifecycle para Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.9.0")

    // Retrofit y Gson
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // OkHttp (para logs de red)
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")

    // Firebase para notificaciones
    implementation(platform("com.google.firebase:firebase-bom:33.13.0"))
    implementation("com.google.firebase:firebase-messaging")

    // Jetpack Datastore para guardar token localmente
    implementation("androidx.datastore:datastore-preferences:1.1.6")

    // Hilt (inyección de dependencias)
    implementation("com.google.dagger:hilt-android:2.56.2")
    kapt("com.google.dagger:hilt-compiler:2.56")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}
