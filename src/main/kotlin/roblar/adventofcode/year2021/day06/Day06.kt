package roblar.adventofcode.year2021.day06

import roblar.adventofcode.FileUtils

fun main() {
    val reader = FileUtils.getBufferedReaderFromResource("/2021_06_input.txt")
    val initialLanternfishSpawnCooldowns = reader.readLine().split(",").map { it.toInt() }
    val days = 256
    val spawnGroups = mutableListOf<LanternfishSpawnGroup>()
    val newSpawnGroups = mutableListOf(LanternfishSpawnGroup(0, 7), LanternfishSpawnGroup(0, 8))

    for (i in 0..6) {
        spawnGroups.add(
            LanternfishSpawnGroup(0, i)
        )
    }

    initialLanternfishSpawnCooldowns.forEach { fishCooldown ->
        val group = spawnGroups.find { group -> group.cooldown == fishCooldown }
        if (group != null) group.size++
    }

    for (i in 0 until days) {
        var newSpawns = 0L

        spawnGroups.forEach { group ->
            if (group.cooldown == 0) {
                newSpawns = group.size
                group.cooldown = 6
            } else {
                group.cooldown--
            }
        }

        newSpawnGroups.forEach { group ->
            if (group.cooldown == 7) {
                val regularGroup = spawnGroups.find { it.cooldown == 6 }
                if (regularGroup != null) regularGroup.size += group.size
                group.size = 0
                group.cooldown = 8
            } else {
                group.cooldown--
            }
        }

        newSpawnGroups.find { it.cooldown == 8 }?.size = newSpawns
    }

    println(spawnGroups.sumOf { it.size } + newSpawnGroups.sumOf { it.size })
}