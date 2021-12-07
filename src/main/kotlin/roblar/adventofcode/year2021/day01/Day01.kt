package roblar.adventofcode.year2021.day01

import roblar.adventofcode.FileUtils
import java.util.LinkedList

fun main() {
    val reader = FileUtils.getBufferedReaderFromResource("/2021_01_input.txt")
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