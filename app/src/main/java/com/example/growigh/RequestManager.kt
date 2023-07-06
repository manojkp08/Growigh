package com.example.growigh

import android.content.Context
import android.widget.Toast
import com.example.growigh.Model.NewsApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class RequestManager(private val context: Context) {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getNewsHeadlines(listener: OnFetchDataListener<NewsApiResponse>, category: String, query: String?) {
        val callNewsApi: CallNewsApi = retrofit.create(CallNewsApi::class.java)
        val call: Call<NewsApiResponse>? =
            query?.let {
                callNewsApi.callHeadlines("us", category,
                    it, context.getString(R.string.api_key))
            }

        try {
            call?.enqueue(object : Callback<NewsApiResponse> {
                override fun onResponse(call: Call<NewsApiResponse>, response: Response<NewsApiResponse>) {
                    if (!response.isSuccessful) {
                        Toast.makeText(context, "Error!!", Toast.LENGTH_SHORT).show()
                    }

                    listener.onFetchData(response.body()?.articles ?: emptyList(), response.message())
                }

                override fun onFailure(call: Call<NewsApiResponse>, t: Throwable) {
                    listener.onError("Request Failed")
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    interface CallNewsApi {
        @GET("top-headlines")
        fun callHeadlines(
            @Query("country") country: String,
            @Query("category") category: String,
            @Query("q") query: String,
            @Query("apiKey") apiKey: String
        ): Call<NewsApiResponse>
    }
}
