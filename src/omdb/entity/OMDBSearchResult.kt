package omdb.entity

data class OMDBSearchResult(
        val search: Array<OMDBRecord>?,
        val totalResults: Int?,
        val response: Boolean = false
)