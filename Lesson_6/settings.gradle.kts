pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Lesson_6"
include(":app")
include(":getsharedpreferences")
include(":securesharedpreferences")
include(":internalfilestorage")
include(":notebook")
include(":employeedb")
