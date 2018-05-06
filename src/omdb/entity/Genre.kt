package omdb.entity

class Genre : MultipleEntry() {
    override fun fromString(string: String) {
        super.fromString(string.toLowerCase())
    }
}

class GenreJsonDeserializer : MultipleEntryJsonDeserializer<Genre>() {
    override fun newInstance(): Genre = Genre()
}

class GenreJsonSerializer : MultipleEntryJsonSerializer<Genre>()