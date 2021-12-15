import kotlin.math.abs

fun main(args: Array<String>) {
    println("Advent of code - day 7!")
    println("======================")
    println("Puzzle 1!")

    val input = readInput(7)
    val crabs = input
        .map { it.split(",") }
        .flatten()
        .map { Crab(it.toInt()) }
    val median = crabs.map { it.position }.sorted().median()
    val fuel = crabs.map { it.position }
        .fold(0) { sum, element -> sum + abs(element - median) }
    println("Result: $fuel")

    println("======================")

    println("Puzzle 2!")

    val maxPos: Int = crabs.map { it.position }.maxOrNull() ?: 0
    var minSum = Int.MAX_VALUE
    for (position in 0 until maxPos) {
        val total = crabs.sumOf { it.costToFuel(position) }
        if (total < minSum) {
            minSum = total
        }
    }

    println("Result: $minSum")
}

class Crab(val position: Int) {

    fun costToFuel(goToPosition: Int): Int {
        val moves = abs(goToPosition - position)
        return moves * (moves + 1) / 2
    }
}

fun List<Int>.median(): Int =
    if (size % 2 == 0) {
        (this[size / 2] + this[(size - 1) / 2]) / 2
    } else {
        this[size / 2]
    }