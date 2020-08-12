package fr.syudagye.pookiebot

import org.json.JSONObject

class Configs(bot: Bot) {

    val rulesMessageId: String
    val guildId: String
    val channels: JSONObject
    val roles: JSONObject

    init {
        val file = javaClass.classLoader.getResourceAsStream("config.json")
        val stringBuilder = StringBuilder()
        var i: Int
        while (file!!.read().also { i = it } != -1) {
            stringBuilder.append(i.toChar())
        }
        file.close()
        val jsonObject = JSONObject(stringBuilder.toString())
        bot.prefix = jsonObject.getString("prefix")
        rulesMessageId = jsonObject.getString("rules_message")
        guildId = jsonObject.getString("guild_id")
        channels = jsonObject.getJSONObject("channels")
        roles = jsonObject.getJSONObject("roles")
    }
}