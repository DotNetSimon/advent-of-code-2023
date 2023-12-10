class Day9 : Day {

    override fun puzzleA(data: List<String>): String {
        val values = data.map{
            findNextValue(it.split(" ").filter{it!=""}.map{it.toLong()})
        }
            return values.sum().toString()
    }

    private fun findNextValue(values: List<Long>): Long {
        val differences = mutableListOf(values.toMutableList())
        do {
            differences.add(differences.last().zipWithNext { a, b -> b - a }.toMutableList())
        } while (!differences.last().all{it==0L})

        val listsOfInts = differences.reversed()
        for (i in 1 until listsOfInts.size) {
            listsOfInts[i].add(listsOfInts[i-1].last() + listsOfInts[i].last())
        }
        return listsOfInts.last().last()
    }

    private fun findPrevValue(values: List<Long>): Long {
        val differences = mutableListOf(values.toMutableList())
        do {
            differences.add(differences.last().zipWithNext { a, b -> b - a }.toMutableList())
        } while (!differences.last().all{it==0L})

        val listsOfInts = differences.reversed().toMutableList()
        for (i in 1 until listsOfInts.size) {
            listsOfInts[i] = (mutableListOf(listsOfInts[i].first() - listsOfInts[i-1].first()) + listsOfInts[i]).toMutableList()
            println(listsOfInts[i])
        }
        return listsOfInts.last().first()
    }

    override fun puzzleB(data: List<String>): String {
        return data.map{
            findPrevValue(it.split(" ").filter{it!=""}.map{it.toLong()})
        }.sum().toString()
    }

}
