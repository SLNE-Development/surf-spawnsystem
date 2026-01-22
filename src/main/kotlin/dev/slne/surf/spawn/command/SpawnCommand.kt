package dev.slne.surf.spawn.command

import dev.jorel.commandapi.kotlindsl.commandTree
import dev.jorel.commandapi.kotlindsl.getValue
import dev.jorel.commandapi.kotlindsl.playerExecutor
import dev.slne.surf.spawn.command.argument.spawnArgument
import dev.slne.surf.spawn.config.SingleSpawnConfig
import dev.slne.surf.spawn.permission.PermissionRegistry
import dev.slne.surf.spawn.service.spawnService
import dev.slne.surf.surfapi.core.api.messages.adventure.sendText

fun spawnCommand() = commandTree("spawn") {
    withPermission(PermissionRegistry.COMMAND_SPAWN)

    playerExecutor { player, _ ->
        player.teleportAsync(spawnService.getNearestSpawnLocation(player.location)).thenRun {
            player.sendText {
                appendSuccessPrefix()
                success("Du wurdest zum nächsten Spawn teleportiert.")
            }
        }
    }

    spawnArgument("spawn") {
        withPermission(PermissionRegistry.COMMAND_SPAWN_SELECT)
        playerExecutor { player, args ->
            val spawn: SingleSpawnConfig by args

            player.teleportAsync(spawn.location).thenRun {
                player.sendText {
                    appendSuccessPrefix()
                    success("Du wurdest zum Spawn ")
                    variableValue(spawn.spawnName)
                    success(" teleportiert.")
                }
            }
        }
    }
}