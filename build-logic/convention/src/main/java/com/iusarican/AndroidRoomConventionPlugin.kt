package com.iusarican

import androidx.room.gradle.RoomExtension
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.google.devtools.ksp.gradle.KspExtension
import com.iusarican.convention.implementation
import com.iusarican.convention.ksp
import com.iusarican.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidRoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            pluginManager.withPlugin("com.android.base") {

                pluginManager.apply("com.google.devtools.ksp")
                pluginManager.apply("androidx.room")

                extensions.configure<KspExtension> {
                    arg("room.generateKotlin", "true")
                }

                extensions.configure<RoomExtension> {
                    schemaDirectory("$projectDir/schemas")
                }

                dependencies {
                    implementation(libs.findLibrary("room-runtime").get())
                    implementation(libs.findLibrary("room-ktx").get())
                    ksp(libs.findLibrary("room-compiler").get())
                }
            }
        }
    }
}