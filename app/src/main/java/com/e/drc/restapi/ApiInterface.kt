package com.e.drc.restapi

import com.e.drc.model.NewsModel
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {


    @GET("top-headlines?sources=google-news&apiKey=9a0c8e375ada4198a26f7a52638c4b78")
    fun getData(): Call<NewsModel>


}
