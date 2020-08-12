package fr.syudagye.pookiebot

import fr.syudagye.pookiebot.commands.Help

class CommandsRegistry(bot: Bot) {
    val commands: Array<Command> = arrayOf(
        Help(bot)
    )
}