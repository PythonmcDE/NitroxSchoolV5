plugins {
    id("io.papermc.paperweight.userdev") version "1.3.9"
}

apply(plugin = "io.papermc.paperweight.userdev")

dependencies {
    paperDevBundle("1.19.3-R0.1-SNAPSHOT")
    implementation(project(":common"))
    implementation(project(":core"))


    //WorldEdit and FaWe
    implementation(platform("com.intellectualsites.bom:bom-1.18.x:1.11"))
    compileOnlyApi("com.fastasyncworldedit:FastAsyncWorldEdit-Core")
    compileOnlyApi("com.fastasyncworldedit:FastAsyncWorldEdit-Bukkit") { isTransitive = false }
}