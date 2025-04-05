plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.ibrahimcanerdogan.core"
}

apply(from = "$rootDir/scripts/common.gradle")