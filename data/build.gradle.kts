plugins {
    // android
    id("com.android.library")
    // jetbrains
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    // maven
    id("maven-publish")
}

android {
    namespace  = "ir.farsroidx.data"
    compileSdk = 33
    defaultConfig {
        minSdk                    = 21
        multiDexEnabled           = true
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    // Preferences
    api("androidx.datastore:datastore-preferences:1.0.0")

    // Room Database
    implementation("androidx.room:room-runtime:2.5.2")
    implementation("androidx.room:room-ktx:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")

    // Retrofit
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-gson:2.9.0")
    api("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // =-------------------------------------------------------------------------------------------=
    // =--= Test Dependencies =---------------------------------------------= Test Dependencies =--=
    // =-------------------------------------------------------------------------------------------=

    // Unit Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

publishing {

    publications {

        register<MavenPublication>("release") {

            groupId    = "ir.farsroidx"
            artifactId = "android-data"
            version    = "1.0.0"

            afterEvaluate {
                from(
                    components["release"]
                )
            }
        }
    }
}