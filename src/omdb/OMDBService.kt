package omdb

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import omdb.entity.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDBService {
    companion object {
        const val DOMAIN = "http://www.omdbapi.com"
        const val OMDB_DATE_FORMAT = "dd MMM yyyy"
        private const val API_KEY_NAME = "apikey"
        private const val API_KEY_VALUE = "b588dfa3"

        val OMDBGson: Gson by lazy {
            GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .setDateFormat(OMDB_DATE_FORMAT)

                    // deserializer
                    .registerTypeAdapter(Director::class.java, DirectorJsonDeserializer())
                    .registerTypeAdapter(Writer::class.java, WriterJsonDeserializer())
                    .registerTypeAdapter(Actors::class.java, ActorsJsonDeserializer())
                    .registerTypeAdapter(Genre::class.java, GenreJsonDeserializer())
                    .registerTypeAdapter(Awards::class.java, AwardsJsonDeserializer())
                    .registerTypeAdapter(OMDBRatingSource::class.java, OMDBRatingSourceJsonDeserializer())
                    .registerTypeAdapter(OMDBRecord::class.java, OMDBRecordJsonDeserializer())

                    // serializer
                    .registerTypeAdapter(Director::class.java, DirectorJsonSerializer())
                    .registerTypeAdapter(Writer::class.java, WriterJsonSerializer())
                    .registerTypeAdapter(Actors::class.java, ActorsJsonSerializer())
                    .registerTypeAdapter(Genre::class.java, GenreJsonSerializer())
                    .registerTypeAdapter(Awards::class.java, AwardsJsonSerializer())
                    .registerTypeAdapter(OMDBRatingSource::class.java, OMDBRatingSourceJsonSerializer())
                    .create()
        }

        val service: OMDBService
            get() = Retrofit.Builder()
                    .baseUrl(DOMAIN)
                    .client(myOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(OMDBGson))
                    .build()
                    .create(OMDBService::class.java)

        private fun myOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                    .addInterceptor {
                        val request = it.request()
                        val newRequest = request.newBuilder().url(
                                request.url().newBuilder()
                                        .addQueryParameter(API_KEY_NAME, API_KEY_VALUE)
                                        .build()
                        ).build()
                        it.proceed(newRequest)
                    }.build()
        }
    }

    @GET("/")
    fun byID(@Query("i") imdbID: String,
             @Query("tomatoes") tomatoes: Boolean = true): Call<OMDBRecord>

    @GET("/")
    fun byTitle(@Query("t") title: String,
                @Query("tomatoes") tomatoes: Boolean = true): Call<OMDBRecord>

    @GET("/")
    fun bySearch(@Query("s") titleQuery: String,
                 @Query("page") page: Int = 1,
                 @Query("type") type: OMDBRecordType = OMDBRecordType.movie,
                 @Query("y") year: String = "2018"
    ): Call<OMDBSearchResult>
}