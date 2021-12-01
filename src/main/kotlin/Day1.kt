import java.io.File

fun main(args: Array<String>) {
    println("Advent of code!")
    println("Day 1 - puzzle 1!")
    val lines = readInputAsListOfInt(1)
    val increased = lines.asSequence()
        .zipWithNext()
        .count { it.first < it.second }
    println("Result: $increased")


    println("Day 1 - puzzle 2!")
    val sumOfIncreased = lines.asSequence()
        .windowed(3, 1)
        .map { it.sum() }
        .zipWithNext()
        .count { it.first < it.second }

    println("Result: $sumOfIncreased")
}

fun readInputAsListOfInt(inputDay: Int): List<Int> {
    return File("src/main/resources/day$inputDay-input").readLines().map { it.toInt() }
}