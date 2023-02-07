import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    `java-library`
    id ("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.freefair.lombok") version "6.6-rc1"
}

allprojects {

    group = "xyz.daarkii"
    version = "RC-1.0"

    apply(plugin = "java")
    apply(plugin = "java-library")
    apply(plugin = "com.github.johnrengelman.shadow")
    apply(plugin = "io.freefair.lombok")

    repositories {
        mavenCentral()
        maven("https://repo.dmulloy2.net/repository/public/")
        maven("https://repo.cloudnetservice.eu/repository/releases/")
        maven("https://jitpack.io")
        maven("https://repo.papermc.io/repository/maven-public/") //paper
        maven("https://repo.codemc.org/repository/maven-public/") //NbtTags
        maven("https://repo.aysu.tv/repository/releases/")
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(18))
        }
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(18)
    }
}

subprojects {

    dependencies {
        compileOnlyApi("com.comphenix.protocol:ProtocolLib:4.7.0")
        compileOnlyApi("tv.aysu.api:core:1.4.5")
        api("com.github.stefvanschie.inventoryframework:IF:0.10.8")
    }

    tasks.withType<ShadowJar> {
        relocate("com.github.stefvanschie.inventoryframework", "xyz.daarkii.school.lib.inventoryframework")
    }
}