package dev.slne.surf.spawn.config.manager

import dev.slne.surf.spawn.config.SpawnConfig
import dev.slne.surf.spawn.plugin
import dev.slne.surf.surfapi.core.api.config.manager.SpongeConfigManager
import dev.slne.surf.surfapi.core.api.config.surfConfigApi

class SpawnConfigManager {
    private val configManager: SpongeConfigManager<SpawnConfig>

    init {
        surfConfigApi.createSpongeYmlConfig(
            SpawnConfig::class.java,
            plugin.dataPath,
            "config.yml"
        )
        configManager = surfConfigApi.getSpongeConfigManagerForConfig(
            SpawnConfig::class.java
        )
        reload()
    }

    fun edit(actions: SpawnConfig.() -> Unit) {
        configManager.config = configManager.config.apply { actions() }
        configManager.save()
    }

    fun reload() {
        configManager.reloadFromFile()
    }

    val config get() = configManager.config
}