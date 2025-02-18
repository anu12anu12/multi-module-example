import java.lang.management.ManagementFactory

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.android.google.dagger.hilt.android)
    alias(libs.plugins.kotlin.serialization)
}
val mockitoAgent = configurations.create("mockitoAgent")

android {
    namespace = "com.anupam.reviews"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    tasks.withType<Test>().all {
        doFirst {
            // Use a safer way to get JVM args if needed, avoiding internal APIs
            val runtimeMxBean = ManagementFactory.getRuntimeMXBean()
            val jvmArgs = runtimeMxBean.inputArguments
            println("JVM Args: $jvmArgs")
        }
        jvmArgs("-javaagent:${mockitoAgent.asPath}")
    }
}

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    api(project(":core:common"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.truth)
    testImplementation(libs.turbine)
    testImplementation(libs.mockk)
    testImplementation(project(":core:sharedTests"))
    testImplementation(project(":core:sharedTests"))
    androidTestImplementation(libs.mockk.android)
    testImplementation(libs.mockito.android)
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.junit.jupiter.params)
    testImplementation(libs.androidx.paging.test)
    mockitoAgent(libs.mockito.core) { isTransitive = false }

    implementation(libs.androidx.material3)
    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    androidTestImplementation(libs.hilt.android.test)
    kspAndroidTest(libs.hilt.compiler)
    testImplementation(libs.hilt.android.test)
    kspTest(libs.hilt.compiler)
    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.moshi)
    implementation(libs.logging.interceptor)

    // Paging
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.room.paging)
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.test)

    //Image loader
    implementation(libs.coil)
    implementation(libs.coil.compose)

    //Moshi
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)
    implementation(libs.androidx.navigation.compose)
}