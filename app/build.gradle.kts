import groovy.lang.Closure

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(28)

    defaultConfig {
        applicationId = "es.voghdev.chucknorrisjokes"
        minSdkVersion(21)
        targetSdkVersion(28)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

val kotlinVersion: String by rootProject.extra
val coroutinesVersion: String by rootProject.extra
val arrowVersion: String by rootProject.extra
val retrofitVersion: String by rootProject.extra

dependencies {

    // Unit Testing
    testImplementation("junit:junit:4.12")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0")
    testImplementation("org.mockito:mockito-core:2.23.0")
    testImplementation("io.kotlintest:kotlintest:2.0.7")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")
    implementation("androidx.appcompat:appcompat:1.0.0")
    implementation("com.google.android.material:material:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("com.squareup.picasso:picasso:2.5.2")
    implementation("com.jakewharton.picasso:picasso2-okhttp3-downloader:1.0.2")
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("io.arrow-kt:arrow-core-data:$arrowVersion")
    implementation("io.arrow-kt:arrow-core-extensions:$arrowVersion")
}
