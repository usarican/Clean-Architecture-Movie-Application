package com.iusarican

import com.iusarican.convention.implementation
import com.iusarican.convention.ksp
import com.iusarican.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = with(target) {
        pluginManager.apply("com.google.devtools.ksp")
        dependencies {
            ksp(libs.findLibrary("hilt.compiler").get())
        }

        /**
         * Purpose of the withPlugin say that if the one of the base plugin applied;
         * com.android.library or com.android.application then apply the plugin and dependencies.
         */
        pluginManager.withPlugin("com.android.base") {
            pluginManager.apply("com.google.dagger.hilt.android") // ✅ Gerçek Hilt Plugin ID
            dependencies {
                implementation(libs.findLibrary("hilt.android").get())
            }
        }
    }
}