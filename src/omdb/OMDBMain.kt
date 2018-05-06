package omdb

import excel.ExcelProperty
import omdb.entity.OMDBRecord

object OMDBMain {
    const val IS_STUB = true
    const val STUB_JSON_FOR_RECORD = "{\"Title\":\"Solo: A Star Wars Story\",\"Year\":\"2018\",\"Rated\":\"N/A\",\"Released\":\"25 May 2018\",\"Runtime\":\"N/A\",\"Genre\":\"Action, Adventure, Fantasy\",\"Director\":\"Ron Howard\",\"Writer\":\"Jon Kasdan (screenplay), Lawrence Kasdan (screenplay), George Lucas (based on characters created by)\",\"Actors\":\"Emilia Clarke, Woody Harrelson, Alden Ehrenreich, Donald Glover\",\"Plot\":\"During an adventure into a dark criminal underworld, Han Solo meets his future copilot Chewbacca and encounters Lando Calrissian years before joining the Rebellion.\",\"Language\":\"English\",\"Country\":\"USA\",\"Awards\":\"N/A\",\"Poster\":\"https://images-na.ssl-images-amazon.com/images/M/MV5BMjAwNzI3OTA5MV5BMl5BanBnXkFtZTgwMzc0MDE4NDM@._V1_SX300.jpg\",\"Ratings\":[],\"Metascore\":\"N/A\",\"imdbRating\":\"N/A\",\"imdbVotes\":\"N/A\",\"imdbID\":\"tt3778644\",\"Type\":\"movie\",\"DVD\":\"N/A\",\"BoxOffice\":\"N/A\",\"Production\":\"Walt Disney Pictures\",\"Website\":\"http://www.starwars.com/films/solo\",\"Response\":\"True\"}\n"

    private val PROPERTY by lazy {
        ExcelProperty("Movies", "OMDB")
    }
    
    @JvmStatic
    fun main(args: Array<String>) {
        val record = if (IS_STUB) {
            OMDBService.OMDBGson.fromJson(STUB_JSON_FOR_RECORD, OMDBRecord::class.java)
            null
        } else {
            OMDBService.service.byID("tt3778644").execute().body()
        }
        record?.let {
            ExcelWriter.writeExcel(PROPERTY, it)
        }
    }

    private fun searchResults() {
        val searchResult = OMDBService.service.bySearch("Man").execute().body() ?: return
        if (searchResult.response) {
            searchResult.search!!
        }
    }
}