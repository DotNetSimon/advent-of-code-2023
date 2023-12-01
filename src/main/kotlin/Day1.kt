/**
 * Day 1 class, should solve the puzzle A and puzzle B for you.
 */
class Day1 : Day {

    private val digits = "1234567890"
    private val digitWords = listOf("0","1","2","3","4","5","6","7","8","9","zero","one","two","three","four","five","six","seven","eight","nine")

    override fun puzzleA(data: List<String>): String {
        return data.map { line ->
                val first = line.first { digits.contains(it) }.toString()
                val second = line.last { digits.contains(it) }.toString()
                (first + second).toInt()
        }.sum().toString()
    }

    override fun puzzleB(data: List<String>): String {
        return data.map { line ->
            val firstWordIndex = line.indexOfAny(digitWords)
            val lastWordIndex = line.lastIndexOfAny(digitWords)

            val first = if (digits.contains(line[firstWordIndex])) line[firstWordIndex].toString()
                else (digitWords.indexOfFirst{it.contains(line.substring(firstWordIndex, firstWordIndex+2))}%10).toString()
            val second = if (digits.contains(line[lastWordIndex])) line[lastWordIndex].toString()
                else (digitWords.indexOfFirst{it.contains(line.substring(lastWordIndex, lastWordIndex+2))}%10).toString()

            (first + second).toInt()
        }.sum().toString()
    }
}
