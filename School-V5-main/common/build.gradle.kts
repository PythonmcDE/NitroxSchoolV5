plugins {
    id("io.papermc.paperweight.userdev") version "1.3.9"
}

apply(plugin = "io.papermc.paperweight.userdev")

dependencies {
    paperDevBundle("1.19.3-R0.1-SNAPSHOT")

    api("org.mongodb:mongo-java-driver:3.12.10")
    api("com.zaxxer:HikariCP:5.0.1")

    api("io.github.bananapuncher714:nbteditor:7.18.3")
}