import java.lang.IllegalStateException

fun main(args: Array<String>) {
    println("Advent of code - day 3!")
    println("======================")
    println("Puzzle 1!")

    val inputGamma = readInput(3).map { it.toCharArray() }
    val mostCommonGama = getMostCommonGamma(inputGamma)
    val mostCommonGamaValue = mostCommonGama.toDecimal()
    val leastCommonGamaValue = mostCommonGama.invertToLeast().toDecimal()

    println("Result most common: $mostCommonGamaValue")
    println("Result least common: $leastCommonGamaValue")
    println("Result: ${mostCommonGamaValue * leastCommonGamaValue}")

    println("======================")

    println("Puzzle 2!")
    val oxygen = filterInputs(inputGamma, 0, mostCommonGama.joinToString(""), false).toDecimal()
    val co2 = filterInputs(inputGamma, 0, mostCommonGama.invertToLeast().joinToString(""), true).toDecimal()

    println("Result oxygen: $oxygen")
    println("Result co2: $co2")
    println("Result vertical: ${oxygen * co2}")
}

fun filterInputs(inputGamma: List<CharArray>, index: Int, commonGamma: String, least: Boolean): List<Char> {
    if (inputGamma.size == 1) return inputGamma.first().toList()
    val filtered = inputGamma.filter { it[index] == commonGamma[index] }
    val newMostGamma = getMostCommonGamma(filtered, least).joinToString("")
    return filterInputs(filtered, index + 1, newMostGamma, least)
}

private fun List<Char>.invertToLeast() = this.map {
    when (it) {
        '0' -> '1'
        '1' -> '0'
        else -> throw IllegalStateException()
    }
}

private fun List<Char>.toDecimal() = this.joinToString("").toInt(2)

private fun getMostCommonGamma(inputGamma: List<CharArray>, invert: Boolean = false): List<Char> {
    val mostCommonGamma = (0 until inputGamma.first().size)
        .map { index -> inputGamma.map { it[index] } }
        .map { digits -> if (digits.size - digits.count { it == '1' } <= digits.count { it == '1' }) '1' else '0' }
    return if (invert) mostCommonGamma.invertToLeast() else mostCommonGamma
}