plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.cloudvault.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cloudvault.app"
        minSdk = 26
        targetSdk = 34
        versionCode = 2
        versionName = "1.0.1"
        resourceConfigurations += listOf("en")
    }

    splits {
        abi {
            isEnable = true
            reset()
            include("arm64-v8a", "armeabi-v7a", "x86_64", "x86")
            isUniversalApk = true
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file("cloudvault.jks")
            storePassword = "cloudvault"
            keyAlias = "cloudvault"
            keyPassword = "cloudvault"
            enableV1Signing = true
            enableV2Signing = true
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("release")
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    packaging {
        jniLibs {
            useLegacyPackaging = true
        }
        resources {
            excludes += listOf(
                "/META-INF/{AL2.0,LGPL2.1}",
                "/META-INF/DEPENDENCIES",
                "/META-INF/LICENSE*",
                "/META-INF/NOTICE*",
                "/META-INF/*.version"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    // VLC (LibVLC Android for Video Streaming)
    implementation("org.videolan.android:libvlc-all:3.6.5")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    // TDLib (AAR containing matched TdApi and native libtdjni.so)
    implementation(files("libs/tdlib.aar"))

    // MediaSession & Audio
    implementation("androidx.media:media:1.7.0")

    // WorkManager for background auto-backup
    implementation("androidx.work:work-runtime-ktx:2.9.0")
}
