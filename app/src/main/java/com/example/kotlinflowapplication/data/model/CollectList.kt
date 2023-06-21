package com.example.kotlinflowapplication.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "articleCollect")
data class ArticleCollect(
    @ColumnInfo
    val author: String,
    @ColumnInfo
    val chapterId: Int,
    @ColumnInfo
    val chapterName: String,
    @ColumnInfo
    val courseId: Int,
    @ColumnInfo
    val desc: String,
    @ColumnInfo
    val envelopePic: String,
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo
    val link: String,
    @ColumnInfo
    val niceDate: String,
    @ColumnInfo
    val origin: String,
    @ColumnInfo
    val originId: Int,
    @ColumnInfo
    val publishTime: Long,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val userId: Int,
    @ColumnInfo
    val visible: Int,
    @ColumnInfo
    val zan: Int
) {
    @Ignore var position: String = ""
}