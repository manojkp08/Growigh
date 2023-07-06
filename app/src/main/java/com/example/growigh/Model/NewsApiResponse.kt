package com.example.growigh.Model

import java.io.Serializable

//class NewsApiResponse {
//    var status: String = ""
//    var totalResults: Int = 0
//    var articles: List<NewsHeadlines>? = null
//}

data class NewsApiResponse(
    var status: String,
    var totalResults: Int,
    var articles: List<NewsHeadlines>
) : Serializable

//    fun getStatus(): String {
//        return status
//    }
//
//    fun setStatus(status: String) {
//        this.status = status
//    }
//
//    fun getTotalResults(): Int {
//        return totalResults
//    }
//
//    fun setTotalResults(totalResults: Int) {
//        this.totalResults = totalResults
//    }
//
//    fun getArticles(): List<NewsHeadlines>? {
//        return articles
//    }
//
//    fun setArticles(articles: List<NewsHeadlines>?) {
//        this.articles = articles
//    }
