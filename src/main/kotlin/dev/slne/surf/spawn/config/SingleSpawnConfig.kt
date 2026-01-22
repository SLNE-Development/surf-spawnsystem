package dev.slne.surf.spawn.config

import org.bukkit.Bukkit

data class SingleSpawnConfig(
    val worldName: String,
    val x: Double,
    val y: Double,
    val z: Double,
    val yaw: Float,
    val pitch: Float
) {
    val world get() = Bukkit.getWorld(worldName) ?: error("World '$worldName' not found")
}
