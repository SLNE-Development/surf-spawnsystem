plugins {
    id("dev.slne.surf.api.gradle.paper-plugin") version "+"
}

group = "dev.slne.surf.spawn"
version = findProperty("version") as String

surfPaperPluginApi {
    mainClass("dev.slne.surf.spawn.PaperMain")
    foliaSupported(true)
    generateLibraryLoader(false)

    useCanvasMc()

    authors.addAll("red")
}