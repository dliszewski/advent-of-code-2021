fun main(args: Array<String>) {
    println("Advent of code - day 8!")
    println("======================")
    println("Puzzle 1!")

    val input = readInput(8)
    val displays = input
        .map { str ->
            val (uniquePatterns, fourDigitOutput) = str.split(" | ")
            Pair(
                uniquePatterns
                    .trim(' ')
                    .split(" ")
                    .map { item -> item.toSortedSet().joinToString("") },
                fourDigitOutput
                    .trim(' ')
                    .split(" ")
                    .map { item -> item.toSortedSet().joinToString("") }
            )
        }

    var uniqueSegmentsFound = 0

    displays.forEach { (uniquePatterns, fourDigitOutput) ->
        val displayMap = mutableMapOf<Int, String>()

        /*
            map known combos for each patter
            1 -> 2 segments
            7 -> 3 segments
            4 -> 4 segments
            8 -> 7 segments
         */
        uniquePatterns.forEach {
            when (it.length) {
                2 -> displayMap[1] = it
                3 -> displayMap[7] = it
                4 -> displayMap[4] = it
                7 -> displayMap[8] = it
            }
        }

        uniqueSegmentsFound += fourDigitOutput.count { it in displayMap.values }
    }
    println("Result: $uniqueSegmentsFound")

    println("======================")

    println("Puzzle 2!")

    println("Result: $displays")
}