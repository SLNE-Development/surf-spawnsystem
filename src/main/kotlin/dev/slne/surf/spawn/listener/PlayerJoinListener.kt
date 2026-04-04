package dev.slne.surf.spawn.listener

import dev.slne.surf.spawn.service.spawnService
import io.papermc.paper.event.player.AsyncPlayerSpawnLocationEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

@Suppress("UnstableApiUsage")
object PlayerJoinListener : Listener {
    @EventHandler
    fun onRespawn(event: AsyncPlayerSpawnLocationEvent) {
        if (!event.isNewPlayer) {
            return
        }

        event.spawnLocation =
            spawnService.getRandomOverworldSpawnLocation() ?: error("No spawns configured!")
    }
}