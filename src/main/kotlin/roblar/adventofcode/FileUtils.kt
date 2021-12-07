package roblar.adventofcode

import java.io.BufferedReader
import kotlin.system.exitProcess

object FileUtils {
    fun getBufferedReaderFromResource(resourceUrl: String): BufferedReader {
        val inputStream = {}.javaClass.getResourceAsStream(resourceUrl)
        if (inputStream == null) {
            println("Could not read file $resourceUrl, exiting program")
            exitProcess(-1)
        }

        return inputStream.bufferedReader()
    }
}