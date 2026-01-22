package dev.slne.surf.spawn.service

import dev.slne.surf.spawn.config.SingleSpawnConfig
import dev.slne.surf.spawn.config.SpawnConfig
import dev.slne.surf.spawn.plugin
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

    fun getSpawns() = _spawns
    fun getSpawn(name: String) = _spawns.firstOrNull { it.spawnName.equals(name, ignoreCase = true) }


    fun addSpawn(spawnName: String, location: Location) {
        val newSpawn = SingleSpawnConfig(
            spawnName = spawnName,
            worldName = location.world.name,
            x = location.x,
            y = location.y,
            z = location.z,
            yaw = location.yaw,
            pitch = location.pitch
        )
        _spawns.add(newSpawn)

        plugin.spawnConfigManager.edit {
            spawns = spawns + newSpawn
        }
    }

    fun deleteSpawn(spawnName: String) {
        val spawn = getSpawn(spawnName) ?: return
        _spawns.remove(spawn)

        plugin.spawnConfigManager.edit {
            spawns = spawns.filterNot { it.spawnName.equals(spawnName, ignoreCase = true) }
        }
    }

    fun getNearestSpawn(location: Location) =
        _spawns.minByOrNull { it.location.distanceSquared(location) }
    fun getNearestSpawnLocation(location: Location) = getNearestSpawn(location)?.location ?: error("No spawns available")

    fun getRandomSpawn() = _spawns.randomOrNull()
    fun getRandomSpawnLocation() = getRandomSpawn()?.location
}