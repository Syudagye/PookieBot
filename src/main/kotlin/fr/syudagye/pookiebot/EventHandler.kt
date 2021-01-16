package fr.syudagye.pookiebot

import fr.syudagye.pookiebot.commands.Help
import fr.syudagye.pookiebot.commands.Info
import fr.syudagye.pookiebot.commands.Ping
import fr.syudagye.pookiebot.commands.Return
import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class EventHandler: ListenerAdapter() {

    companion object{
        val commands = arrayOf(
            //admin

            //staff

            //public
            Help(),
            Ping(),
            Return(),
            Info()
        )
    }

    override fun onReady(event: ReadyEvent) {
        Bot.LOGGER.info("Pookie Bot started !")
    }

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent){

        val prefix = Bot.config.getString("prefix")
        if (event.author.isBot || !event.message.contentRaw.startsWith(prefix)) return

        val args = event.message.contentRaw.split(" ")

        commands.forEach { command ->
            command.getAllCommandCalls().forEach { call ->
                if(args[0] == "$prefix$call"){
                    if (command.mandatoryArgs != null && args.size < command.mandatoryArgs.size + 1){
                        val embed = EmbedBuilder()
                        embed.setTitle(":x: Mauvaise utilisation de la commande !")
                        embed.setDescription("La syntaxe est la suivante :\n`${command.getCommandUsageAsString()}`")
                        return event.channel.sendMessage(embed.build()).queue()
                    }
                    when(command.access){
                        Access.ADMIN ->{
                            if (event.message.member?.hasPermission(Permission.ADMINISTRATOR)!!)
                                command.run(event, args)
                            else
                                event.channel.sendMessage(":x: Tu ne peut pas utiliser cette commande")
                        }
                        Access.STAFF ->{
                            if (event.message.member?.hasPermission(Permission.MESSAGE_MANAGE)!!)
                                command.run(event, args)
                            else
                                event.channel.sendMessage(":x: Tu ne peut pas utiliser cette commande")
                        }
                        Access.PUBLIC ->{
                            command.run(event, args)
                        }
                    }
                }
            }
        }
    }

    override fun onMessageReceived(event: MessageReceivedEvent) {
        if (event.message.contentStripped.toLowerCase() == "ono")
            event.channel.sendFile(javaClass.classLoader.getResourceAsStream("ono.jpg")!!, "ono.png").queue()
    }

    override fun onGuildMessageReactionAdd(event: GuildMessageReactionAddEvent) {
        if (event.member.user.isBot) return

        if (event.messageId == Bot.config.getString("rulesMessage") && event.reactionEmote.emoji == "✅"){
            event.guild.addRoleToMember(event.member, event.guild.getRoleById(Bot.config.getString("roles.membre"))!!).queue()
            event.guild.removeRoleFromMember(event.member, event.guild.getRoleById(Bot.config.getString("roles.nouveau"))!!).queue()
        }
    }

}