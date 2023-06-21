package com.example.kotlinflowapplication.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.kotlinflowapplication.data.model.ArticleCollect

@Dao
interface PlantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllArticleCollects(articleCollects: List<ArticleCollect>)

}