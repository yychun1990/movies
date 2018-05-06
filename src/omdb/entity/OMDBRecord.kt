package omdb.entity

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import excel.ExcelData
import excel.IExcelReadWriteContract
import omdb.OMDBService
import java.lang.reflect.Type
import java.util.*

data class OMDBRecord(
        val title: String,
        val year: String,
        val rated: OMDBRated?,
        val released: Date?,
        val runtime: Int?,
        val genre: Genre,
        val director: Director,
        val writer: Writer,
        val actors: Actors,
        val plot: String?,
        val language: String,
        val country: String,
        val awards: Awards?,
        val ratings: Array<OMDBRating>,
        val imdbVotes: Double?,
        val imdbID: String,
        val type: OMDBRecordType,
        val tomatoURL: String?,
        val dvd: Date?,
        val boxOffice: Double?,
        val production: String?
) : IExcelReadWriteContract {
    companion object {
        const val VALUE_NA = "N/A"
        const val COLUMN_TITLE = "title"
        const val COLUMN_YEAR = "year"
        const val COLUMN_RATED = "rated"
        const val COLUMN_RELEASED = "released"
        const val COLUMN_RUNTIME = "runtime"
        const val COLUMN_GENRE_ACTION = "genre_action"
        const val COLUMN_GENRE_ADVENTURE = "genre_adventure"
        const val COLUMN_GENRE_ANIMATION = "genre_animation"
        const val COLUMN_GENRE_BIOGRAPHY = "genre_biography"
        const val COLUMN_GENRE_COMEDY = "genre_comedy"
        const val COLUMN_GENRE_CRIME = "genre_crime"
        const val COLUMN_GENRE_DOCUMENTARY = "genre_documentary"
        const val COLUMN_GENRE_DRAMA = "genre_drama"
        const val COLUMN_GENRE_FAMILY = "genre_family"
        const val COLUMN_GENRE_FANTASY = "genre_fantasy"
        const val COLUMN_GENRE_FILM_NOIR = "genre_film_noir"
        const val COLUMN_GENRE_HISTORY = "genre_history"
        const val COLUMN_GENRE_HORROR = "genre_horror"
        const val COLUMN_GENRE_MUSIC = "genre_music"
        const val COLUMN_GENRE_MUSICAL = "genre_musical"
        const val COLUMN_GENRE_MYSTERY = "genre_mystery"
        const val COLUMN_GENRE_ROMANCE = "genre_romance"
        const val COLUMN_GENRE_SCI_FI = "genre_sci_fi"
        const val COLUMN_GENRE_SHORT = "genre_short"
        const val COLUMN_GENRE_THRILLER = "genre_thriller"
        const val COLUMN_GENRE_WAR = "genre_war"
        const val COLUMN_GENRE_WESTERN = "genre_western"
        const val COLUMN_DIRECTOR_0 = "director_0"
        const val COLUMN_DIRECTOR_1 = "director_1"
        const val COLUMN_DIRECTOR_2 = "director_2"
        const val COLUMN_DIRECTOR_3 = "director_3"
        const val COLUMN_DIRECTOR_4 = "director_4"
        const val COLUMN_WRITER_0 = "writer_0"
        const val COLUMN_WRITER_1 = "writer_1"
        const val COLUMN_WRITER_2 = "writer_2"
        const val COLUMN_WRITER_3 = "writer_3"
        const val COLUMN_WRITER_4 = "writer_4"
        const val COLUMN_ACTOR_0 = "actor_0"
        const val COLUMN_ACTOR_1 = "actor_1"
        const val COLUMN_ACTOR_2 = "actor_2"
        const val COLUMN_ACTOR_3 = "actor_3"
        const val COLUMN_ACTOR_4 = "actor_4"
        const val COLUMN_ACTOR_5 = "actor_5"
        const val COLUMN_ACTOR_6 = "actor_6"
        const val COLUMN_ACTOR_7 = "actor_7"
        const val COLUMN_ACTOR_8 = "actor_8"
        const val COLUMN_ACTOR_9 = "actor_9"
        const val COLUMN_LANGUAGE = "language"
        const val COLUMN_COUNTRY = "country"
        const val COLUMN_AWARD_WINS = "award_wins"
        const val COLUMN_AWARD_NOMINATIONS = "award_nominations"
        const val COLUMN_RATING_IMDB = "rating_imdb"
        const val COLUMN_RATING_ROTTEN_TOMATOES = "rating_rotten_tomatoes"
        const val COLUMN_RATING_METACRITIC = "rating_metacritic"
        const val COLUMN_VOTES_IMDB = "votes_imdb"
        const val COLUMN_IMDB_ID = "imdb_id"
        const val COLUMN_TYPE = "type"
        const val COLUMN_TOMATOES_URL = "tomatoes_url"
        const val COLUMN_DVD = "dvd"
        const val COLUMN_BOX_OFFICE = "box_office"
        const val COLUMN_PRODUCTION = "production"
    }

    override val columns: Collection<String>
        get() = mutableSetOf<String>().apply {
            add(COLUMN_TITLE)
            add(COLUMN_YEAR)
            add(COLUMN_RATED)
            add(COLUMN_RELEASED)
            add(COLUMN_RUNTIME)

            add(COLUMN_GENRE_ACTION)
            add(COLUMN_GENRE_ADVENTURE)
            add(COLUMN_GENRE_ANIMATION)
            add(COLUMN_GENRE_BIOGRAPHY)
            add(COLUMN_GENRE_COMEDY)
            add(COLUMN_GENRE_CRIME)
            add(COLUMN_GENRE_DOCUMENTARY)
            add(COLUMN_GENRE_DRAMA)
            add(COLUMN_GENRE_FAMILY)
            add(COLUMN_GENRE_FANTASY)
            add(COLUMN_GENRE_FILM_NOIR)
            add(COLUMN_GENRE_HISTORY)
            add(COLUMN_GENRE_HORROR)
            add(COLUMN_GENRE_MUSIC)
            add(COLUMN_GENRE_MUSICAL)
            add(COLUMN_GENRE_MYSTERY)
            add(COLUMN_GENRE_ROMANCE)
            add(COLUMN_GENRE_SCI_FI)
            add(COLUMN_GENRE_SHORT)
            add(COLUMN_GENRE_THRILLER)
            add(COLUMN_GENRE_WAR)
            add(COLUMN_GENRE_WESTERN)

            add(COLUMN_DIRECTOR_0)
            add(COLUMN_DIRECTOR_1)
            add(COLUMN_DIRECTOR_2)
            add(COLUMN_DIRECTOR_3)
            add(COLUMN_DIRECTOR_4)

            add(COLUMN_WRITER_0)
            add(COLUMN_WRITER_1)
            add(COLUMN_WRITER_2)
            add(COLUMN_WRITER_3)
            add(COLUMN_WRITER_4)

            add(COLUMN_ACTOR_0)
            add(COLUMN_ACTOR_1)
            add(COLUMN_ACTOR_2)
            add(COLUMN_ACTOR_3)
            add(COLUMN_ACTOR_4)
            add(COLUMN_ACTOR_5)
            add(COLUMN_ACTOR_6)
            add(COLUMN_ACTOR_7)
            add(COLUMN_ACTOR_8)
            add(COLUMN_ACTOR_9)

            add(COLUMN_LANGUAGE)
            add(COLUMN_COUNTRY)

            add(COLUMN_AWARD_WINS)
            add(COLUMN_AWARD_NOMINATIONS)
            add(COLUMN_RATING_IMDB)
            add(COLUMN_RATING_ROTTEN_TOMATOES)
            add(COLUMN_RATING_METACRITIC)
            add(COLUMN_VOTES_IMDB)
            add(COLUMN_IMDB_ID)
            add(COLUMN_TYPE)
            add(COLUMN_TOMATOES_URL)
            add(COLUMN_DVD)
            add(COLUMN_BOX_OFFICE)
            add(COLUMN_PRODUCTION)
        }

    override val writeMap: Map<String, ExcelData<*>>
        get() = hashMapOf<String, ExcelData<*>>().apply {
            this[COLUMN_TITLE] = ExcelData.StringData(title)
            this[COLUMN_YEAR] = ExcelData.StringData(year)
            released?.let {
                this[COLUMN_RELEASED] = ExcelData.DateData(it)
            }
            rated?.let {
                this[COLUMN_RATED] = ExcelData.StringData(it.name)
            }
            runtime?.let {
                this[COLUMN_RUNTIME] = ExcelData.DoubleData(it)
            }
            genre.values.forEach {
                this["genre_$it"] = ExcelData.BooleanData(true)
                println("genre_$it")
            }
            director.values.take(5).withIndex().forEach {
                this["director_${it.index}"] = ExcelData.StringData(it.value)
                println("director_${it.index}")
            }
            writer.values.take(5).withIndex().forEach {
                this["writer_${it.index}"] = ExcelData.StringData(it.value)
                println("writer_${it.index}")
            }
            actors.values.take(10).withIndex().forEach {
                this["actor_${it.index}"] = ExcelData.StringData(it.value)
                println("actor_${it.index}")
            }
            this[COLUMN_LANGUAGE] = ExcelData.StringData(language)
            this[COLUMN_COUNTRY] = ExcelData.StringData(country)
            awards?.let {
                this[COLUMN_AWARD_WINS] = ExcelData.DoubleData(it.wins)
                this[COLUMN_AWARD_NOMINATIONS] = ExcelData.DoubleData(it.nominations)
            }
            ratings.forEach {
                val column = when (it.source) {
                    OMDBRatingSource.IMDB -> COLUMN_RATING_IMDB
                    OMDBRatingSource.ROTTEN_TOMATOES -> COLUMN_RATING_ROTTEN_TOMATOES
                    OMDBRatingSource.METACRITIC -> COLUMN_RATING_METACRITIC
                }
                this[column] = ExcelData.DoubleData(it.percentageValue)
            }
            imdbVotes?.let {
                this[COLUMN_VOTES_IMDB] = ExcelData.DoubleData(it)
            }
            imdbID.takeIf { it != VALUE_NA }?.let {
                this[COLUMN_IMDB_ID] = ExcelData.StringData(it)
            }
            this[COLUMN_TYPE] = ExcelData.StringData(type.name)
            tomatoURL?.let {
                this[COLUMN_TOMATOES_URL] = ExcelData.StringData(it)
            }
            dvd?.let {
                this[COLUMN_DVD] = ExcelData.DateData(it)
            }
            boxOffice?.let {
                this[COLUMN_BOX_OFFICE] = ExcelData.DoubleData(it)
            }
            production?.let {
                this[COLUMN_PRODUCTION] = ExcelData.StringData(it)
            }
        }
}

class OMDBRecordJsonDeserializer : JsonDeserializer<OMDBRecord> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): OMDBRecord {
        val jsonObject = json?.asJsonObject
                ?: throw JsonParseException("jsonObject is null when deserializing OMDBRecord")
        val title = jsonObject["Title"].asString
        val year = jsonObject["Year"].asString
        val rated = OMDBService.OMDBGson.fromJson(jsonObject["Rated"]?.takeIf { it.asString != OMDBRecord.VALUE_NA }, OMDBRated::class.java)
        val released = OMDBService.OMDBGson.fromJson(jsonObject["Released"]?.takeIf { it.asString != OMDBRecord.VALUE_NA }, Date::class.java)
        val runtime = jsonObject["Runtime"]?.asString.takeIf { it != OMDBRecord.VALUE_NA }?.let {
            "^([0-9]+ min)".toRegex().find(it)?.groupValues?.let { it[1].toInt() }
        }
        val genre = OMDBService.OMDBGson.fromJson(jsonObject["Genre"], Genre::class.java)
        val director = OMDBService.OMDBGson.fromJson(jsonObject["Director"], Director::class.java)
        val writer = OMDBService.OMDBGson.fromJson(jsonObject["Writer"], Writer::class.java)
        val actors = OMDBService.OMDBGson.fromJson(jsonObject["Actors"], Actors::class.java)
        val plot = jsonObject["Plot"]?.asString?.takeIf { it != OMDBRecord.VALUE_NA }
        val language = jsonObject["Language"].asString
        val country = jsonObject["Country"].asString
        val awards = OMDBService.OMDBGson.fromJson(jsonObject["Awards"]?.takeIf { it.asString != OMDBRecord.VALUE_NA }, Awards::class.java)
        val ratings = OMDBService.OMDBGson.fromJson(jsonObject["Ratings"], Array<OMDBRating>::class.java)
        val imdbVotes = jsonObject["imdbVotes"]?.asString?.takeIf { it != OMDBRecord.VALUE_NA }?.replace(",", "")?.toDouble()
        val imdbID = jsonObject["imdbID"].asString
        val type = OMDBService.OMDBGson.fromJson(jsonObject["Type"], OMDBRecordType::class.java)
        val tomatoURL = jsonObject["tomatoURL"]?.asString?.takeIf { it != OMDBRecord.VALUE_NA }
        val dvd = OMDBService.OMDBGson.fromJson(jsonObject["DVD"]?.takeIf { it.asString != OMDBRecord.VALUE_NA }, Date::class.java)
        val boxOffice = jsonObject["BoxOffice"]?.asString?.takeIf { it != OMDBRecord.VALUE_NA }?.removePrefix("$")?.replace(",", "")?.toDouble()
        val production = jsonObject["Production"]?.asString?.takeIf { it != OMDBRecord.VALUE_NA }

        return OMDBRecord(title, year, rated, released, runtime, genre, director, writer, actors, plot, language, country, awards, ratings, imdbVotes, imdbID, type, tomatoURL, dvd, boxOffice, production)
    }
}