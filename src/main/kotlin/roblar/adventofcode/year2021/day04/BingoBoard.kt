package roblar.adventofcode.year2021.day04

class BingoBoard(private val unparsedRows: List<String>) {
    val cellsXYMatrix: Array<Array<BingoCell?>>

    init {
        val numberOfRows = unparsedRows.size
        val numberOfColumns = if (numberOfRows > 0) unparsedRows[0].length else 0
        cellsXYMatrix = Array(numberOfColumns) { arrayOfNulls(numberOfRows) }

        unparsedRows.forEachIndexed { rowIndex, row ->
            row.split(" ").filter { it.isNotBlank() }.forEachIndexed { columnIndex, value ->
                val cell = BingoCell(columnIndex, rowIndex, value.toInt())
                cellsXYMatrix[columnIndex][rowIndex] = cell
            }
        }
    }
}