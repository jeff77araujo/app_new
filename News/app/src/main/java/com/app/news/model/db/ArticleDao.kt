package com.app.news.model.db

import androidx.room.*
import com.app.news.model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateInsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAll(): List<Article>

    @Delete
    suspend fun delete(article: Article)
}