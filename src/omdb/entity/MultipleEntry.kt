package omdb.entity

import com.google.gson.*
import java.lang.reflect.Type

open class MultipleEntry {
    companion object {
        const val DELIMITER = ", "
    }

    val values: MutableCollection<String> = arrayListOf()

    open fun fromString(string: String) {
        values.addAll(string.split(DELIMITER))
    }
}

abstract class MultipleEntryJsonDeserializer<E: MultipleEntry> : JsonDeserializer<E> {
    final override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): E {
        return json?.asString?.let {
            newInstance().apply {
                fromString(it)
            }
        } ?: error("json string is unexpectedly null when deserializing ${MultipleEntry::class.java}")
    }

    abstract fun newInstance(): E
}

open class MultipleEntryJsonSerializer<E: MultipleEntry> : JsonSerializer<E> {
    override fun serialize(src: E?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
        return src?.let {
            JsonPrimitive(it.values.reduce { acc, s -> acc + MultipleEntry.DELIMITER + s })
        } ?: error("src is unexpectedly null")
    }
}