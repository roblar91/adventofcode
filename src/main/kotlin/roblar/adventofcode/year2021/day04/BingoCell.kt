package roblar.adventofcode.year2021.day04

class BingoCell(val positionX: Int, val positionY: Int, val number: Int, var hit: Boolean = false): Comparable<BingoCell> {
    override fun compareTo(other: BingoCell): Int {
        return number.compareTo(other.number)
    }
}