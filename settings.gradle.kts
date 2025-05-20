pluginManagement { includeBuild("common/plugins") }

dependencyResolutionManagement {
    // Reuse version catalog from the main build.
    versionCatalogs {
        create("myLibs", { from(files("./common/versions/libs.versions.toml")) })
    }
}


plugins {
    // Apply the foojay-resolver plugin to allow automatic download of JDKs
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0"
}

rootProject.name = "thePerfectGradleProject"
include("app", "list", "utilities")

include(":versions")
project(":versions").projectDir = file("common/versions")
