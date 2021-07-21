package com.app.news.presenter.news

import com.app.news.model.NewsResponse
import com.app.news.model.data.NewsDataSource
import com.app.news.presenter.ViewHome

class NewsPresenter(private val view: ViewHome.View, private val dataSource: NewsDataSource): NewsHome.Presenter {
    override fun requestAll() {
        this.view.showProgressBar()
        this.dataSource.getBreakingNews(this)
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