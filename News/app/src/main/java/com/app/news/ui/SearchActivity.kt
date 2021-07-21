package com.app.news.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.news.R
import com.app.news.adapter.MainAdapter
import com.app.news.databinding.ActivitySearchBinding
import com.app.news.model.Article
import com.app.news.model.data.NewsDataSource
import com.app.news.presenter.ViewHome
import com.app.news.presenter.search.SearchPresenter
import com.app.news.util.UtilQueryTextListener

class SearchActivity : AppCompatActivity(), ViewHome.View {

    private val mainAdapter by lazy { MainAdapter() }

    private lateinit var presenter: SearchPresenter
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataSource = NewsDataSource(this)
        presenter = SearchPresenter(this, dataSource)
        configRecycle()
        search()
        clickAdapter()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.search_menu -> {
                startActivity(Intent(this, SearchActivity::class.java))
            }
            R.id.favorite -> {
                startActivity(Intent(this, FavoriteActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun search() {
        binding.searchNews.setOnQueryTextListener(
            UtilQueryTextListener(
                this.lifecycle) { newText ->
                newText?.let { query ->
                    if (query.isNotEmpty()) {
                        presenter.search(query)
                        binding.progressBarSearch.visibility = View.VISIBLE
                    }
                }
            }
        )
    }

    private fun configRecycle() {
        with(binding.rvSearch) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(DividerItemDecoration(this@SearchActivity, DividerItemDecoration.VERTICAL))
        }
    }

    private fun clickAdapter() {
        mainAdapter.setOnClickListener { article ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)
        }
    }

    override fun showProgressBar() {
        binding.progressBarSearch.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        binding.progressBarSearch.visibility = View.INVISIBLE
    }

    override fun showArticles(article: List<Article>) {
        mainAdapter.differ.submitList(article.toList())
    }

}