package dev.slne.surf.spawn.service

import dev.slne.surf.spawn.config.SingleSpawnConfig
import dev.slne.surf.spawn.config.SpawnConfig
import dev.slne.surf.spawn.spawnConfig
import dev.slne.surf.surfapi.core.api.util.mutableObjectSetOf

val spawnService = SpawnService()

class SpawnService {
    private val _spawns = mutableObjectSetOf<SingleSpawnConfig>()


    fun reloadSpawns() {
        _spawns.clear()
        _spawns.addAll(spawnConfig.spawns)
    }

}