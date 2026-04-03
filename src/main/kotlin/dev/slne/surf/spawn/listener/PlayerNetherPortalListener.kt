package dev.slne.surf.spawn.listener

import dev.slne.surf.spawn.config.SingleSpawnConfig
import dev.slne.surf.spawn.spawnConfig
import io.canvasmc.canvas.event.EntityPostPortalAsyncEvent
import org.bukkit.Location
import org.bukkit.PortalType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

object PlayerNetherPortalListener : Listener {
    @EventHandler
    fun onNetherPortal(event: EntityPostPortalAsyncEvent) {
        if (event.portalType != PortalType.NETHER) {
            return
        }

        val spawn = isInSpawnProtection(event.entity.location) ?: return
        event.entity.teleportAsync(spawn.location)
    }

    private fun isInSpawnProtection(location: Location): SingleSpawnConfig? =
        spawnConfig.spawns.firstOrNull { spawn ->
            spawn.location.world == location.world && spawn.boundingBox.contains(location.toVector())
        }
}