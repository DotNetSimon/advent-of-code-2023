import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * For testing the code for correctness based on the example input in the explanation.
 * Adjust the DAY, Data, and expected outputs.
 */
class Day11Test {

    // day to test
    private val day = Day11()
    private val expandedUniverse = """....#........
.........#...
#............
.............
.............
........#....
.#...........
............#
.............
.............
.........#...
#....#.......""".trimIndent().split("\n").filterNot { it == "" }
    // source data
    private val dataA = """...#......
.......#..
#.........
..........
......#...
.#........
.........#
..........
.......#..
#...#.....""".trimIndent().split("\n").filterNot { it == "" }

    private val dataB = dataA
    // expected output puzzle A
    private val expected_puzzleA = """374"""

    // expected output puzzle B
    private val expected_puzzleB = """8410"""

    // -------------------
    // Should be no need to modify the part below
    // -------------------
    @Test
    fun puzzleACorrect() {
        val actual = day.puzzleA(dataA)
        println(actual)
        assertEquals(expected_puzzleA, actual)
    }

    @Test
    fun expandsUniverseCorrectly() {
        val actual = day.expandUniverse(dataA)
        println(actual)
        assertEquals(expandedUniverse, actual)
    }
    @Test
    fun puzzleBCorrect() {
        val actual = day.puzzleB(dataB)
        println(actual)
        assertEquals(expected_puzzleB, actual)
    }

}
