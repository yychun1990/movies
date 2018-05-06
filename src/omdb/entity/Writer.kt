package omdb.entity

class Writer : MultipleEntry() {
    val roles: MutableMap<String, String> = hashMapOf()

    override fun fromString(string: String) {
        string.split(MultipleEntry.DELIMITER).forEach {
            "^([\\w ]+)([(]([\\w ]+)[)])?".toRegex().find(it)?.groupValues?.let {
                val full = it[0]
                val name = it[1].trimEnd()
                val role = it[3]
                values.add(name)
                roles[name] = role
            }
        }
    }
}

class WriterJsonDeserializer : MultipleEntryJsonDeserializer<Writer>() {
    override fun newInstance(): Writer = Writer()
}

class WriterJsonSerializer : MultipleEntryJsonSerializer<Writer>()