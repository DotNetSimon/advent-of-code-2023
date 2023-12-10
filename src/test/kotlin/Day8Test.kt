import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * For testing the code for correctness based on the example input in the explanation.
 * Adjust the DAY, Data, and expected outputs.
 */
class Day8Test {

    // day to test
    private val day = DayFactory.createDay(8)

    // source data
    private val dataA = """RL

AAA = (BBB, CCC)
BBB = (DDD, EEE)
CCC = (ZZZ, GGG)
DDD = (DDD, DDD)
EEE = (EEE, EEE)
GGG = (GGG, GGG)
ZZZ = (ZZZ, ZZZ)""".trimIndent().split("\n").filterNot { it == "" }

    private val dataA2 = """LLR

AAA = (BBB, BBB)
BBB = (AAA, ZZZ)
ZZZ = (ZZZ, ZZZ)""".trimIndent().split("\n").filterNot { it == "" }
    private val dataB = """LR

11A = (11B, XXX)
11B = (XXX, 11Z)
11Z = (11B, XXX)
22A = (22B, XXX)
22B = (22C, 22C)
22C = (22Z, 22Z)
22Z = (22B, 22B)
XXX = (XXX, XXX)""".trimIndent().split("\n").filterNot { it == "" }

    // expected output puzzle A
    private val expected_puzzleA = """2"""
    private val expected_puzzleA2 = """6"""

    // expected output puzzle B
    private val expected_puzzleB = """6"""

    // -------------------
    // Should be no need to modify the part below
    // -------------------
    @Test
    fun puzzleACorrect() {
        val actual = day.puzzleA(dataA)
        println(actual)
        assertEquals(expected_puzzleA, actual)
        val actual2 = day.puzzleA(dataA2)
        println(actual2)
        assertEquals(expected_puzzleA2, actual2)
    }

    @Test
    fun puzzleBCorrect() {
        val actual = day.puzzleB(dataB)
        println(actual)
        assertEquals(expected_puzzleB, actual)
    }

}
