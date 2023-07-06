package com.example.growigh

import com.example.growigh.Model.NewsHeadlines

interface OnFetchDataListener<NewsApiResponse> {
    fun onFetchData(list: List<NewsHeadlines>, message: String)
    fun onError(message: String)
}

