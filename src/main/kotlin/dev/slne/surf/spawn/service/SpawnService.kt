package dev.slne.surf.spawn.service

import dev.slne.surf.spawn.config.SingleSpawnConfig
import dev.slne.surf.spawn.config.SpawnConfig
import dev.slne.surf.spawn.spawnConfig
import dev.slne.surf.surfapi.core.api.util.mutableObjectSetOf
import org.bukkit.Location

val spawnService = SpawnService()

class SpawnService {
    private val _spawns = mutableObjectSetOf<SingleSpawnConfig>()

    fun reloadSpawns() {
        _spawns.clear()
        _spawns.addAll(spawnConfig.spawns)
    }

    fun getNearestSpawn(location: Location) =
        _spawns.minByOrNull { it.location.distanceSquared(location) }

    fun getRandomSpawn() = _spawns.randomOrNull()
    fun getRandomSpawnLocation() = getRandomSpawn()?.location
}