package dev.slne.surf.spawn.config

import org.bukkit.Bukkit
import org.bukkit.Location

data class SingleSpawnConfig(
    val spawnName: String,
    val worldName: String,
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float,
    val pitch: Float
) {
    val world get() = Bukkit.getWorld(worldName) ?: error("World '$worldName' not found")
    val location get() = Location(world, x, y, z, yaw, pitch)
}
