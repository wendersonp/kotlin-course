package mastermind

data class Evaluation(val rightPosition: Int, val wrongPosition: Int)

fun MutableList<Char>.removeOne(char: Char): Boolean {
    val index = this.indexOf(char)
    return if (index < 0) {
        false
    } else {
        this.removeAt(index)
        true
    }
}

fun evaluateGuess(secret: String, guess: String): Evaluation {
    val rightPosition = secret.zip(guess).count { it.first == it.second }

    val rightCharacters = "ABCDEF".sumOf { char ->
        secret.count { it == char }.coerceAtMost(guess.count { it == char })
    }

    return Evaluation(rightPosition, rightCharacters - rightPosition)
}
