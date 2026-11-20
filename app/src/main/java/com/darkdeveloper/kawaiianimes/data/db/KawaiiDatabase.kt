package com.darkdeveloper.kawaiianimes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.darkdeveloper.kawaiianimes.data.db.converter.Converters
import com.darkdeveloper.kawaiianimes.data.db.dao.AnimeDao
import com.darkdeveloper.kawaiianimes.data.db.dao.EpisodeDao
import com.darkdeveloper.kawaiianimes.data.db.dao.PlayerDao
import com.darkdeveloper.kawaiianimes.data.db.dao.ServerDao
import com.darkdeveloper.kawaiianimes.data.db.dao.UserDataDao
import com.darkdeveloper.kawaiianimes.data.db.entity.AnimeEntity
import com.darkdeveloper.kawaiianimes.data.db.entity.DownloadedEpisodeEntity
import com.darkdeveloper.kawaiianimes.data.db.entity.EpisodeEntity
import com.darkdeveloper.kawaiianimes.data.db.entity.PlayerEntity
import com.darkdeveloper.kawaiianimes.data.db.entity.SearchedAnimeEntity
import com.darkdeveloper.kawaiianimes.data.db.entity.ServerEntity
import com.darkdeveloper.kawaiianimes.data.db.entity.UserFavoriteEntity
import com.darkdeveloper.kawaiianimes.data.db.entity.UserWatchLaterEntity
import com.darkdeveloper.kawaiianimes.data.db.entity.UserWatchingEntity
import com.darkdeveloper.kawaiianimes.data.db.entity.ViewedEpisodeEntity
import com.darkdeveloper.kawaiianimes.data.db.entity.WatchProgressEntity
import com.darkdeveloper.kawaiianimes.data.db.query.*

@Database(
    entities = [
        AnimeEntity::class,
        EpisodeEntity::class,
        ServerEntity::class,
        PlayerEntity::class,
        UserFavoriteEntity::class,
        UserWatchLaterEntity::class,
        UserWatchingEntity::class,
        ViewedEpisodeEntity::class,
        WatchProgressEntity::class,
        SearchedAnimeEntity::class,
        DownloadedEpisodeEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class KawaiiDatabase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun serverDao(): ServerDao
    abstract fun playerDao(): PlayerDao
    abstract fun userDataDao(): UserDataDao

    abstract fun animeQueries(): AnimeQueries
    abstract fun episodeQueries(): EpisodeQueries
    abstract fun playerQueries(): PlayerQueries
    abstract fun userDataQueries(): UserDataQueries

    companion object {
        const val DATABASE_NAME = "kawaii_database"
    }
}