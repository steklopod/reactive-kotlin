description = """ parent/root module """

plugins {
    java
    base
}

allprojects {
    // group = "ru.steklopod"
    repositories {
        gradlePluginPortal()
    }
   
}

subprojects { tasks { } }

dependencies {
    subprojects.forEach { archives(it) }
}
