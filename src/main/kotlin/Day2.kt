/**
 * Day 1 class, should solve the puzzle A and puzzle B for you.
 */
class Day2 : Day {
    data class CubeGame1(val id: Int, val reds: List<Int>, val blues:  List<Int>, val greens:  List<Int>) {
        companion object {
            fun createFrom(line: String): CubeGame1 {
                val colors = listOf("red", "green", "blue")

                val id = Regex("Game (\\d+):").find(line)?.let { it.groups[1]?.value?.toInt() }!!
                val (red, green, blue) = colors.map { color ->
                    Regex("(\\d+) $color").findAll(line).map { match ->
                        match.groupValues[1].toInt()
                    }.toList()
                }

                return CubeGame1(id, red, blue, green)
            }
        }
    }

    override fun puzzleA(data: List<String>): String {
        val limits = mapOf("red" to 12, "green" to 13, "blue" to 14)
        return data.map { line -> CubeGame1.createFrom(line) }
            .filter { game ->
                game.reds.max() <= limits["red"]!! && game.greens.max() <= limits["green"]!! && game.blues.max() <= limits["blue"]!!
            }.map { game -> game.id }
            .sum()
            .toString()
    }

    override fun puzzleB(data: List<String>): String {
        return data.map { line -> CubeGame1.createFrom(line) }
            .map { game -> game.reds.max() * game.blues.max() * game.greens.max() }
            .sum()
            .toString()
    }
}
