/**
 * Day 1 class, should solve the puzzle A and puzzle B for you.
 */
class Day3 : Day {
    data class Point(val X: Int, val Y: Int)

    private val diagonalAdjacency = mapOf(
        "N" to Point(0, -1),
        "NE" to Point(1, -1),
        "E" to Point(1, 0),
        "SE" to Point(1, 1),
        "S" to Point(0, 1),
        "SW" to Point(-1, 1),
        "W" to Point(-1, 0),
        "NW" to Point(-1, -1)
    )

    override fun puzzleA(data: List<String>): String {
        val realData = data.toMutableList()
        val partNumbers = mutableListOf<Int>()
        realData.forEachIndexed { y, line ->
            line.forEachIndexed { x, ch ->
                if (!ch.isDigit() && ch != '.') {
                    partNumbers.addAll(findAdjacentPartNumbers(x, y, realData))
                }
            }
        }
        return partNumbers.sum().toString()
    }

    /**
     * Do some dirty dirty string search in a 2d array. Make sure to remove the numbers we find by replacing them
     * with ... as well so we don't count duplicates
     */
    private fun findAdjacentPartNumbers(x: Int, y: Int, data: MutableList<String>): Collection<Int> {
        val partNumbers = mutableListOf<Int>()
        diagonalAdjacency.forEach { _, direction ->
            if (x + direction.X >= 0 && x + direction.X < data[y].length &&
                y + direction.Y >= 0 && y + direction.Y < data.size
            ) {
                if (data[y + direction.Y][x + direction.X] != '.') {
                    // find start of value
                    var offset = 0
                    while (x + direction.X - offset > 0 && data[y + direction.Y][x + direction.X - offset - 1].isDigit()) {
                        offset++
                    }
                    val partNr = data[y + direction.Y].substring(x + direction.X - offset).takeWhile { it.isDigit() }
                    partNumbers.add(partNr.toInt())
                    val sb = StringBuilder(data[y + direction.Y])
                    partNr.forEachIndexed { index, _ -> sb.setCharAt(x + direction.X - offset + index, '.') }
                    data[y + direction.Y] = sb.toString()
                }
            }
        }
        return partNumbers
    }

    private fun calculateGearRatio(x: Int, y: Int, data: MutableList<String>): Int {
        if (data[y][x] != '*') return 0
        val parts = findAdjacentPartNumbers(x,y,data)
        return if (parts.size==2) parts.reduce { acc, number -> acc * number } else 0
    }

    override fun puzzleB(data: List<String>): String {
        val realData = data.toMutableList()

        return realData.mapIndexed { y, line ->
            line.mapIndexed { x, ch ->
                if (!ch.isDigit() && ch != '.') {
                    calculateGearRatio(x,y,realData)
                } else 0
            }.sum()
        }.sum().toString()
    }
}
