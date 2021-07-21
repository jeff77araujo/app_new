package com.app.news.presenter

import com.app.news.model.Article

interface ViewHome {

    interface View {
        fun showProgressBar()
        fun showFailure(message: String)
        fun hideProgressBar()
        fun showArticles(article: List<Article>)
    }

    interface Favorite {
        fun showArticles(article: List<Article>)
    }
}