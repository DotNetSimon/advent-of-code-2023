import kotlin.math.max
import kotlin.math.min

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
                    val useConversion = conversion.firstOrNull { c -> startSeed < c.end }

                    // no more useful conversions -> copy the seedrange and be done
                    if (useConversion == null || useConversion.start > seedRange.end) {
                        results.add(SeedRange(startSeed, seedRange.end))
                        break
                    }

                    val newStartSeed = min(useConversion.end, seedRange.end)
                    val rangeStartSeed = max(seedRange.start, useConversion.start)
                    if (rangeStartSeed > startSeed) { // don't forget to add the first part of seedrange to results.
                        results.add(SeedRange(startSeed, rangeStartSeed - 1))
                    }

                    results.add(SeedRange(rangeStartSeed + useConversion.offset, newStartSeed + useConversion.offset))
                    startSeed = newStartSeed
                }
            }
            return results
        }
    }

    override fun puzzleA(data: List<String>): String {
        return chunkByData(data).let { chunks ->
            chunks[0][0].split(" ").drop(1).map { it.toLong() }
                .map { seed ->
                    chunks.drop(1).map { AlmanacMap.createFrom(it) }
                        .fold(seed) { acc, conversion ->
                            conversion.transform(acc)
                        }
                }.min().toString()
        }
    }

    override fun puzzleB(data: List<String>): String {
        chunkByData(data).let { chunks ->
            chunks[0][0].split(" ").drop(1).map { it.toLong() }.chunked(2).let { seeds ->
                return chunks.drop(1).map { AlmanacMap.createFrom(it) }
                    .fold(seeds.map { SeedRange(it[0], it[0] + it[1] - 1) })
                    { acc, map -> map.transform(acc) }
                    .minOf { it.start }.toString()
            }
        }
    }
}
