package dev.slne.surf.spawn.config.manager

import dev.slne.surf.api.core.config.SurfConfigApi
import dev.slne.surf.api.core.config.manager.SpongeConfigManager
import dev.slne.surf.spawn.config.SpawnConfig
import dev.slne.surf.spawn.plugin

class SpawnConfigManager {
    private val configManager: SpongeConfigManager<SpawnConfig>

    init {
        SurfConfigApi.createSpongeYmlConfig(
            SpawnConfig::class.java,
            plugin.dataPath,
            "config.yml"
        )
        configManager = SurfConfigApi.getSpongeConfigManagerForConfig(
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