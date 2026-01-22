plugins {
    id("dev.slne.surf.surfapi.gradle.paper-plugin") version "1.21.11+"
}

group = "dev.slne.surf.spawn"
version = findProperty("version") as String

surfPaperPluginApi {
    mainClass("dev.slne.surf.spawn.PaperMain")
    foliaSupported(true)
    generateLibraryLoader(false)

    authors.addAll("red")
}