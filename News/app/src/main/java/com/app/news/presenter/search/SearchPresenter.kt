package com.app.news.presenter.search

import com.app.news.model.NewsResponse
import com.app.news.model.data.NewsDataSource
import com.app.news.presenter.ViewHome

class SearchPresenter(val view: ViewHome.View, private val dataSource: NewsDataSource): SearchHome.Presenter {
    override fun search(term: String) {
        this.view.showProgressBar()
        this.dataSource.searchNews(term, this)
    }

    override fun onSuccess(newsResponse: NewsResponse) {
        this.view.showArticles(newsResponse.articles)
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }
}