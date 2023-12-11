import java.lang.Exception
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Day11 : Day {

    private data class Point(val X: Int, val Y: Int) {
        fun manhattanDistance(other: Point): Int {
            return abs(this.X - other.X) + abs(this.Y - other.Y)
        }
    }

    private data class Galaxy(val id: Int, val point: Point) {

    }
    override fun puzzleA(data: List<String>): String {
        val universe = expandUniverse(data)
        val galaxies = getGalaxies(universe)
        val distances = getAllDistances(galaxies)
        return distances.sum().toString()
    }

    private fun getAllDistances(galaxies: List<Day11.Galaxy>): List<Int> {
        return galaxies.flatMapIndexed{ index, galaxy1 ->
            galaxies.drop(index+1).map { galaxy2 ->
                galaxy1.point.manhattanDistance(galaxy2.point)
            }
        }
    }

    private fun getGalaxies(universe: List<String>): List<Galaxy> {
        var id = 0
        return universe.flatMapIndexed{ y, line -> line.mapIndexed{ x, each -> if (each == '#') Galaxy(id++, Point(x,y)) else null}.filterNotNull()}
    }

    fun universeExpanding(data: List<String>) {

    }
    fun expandUniverse(data: List<String>): List<String> {
        var newData = mutableListOf<String>()
        data.forEach { line ->
            if (line.all { it == '.' }) newData.add(line) // add double if empty
            newData.add(line)
        }

        var x = 0
        while (x < newData[0].length-1) {
            if (allCharactersAtPosition(x, newData).take(newData.size).all { it==newData[0][x] }) {
                newData = addEmptyLineAt(x, newData)
                x++ // skip one since we doubled this column.
            }
            x++
        }
        return newData
    }

    private fun addEmptyLineAt(x: Int, newData: MutableList<String>): MutableList<String> {
        for (y in 0 until newData.size) {
            newData[y] = newData[y].substring(0, x) + "." + newData[y].substring(x, newData[y].length)
        }
        return newData
    }

    private fun allCharactersAtPosition(x: Int, newData: List<String>) = sequence {
        for (line in newData) {
            yield(line[x])
        }
    }


    override fun puzzleB(data: List<String>): String {
        return "o"
    }


}
