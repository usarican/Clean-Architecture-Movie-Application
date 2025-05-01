package com.iusarican

import org.gradle.kotlin.dsl.configure
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.Properties

class SecretsConventionPlugin : Plugin<Project> {
    override fun apply(project: Project): Unit = with(project) {
        val localProps = Properties().apply {
            val propsFile = rootProject.file("local.properties")
            if (propsFile.exists()) load(propsFile.inputStream())
        }

        val tmdbToken = localProps.getProperty("TMDB_AUTH_TOKEN")

        extensions.configure<LibraryExtension> {
            defaultConfig {
                buildConfigField("String", "TMDB_AUTH_TOKEN", "$tmdbToken")
            }
        }
    }
}