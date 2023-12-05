import kotlin.math.pow

/**
 * Day 1 class, should solve the puzzle A and puzzle B for you.
 */
class Day4 : Day {
    data class ScratchCard(val id: Int, val winningNumbers: List<Int>, val numbers: List<Int>, var instances: Int = 1) {
        companion object {
            fun createFrom(line: String): ScratchCard {
                val (gameId, firstList, secondList) = Regex("""Card\s+(\d+): (.+) \| (.+)""").find(line)!!.destructured
                return ScratchCard(
                    gameId.toInt(),
                    firstList.split(" ").mapNotNull { if (it != "") it.toInt() else null },
                    secondList.split(" ").mapNotNull { if (it != "") it.toInt() else null }
                )
            }
        }

        fun score(): Int {
            return 2f.pow(matches() - 1).toInt()
        }

        fun matches(): Int {
            return numbers.count { it in winningNumbers }
        }
    }

    override fun puzzleA(data: List<String>): String {
        return data.map { ScratchCard.createFrom(it) }.map { card -> card.score() }.sum().toString()
    }

    override fun puzzleB(data: List<String>): String {
        val r = data.map { ScratchCard.createFrom(it) }
        r.forEachIndexed{index, card -> for (i in 1..card.matches()) {
            r[index+i].instances += card.instances
        }}
        return r.map{card -> card.instances}.sum().toString()
    }
}
