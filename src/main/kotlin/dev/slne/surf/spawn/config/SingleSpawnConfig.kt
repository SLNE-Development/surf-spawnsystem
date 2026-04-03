package dev.slne.surf.spawn.config

import dev.slne.surf.spawn.spawnConfig
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.util.BoundingBox
import org.spongepowered.configurate.objectmapping.ConfigSerializable

@ConfigSerializable
data class SingleSpawnConfig(
    val spawnName: String = "default_spawn",
    val worldName: String = Bukkit.getWorlds().first().name,
    val x: Double = 0.0,
    val y: Double = 0.0,
    val z: Double = 0.0,
    val yaw: Float = 0f,
    val pitch: Float = 0f
) {
    val world get() = Bukkit.getWorld(worldName) ?: error("World '$worldName' not found")
    val location get() = Location(world, x, y, z, yaw, pitch)

    private val spawnRange by lazy {
        spawnConfig.spawnRadius
    }

    val boundingBox by lazy {
        BoundingBox(
            x - spawnRange, y - spawnRange, z - spawnRange,
            x + spawnRange, y + spawnRange, z + spawnRange
        )
    }
}
