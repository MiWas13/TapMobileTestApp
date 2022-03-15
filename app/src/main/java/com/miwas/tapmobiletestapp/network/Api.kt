package com.miwas.tapmobiletestapp.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("results")
    fun getSearchResults(@Query("search_query") query: String): Call<String>

    companion object {
        const val BASE_URL = "https://www.youtube.com/"
    }
}