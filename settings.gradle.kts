pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://jitpack.io")
        // Aliyun mirrors as fallback for China-only libraries
        maven("https://maven.aliyun.com/repository/google")
        maven("https://maven.aliyun.com/repository/central")
        maven("https://maven.aliyun.com/repository/public")
    }
}
plugins {
    id("com.highcapable.sweetdependency") version "1.0.4"
    id("com.highcapable.sweetproperty") version "1.0.5"
}
sweetProperty {
    isEnable = true
    global {
        all {
            isEnableTypeAutoConversion = true
            propertiesFileNames(
                "keystore.properties",
                "application.properties",
                isAddDefault = true
            )
            permanentKeyValues(
                "keystore.file" to "",
                "keystore.password" to "",
                "keystore.key.alias" to "",
                "keystore.key.password" to "",
            )
            generateFrom(CURRENT_PROJECT, ROOT_PROJECT)
        }
        buildScript {
            extensionName = "property"
        }
    }
}

rootProject.name = "TiebaLite"
include(":app")

dependencyResolutionManagement {
    repositories {
        maven { url = uri("https://verve.jfrog.io/artifactory/verve-gradle-release/") }
    }
}
