package dev.slne.surf.spawn.command

import dev.jorel.commandapi.kotlindsl.commandTree
import dev.slne.surf.spawn.permission.PermissionRegistry

fun spawnCommand() = commandTree("spawn") {
    withPermission(PermissionRegistry.COMMAND_SPAWN)
}