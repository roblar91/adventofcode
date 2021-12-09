package roblar.adventofcode.year2021.day05

class Point(val x: Int, val y: Int): Comparable<Point> {
    override fun compareTo(other: Point): Int {
        val xComparison = x.compareTo(other.x)
        if (xComparison != 0) return xComparison
        return y.compareTo(other.y)
    }
}