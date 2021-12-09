package roblar.adventofcode.year2021.day05

import roblar.adventofcode.FileUtils
import java.util.TreeMap

fun main() {
    val reader = FileUtils.getBufferedReaderFromResource("/2021_05_input.txt")

    val lines = mutableListOf<Line>()

    while (reader.ready()) {
        val rawLine = reader.readLine()
        val points = rawLine.split(" -> ").map {
            val coordinates = it.split(",").toList()
            Point(coordinates[0].toInt(), coordinates[1].toInt())
        }

        lines.add(Line(points[0], points[1]))
    }

    val heatMap = TreeMap<Point, Int>()
    lines.filter { line ->
        line.isHorizontal || line.isVertical || line.isDiagonal
    }.map {
        line -> line.getAllPointsInLine()
    }.flatten().forEach { point ->
        val hits = heatMap[point] ?: 0
        heatMap[point] = hits + 1
    }

    val part2Answer = heatMap.values.count { it > 1 }
    println(part2Answer)
}