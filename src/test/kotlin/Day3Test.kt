import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * For testing the code for correctness based on the example input in the explanation.
 * Adjust the DAY, Data, and expected outputs.
 */
class Day3Test {

    // day to test
    private val day = DayFactory.createDay(3)

    // source data
    private val dataA = """467..114..
...*......
..35..633.
......#...
617*......
.....+.58.
..592.....
......755.
...${'$'}.*....
.664.598..""".trimIndent().split("\n").filterNot{it == ""}

    private val dataB = dataA

    // expected output puzzle A
    private val expected_puzzleA = """4361"""

    // expected output puzzle B
    private val expected_puzzleB = """467835"""

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
    fun puzzleBCorrect() {
        val actual = day.puzzleB(dataB)
        println(actual)
        assertEquals(expected_puzzleB, actual)
    }
}
