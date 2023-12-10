import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * For testing the code for correctness based on the example input in the explanation.
 * Adjust the DAY, Data, and expected outputs.
 */
class Day9Test {

    // day to test
    private val day = DayFactory.createDay(9)

    // source data
    private val dataA = """0 3 6 9 12 15
1 3 6 10 15 21
10 13 16 21 30 45""".trimIndent().split("\n").filterNot { it == "" }

    private val dataB = dataA

    // expected output puzzle A
    private val expected_puzzleA = """114"""

    // expected output puzzle B
    private val expected_puzzleB = """2"""

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
