plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
    id("kotlin-parcelize")
}

android {
    namespace = "com.keerthi77459.voicenotes"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.keerthi77459.voicenotes"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "0.0.1"

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
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/DEPENDENCIES"
        }
    }
}

dependencies {

// --- CORE AND LIFECYCLE (Use Stable Versions) ---
    implementation("androidx.core:core-ktx:1.13.1") // Updated to stable
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.6") // Updated to latest stable
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.activity:activity-compose:1.9.3") // Updated to latest stable

    // --- COMPOSE (The Fix) ---
    // Update BOM to 2024.10.00 (or at least 2024.09.00) to get Compose 1.7+
    implementation(platform("androidx.compose:compose-bom:2024.10.00"))

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")

    // You can remove the version 1.3.0 here, the BOM 2024.10.00 maps to M3 1.3.0 automatically
    implementation("androidx.compose.material3:material3")

    // This will now pull in Material 2 version 1.7.0 (Compatible with new Ripple)
    implementation("androidx.compose.material:material-icons-extended")

    // --- GOOGLE MATERIAL ---
    implementation("com.google.android.material:material:1.12.0")

    // --- NAVIGATION & HILT ---
    implementation("androidx.navigation:navigation-compose:2.8.3")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("com.google.dagger:hilt-android:2.51.1") // 2.52 is fine if available, 2.51.1 is common stable
    ksp("com.google.dagger:hilt-compiler:2.51.1")

    implementation("com.google.code.gson:gson:2.11.0")

    implementation(platform("com.google.firebase:firebase-bom:34.3.0"))
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-crashlytics")
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-database")
    implementation("com.google.firebase:firebase-firestore")
    implementation("com.google.firebase:firebase-appcheck-playintegrity")
    implementation("com.google.firebase:firebase-appcheck-debug")
    implementation("com.google.firebase:firebase-appcheck")
    implementation("com.google.firebase:firebase-functions")
    implementation("com.google.firebase:firebase-perf")
    implementation("com.google.firebase:firebase-messaging")
    implementation("com.google.firebase:firebase-messaging-directboot")
    implementation("com.google.firebase:firebase-inappmessaging-display")

    implementation("androidx.core:core-splashscreen:1.0.1")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.4")

    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")

    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.6")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation("com.github.marlonlom:timeago:4.0.3")
    implementation("com.razorpay:checkout:1.6.41")

    implementation("com.patrykandpatrick.vico:compose-m3:1.12.0")
//    implementation("com.github.jkuatdsc:form-builder:1.0.7")

    implementation("com.google.android.gms:play-services-auth:21.3.0")
    implementation("com.google.auth:google-auth-library-oauth2-http:1.16.0")
    implementation("com.google.http-client:google-http-client-gson:1.42.3")
    implementation("com.google.api-client:google-api-client-android:1.26.0")
    implementation("com.google.apis:google-api-services-drive:v3-rev136-1.25.0")

    implementation("com.facebook.android:facebook-android-sdk:18.0.2")
    implementation("commons-io:commons-io:2.16.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

//    Compose Permission
    implementation("com.google.accompanist:accompanist-permissions:0.21.1-beta")

//    implementation("org.apache.poi:poi:5.5.0") // For .xls files
//    implementation("org.apache.poi:poi-ooxml:5.5.0") // For .xlsx files
//    implementation("com.fasterxml.aalto:aalto-xml:1.3.2") // Required by POI

}
