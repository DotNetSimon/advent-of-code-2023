class Day8 : Day {

    data class Node(val origin: String, val left: String, val right: String)

    override fun puzzleA(data: List<String>): String {
        val (steps, nodes) = getStepsAndNodes(data)

        var current = nodes["AAA"]!!
        var step = 0
        while (current.origin != "ZZZ") {
            steps.forEach { dir ->
                current = if (dir == 'L') nodes[current.left]!! else nodes[current.right]!!
                step++
                if (current.origin == "ZZZ") return step.toString()
            }
        }
        return "Not found?!"
    }

    private fun getStepsAndNodes(data: List<String>): Pair<String, Map<String, Node>> {
        val steps = data[0]
        val nodes = data.drop(1).map {
            val regex = Regex("""(\w+) = \((\w+), (\w+)\)""")
            val matchResult = regex.find(it)
            val (origin, leftnode, rightNode) = matchResult?.destructured ?: throw Exception("confused")
            Node(origin, leftnode, rightNode)
        }.associateBy { it.origin }
        return Pair(steps, nodes)
    }

    override fun puzzleB(data: List<String>): String {
        val (steps, nodes) = getStepsAndNodes(data)

        var starts = nodes.filter { it.key.endsWith('A') }.values

        val factors = starts.map { start ->
            var current = start
            var step = 0
            while (!current.origin.endsWith('Z')) {
                steps.forEach { dir ->
                    current = if (dir == 'L') nodes[current.left]!! else nodes[current.right]!!
                    step++
                    //if (current.origin.endsWith('Z')) return step.toString()
                }
            }
            println("Steps for ${start.origin} to reach destination ${current.origin} : $step")
            step.toLong()
        }
        val result = factors.reduce { acc, factor -> lcm(acc, factor) }
        return result.toString()
    }

    fun gcd(a: Long, b: Long): Long {
        return if (b == 0L) a else gcd(b, a % b)
    }

    fun lcm(a: Long, b: Long): Long {
        return if (a == 0L || b == 0L) 0 else Math.abs(a * b) / gcd(a, b)
    }

//    fun questionable() {
//        var step = 0
//        while (!current.all{it.origin.endsWith('Z')})  {
//            steps.forEach { dir ->
//                current = current.map{node-> if (dir == 'L') nodes[node.left]!! else nodes[node.right]!!}
//                step++
//                if (current.all{it.origin.endsWith('Z')}) return step.toString()
//            }
//        }
//    }
}
