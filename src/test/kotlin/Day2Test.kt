import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * For testing the code for correctness based on the example input in the explanation.
 * Adjust the DAY, Data, and expected outputs.
 */
class Day2Test {

    // day to test
    private val day = DayFactory.createDay(1)

    // source data
    private val dataA = """1abc2
pqr3stu8vwx
a1b2c3d4e5f
treb7uchet
""".trimIndent().split("\n").filterNot{it == ""}

    private val dataB = """two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen""".trimIndent().split("\n").filterNot{it == ""}

    // expected output puzzle A
    private val expected_puzzleA = """142"""

    // expected output puzzle B
    private val expected_puzzleB = """281"""

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
