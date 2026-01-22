package dev.slne.surf.spawn.command

import dev.jorel.commandapi.kotlindsl.commandTree
import dev.slne.surf.spawn.permission.PermissionRegistry

fun surfSpawnCommand() = commandTree("surfSpawn") {
    withPermission(PermissionRegistry.COMMAND_SURF_SPAWN)
}