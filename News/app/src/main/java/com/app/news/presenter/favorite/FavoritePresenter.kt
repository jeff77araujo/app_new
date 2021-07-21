package com.app.news.presenter.favorite

import com.app.news.model.Article
import com.app.news.model.data.NewsDataSource
import com.app.news.presenter.ViewHome

class FavoritePresenter(val view: ViewHome.Favorite, private val dataSource: NewsDataSource): Favorite.Presenter {

    fun getAll() {
        this.dataSource.getAllArticle(this)
    }

    fun saveArticle(article: Article) {
        this.dataSource.saveArticle(article)
    }

    fun deleteArticle(article: Article) {
        this.dataSource.deleteArticle(article)
    }

    override fun onSuccess(articles: List<Article>) {
        this.view.showArticles(articles)
    }
}