package dev.slne.surf.spawn.service

import dev.slne.surf.api.core.util.mutableObjectSetOf
import dev.slne.surf.spawn.config.SingleSpawnConfig
import dev.slne.surf.spawn.spawnConfig
import dev.slne.surf.spawn.spawnConfigManager
import org.bukkit.Bukkit
import org.bukkit.Location

val spawnService = SpawnService()

class SpawnService {
    private val _spawns = mutableObjectSetOf<SingleSpawnConfig>()

    fun reloadSpawns() {
        _spawns.clear()
        _spawns.addAll(spawnConfig.spawns)
    }

    fun getSpawns() = _spawns
    fun getSpawn(name: String) =
        _spawns.firstOrNull { it.spawnName.equals(name, ignoreCase = true) }


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

        spawnConfigManager.edit {
            spawns = spawns + newSpawn
        }
    }

    fun deleteSpawn(spawnName: String) {
        val spawn = getSpawn(spawnName) ?: return
        _spawns.remove(spawn)

        spawnConfigManager.edit {
            spawns = spawns.filterNot { it.spawnName.equals(spawnName, ignoreCase = true) }
        }
    }

    fun getNearestSpawn(location: Location) =
        _spawns.filter { it.world == location.world }
            .minByOrNull { it.location.distanceSquared(location) }

    fun getNearestSpawnLocation(location: Location) =
        getNearestSpawn(location)?.location ?: error("No spawns available")

    fun getRandomOverworldSpawn() =
        _spawns.filter { it.world == Bukkit.getWorlds().first() }.randomOrNull()

    fun getRandomOverworldSpawnLocation() = getRandomOverworldSpawn()?.location
}