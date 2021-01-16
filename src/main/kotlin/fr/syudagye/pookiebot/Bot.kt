package fr.syudagye.pookiebot

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import net.dv8tion.jda.api.JDABuilder
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Bot {

    companion object{
        val LOGGER: Logger = LoggerFactory.getLogger("[Bot]")
        val config: Config = ConfigFactory.load()
    }

    init {
        JDABuilder
            .createDefault(config.getString("token"))
            .addEventListeners(EventHandler())
            .build()
    }
}