package roblar.adventofcode.year2021

import java.util.LinkedList
import kotlin.system.exitProcess

fun main() {
    val reader = {}.javaClass.getResourceAsStream("/2021_01_input.txt")?.bufferedReader()
    if (reader == null) {
        println("Could not read file")
        exitProcess(-1)
    }

    val slidingWindowSize = 3
    val measurementQueue = LinkedList<Int>()
    var depthIncreaseCount = 0
    var previousMeasurement : Int? = null

    while(reader.ready()) {
        measurementQueue.addLast(reader.readLine().toInt())

        if (measurementQueue.size > slidingWindowSize) measurementQueue.removeFirst()
        if (measurementQueue.size == slidingWindowSize) {
            val sum = measurementQueue.sum()
            if (previousMeasurement != null && sum > previousMeasurement) depthIncreaseCount++
            previousMeasurement = sum
        }
    }

    println(depthIncreaseCount)
}