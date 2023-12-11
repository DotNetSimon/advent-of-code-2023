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


    private fun getAllDistances(galaxies: List<Galaxy>): List<Int> {
        return galaxies.flatMapIndexed { index, galaxy1 ->
            galaxies.drop(index + 1).map { galaxy2 ->
                galaxy1.point.manhattanDistance(galaxy2.point)
            }
        }
    }

    private data class Drift(val galaxy1: Galaxy, val galaxy2: Galaxy, val oldDistance: Int, val newDistance: Int, val drift: Int)
    private fun getAllDistances(galaxies: List<Galaxy>, rowsExpanding: List<Int>, colsExpanding: List<Int>): List<Drift> {
        return galaxies.flatMapIndexed { index, galaxy1 ->
            galaxies.drop(index + 1).map { galaxy2 ->
                val distance = galaxy1.point.manhattanDistance(galaxy2.point)
                val ydrift = rowsExpanding.count{it in min(galaxy1.point.Y, galaxy2.point.Y)..max(galaxy1.point.Y, galaxy2.point.Y)}
                val xdrift = colsExpanding.count{it in min(galaxy1.point.X, galaxy2.point.X)..max(galaxy1.point.X, galaxy2.point.X)}
                val EXPANSION_FACTOR = 1000000
                var newDistance = distance - (ydrift + xdrift)
                newDistance += ydrift * EXPANSION_FACTOR
                newDistance += xdrift * EXPANSION_FACTOR
                Drift(galaxy1, galaxy2, distance, newDistance, ydrift+xdrift)
            }
        }
    }
    private fun getGalaxies(universe: List<String>): List<Galaxy> {
        var id = 0
        return universe.flatMapIndexed { y, line ->
            line.mapIndexed { x, each ->
                if (each == '#') Galaxy(
                    id++,
                    Point(x, y)
                ) else null
            }.filterNotNull()
        }
    }

    fun universeExpanding(data: List<String>): Pair<List<Int>, List<Int>> {
        val rowsExpanding = mutableListOf<Int>()
        val colsExpanding = mutableListOf<Int>()
        data.forEachIndexed { index, line ->
            if (line.all { it == '.' }) rowsExpanding.add(index)
        }
        for (x in 0 until data[0].length) {
            if (allCharactersAtPosition(x, data).take(data.size).all { it == '.' }) {
                colsExpanding.add(x)
            }
        }
        return Pair(rowsExpanding, colsExpanding)
    }

    fun expandUniverse(data: List<String>): List<String> {
        var newData = mutableListOf<String>()
        data.forEach { line ->
            if (line.all { it == '.' }) newData.add(line) // add double if empty
            newData.add(line)
        }

        var x = 0
        while (x < newData[0].length - 1) {
            if (allCharactersAtPosition(x, newData).take(newData.size).all { it == newData[0][x] }) {
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

    override fun puzzleA(data: List<String>): String {
        val universe = expandUniverse(data)
        val galaxies = getGalaxies(universe)
        val distances = getAllDistances(galaxies)
        return distances.sum().toString()
    }

    override fun puzzleB(data: List<String>): String {
        val (rowsExpanding, colsExpanding) = universeExpanding(data)
        val galaxies = getGalaxies(data)
        val distances = getAllDistances(galaxies, rowsExpanding, colsExpanding)
        return distances.map{it.newDistance.toLong()}.sum().toString()
    }


}
