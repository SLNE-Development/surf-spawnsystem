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
        println("Entity ${event.entity.name} entered a portal of type ${event.portalType}")
        if (event.portalType != PortalType.NETHER) {
            return
        }

        println("Checking if entity is in spawn protection...")

        val spawn = isInSpawnProtection(event.entity.location) ?: return
        println("Entity is in spawn protection, teleporting to spawn ${spawn.spawnName} at ${spawn.location}")
        event.entity.teleportAsync(spawn.location)
    }

    private fun isInSpawnProtection(location: Location): SingleSpawnConfig? =
        spawnConfig.spawns.firstOrNull { spawn ->
            (spawn.location.world == location.world && spawn.boundingBox.contains(location.toVector())).also {
                println("Spawn ${spawn.spawnName} is ${if (it) "within" else "outside of"} spawn protection radius")
            }
        }
}