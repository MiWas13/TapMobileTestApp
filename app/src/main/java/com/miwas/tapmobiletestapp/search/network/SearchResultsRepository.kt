package com.miwas.tapmobiletestapp.search.network

import com.miwas.tapmobiletestapp.network.RetrofitClient
import com.miwas.tapmobiletestapp.search.model.VideoItem
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.suspendCoroutine

class SearchResultsRepository() {

    //Unreal to make clean architecture in this short period of time :(
    val regexForResponse = "\"videoId\"\\s*:\\s*\"(.+?)\""
    var baseYouTubeUrl = "https://www.youtube.com/embed/"

    suspend fun getResults(request: String) = suspendCoroutine<List<VideoItem>> { continuation ->
        RetrofitClient.instance?.myApi?.let { api ->
            val call: Call<String> = api.getSearchResults(request)

            call.enqueue(object : Callback<String> {

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    response.body()?.let { responseBody ->
                        val matches = Regex(regexForResponse).findAll(responseBody)
                        val ids = matches
                            //May occure some bugs. Let's fix in next release
                            .map { it.value.substring(11, 22) }
                            .distinct()
                            .toList()

                        continuation.resumeWith(Result.success(transformToDomainModel(ids)))
                    }

                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    t.localizedMessage
                }
            })

        }
    }

    fun transformToDomainModel(listOfVideosFromResponse: List<String>) =
        listOfVideosFromResponse.take(10).map {

            val youtubeUrl = baseYouTubeUrl + it
            val frameVideo = "<html><body><iframe width=\"380\" height=\"300\" " +
                    "src='" + youtubeUrl + "' frameborder=\"0\" allowfullscreen>" +
                    "</iframe></body></html>"

            VideoItem(youtubeUrl, frameVideo)
        }

}