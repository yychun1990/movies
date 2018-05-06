package omdb.entity

import com.google.gson.*
import java.lang.reflect.Type

enum class OMDBRatingSource {
    IMDB {
        override val apiSource: String = "Internet Movie Database"
    },
    ROTTEN_TOMATOES {
        override val apiSource: String = "Rotten Tomatoes"
    },
    METACRITIC {
        override val apiSource: String = "Metacritic"
    },;

    abstract val apiSource: String
}

class OMDBRatingSourceJsonDeserializer: JsonDeserializer<OMDBRatingSource> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OMDBRatingSource? {
        val jsonString = json?.asString ?: throw JsonParseException("jsonString is null when deserializing OMDBRatingSource")
        return OMDBRatingSource.values().find { it.apiSource == jsonString }
    }
}

class OMDBRatingSourceJsonSerializer: JsonSerializer<OMDBRatingSource> {
    override fun serialize(src: OMDBRatingSource?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return src?.let { JsonPrimitive(it.apiSource) } ?: throw JsonParseException("src is null when serializing OMDBRatingSource")
    }
}