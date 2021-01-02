package gaur.himanshu.august.androidtestingcodelabs.adapters

import gaur.himanshu.august.androidtestingcodelabs.networks.response.Article

interface NewsClickListioners {


    fun newsHandler(article: Article,position:Int)

}