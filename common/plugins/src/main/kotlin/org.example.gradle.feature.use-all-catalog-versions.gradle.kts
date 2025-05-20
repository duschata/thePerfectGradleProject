plugins { id("org.gradle.java-platform") }

// Allow upgrading/downgrading (transitive) versions via catalog by adding strict constraints
dependencies.constraints {
    val libs = versionCatalogs.named("myLibs")
    val catalogEntries = libs.libraryAliases.map { libs.findLibrary(it).get().get() }
    catalogEntries.forEach { entry ->
        val module = "${entry.module.group}:${entry.module.name}"
        val requiredVersion = entry.versionConstraint.requiredVersion
        if (requiredVersion.isNotEmpty()) {
            api(module) {
                version {
                    strictly(requiredVersion)
//                    require(requiredVersion)
                }
            }
            logger.info("bind $module to version $requiredVersion")
        }
    }
}
