package fr.syudagye.pookiebot.commands

import fr.syudagye.pookiebot.Access
import fr.syudagye.pookiebot.Bot
import fr.syudagye.pookiebot.Command
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

class Return(bot: Bot): Command(bot.jda, Access.PUBLIC, "return", null, null, null) {

    override val description: String = ":repeat:"

    override fun run(event: GuildMessageReceivedEvent, args: List<String>) {
        event.channel.sendFile(javaClass.classLoader.getResourceAsStream("return.jpg")!!, "return.png").queue()
    }
}