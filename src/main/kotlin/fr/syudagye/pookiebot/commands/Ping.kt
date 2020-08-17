package fr.syudagye.pookiebot.commands

import fr.syudagye.pookiebot.Access
import fr.syudagye.pookiebot.Bot
import fr.syudagye.pookiebot.Command
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.awt.Color

class Ping(bot: Bot): Command(bot.jda, Access.PUBLIC, "ping", arrayOf("p"), null, null) {

    override val description: String = "Ping le bot"

    override fun run(event: GuildMessageReceivedEvent, args: List<String>) {
        val embed = EmbedBuilder()
        embed.setTitle(":ping_pong: Pong")
        embed.addField("Ping du bot", "*marche po :c*", false)
        embed.addField("Ping de l'API de Discord", "${event.jda.gatewayPing}ms", false)
        embed.setColor(Color.ORANGE)

        event.channel.sendMessage(embed.build()).queue()
    }

}