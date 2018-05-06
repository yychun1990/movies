package omdb.entity

import com.google.gson.annotations.SerializedName

data class OMDBMovieRequest(
        @SerializedName("i")
        val imdbMovieId: String?,
        @SerializedName("t")
        val movieTitle: String?,
        @SerializedName("type")
        val type: OMDBRecordType? = null,
        @SerializedName("y")
        val yearOfRelease: Int? = null,
        @SerializedName("plot")
        val plot: OMDBPlotType? = null,
        @SerializedName("r")
        val r: OMDBReturnDataType = OMDBReturnDataType.json
)