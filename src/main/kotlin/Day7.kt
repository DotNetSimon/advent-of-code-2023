class Day7 : Day {

    data class Hand(val cards: List<Char>, val bet: Int) : Comparable<Hand> {
        private val types = listOf("five", "four", "full", "three", "twop", "onep", "high")
        private val cardRank = "AKQJT98765432"
        fun type(): String {
            val cardCountMap = cards.groupingBy { it }.eachCount()
            val sortedByCount = cardCountMap.entries.sortedByDescending { it.value }
            val distinctCardCount = cardCountMap.keys.toSet()

            if (distinctCardCount.size == 1) return "five"
            if (distinctCardCount.size == 2 && sortedByCount[0].value == 4) return "four"
            if (distinctCardCount.size == 2 && sortedByCount[0].value == 3 && sortedByCount[1].value == 2) return "full"
            if (distinctCardCount.size == 3 && sortedByCount[0].value == 3 && sortedByCount[1].value == 1) return "three"
            if (distinctCardCount.size == 3 && sortedByCount[0].value == 2 && sortedByCount[1].value == 2) return "twop"
            if (distinctCardCount.size == 4 && sortedByCount[0].value == 2) return "onep"
            if (distinctCardCount.size == 5 && sortedByCount[0].value == 1) return "high"
            else throw Exception("Confused")
        }

        override fun compareTo(other: Hand): Int {
            val handTypeComparison = types.indexOf(this.type()) - types.indexOf(other.type())

            return if (handTypeComparison != 0) {
                handTypeComparison
            } else {
                this.cardsCompare(other.cards)
            }
        }

        private fun cardsCompare(otherCards: List<Char>): Int {
            for (i in 0..5) {
                if (this.cards[i] != otherCards[i]) {
                    return cardRank.indexOf(this.cards[i]) - cardRank.indexOf(otherCards[i])
                }
            }
            return 0
        }
    }

    data class HandB(val cards: List<Char>, val type: String, val bet: Int) : Comparable<HandB> {
        private val types = listOf("five", "four", "full", "three", "twop", "onep", "high")
        private val cardRank = "AKQT98765432J"

        companion object {
            fun type(cards: List<Char>): String {
                val cardCountMap = cards.groupingBy { it }.eachCount()
                val sortedByCount = cardCountMap.entries.sortedByDescending { it.value }
                val distinctCardCount = cardCountMap.keys.toSet()

                // how many jokers in the hand?
                val jokers = cards.count { it == 'J' }

                if (distinctCardCount.size == 1) return "five" // remains the same.

                if (distinctCardCount.size == 2 && sortedByCount[0].value == 4) { // if the 1 card is joker -> five
                    // edge case: if we have 4 jokers -> also five
                    return if (jokers == 1 || jokers == 4) "five" else "four"
                }
                if (distinctCardCount.size == 2 && sortedByCount[0].value == 3 && sortedByCount[1].value == 2) {
                    // we have 3 and 2. if any of these two sets are jokers we are five cards.
                    return if (jokers == 2 || jokers == 3) "five" else "full"
                }
                if (distinctCardCount.size == 3 && sortedByCount[0].value == 3 && sortedByCount[1].value == 1) {
                    // we have 3 the same and 2 individual cards. either all 3 are jokers -> four or 1 single joker -> four.
                    return if (jokers == 3 || jokers == 1) "four" else "three"
                }
                if (distinctCardCount.size == 3 && sortedByCount[0].value == 2 && sortedByCount[1].value == 2) {
                    // 3 different cards and two pairs. if one pair are jokers we have four, if the 1 is J then full house.
                    return if (jokers == 2) "four" else if (jokers == 1) "full" else "twop"
                }
                if (distinctCardCount.size == 4 && sortedByCount[0].value == 2) {
                    // we have 4 different cards, 1 pair. if there is at least 1 joker we have three.
                    return if (jokers == 2 || jokers == 1) "three" else "onep"
                }
                if (distinctCardCount.size == 5) {
                    // we have 5 different cards. if there's 1 J it is a pair instead of high.
                    return if (jokers == 1) "onep" else "high"
                } else throw Exception("Confused")
            }
        }

        override fun compareTo(other: HandB): Int {
            val handTypeComparison = types.indexOf(this.type) - types.indexOf(other.type)

            return if (handTypeComparison != 0) {
                handTypeComparison
            } else {
                this.cardsCompare(other.cards)
            }
        }

        private fun cardsCompare(otherCards: List<Char>): Int {
            for (i in 0..5) {
                if (this.cards[i] != otherCards[i]) {
                    return cardRank.indexOf(this.cards[i]) - cardRank.indexOf(otherCards[i])
                }
            }
            return 0
        }
    }

    override fun puzzleA(data: List<String>): String {
        return data.map { it -> it.split(" ").let { Hand(it[0].toList(), it[1].toInt()) } }.sortedDescending()
            .mapIndexed { index, hand -> hand.bet * (index + 1) }
            .sum().toString()
    }

    override fun puzzleB(data: List<String>): String {
        return data.map { it -> it.split(" ").let { HandB(it[0].toList(), HandB.type(it[0].toList()), it[1].toInt()) } }
            .sortedDescending()
            .mapIndexed { index, hand -> hand.bet * (index + 1).toLong() }
            .sum().toString()
    }
}
