package nicestring

fun String.isNice(): Boolean {
    val conditions = arrayOf<Boolean>(false, false, false)
    conditions[0] = this.zipWithNext().none {(first, second) ->
        "$first$second" in arrayOf("bu", "ba", "be")
    }
    conditions[1] = this.count { it in "aeiou" } >= 3
    conditions[2] = this.zipWithNext().any { (first, second) -> first == second }
    return conditions.count { it } >= 2
}