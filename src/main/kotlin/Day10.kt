import java.lang.Exception
import kotlin.math.max
import kotlin.math.min

class Day10 : Day {

    private val North = "|F7S"
    private val South = "|JLS"
    private val East = "-J7S"
    private val West = "-FLS"

    data class Pipe(val X: Int, val Y: Int, val shape: Char)
    private data class Point(val X: Int, val Y: Int) {
        fun plus(other: Point): Point {
            return Point(this.X + other.X, this.Y + other.Y)
        }
    }

    private data class Shape(val shape: Char, val offsets: List<Offset>)

    private data class Offset(val offsetPoint: Point, val shapes: String)

    private val shapes = listOf(
        Shape('|', listOf(Offset(Point(0, -1), North), Offset(Point(0, 1), South))),
        Shape('-', listOf(Offset(Point(-1, 0), West), Offset(Point(1, 0), East))),
        Shape('L', listOf(Offset(Point(0, -1), North), Offset(Point(1, 0), East))),
        Shape('J', listOf(Offset(Point(0, -1), North), Offset(Point(-1, 0), West))),
        Shape('7', listOf(Offset(Point(-1, 0), West), Offset(Point(0, 1), South))),
        Shape('F', listOf(Offset(Point(1, 0), East), Offset(Point(0, 1), South))),
        Shape(
            'S', listOf(
                Offset(Point(0, -1), "|F7"),
                Offset(Point(0, 1), "|JL"),
                Offset(Point(-1, 0), "-FL"),
                Offset(Point(1, 0), "-J7")
            )
        )
    )

    override fun puzzleA(data: List<String>): String {

        val route = createRoute(data)

        return (route.size / 2).toString()
    }

    private fun createRoute(data: List<String>): MutableList<Pipe> {
        val map = data.flatMapIndexed { y, line ->
            line.mapIndexed { x, spot -> if (spot != '.') Pipe(x, y, spot) else null }.filterNotNull()
        }.associateBy { Point(it.X, it.Y) }
        val route = mutableListOf<Pipe>()
        val start = map.filter { it.value.shape == 'S' }.values.first()!!
        var current = findFirstNeighbor(start, map).first()
        route.add(start)

        while (current != start) {
            val prev = route.last()
            route.add(current)
            current = findNextNeighbor(current, prev, map)
        }
        return route
    }

    private fun findNextNeighbor(current: Pipe, prev: Pipe, map: Map<Point, Pipe>): Pipe {
        // find the first non-previous neighbor or fail.
        return findFirstNeighbor(current, map).find { it != prev }!!
    }

    private fun findFirstNeighbor(start: Pipe, map: Map<Point, Pipe>) = sequence<Pipe> {
        for (offset in shapes.find { it.shape == start.shape }!!.offsets) {
            map[Point(start.X + offset.offsetPoint.X, start.Y + offset.offsetPoint.Y)]?.let {
                if (offset.shapes.contains(it.shape)) {
                    yield(it)
                }
            }
        }
        throw Exception("No neighbor!")
    }

    override fun puzzleB(data: List<String>): String {
        val route = createRoute(data)

        return data.flatMapIndexed{y, line -> line.mapIndexed{x, spot -> isInsideRoute(x,y, route)} }.sum().toString()
    }

    fun isInsideRoute(x: Int, y: Int, route: List<Pipe>): Int {
        if (route.any{it.X == x && it.Y == y}) return 0

        var inside = false

        val n = route.size
        var p1x = route.first().X
        var p1y = route.first().Y

        for (i in 0..route.size)
        {
            var p2x = route[i % n].X
            var p2y = route[i % n].Y

            if (y > min(p1y, p2y)) {
                if (y <= max(p1y, p2y)) {
                    if (x <= max (p1x, p2x)) {
                        if (p1y != p2y) {
                            val xInters = (y-p1y) * (p2x - p1x) / (p2y-p1y) + p1x
                            if (p1x == p2x || x < xInters) {
                                inside = !inside
                            }
                        }
                    }
                }
            }
            p1x = p2x
            p1y = p2y
        }
        return if (inside)  1 else 0
    }

}
