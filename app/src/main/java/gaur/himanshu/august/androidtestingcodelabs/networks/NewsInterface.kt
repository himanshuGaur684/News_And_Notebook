package gaur.himanshu.august.androidtestingcodelabs.networks

import gaur.himanshu.august.androidtestingcodelabs.networks.response.Article
import gaur.himanshu.august.androidtestingcodelabs.networks.response.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsInterface {


    @GET("top-headlines")
    suspend fun getAllNews(
        @Query("country") country:String,
        @Query("category") category:String,
        @Query("apiKey") apiKey:String,
        @Query("pageNumber") pageNumber:Int
    ):Response<NewsApiResponse>


}