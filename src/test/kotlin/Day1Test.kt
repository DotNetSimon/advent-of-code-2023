import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * For testing the code for correctness based on the example input in the explanation.
 * Adjust the DAY, Data, and expected outputs.
 */
class Day1Test {

    // day to test
    val day = DayFactory.createDay(1)

    // source data
    val data = """498,4 -> 498,6 -> 496,6
503,4 -> 502,4 -> 502,9 -> 494,9
""".trimIndent().split("\n").filterNot{it == ""}

    // expected output puzzle A
    val expected_puzzleA = """0 ......+...
1 ..........
2 ......o...
3 .....ooo..
4 ....#ooo##
5 ...o#ooo#.
6 ..###ooo#.
7 ....oooo#.
8 .o.ooooo#.
9 #########.
"""

    // expected output puzzle B
    val expected_puzzleB = """............o............
...........ooo...........
..........ooooo..........
.........ooooooo.........
........oo#ooo##o........
.......ooo#ooo#ooo.......
......oo###ooo#oooo......
.....oooo.oooo#ooooo.....
....oooooooooo#oooooo....
...ooo#########ooooooo...
..ooooo.......ooooooooo..
#########################"""

    // -------------------
    // Should be no need to modify the part below
    // -------------------
    @Test
    fun puzzleACorrect() {
        val actual = day.puzzleA(data)
        println(actual)
        assertEquals(expected_puzzleA, actual)
    }

    @Test
    fun puzzleBCorrect() {
        val actual = day.puzzleB(data)
        println(actual)
        assertEquals(expected_puzzleB, actual)
    }
}
