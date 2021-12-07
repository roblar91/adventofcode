package roblar.adventofcode.year2021.day02

import roblar.adventofcode.FileUtils

fun main() {
    val reader = FileUtils.getBufferedReaderFromResource("/2021_02_input.txt")
    val commands = ArrayList<Command>()

    while(reader.ready()) {
        val symbols = reader.readLine().split(" ")
        val direction = Direction.valueOf(symbols[0].uppercase())
        val value = symbols[1].toInt()
        commands.add(Command(direction, value))
    }

    var depth = 0
    var horizontalPosition = 0
    var aim = 0
    for (command in commands) {
        when(command.direction) {
            Direction.UP -> aim -= command.value
            Direction.DOWN -> aim += command.value
            Direction.FORWARD -> {
                horizontalPosition += command.value
                depth += aim * command.value
            }
        }
    }

    println("Depth: $depth")
    println("Horizontal position: $horizontalPosition")
    println("Aim: $aim")
    println("Depth x Horizontal position: ${depth * horizontalPosition}")
}

private class Command(val direction: Direction, val value: Int)

private enum class Direction {
    UP,
    DOWN,
    FORWARD
}