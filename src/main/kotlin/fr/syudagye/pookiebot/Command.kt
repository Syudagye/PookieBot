package fr.syudagye.pookiebot

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

abstract class Command(open val jda: JDA, val access: Access, val name: String, val aliases: Array<String>?, val mandatoryArgs: Array<String>?, val optionalArgs: Array<String>?){

    abstract val description: String

    abstract fun run(event: GuildMessageReceivedEvent, args: List<String>)

    fun getCommandUsageAsString(): String{
        val str = StringBuilder()
        mandatoryArgs?.forEach { s ->
            str.append("<${s}> ")
        }
        optionalArgs?.forEach { s ->
            str.append("[${s}] ")
        }
        return "`c!${name} ${str}`"
    }

    fun getCommandAliasesAsString(): String{
        val str = StringBuilder()
        if (aliases != null){
            aliases.forEach { s ->
                str.append("`${s}` ")
            }
        }else
            str.append("`Aucun alias`")
        return str.toString()
    }

    fun getAllCommandCalls(): ArrayList<String>{
        if (aliases != null){
            val array = arrayListOf(name)
            aliases.forEach { s -> array.add(s) }
            return array
        }else
            return arrayListOf(name)
    }

}