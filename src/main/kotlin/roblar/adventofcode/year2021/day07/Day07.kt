package roblar.adventofcode.year2021.day07

import roblar.adventofcode.FileUtils
import java.util.TreeMap
import kotlin.math.abs

fun main() {
    val reader = FileUtils.getBufferedReaderFromResource("/2021_07_input.txt")
    val input = reader.readLine().split(",").map { it.toInt() }
    val positionsAndSizes = TreeMap<Int, Int>()

    input.forEach { crabPosition ->
        val oldSize = positionsAndSizes[crabPosition] ?: 0
        positionsAndSizes[crabPosition] = oldSize + 1
    }

    // Brute force!!!
    val minPosition = positionsAndSizes.firstKey()
    val maxPosition = positionsAndSizes.lastKey()

    var leastFuelUsed: Long? = null
    for (i in minPosition .. maxPosition) {
        var fuelUsed = 0L

        positionsAndSizes.forEach { (position, size) ->
            val absoluteDistance = abs(position - i)
            val adjustedDistance = 0.rangeTo(absoluteDistance).sum()
            fuelUsed += adjustedDistance.times(size)
        }

        if (leastFuelUsed == null || leastFuelUsed > fuelUsed) leastFuelUsed = fuelUsed
    }

    println(leastFuelUsed)
}