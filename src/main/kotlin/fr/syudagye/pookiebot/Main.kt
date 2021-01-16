package fr.syudagye.pookiebot

import org.slf4j.Logger
import org.slf4j.LoggerFactory

val LOGGER: Logger = LoggerFactory.getLogger("INIT")

fun main(args: Array<String>) {
    try {
        Bot()
    }catch (e: Exception){
        LOGGER.error("${e.printStackTrace()}")
    }
}