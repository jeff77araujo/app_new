package com.app.news.presenter.favorite

import com.app.news.model.Article

interface Favorite {

    interface Presenter {
        fun onSuccess(articles: List<Article>)
    }

}