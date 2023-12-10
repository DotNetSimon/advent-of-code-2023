import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * For testing the code for correctness based on the example input in the explanation.
 * Adjust the DAY, Data, and expected outputs.
 */
class Day7Test {

    // day to test
    private val day = DayFactory.createDay(7)

    // source data
    private val dataA = """32T3K 765
T55J5 684
KK677 28
KTJJT 220
QQQJA 483""".trimIndent().split("\n").filterNot{it == ""}

    private val dataB = dataA

    // expected output puzzle A
    private val expected_puzzleA = """6440"""

    // expected output puzzle B
    private val expected_puzzleB = """5905"""

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

    @Test
    fun handOfFive() {
        assertEquals(Day7.HandB.type("AAAAJ".toList()), "five")
        assertEquals(Day7.HandB.type("AAAJA".toList()), "five")
        assertEquals(Day7.HandB.type("AAJAA".toList()), "five")
        assertEquals(Day7.HandB.type("AJAAA".toList()), "five")
        assertEquals(Day7.HandB.type("JAAAA".toList()), "five")
        assertEquals(Day7.HandB.type("AJAAJ".toList()), "five")
        assertEquals(Day7.HandB.type("AAJJA".toList()), "five")
        assertEquals(Day7.HandB.type("JAJAA".toList()), "five")
        assertEquals(Day7.HandB.type("JJAAA".toList()), "five")
        assertEquals(Day7.HandB.type("JAAAJ".toList()), "five")

        val data = listOf("AAAAJ", "AAAJA","AAJAA","AJAAA","JAAAA", "AJJAA", "AAJJA", "JAAAJ")
        val hands = data.map { Day7.HandB(it.toList(), Day7.HandB.type(it.toList()), 1) }.sorted().reversed()
        assertEquals(hands[0].cards, "JAAAJ".toList())
    }

    @Test
    fun handOfFour() {
        assertEquals(Day7.HandB.type("JJAAQ".toList()), "four")
        assertEquals(Day7.HandB.type("JAQQQ".toList()), "four")
        assertEquals(Day7.HandB.type("JQQAQ".toList()), "four")
        assertEquals(Day7.HandB.type("JAAJQ".toList()), "four")
        val data = listOf("AAQAJ", "QQAJQ", "JAAAQ", "JJAAQ", "QQAJJ")
        val hands = data.map { Day7.HandB(it.toList(), Day7.HandB.type(it.toList()), 1) }.sorted().reversed()
        assertEquals(hands[0].cards, "JJAAQ".toList())
    }

    @Test
    fun handOfFullHouse() {
        assertEquals(Day7.HandB.type("JJAQQ".toList()), "four")
        assertEquals(Day7.HandB.type("JAAQQ".toList()), "full")
        assertEquals(Day7.HandB.type("AAAQQ".toList()), "full")
        assertEquals(Day7.HandB.type("JJJQQ".toList()), "five")
        assertEquals(Day7.HandB.type("AAAQJ".toList()), "four")
        assertEquals(Day7.HandB.type("AAJQJ".toList()), "four")
        assertEquals(Day7.HandB.type("AAQQJ".toList()), "full")
        val data = listOf("JJAQQ","JAAQQ","AAAQQ","AAAQJ","AAJQJ","AAQQJ", "JJJQQ")
        val hands = data.map { Day7.HandB(it.toList(), Day7.HandB.type(it.toList()), 1) }.sorted().reversed()
        assertEquals(hands[0].cards, "JAAQQ".toList())
        assertEquals(hands[5].cards, "AAAQJ".toList())
        assertEquals(hands[6].cards, "JJJQQ".toList())
    }

}
