package dev.slne.surf.spawn.command

import dev.jorel.commandapi.kotlindsl.anyExecutor
import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.getValue
import dev.jorel.commandapi.kotlindsl.literalArgument
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.jorel.commandapi.kotlindsl.stringArgument
import dev.slne.surf.spawn.permission.PermissionRegistry
import dev.slne.surf.spawn.service.spawnService
import dev.slne.surf.surfapi.core.api.messages.adventure.getPointer
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText
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
                    appendPrefix()
                    success("Der Spawn ")
                    variableValue(spawnName)
                    success(" wurde gespeichert.")
                }
            }
        }
    }

    literalArgument("list") {
        anyExecutor { executor, _ ->
            val spawns = spawnService.getSpawns()

            if(spawns.isEmpty()) {
                executor.sendText {
                    appendPrefix()
                    error("Es sind keine Spawns gespeichert.")
                }
                return@anyExecutor
            }

            executor.sendText {
                appendPrefix()
                info("Es sind ")
                variableValue(spawns.size)
                info(" Spawns gespeichert: ")

                spawns.forEachIndexed { index, spawn ->
                    append {
                        variableValue(spawn.spawnName)
                        clickEvent(ClickEvent.callback {
                            Bukkit.getPlayer(it.getPointer(Identity.UUID) ?: return@callback)?.teleportAsync(spawn.location)?.thenRun {
                                it.sendText {
                                    appendPrefix()
                                    success("Du wurdest zum Spawn ")
                                    variableValue(spawn.spawnName)
                                    success(" teleportiert.")
                                }
                            }
                        })

                        if(index < spawns.size - 1) {
                            spacer(", ")
                        }
                    }
                }
            }
        }
    }
}