rootProject.name = "School-v5"
include("core")

//Plugin management
pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}
include("common")
include("dungeon")
