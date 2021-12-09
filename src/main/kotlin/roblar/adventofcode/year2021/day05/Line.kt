package roblar.adventofcode.year2021.day05

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Line(val source: Point, val destination: Point) {
    val isHorizontal: Boolean = source.y == destination.y
    val isVertical: Boolean = source.x == destination.x
    val isDiagonal: Boolean = abs(source.x - destination.x) == abs(source.y - destination.y)

    fun getAllPointsInLine(): List<Point> {
        val points = mutableListOf<Point>()

        if (isHorizontal) {
            for (i in min(source.x, destination.x) .. max(source.x, destination.x)) {
                points.add(Point(i, source.y))
            }
        } else if (isVertical) {
            for (i in min(source.y, destination.y) .. max(source.y, destination.y)) {
                points.add(
                    Point(source.x, i)
                )
            }
        } else if (isDiagonal) {
            // up right
            if (source.y < destination.y && source.x < destination.x) {
                for (i in 0 .. abs(destination.x - source.x)) {
                    points.add(Point(source.x + i, source.y + i))
                }
            }

            // down right
            else if (source.y > destination.y  && source.x < destination.x) {
                for (i in 0 .. abs(destination.x - source.x)) {
                    points.add(Point(source.x + i, source.y - i))
                }
            }

            // down left
            else if (source.y > destination.y && source.x > destination.x) {
                for (i in 0 .. abs(destination.x - source.x)) {
                    points.add(Point(source.x - i, source.y - i))
                }
            }

            // up left
            else if (source.y < destination.y && source.x > destination.x) {
                for (i in 0 .. abs(destination.x - source.x)) {
                    points.add(Point(source.x - i, source.y + i))
                }
            }
        }

        return points
    }
}