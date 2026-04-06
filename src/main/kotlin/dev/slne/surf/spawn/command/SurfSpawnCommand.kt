package dev.slne.surf.spawn.command

import dev.jorel.commandapi.kotlindsl.*
import dev.slne.surf.api.core.messages.adventure.getPointer
import dev.slne.surf.api.core.messages.adventure.sendText
import dev.slne.surf.spawn.command.argument.spawnArgument
import dev.slne.surf.spawn.config.SingleSpawnConfig
import dev.slne.surf.spawn.permission.PermissionRegistry
import dev.slne.surf.spawn.service.spawnService
import dev.slne.surf.spawn.spawnConfigManager
import net.kyori.adventure.identity.Identity
import net.kyori.adventure.text.event.ClickEvent
import org.bukkit.Bukkit

fun surfSpawnCommand() = commandTree("surfSpawn") {
    withPermission(PermissionRegistry.COMMAND_SURF_SPAWN)

    literalArgument("set") {
        stringArgument("spawnName") {
            playerExecutor { player, args ->
                val spawnName: String by args

                spawnService.addSpawn(spawnName, player.location)

                player.sendText {
                    appendSuccessPrefix()
                    success("Der Spawn ")
                    variableValue(spawnName)
                    success(" wurde gespeichert.")
                }
            }
        }
    }

    literalArgument("reload") {
        anyExecutor { executor, _ ->
            spawnConfigManager.reload()
            spawnService.reloadSpawns()

            executor.sendText {
                appendSuccessPrefix()
                success("Die Spawns wurden neu geladen.")
            }
        }
    }

    literalArgument("delete") {
        spawnArgument("spawn") {
            anyExecutor { executor, args ->
                val spawn: SingleSpawnConfig by args

                spawnService.deleteSpawn(spawn.spawnName)

                executor.sendText {
                    appendSuccessPrefix()
                    success("Der Spawn ")
                    variableValue(spawn.spawnName)
                    success(" wurde gelöscht.")
                }
            }
        }
    }

    literalArgument("list") {
        anyExecutor { executor, _ ->
            val spawns = spawnService.getSpawns()

            if (spawns.isEmpty()) {
                executor.sendText {
                    appendErrorPrefix()
                    error("Es sind keine Spawns gespeichert.")
                }
                return@anyExecutor
            }

            executor.sendText {
                appendInfoPrefix()
                info("Es sind ")
                variableValue(spawns.size)
                info(" Spawns gespeichert: ")

                spawns.forEachIndexed { index, spawn ->
                    append {
                        variableValue(spawn.spawnName)
                        clickEvent(ClickEvent.callback {
                            Bukkit.getPlayer(it.getPointer(Identity.UUID) ?: return@callback)
                                ?.teleportAsync(spawn.location)?.thenRun {
                                    it.sendText {
                                        appendSuccessPrefix()
                                        success("Du wurdest zum Spawn ")
                                        variableValue(spawn.spawnName)
                                        success(" teleportiert.")
                                    }
                                }
                        })

                        if (index < spawns.size - 1) {
                            spacer(", ")
                        }
                    }
                }
            }
        }
    }
}