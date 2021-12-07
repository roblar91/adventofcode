package roblar.adventofcode.year2021.day04

import roblar.adventofcode.FileUtils

fun main() {
    val reader = FileUtils.getBufferedReaderFromResource("/2021_04_input.txt")
    val draws = reader.readLine().split(",").map { it.toInt() }
    val boards = mutableListOf<BingoBoard>()

    val boardRows = mutableListOf<String>()
    while (reader.ready()) {
        val line = reader.readLine()

        if (line.isEmpty() && boardRows.isNotEmpty()) {
            boards.add(BingoBoard(boardRows))
            boardRows.clear()
        }

        if (line.isNotEmpty()) {
            boardRows.add(line)
        }
    }

    println()
}