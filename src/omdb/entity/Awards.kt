package omdb.entity

import com.google.gson.*
import java.lang.reflect.Type

data class Awards(
        val wins: Int,
        val nominations: Int
)

class AwardsJsonDeserializer : JsonDeserializer<Awards> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Awards? {
        val jsonString = json?.asString ?: throw JsonParseException("jsonString is null when deserializing Awards")
        return if (jsonString == OMDBRecord.VALUE_NA) {
            null
        } else {
            with("([0-9]+) win[s]? & ([0-9]+) nomination[s]?".toRegex()) {
                if (matches(jsonString)) {
                    find(jsonString)?.groupValues?.let {
                        return Awards(it[1].toInt(), it[2].toInt())
                    }
                }
            }
            with("([0-9]+) win[s]".toRegex()) {
                if (matches(jsonString)) {
                    find(jsonString)?.groupValues?.let {
                        return Awards(it[1].toInt(), 0)
                    }
                }
            }
            with("([0-9]+) nomination[s]".toRegex()) {
                if (matches(jsonString)) {
                    find(jsonString)?.groupValues?.let {
                        return Awards(0, it[1].toInt())
                    }
                }
            }
            return null
        }
    }
}

class AwardsJsonSerializer : JsonSerializer<Awards> {
    override fun serialize(src: Awards?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        val wins = src?.wins ?: 0
        val nominations = src?.nominations ?: 0
        return JsonPrimitive("$wins wins & $nominations nominations")
    }
}