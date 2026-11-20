package com.darkdeveloper.kawaiianimes.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "searched_anime")
data class SearchedAnimeEntity(
    @PrimaryKey val query: String,
    val results: String,
    val searchedAt: Long = System.currentTimeMillis()
)