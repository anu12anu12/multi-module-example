import java.lang.management.ManagementFactory

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.ksp)
  alias(libs.plugins.android.google.dagger.hilt.android)
  alias(libs.plugins.kotlin.serialization)
}

val mockitoAgent = configurations.create("mockitoAgent")

android {
  namespace = "com.getyourguide.interview"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.getyourguide.interview"
    minSdk = 26
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "com.getyourguide.interview.HiltTestRunner"
    buildConfigField("String", "BASE_URL", "\"https://travelers-api.getyourguide.com/\"")
  }

  buildFeatures {
    viewBinding = true
    compose = true
    buildConfig = true
  }

  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
  }

  kotlin {
    jvmToolchain(21)
  }

  packaging {
    resources.excludes.add("META-INF/*.kotlin_module")
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
  implementation(project(":features:reviews"))
  implementation(project(":features:wishlist"))
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.androidx.fragment.ktx)

  // UI
  implementation(libs.androidx.constraintlayout)

  // Network
  //Retrofit
  implementation(libs.retrofit)
  implementation(libs.converter.moshi)
  implementation(libs.logging.interceptor)
  //Moshi
  implementation(libs.moshi)
  ksp(libs.moshi.codegen)

  // UI
  //Image loader
  implementation(libs.coil)
  implementation(libs.coil.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
  // Compose Navigation
  implementation(libs.androidx.navigation.compose)

  // Tests
  testImplementation(libs.kotlinx.coroutines.test)
  testImplementation(libs.truth)
  testImplementation(libs.turbine)
  testImplementation(libs.mockk)
  androidTestImplementation(libs.mockk.android)
  testImplementation(libs.mockito.android)
  testImplementation(libs.mockito.core)
  testImplementation(libs.mockito.inline)
  testImplementation(libs.mockito.kotlin)
  mockitoAgent(libs.mockito.core) { isTransitive = false }
  testImplementation(libs.junit.jupiter.params)
  testImplementation(libs.androidx.paging.test)

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

  // Paging
  implementation(libs.androidx.paging.compose)
  implementation(libs.androidx.room.paging)
  implementation(libs.androidx.paging.runtime.ktx)
  implementation(libs.androidx.paging.test)
}
