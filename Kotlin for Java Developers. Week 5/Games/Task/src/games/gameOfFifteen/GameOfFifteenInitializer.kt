package games.gameOfFifteen

import kotlin.properties.Delegates
import kotlin.random.Random
import kotlin.random.nextInt

interface GameOfFifteenInitializer {
    /*
     * Even permutation of numbers 1..15
     * used to initialized the first 15 cells on a board.
     * The last cell is empty.
     */
    val initialPermutation: List<Int>
}

class RandomGameInitializer : GameOfFifteenInitializer {
    /*
     * Generate a random permutation from 1 to 15.
     * `shuffled()` function might be helpful.
     * If the permutation is not even, make it even (for instance,
     * by swapping two numbers).
     */
    override val initialPermutation by lazy {
        val permutation = (1..15).shuffled().toMutableList()
        while (!isEven(permutation)) {

            val firstIndex = Random.nextInt(0..14)
            val secondIndex = Random.nextInt(0..14)

            val swap = permutation[firstIndex]
            permutation[firstIndex] = permutation[secondIndex]
            permutation[secondIndex] = swap
        }
        return@lazy permutation
    }
}

