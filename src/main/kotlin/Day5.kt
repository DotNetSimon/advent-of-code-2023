import kotlin.math.max
import kotlin.math.min

/**
 * Day 1 class, should solve the puzzle A and puzzle B for you.
 */
class Day5 : Day {

    fun chunkByData(data: List<String>): List<List<String>> {
        val chunks = mutableListOf<List<String>>()
        var currentChunk = mutableListOf<String>()

        for (item in data) {
            if (item.contains("-")) {
                if (currentChunk.isNotEmpty()) {
                    chunks.add(currentChunk.toList())
                    currentChunk.clear()
                    currentChunk.add(item)
                }
            } else {
                currentChunk.add(item)
            }
        }

        if (currentChunk.isNotEmpty()) {
            chunks.add(currentChunk.toList())
        }

        return chunks
    }

    data class SeedRange(val start: Long, val end: Long)
    data class Conversion(val start: Long, val end: Long, val offset: Long)
    data class AlmanacMap(val source: String, val destination: String, val conversion: List<Conversion>) {
        companion object {
            fun createFrom(data: List<String>): AlmanacMap {
                val (from, _, to) = data[0].split("-")
                val conversions = data.drop(1).map { input ->
                    val (dest, source, range) = input.split(" ").map { it.toLong() }
                    Conversion(source, source + range, dest - source)
                }.sortedBy { it.start }

                return AlmanacMap(
                    from,
                    to,
                    conversions,
                )
            }
        }

        fun transform(input: Long): Long {
            return conversion.find { c -> input >= c.start && input < c.end }?.offset?.let { it + input } ?: input
        }

        fun transform(seeds: List<SeedRange>): List<SeedRange> {
            val results = mutableListOf<SeedRange>()
            for (seedRange in seeds) {
                var startSeed = seedRange.start
                while (startSeed < seedRange.end) {
                    val useConversion = conversion.firstOrNull{ c -> startSeed < c.end}

                    // no more useful conversions -> copy the seedrange and be done
                    if (useConversion == null || useConversion.start > seedRange.end) {
                        results.add(SeedRange(startSeed, seedRange.end))
                        break
                    }

                    val newStartSeed = min(useConversion.end, seedRange.end)
                    val rangeStartSeed = max(seedRange.start, useConversion.start)
                    if (rangeStartSeed > startSeed) { // don't forget to add the first part of seedrange to results.
                        results.add(SeedRange(startSeed, rangeStartSeed-1))
                    }

                    results.add(SeedRange(rangeStartSeed+useConversion.offset, newStartSeed+useConversion.offset))
                    startSeed = newStartSeed
                }
            }
            return results
        }
    }

    override fun puzzleA(data: List<String>): String {
        val chunks = chunkByData(data)

        val seeds = chunks[0][0].split(" ").drop(1).map { it.toLong() }

        val maps = chunks.drop(1).map { AlmanacMap.createFrom(it) }

        val result = seeds.map { seed ->
            var current = seed
            maps.forEach { conversion -> current = conversion.transform(current) }
            current
        }

        return result.min().toString()
    }

    override fun puzzleB(data: List<String>): String {
        val chunks = chunkByData(data)

        val seeds = chunks[0][0].split(" ").drop(1).map { it.toLong() }.chunked(2)
        var allSeeds = seeds.map { SeedRange(it[0], it[0] + it[1] - 1) }

        val maps = chunks.drop(1).map { AlmanacMap.createFrom(it) }

        for (map in maps) {
            allSeeds = map.transform(allSeeds)
        }

        return allSeeds.minOf { it.start }.toString()
    }
}
