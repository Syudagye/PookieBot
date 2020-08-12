package fr.syudagye.pookiebot.commands

import fr.syudagye.pookiebot.Access
import fr.syudagye.pookiebot.Bot
import fr.syudagye.pookiebot.Command
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class Help(bot: Bot): Command(bot.jda, Access.PUBLIC, "help", null,  null, null) {

    override val description = "Menu d'aide"

    override fun run(event: GuildMessageReceivedEvent, args: List<String>) {
        event.channel.sendMessage("*ne fonctionne pas pour le moment*").queue()
    }
}