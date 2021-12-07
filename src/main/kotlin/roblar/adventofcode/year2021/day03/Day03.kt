package roblar.adventofcode.year2021.day03

import roblar.adventofcode.FileUtils

fun main() {
    val reader = FileUtils.getBufferedReaderFromResource("/2021_03_input.txt")
    val sampleLength = 12
    val sampleZeroes = IntArray(sampleLength)
    val sampleOnes = IntArray(sampleLength)
    val majoritySamples = mutableListOf<String>()
    val minoritySamples = mutableListOf<String>()

    while (reader.ready()) {
        val sample = reader.readLine()
        sample.forEachIndexed { index: Int, character: Char ->
            when(character) {
                '0' -> sampleZeroes[index]++
                '1' -> sampleOnes[index]++
            }
        }
        majoritySamples.add(sample)
        minoritySamples.add(sample)
    }

    val gammaRate = IntArray(sampleLength)
    val epsilonRate = IntArray(sampleLength)

    for (i in 0 until sampleLength) {
        gammaRate[i] = if (sampleZeroes[i] > sampleOnes[i]) 0 else 1
        epsilonRate[i] = if (sampleZeroes[i] > sampleOnes[i]) 1 else 0

        if (majoritySamples.size > 1) {
            var zeroes = 0
            var ones = 0
            for (sample in majoritySamples) {
                when (sample[i]) {
                    '0' -> zeroes++
                    '1' -> ones++
                }
            }

            val keepZeroes = zeroes > ones
            majoritySamples.removeAll { if (keepZeroes) it[i] != '0' else it[i] != '1' }
        }

        if (minoritySamples.size > 1) {
            var zeroes = 0
            var ones = 0
            for (sample in minoritySamples) {
                when (sample[i]) {
                    '0' -> zeroes++
                    '1' -> ones++
                }
            }

            val keepZeroes = zeroes <= ones
            minoritySamples.removeAll { if (keepZeroes) it[i] != '0' else it[i] != '1' }
        }
    }
    val part1Result = parseReading(gammaRate).times(
        parseReading(epsilonRate)
    )
    val part2Result = majoritySamples[0].toInt(2).times(minoritySamples[0].toInt(2))

    println("Part 1 result: $part1Result")
    println("Part 2 result: $part2Result")
}

fun parseReading(reading: IntArray): Int = reading.contentToString().filter{ it == '0' || it == '1' }.toInt(2)