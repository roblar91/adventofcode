package roblar.adventofcode.year2021.day08

import roblar.adventofcode.FileUtils

fun main() {
    val reader = FileUtils.getBufferedReaderFromResource("/2021_08_input.txt")
    val outputNumbers = mutableListOf<Int>()

    while (reader.ready()) {
        val line = reader.readLine()
        val definitionSignals = line.split(" | ")[0].split(" ")
        val outputSignals = line.split(" | ")[1].split(" ")
        val digitDefinitions = mutableMapOf<Int, String?>()

        digitDefinitions[1] = definitionSignals.find { it.length == 2 }
        digitDefinitions[4] = definitionSignals.find { it.length == 4 }
        digitDefinitions[7] = definitionSignals.find { it.length == 3 }
        digitDefinitions[8] = definitionSignals.find { it.length == 7 }
        digitDefinitions[6] = definitionSignals.find { it.length == 6 && it.containsExactNumberOf(digitDefinitions[1]!!, 1) }
        digitDefinitions[9] = definitionSignals.find { it.length == 6 && it.containsExactNumberOf(digitDefinitions[4]!!, 4) }
        digitDefinitions[0] = definitionSignals.find { it.length == 6 && it.containsExactNumberOf(digitDefinitions[1]!!, 2) && it.containsExactNumberOf(digitDefinitions[4]!!, 3) }
        digitDefinitions[2] = definitionSignals.find { it.length == 5 && it.containsExactNumberOf(digitDefinitions[4]!!, 2) }
        digitDefinitions[3] = definitionSignals.find { it.length == 5 && it.containsExactNumberOf(digitDefinitions[1]!!, 2) }
        digitDefinitions[5] = definitionSignals.find { it.length == 5 && it.containsExactNumberOf(digitDefinitions[6]!!, 5) }

        val outputNumber = StringBuilder()
        outputSignals.forEach { signal ->
            digitDefinitions.forEach { (digit, signalDef) ->
                if (signalDef!!.length == signal.length && signalDef.containsExactNumberOf(signal, signal.length)) {
                    outputNumber.append(digit)
                }
            }
        }
        outputNumbers.add(outputNumber.toString().toInt())
    }

    val part2Answer = outputNumbers.sum()
    println("Part 2 answer: $part2Answer")
}

fun String.containsExactNumberOf(other: String, num: Int): Boolean {
    var hits = 0
    other.forEach { c ->
        if (this.contains(c)) hits++
    }

    return hits == num
}