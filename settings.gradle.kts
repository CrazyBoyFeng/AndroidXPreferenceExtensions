pluginManagement.repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "AndroidXPreferenceExtensions"
include(":base")
include(":edittext")
include(":numberpicker")
include(":demo")
