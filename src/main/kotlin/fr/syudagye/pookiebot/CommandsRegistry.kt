package fr.syudagye.pookiebot

import fr.syudagye.pookiebot.commands.Help
import fr.syudagye.pookiebot.commands.Info
import fr.syudagye.pookiebot.commands.Ping
import fr.syudagye.pookiebot.commands.Return

class CommandsRegistry(bot: Bot) {
    val commands: Array<Command> = arrayOf(
        //admin

        //staff

        //public
        Help(bot),
        Ping(bot),
        Return(bot),
        Info(bot)
    )
}