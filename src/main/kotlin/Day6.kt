class Day6 : Day {

    override fun puzzleA(data: List<String>): String {
        val times = data[0].split(" ").filterNot { it == "" }.drop(1).map { it.toInt() }
        val maxDistances = data[1].split(" ").filterNot { it == "" }.drop(1).map { it.toInt() }

        return times.mapIndexed { index, time ->
            var winOptions = 0
            for (i in 1..time) {
                if ((time - i) * i > maxDistances[index]) winOptions++
            }
            winOptions
        }.fold(1) { acc, i -> i * acc }.toString()
    }

    override fun puzzleB(data: List<String>): String {
        val time = data[0].split(" ").filterNot { it == "" }.drop(1).joinToString("").toLong()
        val maxDistance = data[1].split(" ").filterNot { it == "" }.drop(1).joinToString("").toLong()

        var winOptions = 0
        for (i in 1..time) {
            if ((time - i) * i > maxDistance) winOptions++
            else if (winOptions > 0) {
                break;
            }
        }
        return winOptions.toString()
    }
}
