package dev.slne.surf.spawn.command.argument

import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.CommandTree
import dev.jorel.commandapi.arguments.Argument
import dev.jorel.commandapi.arguments.ArgumentSuggestions
import dev.jorel.commandapi.arguments.CustomArgument
import dev.jorel.commandapi.arguments.StringArgument
import dev.slne.surf.spawn.config.SingleSpawnConfig
import dev.slne.surf.spawn.service.spawnService
import dev.slne.surf.surfapi.core.api.messages.adventure.buildText
import org.bukkit.Location

class SpawnArgument(nodeName: String) :
    CustomArgument<SingleSpawnConfig, String>(StringArgument(nodeName), { info ->
        spawnService.getSpawn(info.input) ?: throw CustomArgumentException.fromAdventureComponent(
            buildText {
                appendErrorPrefix()
                error("Der Spawn wurde nicht gefunden.")
            })
    }) {
    init {
        this.replaceSuggestions(
            ArgumentSuggestions.stringCollection {
                spawnService.getSpawns().map { it.spawnName }
            }
        )
    }
}

inline fun CommandTree.spawnArgument(
    nodeName: String,
    optional: Boolean = false,
    block: Argument<*>.() -> Unit = {}
): CommandTree = then(
    SpawnArgument(nodeName).setOptional(optional).apply(block)
)

inline fun Argument<*>.spawnArgument(
    nodeName: String,
    optional: Boolean = false,
    block: Argument<*>.() -> Unit = {}
): Argument<*> = then(
    SpawnArgument(nodeName).setOptional(optional).apply(block)
)

inline fun CommandAPICommand.spawnArgument(
    nodeName: String,
    optional: Boolean = false,
    block: Argument<*>.() -> Unit = {}
): CommandAPICommand =
    withArguments(SpawnArgument(nodeName).setOptional(optional).apply(block))