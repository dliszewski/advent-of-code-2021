import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main(args: Array<String>) {
    println("Advent of code - day 5!")
    println("======================")
    println("Puzzle 1!")

    val input = readInput(5)

    val lines = input.map {
        val (point1, point2) = it.split("->")
        val start = Point(point1.parseX(), point1.parseY())
        val end = Point(point2.parseX(), point2.parseY())
        Line(start, end)
    }
    val result = lines
        .filter { it.isHorizontalOrVertical() }
        .map { it.pointsBetween() }
        .flatten()
        .groupBy { it }
        .mapValues { it.value.size }
        .count { it.value >= 2 }

    println("Result: $result")

    println("======================")

    println("Puzzle 2!")
    val result2 = lines
        .filter { it.isHorizontalOrVertical() || it.isDiagonal()}
        .map { it.pointsBetween() }
        .flatten()
        .groupBy { it }
        .mapValues { it.value.size }
        .count { it.value >= 2 }

    println("Result: $result2")
}

private fun String.parseX() = this.trim().split(",")[0].toInt()
private fun String.parseY() = this.trim().split(",")[1].toInt()


data class Line(val start: Point, val end: Point) {

    fun isHorizontalOrVertical(): Boolean {
        return start.x == end.x || start.y == end.y
    }

    fun isDiagonal(): Boolean {
        return abs(start.x - end.x) == abs(start.y - end.y)
    }

    fun pointsBetween(): List<Point> {
        return if(isHorizontalOrVertical()) {
            val min = min(start.x, end.x)
            val max = max(start.x, end.x)
            (min..max)
                .map { x ->
                    val min1 = min(start.y, end.y)
                    val max1 = max(start.y, end.y)
                    (min1..max1).map { y -> Point(x, y) }
                }.flatten()
        } else {
            val dx = start.x - end.x
            val dy = start.y - end.y
            (0..abs(dx))
                .map {
                    Point(
                        if(dx < 0) start.x + it else start.x - it,
                        if(dy < 0) start.y + it else start.y - it
                    )
                }
        }

    }
}

data class Point(val x: Int, val y: Int): Comparable<Point> {
    override fun compareTo(other: Point): Int {
        if (this.x < other.x) return -1
        if (this.x > other.x) return 1
        if (this.y < other.y) return -1
        if (this.y > other.y) return 1
        return 0
    }
}