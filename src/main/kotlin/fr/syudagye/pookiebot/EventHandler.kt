package fr.syudagye.pookiebot

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

class EventHandler(val bot: Bot): ListenerAdapter() {

    override fun onReady(event: ReadyEvent) {
        println("Pookie Bot is now ONLINE !")
    }

    override fun onGuildMessageReceived(event: GuildMessageReceivedEvent){

        if (event.author.isBot || !event.message.contentRaw.startsWith(bot.prefix)) return

        val args = event.message.contentRaw.split(" ")

        bot.commandsRegistry.commands.forEach { command ->
            command.getAllCommandCalls().forEach { call ->
                if(args[0] == "${bot.prefix}${call}"){
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

        if (event.messageId == bot.configs.rulesMessageId && event.reactionEmote.emoji == "✅"){
            event.guild.addRoleToMember(event.member, event.guild.getRoleById(bot.configs.roles.getString("membre"))!!).queue()
            event.guild.removeRoleFromMember(event.member, event.guild.getRoleById(bot.configs.roles.getString("nouveau"))!!).queue()
        }
    }

}