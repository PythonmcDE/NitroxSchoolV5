plugins {
    id("io.papermc.paperweight.userdev") version "1.3.9"
}

apply(plugin = "io.papermc.paperweight.userdev")

dependencies {
    paperDevBundle("1.19.3-R0.1-SNAPSHOT")
    implementation(project(":common"))

    //NbtTags
    api("io.github.bananapuncher714:nbteditor:7.18.3")

    //scoreboards
    implementation("com.github.CubBossa:MenuFramework:v1.1.5")
}