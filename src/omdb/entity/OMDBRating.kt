package omdb.entity

import java.io.Serializable

data class OMDBRating(
        val source: OMDBRatingSource,
        val value: String
) : Serializable {
    val percentageValue: Int
        get() = when {
            value.contains("/") -> with(value.split("/")) {
                Math.round(this[0].toFloat() / this[1].toFloat() * 100)
            }
            value.contains("%") -> {
                Math.round(value.substring(0, value.lastIndex - 1).toFloat())
            }
            else -> try {
                Math.round(value.toFloat())
            } catch (e: NumberFormatException) {
                0
            }
        }
}