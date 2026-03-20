package dev.slne.surf.spawn.listener

import dev.slne.surf.spawn.service.spawnService
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRespawnEvent

object PlayerRespawnListener : Listener {
    @EventHandler
    fun onRespawn(event: PlayerRespawnEvent) {
        val hasRespawnPoint = event.player.respawnLocation != null

        if(hasRespawnPoint) {
            return
        }

        spawnService.getNearestSpawn(event.player.location)?.location?.let {
            event.respawnLocation = it
        }
    }
}