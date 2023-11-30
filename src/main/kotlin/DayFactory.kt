/**
 * Responsible for instantiating the correct date class.
 */
class DayFactory {
    companion object {
        fun createDay(day: Int): Day {
            return Class.forName("Day$day")?.newInstance() as Day
        }
    }
}

/**
 * All Days should extend this, so we can generate and call them on the fly.
 */
interface Day {
    fun puzzleA(data: List<String>): String
    fun puzzleB(data: List<String>): String
}
