package com.example.growigh

import com.example.growigh.Model.NewsHeadlines

interface SelectListener {
    fun onNewsClicked(headlines: NewsHeadlines)
}