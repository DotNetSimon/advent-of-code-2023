import kotlin.system.measureTimeMillis

/**
 * Runs the main loop to solve puzzle A or B
 * Only adjust day and puzzle lines and run it. If you have the corresponding class ready it should work!
 */
fun main() {

    // --------- SET DAY AND PUZZLE AND RUN MAIN -----------
    val day = 11
//    val puzzle = "A"
    val puzzle ="B"
    // -----------------------------------------------------

    println("Advent of code: Day $day")
    println("Selected puzzle: $puzzle")

    val data = DataSource.getDataAsList(day)
    val dayClass = DayFactory.createDay(day)

    val timeInMillis = measureTimeMillis {
        val result = if (puzzle == "A") dayClass.puzzleA(data) else dayClass.puzzleB(data)
        println("Result for puzzle $puzzle:\n$result")
    }

    println("(The execution took $timeInMillis ms)")
}
