import java.io.File

fun readInputAsListOfInt(inputDay: Int): List<Int> {
    return File("src/main/resources/day$inputDay-input").readLines().map { it.toInt() }
}

fun readInput(inputDay: Int): List<String> {
    return File("src/main/resources/day$inputDay-input").readLines()
}