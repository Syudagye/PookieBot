package fr.syudagye.pookiebot.commands

import fr.syudagye.pookiebot.Access
import fr.syudagye.pookiebot.Bot
import fr.syudagye.pookiebot.Command
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.awt.Color

class Info(val bot: Bot): Command(bot.jda, Access.PUBLIC, "info", arrayOf("i"), arrayOf("command"), null) {

    override val description: String = "Donne des information sur une commande"

    override fun run(event: GuildMessageReceivedEvent, args: List<String>) {
        var command: Command? = null
        bot.commandsRegistry.commands.forEach { cmd ->
            if (cmd.name == args[1])
                command = cmd
        }
        if (command == null)
            event.channel.sendMessage(":x: Cette commande n'existe pas")
        else{
            val embed = EmbedBuilder()
            embed.setTitle(":information_source: Info commande")
            embed.setDescription("`${command!!.getCommandUsageAsString()}`")
            embed.addField("Description", command!!.description, false)
            when (command!!.access){
                Access.PUBLIC -> embed.addField("Accès", "Publique", true)
                Access.STAFF -> embed.addField("Accès", "Staff", true)
                Access.ADMIN -> embed.addField("Accès", "Admins", true)
            }
            embed.addField("Alias", command!!.getCommandAliasesAsString(), true)
            embed.setColor(Color.CYAN)

            event.channel.sendMessage(embed.build()).queue()
        }
    }

}