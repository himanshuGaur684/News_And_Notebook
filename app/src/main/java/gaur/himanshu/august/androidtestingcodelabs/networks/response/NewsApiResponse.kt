package gaur.himanshu.august.androidtestingcodelabs.networks.response

data class NewsApiResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)