package omdb.entity

class Director : MultipleEntry()

class DirectorJsonDeserializer: MultipleEntryJsonDeserializer<Director>() {
    override fun newInstance(): Director = Director()
}

class DirectorJsonSerializer: MultipleEntryJsonSerializer<Director>()