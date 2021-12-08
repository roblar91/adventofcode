package roblar.adventofcode.year2021.day04

import java.util.SortedSet

class BingoBoard(unparsedRows: List<String>) {
    private val cellsSortedSet: SortedSet<BingoCell>
    private val cellsRowsColumnsMatrix: MutableList<MutableList<BingoCell>>
    var win = false
    var scoreMultiplier = 0

    init {
        val numberOfRows = unparsedRows.size
        cellsRowsColumnsMatrix = MutableList(numberOfRows) { mutableListOf() }

        unparsedRows.forEachIndexed { rowIndex, row ->
            row.split(" ").filter { it.isNotBlank() }.forEachIndexed { columnIndex, value ->
                val cell = BingoCell(columnIndex, rowIndex, value.toInt())
                cellsRowsColumnsMatrix[rowIndex].add(columnIndex, cell)
            }
        }

        cellsSortedSet = cellsRowsColumnsMatrix.flatten().toSortedSet { cell1: BingoCell, cell2: BingoCell -> cell1.compareTo(cell2) }
    }

    fun drawAndCheckBingo(number: Int): Boolean {
        val cell = cellsSortedSet.find{ number == it?.number } ?: return false
        cell.hit = true

        val rowToCheck = cell.positionY
        var bingo = true
        cellsRowsColumnsMatrix[rowToCheck].forEach { if (!it.hit) bingo = false }

        if (!bingo) {
            val columnToCheck = cell.positionX
            bingo = true
            cellsRowsColumnsMatrix.forEach { if (!it[columnToCheck].hit) bingo = false }
        }

        if (bingo) {
            win = true
            scoreMultiplier = number
        }
        return bingo
    }

    fun getScore(): Int {
        val sumOfNotHitCells = cellsSortedSet
            .filter { !it.hit }
            .sumOf { it.number }

        return sumOfNotHitCells.times(scoreMultiplier)
    }
}