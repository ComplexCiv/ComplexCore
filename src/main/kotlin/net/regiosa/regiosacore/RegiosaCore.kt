package net.regiosa.regiosacore

import com.google.inject.Inject
import ninja.leaping.configurate.ConfigurationNode
import ninja.leaping.configurate.commented.CommentedConfigurationNode
import ninja.leaping.configurate.loader.ConfigurationLoader
import org.slf4j.Logger
import org.spongepowered.api.Game
import org.spongepowered.api.config.DefaultConfig
import org.spongepowered.api.event.Listener
import org.spongepowered.api.event.game.state.GamePreInitializationEvent
import org.spongepowered.api.event.game.state.GameStoppingServerEvent
import org.spongepowered.api.plugin.Plugin
import java.io.File
import java.io.IOException

@Plugin(id = PomData.ARTIFACT_ID, name = PomData.NAME, version = PomData.VERSION)
class SpongeCassandra {

    @Inject
    lateinit private var logger: Logger

    @Inject
    @DefaultConfig(sharedRoot = true)
    lateinit private var defaultConfig: File

    @Inject
    @DefaultConfig(sharedRoot = true)
    lateinit private var configLoader: ConfigurationLoader<CommentedConfigurationNode>

    lateinit private var config: ConfigurationNode

    @Inject
    lateinit private var game: Game

    @Listener
    fun onPreInit(event: GamePreInitializationEvent) {
        initConfig()
    }

    @Listener
    fun disable(event: GameStoppingServerEvent) {

    }

    private fun initConfig() {
        try {
            config = configLoader.load()

            if (!defaultConfig.exists()) {
                //TODO: Config Options if we need them I guess
                configLoader.save(config)
            }
        } catch (e: IOException) {
            logger.warn("Main config could not be loaded/created/changed!")
        }

    }
}
