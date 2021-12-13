fun main(args: Array<String>) {
    println("Advent of code - day 6!")
    println("======================")
    println("Puzzle 1!")

    val input = readInput(6)

    val fishes = input.map { it.split(",") }.flatten().map { Lanternfish(it.toInt()) }
    val result = simulate(fishes, 80)
    println("Result: ${result.size}")

    println("======================")

    println("Puzzle 2!")
    val result2 = simulate2(fishes, 256)
    println("Result: $result2")
}

fun simulate(fishes: List<Lanternfish>, days: Int): List<Lanternfish> {
    var colony = fishes
    for (i in 1..days) {
        colony = colony.map {
            it.passDay()
        }
            .flatten()
    }
    return colony
}

fun simulate2(fishes: List<Lanternfish>, days: Int): Long {
    val fishPerTimer = LongArray(10) { 0 }
    fishes.forEach { fishPerTimer[it.timer] = fishPerTimer[it.timer] + 1 }
    for (day in 0 until days) {
        fishPerTimer[7] += fishPerTimer[0]
        fishPerTimer[9] = fishPerTimer[0]
        for (i in 0..8) {
            fishPerTimer[i] = fishPerTimer[i + 1]
        }
        fishPerTimer[9] = 0
    }
    return fishPerTimer.sum()
}

data class Lanternfish(val timer: Int = 6) {

    fun passDay(): List<Lanternfish> {
        return when (timer) {
            0 -> listOf(Lanternfish(6), Lanternfish(8))
            in 1..Int.MAX_VALUE -> listOf(Lanternfish(timer-1))
            else -> throw IllegalStateException("Wrong fish timer")
        }
    }

}