package dev.slne.surf.spawn

import com.github.shynixn.mccoroutine.folia.SuspendingJavaPlugin
import dev.slne.surf.spawn.command.spawnCommand
import dev.slne.surf.spawn.command.surfSpawnCommand
import dev.slne.surf.spawn.config.manager.SpawnConfigManager
import org.bukkit.plugin.java.JavaPlugin

val plugin get() = JavaPlugin.getPlugin(PaperMain::class.java)

class PaperMain : SuspendingJavaPlugin() {
    val spawnConfigManager = SpawnConfigManager()

    override fun onEnable() {
        spawnCommand()
        surfSpawnCommand()
    }
}

val spawnConfig get() = plugin.spawnConfigManager.config