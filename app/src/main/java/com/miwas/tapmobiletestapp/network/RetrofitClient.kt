package com.miwas.tapmobiletestapp.network

import com.miwas.tapmobiletestapp.network.Api.Companion.BASE_URL

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory


class RetrofitClient {

    var myApi: Api

    companion object {
        @get:Synchronized
        var instance: RetrofitClient? = null
            get() {
                if (field == null) {
                    field = RetrofitClient()
                }
                return field
            }
    }

    init {

        val retrofit: Retrofit =
            Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
        myApi = retrofit.create(Api::class.java)
    }
}
