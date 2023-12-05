import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * For testing the code for correctness based on the example input in the explanation.
 * Adjust the DAY, Data, and expected outputs.
 */
class Day5Test {

    // day to test
    private val day = DayFactory.createDay(5)

    // source data
    private val dataA = """seeds: 79 14 55 13

seed-to-soil map:
50 98 2
52 50 48

soil-to-fertilizer map:
0 15 37
37 52 2
39 0 15

fertilizer-to-water map:
49 53 8
0 11 42
42 0 7
57 7 4

water-to-light map:
88 18 7
18 25 70

light-to-temperature map:
45 77 23
81 45 19
68 64 13

temperature-to-humidity map:
0 69 1
1 0 69

humidity-to-location map:
60 56 37
56 93 4""".trimIndent().split("\n").filterNot{it == ""}

    private val dataB = dataA

    // expected output puzzle A
    private val expected_puzzleA = """35"""

    // expected output puzzle B
    private val expected_puzzleB = """46"""

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
    // output bad:
    //    64029620
    //    64029620

    @Test
    fun `seedrange end captured`() {
        val data = """""${'"'}seeds: 10 10 20 20

seed-to-soil map:
30 0 5
1 25 5""".trimIndent().split("\n").filterNot{it == ""}
        val expected = "1"
        val actual = day.puzzleB(data)
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    fun `seedrange start captured`() {
        val data = """""${'"'}seeds: 10 10

seed-to-soil map:
20 9 5
50 25 5""".trimIndent().split("\n").filterNot{it == ""}
        val expected = "14"
        val actual = day.puzzleB(data)
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    fun `seedrange middle captured`() {
        val data = """""${'"'}seeds: 10 10

seed-to-soil map:
0 15 2
50 25 5""".trimIndent().split("\n").filterNot{it == ""}
        val expected = "0"
        val actual = day.puzzleB(data)
        println(actual)
        assertEquals(expected, actual)
    }

    @Test
    fun `seedrange none captured`() {
        val data = """""${'"'}seeds: 10 10

seed-to-soil map:
0 25 2
50 5 4""".trimIndent().split("\n").filterNot{it == ""}
        val expected = "10"
        val actual = day.puzzleB(data)
        println(actual)
        assertEquals(expected, actual)
    }

    // some situations:

    // start captured
    // seed range: 10-20
    // conversion: 5-15: -10
    // outcome: 0-5, 15-20

    // entirely captured
    // seed range: 10-20
    // conversion: 5-25: -10
    // outcome: 0-10

    // none captured
    // seed range 10-20
    // conversion 0-5, 25-30
    // outcome: 10-20

    // in the middle
    // seed range: 10-20
    // conversion: 15-17: -10
    // outcome: 10-14, 5-6, 17-20

    // end captured
    // seed range: 10-20
    // conversion: 17-25: -15
    // outcome: 10-16, 2-5

}
