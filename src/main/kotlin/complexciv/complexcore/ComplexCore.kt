package complexciv.complexcore

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
import java.util.Collections

@Plugin(id = PomData.ARTIFACT_ID, name = PomData.NAME, version = PomData.VERSION)
class SpongeCassandra {

    @Inject
    private val logger: Logger? = null

    @Inject
    @DefaultConfig(sharedRoot = true)
    private val defaultConfig: File? = null

    @Inject
    @DefaultConfig(sharedRoot = true)
    private val configLoader: ConfigurationLoader<CommentedConfigurationNode>? = null

    private var config: ConfigurationNode? = null

    @Inject
    private val game: Game? = null

    @Listener
    fun onPreInit(event: GamePreInitializationEvent) {

    }

    @Listener
    fun disable(event: GameStoppingServerEvent) {

    }

    private fun initConfig() {
        try {
            config = configLoader!!.load()

            if (!defaultConfig!!.exists()) {
//                config!!.getNode("cassandra", "contact-points").setValue(listOf<T>("localhost"))
//                config!!.getNode("cassandra", "port").setValue(9042)
                configLoader!!.save(config)
            }
        } catch (e: IOException) {
            logger!!.warn("Main config could not be loaded/created/changed!")
        }

    }
}
