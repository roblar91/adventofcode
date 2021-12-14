package roblar.adventofcode.year2021.day09

import roblar.adventofcode.FileUtils

fun main() {
    val reader = FileUtils.getBufferedReaderFromResource("/2021_09_input.txt")
    val heightMap = mutableListOf<List<Int>>()
    val basinSizes = mutableListOf<Int>()

    while (reader.ready()) {
        val line = reader.readLine()
        heightMap.add(line.map { it.digitToInt() })
    }

    heightMap.forEachIndexed { rowIndex, row ->
        row.forEachIndexed { columnIndex, value ->
            val above = if (rowIndex - 1 >= 0) heightMap[rowIndex - 1][columnIndex] else null
            val below = if (rowIndex + 1 < heightMap.size) heightMap[rowIndex + 1][columnIndex] else null
            val left = if (columnIndex - 1 >= 0) heightMap[rowIndex][columnIndex - 1] else null
            val right = if (columnIndex + 1 < row.size) heightMap[rowIndex][columnIndex + 1] else null

            val isLowPoint = (above == null || above > value)
                    && (below == null || below > value)
                    && (left == null || left > value)
                    && (right == null || right > value)

            if (isLowPoint) {
                val visitedNodes = hashMapOf<Pair<Int, Int>, Boolean>()
                basinSizes.add(calculateBasinSize(rowIndex, columnIndex, heightMap, visitedNodes))
            }
        }
    }

    var result = 1
    basinSizes.sort()
    basinSizes.takeLast(3).forEach { result = result.times(it) }

    println(result)
}

fun calculateBasinSize(rowIndex: Int, columnIndex: Int, heightMap: MutableList<List<Int>>, visitedNodes: HashMap<Pair<Int, Int>, Boolean>): Int {
    val nodeKey = Pair(rowIndex, columnIndex)
    if (visitedNodes[nodeKey] != null
        || rowIndex !in 0 until heightMap.size
        || columnIndex !in 0 until heightMap[0].size
        || heightMap[rowIndex][columnIndex] == 9) {
        return 0
    }

    visitedNodes[nodeKey] = true
    return 1 +
            calculateBasinSize(rowIndex, columnIndex + 1, heightMap, visitedNodes) +
            calculateBasinSize(rowIndex, columnIndex - 1, heightMap, visitedNodes) +
            calculateBasinSize(rowIndex + 1, columnIndex, heightMap, visitedNodes) +
            calculateBasinSize(rowIndex - 1, columnIndex, heightMap, visitedNodes)
}