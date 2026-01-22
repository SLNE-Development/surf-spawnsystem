package dev.slne.surf.spawn.permission

import dev.slne.surf.surfapi.bukkit.api.permission.PermissionRegistry

object PermissionRegistry : PermissionRegistry() {
    const val BASE = "surf.spawn"
    const val BASE_COMMAND = "$BASE.command"

    val COMMAND_SPAWN = create("$BASE_COMMAND.spawn")
    val COMMAND_SPAWN_SELECT = create("$BASE_COMMAND.spawn.select")
    val COMMAND_SURF_SPAWN = create("$BASE_COMMAND.surfspawn")
}