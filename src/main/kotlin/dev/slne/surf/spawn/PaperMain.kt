package dev.slne.surf.spawn

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import dev.slne.surf.api.paper.event.register
import dev.slne.surf.spawn.command.spawnCommand
import dev.slne.surf.spawn.command.surfSpawnCommand
import dev.slne.surf.spawn.config.manager.SpawnConfigManager
import dev.slne.surf.spawn.listener.PlayerJoinListener
import dev.slne.surf.spawn.listener.PlayerNetherPortalListener
import dev.slne.surf.spawn.listener.PlayerRespawnListener
import dev.slne.surf.spawn.service.spawnService
import org.bukkit.plugin.java.JavaPlugin

val plugin get() = JavaPlugin.getPlugin(PaperMain::class.java)

class PaperMain : SuspendingJavaPlugin() {
    override fun onEnable() {
        spawnCommand()
        surfSpawnCommand()

        spawnService.reloadSpawns()

        PlayerJoinListener.register()
        PlayerRespawnListener.register()
        PlayerNetherPortalListener.register()
    }
}

val spawnConfigManager = SpawnConfigManager()
val spawnConfig get() = spawnConfigManager.config