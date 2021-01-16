package fr.syudagye.pookiebot.commands

import fr.syudagye.pookiebot.Access
import fr.syudagye.pookiebot.Bot
import fr.syudagye.pookiebot.Command
import fr.syudagye.pookiebot.EventHandler
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.awt.Color

class Help: Command(Access.PUBLIC, "help", arrayOf("h"),  null, null) {

    override val description = "Menu d'aide"

    override fun run(event: GuildMessageReceivedEvent, args: List<String>) {
        val publicCommands: ArrayList<Command> = java.util.ArrayList()
        val staffCommands: ArrayList<Command> = java.util.ArrayList()
        val adminCommands: ArrayList<Command> = java.util.ArrayList()
        EventHandler.commands.forEach { command ->
            when (command.access){
                Access.ADMIN -> adminCommands.add(command)
                Access.STAFF -> staffCommands.add(command)
                Access.PUBLIC -> publicCommands.add(command)
            }
        }
        val embed = EmbedBuilder()
        embed.setTitle(":information_source: Voici la liste des commandes pour le Pookie Serv")
        embed.setDescription("Avant chaque commande, il faut utiliser le prefix `${Bot.config.getString("prefix")}`")
        embed.setColor(Color.RED)
        val stringBuilder = StringBuilder()

        //commandes publiques
        publicCommands.forEach { command -> stringBuilder.append("`${command.name}` - ${command.description}\n") }
        if (stringBuilder.isEmpty())
            stringBuilder.append("*Aucune commande*")
        embed.addField("Commandes publiques", stringBuilder.toString(), false)
        stringBuilder.clear()

        //commandes staff
        staffCommands.forEach { command -> stringBuilder.append("`${command.name}` - ${command.description}\n") }
        if (stringBuilder.isEmpty())
            stringBuilder.append("*Aucune commande*")
        embed.addField("Commandes staff", stringBuilder.toString(), false)
        stringBuilder.clear()

        //commandes admins
        adminCommands.forEach { command -> stringBuilder.append("`${command.name}` - ${command.description}\n") }
        if (stringBuilder.isEmpty())
            stringBuilder.append("*Aucune commande*")
        embed.addField("Commandes admins", stringBuilder.toString(), false)

        event.channel.sendMessage(embed.build()).queue()
    }
}