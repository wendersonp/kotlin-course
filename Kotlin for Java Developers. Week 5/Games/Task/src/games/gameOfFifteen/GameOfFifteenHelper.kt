package games.gameOfFifteen

/*
 * This function should return the parity of the permutation.
 * true - the permutation is even
 * false - the permutation is odd
 * https://en.wikipedia.org/wiki/Parity_of_a_permutation

 * If the game of fifteen is started with the wrong parity, you can't get the correct result
 *   (numbers sorted in the right order, empty cell at last).
 * Thus the initial permutation should be correct.
 */
fun isEven(permutation: List<Int>): Boolean {
    val count = sequence<Pair<Int, Int>> {
        permutation.forEachIndexed { index, current ->
            val elements = permutation.slice(0 until index).filter { value ->
                value > current
            }.map { it to current }
            yieldAll(elements)
        }
    }.count()

    return count % 2 == 0
}