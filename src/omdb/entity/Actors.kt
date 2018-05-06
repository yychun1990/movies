package omdb.entity

class Actors : MultipleEntry()

class ActorsJsonDeserializer: MultipleEntryJsonDeserializer<Actors>() {
    override fun newInstance(): Actors = Actors()
}

class ActorsJsonSerializer: MultipleEntryJsonSerializer<Actors>()