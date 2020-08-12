package fr.syudagye.pookiebot

import net.dv8tion.jda.api.JDABuilder


class Bot(token: String) {

    lateinit var prefix: String

    val jda = JDABuilder.createDefault(token).build()
    val commandsRegistry = CommandsRegistry(this)
    val configs = Configs(this)

    val eventListener = EventHandler(this)

    init {
        jda.addEventListener(eventListener)
    }
}