fun main(args: Array<String>) {
    println("Advent of code - day 2!")
    println("======================")
    println("Puzzle 1!")

    val directions = readInput(2)
        .map { it.split(" ") }
        .map { (direction, unit) -> Direction.valueOf(direction.uppercase()) to unit.toInt() }
        .groupBy ({ it.first }, {it.second})
        .mapValues { it.value.sum() }

    val horizontal = directions.getOrDefault(Direction.FORWARD, 0)
    val vertical: Int = directions.getOrDefault(Direction.DOWN, 0) - directions.getOrDefault(Direction.UP, 0)

    println("Result horizontal: $horizontal")
    println("Result vertical: $vertical")
    println("Result vertical: ${horizontal * vertical}")

    println("======================")

    println("Puzzle 2!")
    var horizontal2 = 0
    var vertical2 = 0
    var aim = 0

    readInput(2)
        .map { it.split(" ") }
        .map { (direction, unit) -> direction to unit.toInt() }
        .forEach { (direction, unit) -> when (direction) {
            "forward" -> {
                horizontal2 += unit
                vertical2 += aim * unit
            }
            "down" -> aim += unit
            "up" -> aim -= unit
            else -> throw Exception("wrong input")
        } }

    println("Result horizontal: $horizontal2")
    println("Result vertical: $vertical2")
    println("Result vertical: ${horizontal2 * vertical2}")
}

enum class Direction {
    FORWARD, DOWN, UP
}