package dev.slne.surf.spawn.listener

import dev.slne.surf.spawn.service.spawnService
import io.papermc.paper.event.player.AsyncPlayerSpawnLocationEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

@Suppress("UnstableApiUsage")
object PlayerJoinListener : Listener {
    @EventHandler
    fun onRespawn(event: AsyncPlayerSpawnLocationEvent) {
        if(!event.isNewPlayer) {
            return
        }

        event.spawnLocation = spawnService.getRandomSpawnLocation() ?: error("No spawns configured!")
    }
}