package com.darkdeveloper.kawaiianimes.data.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.darkdeveloper.kawaiianimes.data.db.dao.AnimeDao;
import com.darkdeveloper.kawaiianimes.data.db.dao.AnimeDao_Impl;
import com.darkdeveloper.kawaiianimes.data.db.dao.EpisodeDao;
import com.darkdeveloper.kawaiianimes.data.db.dao.EpisodeDao_Impl;
import com.darkdeveloper.kawaiianimes.data.db.dao.PlayerDao;
import com.darkdeveloper.kawaiianimes.data.db.dao.PlayerDao_Impl;
import com.darkdeveloper.kawaiianimes.data.db.dao.ServerDao;
import com.darkdeveloper.kawaiianimes.data.db.dao.ServerDao_Impl;
import com.darkdeveloper.kawaiianimes.data.db.dao.UserDataDao;
import com.darkdeveloper.kawaiianimes.data.db.dao.UserDataDao_Impl;
import com.darkdeveloper.kawaiianimes.data.db.query.AnimeQueries;
import com.darkdeveloper.kawaiianimes.data.db.query.AnimeQueries_Impl;
import com.darkdeveloper.kawaiianimes.data.db.query.EpisodeQueries;
import com.darkdeveloper.kawaiianimes.data.db.query.EpisodeQueries_Impl;
import com.darkdeveloper.kawaiianimes.data.db.query.PlayerQueries;
import com.darkdeveloper.kawaiianimes.data.db.query.PlayerQueries_Impl;
import com.darkdeveloper.kawaiianimes.data.db.query.UserDataQueries;
import com.darkdeveloper.kawaiianimes.data.db.query.UserDataQueries_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class KawaiiDatabase_Impl extends KawaiiDatabase {
  private volatile AnimeDao _animeDao;

  private volatile EpisodeDao _episodeDao;

  private volatile ServerDao _serverDao;

  private volatile PlayerDao _playerDao;

  private volatile UserDataDao _userDataDao;

  private volatile AnimeQueries _animeQueries;

  private volatile EpisodeQueries _episodeQueries;

  private volatile PlayerQueries _playerQueries;

  private volatile UserDataQueries _userDataQueries;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `anime` (`id` TEXT NOT NULL, `name` TEXT NOT NULL, `nameAlternative` TEXT, `poster` TEXT NOT NULL, `banner` TEXT, `overview` TEXT, `aired` TEXT, `type` TEXT NOT NULL, `status` TEXT NOT NULL, `genres` TEXT NOT NULL, `rating` REAL NOT NULL, `voteAverage` REAL NOT NULL, `season` TEXT, `broadcast` TEXT, `prequel` TEXT, `sequel` TEXT, `related` TEXT, `views` INTEGER NOT NULL, `updatedAt` INTEGER NOT NULL, `languages` TEXT NOT NULL, `isFavorite` INTEGER NOT NULL, `isWatchLater` INTEGER NOT NULL, `lastViewedAt` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_anime_name` ON `anime` (`name`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_anime_status` ON `anime` (`status`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_anime_type` ON `anime` (`type`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_anime_updatedAt` ON `anime` (`updatedAt`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `episode` (`id` TEXT NOT NULL, `animeId` TEXT NOT NULL, `number` INTEGER NOT NULL, `title` TEXT, `overview` TEXT, `aired` TEXT, `duration` INTEGER, `thumbnail` TEXT, `filler` INTEGER NOT NULL, `languages` TEXT NOT NULL, `servers` TEXT NOT NULL, `isDownloaded` INTEGER NOT NULL, `downloadProgress` REAL NOT NULL, `localPath` TEXT, PRIMARY KEY(`id`), FOREIGN KEY(`animeId`) REFERENCES `anime`(`id`) ON UPDATE NO ACTION ON DELETE CASCADE )");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_episode_animeId` ON `episode` (`animeId`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_episode_number` ON `episode` (`number`)");
        db.execSQL("CREATE INDEX IF NOT EXISTS `index_episode_languages` ON `episode` (`languages`)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `server` (`id` TEXT NOT NULL, `episodeId` TEXT NOT NULL, `name` TEXT NOT NULL, `type` INTEGER NOT NULL, `embedUrl` TEXT NOT NULL, `quality` INTEGER, `language` TEXT NOT NULL, `status` INTEGER NOT NULL, `isPremium` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `player` (`id` TEXT NOT NULL, `episodeId` TEXT NOT NULL, `embedUrl` TEXT NOT NULL, `referer` TEXT NOT NULL, `language` TEXT NOT NULL, `videoUrl` TEXT, `servers` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_favorite` (`userId` TEXT NOT NULL, `animeId` TEXT NOT NULL, `addedAt` INTEGER NOT NULL, PRIMARY KEY(`userId`, `animeId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_watch_later` (`userId` TEXT NOT NULL, `animeId` TEXT NOT NULL, `addedAt` INTEGER NOT NULL, PRIMARY KEY(`userId`, `animeId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `user_watching` (`userId` TEXT NOT NULL, `animeId` TEXT NOT NULL, `currentEpisode` INTEGER NOT NULL, `progress` REAL NOT NULL, `updatedAt` INTEGER NOT NULL, PRIMARY KEY(`userId`, `animeId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `viewed_episode` (`userId` TEXT NOT NULL, `episodeId` TEXT NOT NULL, `viewedAt` INTEGER NOT NULL, `watchDuration` INTEGER NOT NULL, PRIMARY KEY(`userId`, `episodeId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `watch_progress` (`userId` TEXT NOT NULL, `animeId` TEXT NOT NULL, `episodeId` TEXT NOT NULL, `episodeNumber` INTEGER NOT NULL, `progress` REAL NOT NULL, `timestamp` INTEGER NOT NULL, PRIMARY KEY(`userId`, `animeId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `searched_anime` (`query` TEXT NOT NULL, `results` TEXT NOT NULL, `searchedAt` INTEGER NOT NULL, PRIMARY KEY(`query`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `downloaded_episode` (`id` TEXT NOT NULL, `animeId` TEXT NOT NULL, `animeName` TEXT NOT NULL, `animePoster` TEXT NOT NULL, `episodeNumber` INTEGER NOT NULL, `episodeId` TEXT NOT NULL, `language` TEXT NOT NULL, `serverName` TEXT NOT NULL, `playerId` TEXT NOT NULL, `downloadStatus` INTEGER NOT NULL, `downloadProgress` REAL NOT NULL, `totalBytes` INTEGER NOT NULL, `downloadedBytes` INTEGER NOT NULL, `localPath` TEXT, `streamUrl` TEXT NOT NULL, `createdAt` INTEGER NOT NULL, `completedAt` INTEGER, `lastError` TEXT, `ownerUserId` TEXT NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '14b0ad9150da4f182104c5be2d0a64b9')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `anime`");
        db.execSQL("DROP TABLE IF EXISTS `episode`");
        db.execSQL("DROP TABLE IF EXISTS `server`");
        db.execSQL("DROP TABLE IF EXISTS `player`");
        db.execSQL("DROP TABLE IF EXISTS `user_favorite`");
        db.execSQL("DROP TABLE IF EXISTS `user_watch_later`");
        db.execSQL("DROP TABLE IF EXISTS `user_watching`");
        db.execSQL("DROP TABLE IF EXISTS `viewed_episode`");
        db.execSQL("DROP TABLE IF EXISTS `watch_progress`");
        db.execSQL("DROP TABLE IF EXISTS `searched_anime`");
        db.execSQL("DROP TABLE IF EXISTS `downloaded_episode`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        db.execSQL("PRAGMA foreign_keys = ON");
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsAnime = new HashMap<String, TableInfo.Column>(23);
        _columnsAnime.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("nameAlternative", new TableInfo.Column("nameAlternative", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("poster", new TableInfo.Column("poster", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("banner", new TableInfo.Column("banner", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("overview", new TableInfo.Column("overview", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("aired", new TableInfo.Column("aired", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("genres", new TableInfo.Column("genres", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("rating", new TableInfo.Column("rating", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("voteAverage", new TableInfo.Column("voteAverage", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("season", new TableInfo.Column("season", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("broadcast", new TableInfo.Column("broadcast", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("prequel", new TableInfo.Column("prequel", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("sequel", new TableInfo.Column("sequel", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("related", new TableInfo.Column("related", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("views", new TableInfo.Column("views", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("languages", new TableInfo.Column("languages", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("isFavorite", new TableInfo.Column("isFavorite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("isWatchLater", new TableInfo.Column("isWatchLater", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsAnime.put("lastViewedAt", new TableInfo.Column("lastViewedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAnime = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAnime = new HashSet<TableInfo.Index>(4);
        _indicesAnime.add(new TableInfo.Index("index_anime_name", false, Arrays.asList("name"), Arrays.asList("ASC")));
        _indicesAnime.add(new TableInfo.Index("index_anime_status", false, Arrays.asList("status"), Arrays.asList("ASC")));
        _indicesAnime.add(new TableInfo.Index("index_anime_type", false, Arrays.asList("type"), Arrays.asList("ASC")));
        _indicesAnime.add(new TableInfo.Index("index_anime_updatedAt", false, Arrays.asList("updatedAt"), Arrays.asList("ASC")));
        final TableInfo _infoAnime = new TableInfo("anime", _columnsAnime, _foreignKeysAnime, _indicesAnime);
        final TableInfo _existingAnime = TableInfo.read(db, "anime");
        if (!_infoAnime.equals(_existingAnime)) {
          return new RoomOpenHelper.ValidationResult(false, "anime(com.darkdeveloper.kawaiianimes.data.db.entity.AnimeEntity).\n"
                  + " Expected:\n" + _infoAnime + "\n"
                  + " Found:\n" + _existingAnime);
        }
        final HashMap<String, TableInfo.Column> _columnsEpisode = new HashMap<String, TableInfo.Column>(14);
        _columnsEpisode.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEpisode.put("animeId", new TableInfo.Column("animeId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEpisode.put("number", new TableInfo.Column("number", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEpisode.put("title", new TableInfo.Column("title", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEpisode.put("overview", new TableInfo.Column("overview", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEpisode.put("aired", new TableInfo.Column("aired", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEpisode.put("duration", new TableInfo.Column("duration", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEpisode.put("thumbnail", new TableInfo.Column("thumbnail", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEpisode.put("filler", new TableInfo.Column("filler", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEpisode.put("languages", new TableInfo.Column("languages", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEpisode.put("servers", new TableInfo.Column("servers", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEpisode.put("isDownloaded", new TableInfo.Column("isDownloaded", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEpisode.put("downloadProgress", new TableInfo.Column("downloadProgress", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsEpisode.put("localPath", new TableInfo.Column("localPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysEpisode = new HashSet<TableInfo.ForeignKey>(1);
        _foreignKeysEpisode.add(new TableInfo.ForeignKey("anime", "CASCADE", "NO ACTION", Arrays.asList("animeId"), Arrays.asList("id")));
        final HashSet<TableInfo.Index> _indicesEpisode = new HashSet<TableInfo.Index>(3);
        _indicesEpisode.add(new TableInfo.Index("index_episode_animeId", false, Arrays.asList("animeId"), Arrays.asList("ASC")));
        _indicesEpisode.add(new TableInfo.Index("index_episode_number", false, Arrays.asList("number"), Arrays.asList("ASC")));
        _indicesEpisode.add(new TableInfo.Index("index_episode_languages", false, Arrays.asList("languages"), Arrays.asList("ASC")));
        final TableInfo _infoEpisode = new TableInfo("episode", _columnsEpisode, _foreignKeysEpisode, _indicesEpisode);
        final TableInfo _existingEpisode = TableInfo.read(db, "episode");
        if (!_infoEpisode.equals(_existingEpisode)) {
          return new RoomOpenHelper.ValidationResult(false, "episode(com.darkdeveloper.kawaiianimes.data.db.entity.EpisodeEntity).\n"
                  + " Expected:\n" + _infoEpisode + "\n"
                  + " Found:\n" + _existingEpisode);
        }
        final HashMap<String, TableInfo.Column> _columnsServer = new HashMap<String, TableInfo.Column>(9);
        _columnsServer.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServer.put("episodeId", new TableInfo.Column("episodeId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServer.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServer.put("type", new TableInfo.Column("type", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServer.put("embedUrl", new TableInfo.Column("embedUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServer.put("quality", new TableInfo.Column("quality", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServer.put("language", new TableInfo.Column("language", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServer.put("status", new TableInfo.Column("status", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsServer.put("isPremium", new TableInfo.Column("isPremium", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysServer = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesServer = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoServer = new TableInfo("server", _columnsServer, _foreignKeysServer, _indicesServer);
        final TableInfo _existingServer = TableInfo.read(db, "server");
        if (!_infoServer.equals(_existingServer)) {
          return new RoomOpenHelper.ValidationResult(false, "server(com.darkdeveloper.kawaiianimes.data.db.entity.ServerEntity).\n"
                  + " Expected:\n" + _infoServer + "\n"
                  + " Found:\n" + _existingServer);
        }
        final HashMap<String, TableInfo.Column> _columnsPlayer = new HashMap<String, TableInfo.Column>(7);
        _columnsPlayer.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayer.put("episodeId", new TableInfo.Column("episodeId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayer.put("embedUrl", new TableInfo.Column("embedUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayer.put("referer", new TableInfo.Column("referer", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayer.put("language", new TableInfo.Column("language", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayer.put("videoUrl", new TableInfo.Column("videoUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPlayer.put("servers", new TableInfo.Column("servers", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPlayer = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPlayer = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPlayer = new TableInfo("player", _columnsPlayer, _foreignKeysPlayer, _indicesPlayer);
        final TableInfo _existingPlayer = TableInfo.read(db, "player");
        if (!_infoPlayer.equals(_existingPlayer)) {
          return new RoomOpenHelper.ValidationResult(false, "player(com.darkdeveloper.kawaiianimes.data.db.entity.PlayerEntity).\n"
                  + " Expected:\n" + _infoPlayer + "\n"
                  + " Found:\n" + _existingPlayer);
        }
        final HashMap<String, TableInfo.Column> _columnsUserFavorite = new HashMap<String, TableInfo.Column>(3);
        _columnsUserFavorite.put("userId", new TableInfo.Column("userId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserFavorite.put("animeId", new TableInfo.Column("animeId", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserFavorite.put("addedAt", new TableInfo.Column("addedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserFavorite = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserFavorite = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserFavorite = new TableInfo("user_favorite", _columnsUserFavorite, _foreignKeysUserFavorite, _indicesUserFavorite);
        final TableInfo _existingUserFavorite = TableInfo.read(db, "user_favorite");
        if (!_infoUserFavorite.equals(_existingUserFavorite)) {
          return new RoomOpenHelper.ValidationResult(false, "user_favorite(com.darkdeveloper.kawaiianimes.data.db.entity.UserFavoriteEntity).\n"
                  + " Expected:\n" + _infoUserFavorite + "\n"
                  + " Found:\n" + _existingUserFavorite);
        }
        final HashMap<String, TableInfo.Column> _columnsUserWatchLater = new HashMap<String, TableInfo.Column>(3);
        _columnsUserWatchLater.put("userId", new TableInfo.Column("userId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserWatchLater.put("animeId", new TableInfo.Column("animeId", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserWatchLater.put("addedAt", new TableInfo.Column("addedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserWatchLater = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserWatchLater = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserWatchLater = new TableInfo("user_watch_later", _columnsUserWatchLater, _foreignKeysUserWatchLater, _indicesUserWatchLater);
        final TableInfo _existingUserWatchLater = TableInfo.read(db, "user_watch_later");
        if (!_infoUserWatchLater.equals(_existingUserWatchLater)) {
          return new RoomOpenHelper.ValidationResult(false, "user_watch_later(com.darkdeveloper.kawaiianimes.data.db.entity.UserWatchLaterEntity).\n"
                  + " Expected:\n" + _infoUserWatchLater + "\n"
                  + " Found:\n" + _existingUserWatchLater);
        }
        final HashMap<String, TableInfo.Column> _columnsUserWatching = new HashMap<String, TableInfo.Column>(5);
        _columnsUserWatching.put("userId", new TableInfo.Column("userId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserWatching.put("animeId", new TableInfo.Column("animeId", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserWatching.put("currentEpisode", new TableInfo.Column("currentEpisode", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserWatching.put("progress", new TableInfo.Column("progress", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsUserWatching.put("updatedAt", new TableInfo.Column("updatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysUserWatching = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesUserWatching = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoUserWatching = new TableInfo("user_watching", _columnsUserWatching, _foreignKeysUserWatching, _indicesUserWatching);
        final TableInfo _existingUserWatching = TableInfo.read(db, "user_watching");
        if (!_infoUserWatching.equals(_existingUserWatching)) {
          return new RoomOpenHelper.ValidationResult(false, "user_watching(com.darkdeveloper.kawaiianimes.data.db.entity.UserWatchingEntity).\n"
                  + " Expected:\n" + _infoUserWatching + "\n"
                  + " Found:\n" + _existingUserWatching);
        }
        final HashMap<String, TableInfo.Column> _columnsViewedEpisode = new HashMap<String, TableInfo.Column>(4);
        _columnsViewedEpisode.put("userId", new TableInfo.Column("userId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsViewedEpisode.put("episodeId", new TableInfo.Column("episodeId", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsViewedEpisode.put("viewedAt", new TableInfo.Column("viewedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsViewedEpisode.put("watchDuration", new TableInfo.Column("watchDuration", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysViewedEpisode = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesViewedEpisode = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoViewedEpisode = new TableInfo("viewed_episode", _columnsViewedEpisode, _foreignKeysViewedEpisode, _indicesViewedEpisode);
        final TableInfo _existingViewedEpisode = TableInfo.read(db, "viewed_episode");
        if (!_infoViewedEpisode.equals(_existingViewedEpisode)) {
          return new RoomOpenHelper.ValidationResult(false, "viewed_episode(com.darkdeveloper.kawaiianimes.data.db.entity.ViewedEpisodeEntity).\n"
                  + " Expected:\n" + _infoViewedEpisode + "\n"
                  + " Found:\n" + _existingViewedEpisode);
        }
        final HashMap<String, TableInfo.Column> _columnsWatchProgress = new HashMap<String, TableInfo.Column>(6);
        _columnsWatchProgress.put("userId", new TableInfo.Column("userId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatchProgress.put("animeId", new TableInfo.Column("animeId", "TEXT", true, 2, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatchProgress.put("episodeId", new TableInfo.Column("episodeId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatchProgress.put("episodeNumber", new TableInfo.Column("episodeNumber", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatchProgress.put("progress", new TableInfo.Column("progress", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatchProgress.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWatchProgress = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWatchProgress = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWatchProgress = new TableInfo("watch_progress", _columnsWatchProgress, _foreignKeysWatchProgress, _indicesWatchProgress);
        final TableInfo _existingWatchProgress = TableInfo.read(db, "watch_progress");
        if (!_infoWatchProgress.equals(_existingWatchProgress)) {
          return new RoomOpenHelper.ValidationResult(false, "watch_progress(com.darkdeveloper.kawaiianimes.data.db.entity.WatchProgressEntity).\n"
                  + " Expected:\n" + _infoWatchProgress + "\n"
                  + " Found:\n" + _existingWatchProgress);
        }
        final HashMap<String, TableInfo.Column> _columnsSearchedAnime = new HashMap<String, TableInfo.Column>(3);
        _columnsSearchedAnime.put("query", new TableInfo.Column("query", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSearchedAnime.put("results", new TableInfo.Column("results", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSearchedAnime.put("searchedAt", new TableInfo.Column("searchedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSearchedAnime = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSearchedAnime = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSearchedAnime = new TableInfo("searched_anime", _columnsSearchedAnime, _foreignKeysSearchedAnime, _indicesSearchedAnime);
        final TableInfo _existingSearchedAnime = TableInfo.read(db, "searched_anime");
        if (!_infoSearchedAnime.equals(_existingSearchedAnime)) {
          return new RoomOpenHelper.ValidationResult(false, "searched_anime(com.darkdeveloper.kawaiianimes.data.db.entity.SearchedAnimeEntity).\n"
                  + " Expected:\n" + _infoSearchedAnime + "\n"
                  + " Found:\n" + _existingSearchedAnime);
        }
        final HashMap<String, TableInfo.Column> _columnsDownloadedEpisode = new HashMap<String, TableInfo.Column>(19);
        _columnsDownloadedEpisode.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedEpisode.put("animeId", new TableInfo.Column("animeId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedEpisode.put("animeName", new TableInfo.Column("animeName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedEpisode.put("animePoster", new TableInfo.Column("animePoster", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedEpisode.put("episodeNumber", new TableInfo.Column("episodeNumber", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedEpisode.put("episodeId", new TableInfo.Column("episodeId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedEpisode.put("language", new TableInfo.Column("language", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedEpisode.put("serverName", new TableInfo.Column("serverName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedEpisode.put("playerId", new TableInfo.Column("playerId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedEpisode.put("downloadStatus", new TableInfo.Column("downloadStatus", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedEpisode.put("downloadProgress", new TableInfo.Column("downloadProgress", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedEpisode.put("totalBytes", new TableInfo.Column("totalBytes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedEpisode.put("downloadedBytes", new TableInfo.Column("downloadedBytes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedEpisode.put("localPath", new TableInfo.Column("localPath", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedEpisode.put("streamUrl", new TableInfo.Column("streamUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedEpisode.put("createdAt", new TableInfo.Column("createdAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedEpisode.put("completedAt", new TableInfo.Column("completedAt", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedEpisode.put("lastError", new TableInfo.Column("lastError", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDownloadedEpisode.put("ownerUserId", new TableInfo.Column("ownerUserId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDownloadedEpisode = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDownloadedEpisode = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDownloadedEpisode = new TableInfo("downloaded_episode", _columnsDownloadedEpisode, _foreignKeysDownloadedEpisode, _indicesDownloadedEpisode);
        final TableInfo _existingDownloadedEpisode = TableInfo.read(db, "downloaded_episode");
        if (!_infoDownloadedEpisode.equals(_existingDownloadedEpisode)) {
          return new RoomOpenHelper.ValidationResult(false, "downloaded_episode(com.darkdeveloper.kawaiianimes.data.db.entity.DownloadedEpisodeEntity).\n"
                  + " Expected:\n" + _infoDownloadedEpisode + "\n"
                  + " Found:\n" + _existingDownloadedEpisode);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "14b0ad9150da4f182104c5be2d0a64b9", "45cbff7915475998842816a47daa2c23");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "anime","episode","server","player","user_favorite","user_watch_later","user_watching","viewed_episode","watch_progress","searched_anime","downloaded_episode");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    final boolean _supportsDeferForeignKeys = android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP;
    try {
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = FALSE");
      }
      super.beginTransaction();
      if (_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA defer_foreign_keys = TRUE");
      }
      _db.execSQL("DELETE FROM `anime`");
      _db.execSQL("DELETE FROM `episode`");
      _db.execSQL("DELETE FROM `server`");
      _db.execSQL("DELETE FROM `player`");
      _db.execSQL("DELETE FROM `user_favorite`");
      _db.execSQL("DELETE FROM `user_watch_later`");
      _db.execSQL("DELETE FROM `user_watching`");
      _db.execSQL("DELETE FROM `viewed_episode`");
      _db.execSQL("DELETE FROM `watch_progress`");
      _db.execSQL("DELETE FROM `searched_anime`");
      _db.execSQL("DELETE FROM `downloaded_episode`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      if (!_supportsDeferForeignKeys) {
        _db.execSQL("PRAGMA foreign_keys = TRUE");
      }
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(AnimeDao.class, AnimeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(EpisodeDao.class, EpisodeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ServerDao.class, ServerDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PlayerDao.class, PlayerDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserDataDao.class, UserDataDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(AnimeQueries.class, AnimeQueries_Impl.getRequiredConverters());
    _typeConvertersMap.put(EpisodeQueries.class, EpisodeQueries_Impl.getRequiredConverters());
    _typeConvertersMap.put(PlayerQueries.class, PlayerQueries_Impl.getRequiredConverters());
    _typeConvertersMap.put(UserDataQueries.class, UserDataQueries_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public AnimeDao animeDao() {
    if (_animeDao != null) {
      return _animeDao;
    } else {
      synchronized(this) {
        if(_animeDao == null) {
          _animeDao = new AnimeDao_Impl(this);
        }
        return _animeDao;
      }
    }
  }

  @Override
  public EpisodeDao episodeDao() {
    if (_episodeDao != null) {
      return _episodeDao;
    } else {
      synchronized(this) {
        if(_episodeDao == null) {
          _episodeDao = new EpisodeDao_Impl(this);
        }
        return _episodeDao;
      }
    }
  }

  @Override
  public ServerDao serverDao() {
    if (_serverDao != null) {
      return _serverDao;
    } else {
      synchronized(this) {
        if(_serverDao == null) {
          _serverDao = new ServerDao_Impl(this);
        }
        return _serverDao;
      }
    }
  }

  @Override
  public PlayerDao playerDao() {
    if (_playerDao != null) {
      return _playerDao;
    } else {
      synchronized(this) {
        if(_playerDao == null) {
          _playerDao = new PlayerDao_Impl(this);
        }
        return _playerDao;
      }
    }
  }

  @Override
  public UserDataDao userDataDao() {
    if (_userDataDao != null) {
      return _userDataDao;
    } else {
      synchronized(this) {
        if(_userDataDao == null) {
          _userDataDao = new UserDataDao_Impl(this);
        }
        return _userDataDao;
      }
    }
  }

  @Override
  public AnimeQueries animeQueries() {
    if (_animeQueries != null) {
      return _animeQueries;
    } else {
      synchronized(this) {
        if(_animeQueries == null) {
          _animeQueries = new AnimeQueries_Impl(this);
        }
        return _animeQueries;
      }
    }
  }

  @Override
  public EpisodeQueries episodeQueries() {
    if (_episodeQueries != null) {
      return _episodeQueries;
    } else {
      synchronized(this) {
        if(_episodeQueries == null) {
          _episodeQueries = new EpisodeQueries_Impl(this);
        }
        return _episodeQueries;
      }
    }
  }

  @Override
  public PlayerQueries playerQueries() {
    if (_playerQueries != null) {
      return _playerQueries;
    } else {
      synchronized(this) {
        if(_playerQueries == null) {
          _playerQueries = new PlayerQueries_Impl(this);
        }
        return _playerQueries;
      }
    }
  }

  @Override
  public UserDataQueries userDataQueries() {
    if (_userDataQueries != null) {
      return _userDataQueries;
    } else {
      synchronized(this) {
        if(_userDataQueries == null) {
          _userDataQueries = new UserDataQueries_Impl(this);
        }
        return _userDataQueries;
      }
    }
  }
}
