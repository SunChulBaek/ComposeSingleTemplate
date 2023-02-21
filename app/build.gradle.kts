plugins {
    id("kr.pe.ssun.application")
    id("kr.pe.ssun.application.compose")
    id("kr.pe.ssun.hilt")
}

android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    // TODO : build fix (https://dagger.dev/hilt/gradle-setup.html)
    hilt {
        enableAggregatingTask = false
    }
}

dependencies {
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.3.0")

    implementation(libs.androidx.core.splashscreen)

    // accompanist
    implementation(libs.accompanist.appcompat.theme)
    implementation(libs.accompanist.drawablepainter)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.accompanist.navigation.animation)
    implementation(libs.accompanist.navigation.material)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.pager.indicator)
    implementation(libs.accompanist.permissions)
    implementation(libs.accompanist.placeholder.material)
    implementation(libs.accompanist.placeholder)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.accompanist.webview)

    // Compose
    // Choose one of the following:
    // Material Design 3
    implementation(libs.androidx.compose.material3)
    // or Material Design 2
    //implementation(libs.androidx.compose.material)
    // or skip Material Design and build directly on top of foundational components
    // implementation("androidx.compose.foundation:foundation")
    // or only import the main APIs for the underlying toolkit systems,
    // such as input and measurement/layout
    // implementation("androidx.compose.ui:ui")

    // Android Studio Preview support
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)

    // UI Tests
    // androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    // debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Optional - Included automatically by material, only add when you need
    // the icons but not the material library (e.g. when using Material3 or a
    // custom design system based on Foundation)
    // implementation("androidx.compose.material:material-icons-core")
    // Optional - Add full set of material icons
    // implementation("androidx.compose.material:material-icons-extended")
    // Optional - Add window size utils
    // implementation("androidx.compose.material3:material3-window-size-class")

    // Optional - Integration with activities
    implementation(libs.androidx.activity.compose)
    // Optional - Integration with ViewModels
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Optional - Integration with LiveData
    // implementation("androidx.compose.runtime:runtime-livedata")
    // Optional - Integration with RxJava
    // implementation("androidx.compose.runtime:runtime-rxjava2")

    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.constraint.layout.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.lottie.compose)

    // Android Architecture Components
    implementation(libs.androidx.lifecycle.runtime.ktx)

    implementation(libs.androidx.startup)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson.converter)
    implementation(libs.okhttp.logging.interceptor)

    // Coil
    implementation(libs.coil.kt)
    implementation(libs.coil.kt.compose)

    // Timber
    implementation(libs.timber)
}