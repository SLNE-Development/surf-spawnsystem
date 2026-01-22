package dev.slne.surf.spawn.config

import org.bukkit.Bukkit

data class SpawnConfig(
    var spawns: List<SingleSpawnConfig> = mutableListOf(Bukkit.getWorlds().first().spawnLocation.let {
        SingleSpawnConfig(
            spawnName = "vanilla_spawn",
            worldName = it.world.name,
            x = it.x,
            y = it.y,
            z = it.z,
            yaw = it.yaw,
            pitch = it.pitch
        )
    })
)