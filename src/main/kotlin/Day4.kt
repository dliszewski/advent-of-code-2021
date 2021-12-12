fun main(args: Array<String>) {
    println("Advent of code - day 4!")
    println("======================")
    println("Puzzle 1!")

    val lines = readInput(4)
    val numbers = lines.first().split(",")
    val boards = mapToBoards(lines)
    var result = 0

    for (number in numbers) {
        var won = false
        var sum = 0
        for (board in boards) {
            board.crossOutNumber(number.toInt())
            won = board.checkIfWon()
            if (won) {
                sum = board.sumOfUnmarked()
                println("Sum of unmarked: $sum")
                break
            }
        }
        if (won) {
            println("Winning number: ${number.toInt()}")
            result = sum * number.toInt()
            break
        }
    }

    println("Result: $result")

    println("======================")

    println("Puzzle 2!")

    val boards2 = mapToBoards(lines)

    var lastWinningResult = 0

    for (number in numbers) {
        var lastWinner = false
        var sum = 0
        for (board in boards2) {
            board.crossOutNumber(number.toInt())
            if (board.checkIfWon()) {
                board.winner = true
                if (boards2.count { it.winner } == boards2.size) {
                    lastWinner = true
                    sum = board.sumOfUnmarked()
                    println("Sum of unmarked: $sum")
                    break
                }
            }
        }
        if (lastWinner) {
            println("Winning number: ${number.toInt()}")
            lastWinningResult = sum * number.toInt()
            break
        }
    }

    println("Result: $lastWinningResult")
}

private fun mapToBoards(lines: List<String>) = lines.drop(2).windowed(5, 6)
    .map { board ->
        Board(board.mapIndexed { rowIndex, numbersInRow ->
            numbersInRow.split(" ").filter { number -> number.isNotEmpty() }
                .mapIndexed { columnIndex, value -> BoardCell(rowIndex, columnIndex, value.toInt()) }
        }.flatten())
    }

class Board(private val cells: List<BoardCell>) {

    var winner: Boolean = false

    fun checkIfWon(): Boolean {
        return anyRowWon() || anyColumnWon()
    }

    private fun anyColumnWon(): Boolean {
        return (0 until 5).map { columnIndex -> cells.filter { it.j == columnIndex }.all { it.match } }.any { it }
    }

    private fun anyRowWon(): Boolean {
        return (0 until 5).map { rowIndex -> cells.filter { it.i == rowIndex }.all { it.match } }.any { it }
    }

    fun crossOutNumber(number: Int) {
        cells.firstOrNull { it.value == number }?.match = true
    }

    fun sumOfUnmarked(): Int {
        return cells.filter { it.match.not() }.sumOf { it.value }
    }
}

class BoardCell(val i: Int, val j: Int, val value: Int) {
    var match: Boolean = false
}