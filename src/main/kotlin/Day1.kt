fun main(args: Array<String>) {
    println("Advent of code - day 1!")
    println("======================")
    println("Puzzle 1!")
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
