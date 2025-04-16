package com.iusarican

import com.iusarican.convention.implementation
import com.iusarican.convention.ksp
import com.iusarican.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class RetrofitConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            pluginManager.apply("com.google.devtools.ksp")

            dependencies {
                implementation(libs.findLibrary("retrofit").get())
                implementation(libs.findLibrary("kotlinx-serialization-json").get())
                implementation(libs.findLibrary("okhttp").get())
                implementation(libs.findLibrary("logging-interceptor").get())
                implementation(libs.findLibrary("moshi").get())
                implementation(libs.findLibrary("moshi-converter").get())
                ksp(libs.findLibrary("moshi-codegen").get())
            }
        }
    }
}