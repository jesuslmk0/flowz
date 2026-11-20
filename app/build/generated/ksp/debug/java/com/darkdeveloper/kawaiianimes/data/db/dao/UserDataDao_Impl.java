package com.darkdeveloper.kawaiianimes.data.db.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.darkdeveloper.kawaiianimes.data.db.entity.DownloadedEpisodeEntity;
import com.darkdeveloper.kawaiianimes.data.db.entity.SearchedAnimeEntity;
import com.darkdeveloper.kawaiianimes.data.db.entity.UserFavoriteEntity;
import com.darkdeveloper.kawaiianimes.data.db.entity.UserWatchLaterEntity;
import com.darkdeveloper.kawaiianimes.data.db.entity.UserWatchingEntity;
import com.darkdeveloper.kawaiianimes.data.db.entity.ViewedEpisodeEntity;
import com.darkdeveloper.kawaiianimes.data.db.entity.WatchProgressEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDataDao_Impl implements UserDataDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserFavoriteEntity> __insertionAdapterOfUserFavoriteEntity;

  private final EntityInsertionAdapter<UserWatchLaterEntity> __insertionAdapterOfUserWatchLaterEntity;

  private final EntityInsertionAdapter<UserWatchingEntity> __insertionAdapterOfUserWatchingEntity;

  private final EntityInsertionAdapter<ViewedEpisodeEntity> __insertionAdapterOfViewedEpisodeEntity;

  private final EntityInsertionAdapter<WatchProgressEntity> __insertionAdapterOfWatchProgressEntity;

  private final EntityInsertionAdapter<DownloadedEpisodeEntity> __insertionAdapterOfDownloadedEpisodeEntity;

  private final EntityInsertionAdapter<SearchedAnimeEntity> __insertionAdapterOfSearchedAnimeEntity;

  private final SharedSQLiteStatement __preparedStmtOfRemoveFavorite;

  private final SharedSQLiteStatement __preparedStmtOfRemoveWatchLater;

  private final SharedSQLiteStatement __preparedStmtOfRemoveWatching;

  private final SharedSQLiteStatement __preparedStmtOfUpdateDownloadStatus;

  public UserDataDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserFavoriteEntity = new EntityInsertionAdapter<UserFavoriteEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_favorite` (`userId`,`animeId`,`addedAt`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserFavoriteEntity entity) {
        statement.bindString(1, entity.getUserId());
        statement.bindString(2, entity.getAnimeId());
        statement.bindLong(3, entity.getAddedAt());
      }
    };
    this.__insertionAdapterOfUserWatchLaterEntity = new EntityInsertionAdapter<UserWatchLaterEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_watch_later` (`userId`,`animeId`,`addedAt`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserWatchLaterEntity entity) {
        statement.bindString(1, entity.getUserId());
        statement.bindString(2, entity.getAnimeId());
        statement.bindLong(3, entity.getAddedAt());
      }
    };
    this.__insertionAdapterOfUserWatchingEntity = new EntityInsertionAdapter<UserWatchingEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `user_watching` (`userId`,`animeId`,`currentEpisode`,`progress`,`updatedAt`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserWatchingEntity entity) {
        statement.bindString(1, entity.getUserId());
        statement.bindString(2, entity.getAnimeId());
        statement.bindLong(3, entity.getCurrentEpisode());
        statement.bindDouble(4, entity.getProgress());
        statement.bindLong(5, entity.getUpdatedAt());
      }
    };
    this.__insertionAdapterOfViewedEpisodeEntity = new EntityInsertionAdapter<ViewedEpisodeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `viewed_episode` (`userId`,`episodeId`,`viewedAt`,`watchDuration`) VALUES (?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ViewedEpisodeEntity entity) {
        statement.bindString(1, entity.getUserId());
        statement.bindString(2, entity.getEpisodeId());
        statement.bindLong(3, entity.getViewedAt());
        statement.bindLong(4, entity.getWatchDuration());
      }
    };
    this.__insertionAdapterOfWatchProgressEntity = new EntityInsertionAdapter<WatchProgressEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `watch_progress` (`userId`,`animeId`,`episodeId`,`episodeNumber`,`progress`,`timestamp`) VALUES (?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final WatchProgressEntity entity) {
        statement.bindString(1, entity.getUserId());
        statement.bindString(2, entity.getAnimeId());
        statement.bindString(3, entity.getEpisodeId());
        statement.bindLong(4, entity.getEpisodeNumber());
        statement.bindDouble(5, entity.getProgress());
        statement.bindLong(6, entity.getTimestamp());
      }
    };
    this.__insertionAdapterOfDownloadedEpisodeEntity = new EntityInsertionAdapter<DownloadedEpisodeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `downloaded_episode` (`id`,`animeId`,`animeName`,`animePoster`,`episodeNumber`,`episodeId`,`language`,`serverName`,`playerId`,`downloadStatus`,`downloadProgress`,`totalBytes`,`downloadedBytes`,`localPath`,`streamUrl`,`createdAt`,`completedAt`,`lastError`,`ownerUserId`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DownloadedEpisodeEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getAnimeId());
        statement.bindString(3, entity.getAnimeName());
        statement.bindString(4, entity.getAnimePoster());
        statement.bindLong(5, entity.getEpisodeNumber());
        statement.bindString(6, entity.getEpisodeId());
        statement.bindString(7, entity.getLanguage());
        statement.bindString(8, entity.getServerName());
        statement.bindString(9, entity.getPlayerId());
        statement.bindLong(10, entity.getDownloadStatus());
        statement.bindDouble(11, entity.getDownloadProgress());
        statement.bindLong(12, entity.getTotalBytes());
        statement.bindLong(13, entity.getDownloadedBytes());
        if (entity.getLocalPath() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getLocalPath());
        }
        statement.bindString(15, entity.getStreamUrl());
        statement.bindLong(16, entity.getCreatedAt());
        if (entity.getCompletedAt() == null) {
          statement.bindNull(17);
        } else {
          statement.bindLong(17, entity.getCompletedAt());
        }
        if (entity.getLastError() == null) {
          statement.bindNull(18);
        } else {
          statement.bindString(18, entity.getLastError());
        }
        statement.bindString(19, entity.getOwnerUserId());
      }
    };
    this.__insertionAdapterOfSearchedAnimeEntity = new EntityInsertionAdapter<SearchedAnimeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `searched_anime` (`query`,`results`,`searchedAt`) VALUES (?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final SearchedAnimeEntity entity) {
        statement.bindString(1, entity.getQuery());
        statement.bindString(2, entity.getResults());
        statement.bindLong(3, entity.getSearchedAt());
      }
    };
    this.__preparedStmtOfRemoveFavorite = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM user_favorite WHERE userId = ? AND animeId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfRemoveWatchLater = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM user_watch_later WHERE userId = ? AND animeId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfRemoveWatching = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM user_watching WHERE userId = ? AND animeId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateDownloadStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE downloaded_episode SET downloadStatus = ?, downloadProgress = ?, downloadedBytes = ?, lastError = ? WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertFavorite(final UserFavoriteEntity favorite,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfUserFavoriteEntity.insertAndReturnId(favorite);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertWatchLater(final UserWatchLaterEntity watchLater,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfUserWatchLaterEntity.insertAndReturnId(watchLater);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertWatching(final UserWatchingEntity watching,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfUserWatchingEntity.insertAndReturnId(watching);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertViewedEpisode(final ViewedEpisodeEntity viewed,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfViewedEpisodeEntity.insertAndReturnId(viewed);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertWatchProgress(final WatchProgressEntity progress,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfWatchProgressEntity.insertAndReturnId(progress);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertDownloadedEpisode(final DownloadedEpisodeEntity download,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfDownloadedEpisodeEntity.insertAndReturnId(download);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertSearchedAnime(final SearchedAnimeEntity searched,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfSearchedAnimeEntity.insertAndReturnId(searched);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object removeFavorite(final String userId, final String animeId,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveFavorite.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, userId);
        _argIndex = 2;
        _stmt.bindString(_argIndex, animeId);
        try {
          __db.beginTransaction();
          try {
            final Integer _result = _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfRemoveFavorite.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object removeWatchLater(final String userId, final String animeId,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveWatchLater.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, userId);
        _argIndex = 2;
        _stmt.bindString(_argIndex, animeId);
        try {
          __db.beginTransaction();
          try {
            final Integer _result = _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfRemoveWatchLater.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object removeWatching(final String userId, final String animeId,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveWatching.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, userId);
        _argIndex = 2;
        _stmt.bindString(_argIndex, animeId);
        try {
          __db.beginTransaction();
          try {
            final Integer _result = _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfRemoveWatching.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateDownloadStatus(final String id, final int status, final float progress,
      final long bytes, final String error, final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateDownloadStatus.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, status);
        _argIndex = 2;
        _stmt.bindDouble(_argIndex, progress);
        _argIndex = 3;
        _stmt.bindLong(_argIndex, bytes);
        _argIndex = 4;
        if (error == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, error);
        }
        _argIndex = 5;
        _stmt.bindString(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            final Integer _result = _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateDownloadStatus.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getFavorites(final String userId,
      final Continuation<? super List<UserFavoriteEntity>> $completion) {
    final String _sql = "SELECT * FROM user_favorite WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UserFavoriteEntity>>() {
      @Override
      @NonNull
      public List<UserFavoriteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfAnimeId = CursorUtil.getColumnIndexOrThrow(_cursor, "animeId");
          final int _cursorIndexOfAddedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "addedAt");
          final List<UserFavoriteEntity> _result = new ArrayList<UserFavoriteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserFavoriteEntity _item;
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpAnimeId;
            _tmpAnimeId = _cursor.getString(_cursorIndexOfAnimeId);
            final long _tmpAddedAt;
            _tmpAddedAt = _cursor.getLong(_cursorIndexOfAddedAt);
            _item = new UserFavoriteEntity(_tmpUserId,_tmpAnimeId,_tmpAddedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<UserFavoriteEntity>> observeFavorites(final String userId) {
    final String _sql = "SELECT * FROM user_favorite WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"user_favorite"}, new Callable<List<UserFavoriteEntity>>() {
      @Override
      @NonNull
      public List<UserFavoriteEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfAnimeId = CursorUtil.getColumnIndexOrThrow(_cursor, "animeId");
          final int _cursorIndexOfAddedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "addedAt");
          final List<UserFavoriteEntity> _result = new ArrayList<UserFavoriteEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserFavoriteEntity _item;
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpAnimeId;
            _tmpAnimeId = _cursor.getString(_cursorIndexOfAnimeId);
            final long _tmpAddedAt;
            _tmpAddedAt = _cursor.getLong(_cursorIndexOfAddedAt);
            _item = new UserFavoriteEntity(_tmpUserId,_tmpAnimeId,_tmpAddedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getWatchLater(final String userId,
      final Continuation<? super List<UserWatchLaterEntity>> $completion) {
    final String _sql = "SELECT * FROM user_watch_later WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UserWatchLaterEntity>>() {
      @Override
      @NonNull
      public List<UserWatchLaterEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfAnimeId = CursorUtil.getColumnIndexOrThrow(_cursor, "animeId");
          final int _cursorIndexOfAddedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "addedAt");
          final List<UserWatchLaterEntity> _result = new ArrayList<UserWatchLaterEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserWatchLaterEntity _item;
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpAnimeId;
            _tmpAnimeId = _cursor.getString(_cursorIndexOfAnimeId);
            final long _tmpAddedAt;
            _tmpAddedAt = _cursor.getLong(_cursorIndexOfAddedAt);
            _item = new UserWatchLaterEntity(_tmpUserId,_tmpAnimeId,_tmpAddedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getWatching(final String userId,
      final Continuation<? super List<UserWatchingEntity>> $completion) {
    final String _sql = "SELECT * FROM user_watching WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UserWatchingEntity>>() {
      @Override
      @NonNull
      public List<UserWatchingEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfAnimeId = CursorUtil.getColumnIndexOrThrow(_cursor, "animeId");
          final int _cursorIndexOfCurrentEpisode = CursorUtil.getColumnIndexOrThrow(_cursor, "currentEpisode");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final List<UserWatchingEntity> _result = new ArrayList<UserWatchingEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserWatchingEntity _item;
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpAnimeId;
            _tmpAnimeId = _cursor.getString(_cursorIndexOfAnimeId);
            final int _tmpCurrentEpisode;
            _tmpCurrentEpisode = _cursor.getInt(_cursorIndexOfCurrentEpisode);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _item = new UserWatchingEntity(_tmpUserId,_tmpAnimeId,_tmpCurrentEpisode,_tmpProgress,_tmpUpdatedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getWatchingForAnime(final String userId, final String animeId,
      final Continuation<? super UserWatchingEntity> $completion) {
    final String _sql = "SELECT * FROM user_watching WHERE userId = ? AND animeId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    _argIndex = 2;
    _statement.bindString(_argIndex, animeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserWatchingEntity>() {
      @Override
      @Nullable
      public UserWatchingEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfAnimeId = CursorUtil.getColumnIndexOrThrow(_cursor, "animeId");
          final int _cursorIndexOfCurrentEpisode = CursorUtil.getColumnIndexOrThrow(_cursor, "currentEpisode");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final UserWatchingEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpAnimeId;
            _tmpAnimeId = _cursor.getString(_cursorIndexOfAnimeId);
            final int _tmpCurrentEpisode;
            _tmpCurrentEpisode = _cursor.getInt(_cursorIndexOfCurrentEpisode);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            _result = new UserWatchingEntity(_tmpUserId,_tmpAnimeId,_tmpCurrentEpisode,_tmpProgress,_tmpUpdatedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getViewedEpisodes(final String userId,
      final Continuation<? super List<ViewedEpisodeEntity>> $completion) {
    final String _sql = "SELECT * FROM viewed_episode WHERE userId = ? ORDER BY viewedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ViewedEpisodeEntity>>() {
      @Override
      @NonNull
      public List<ViewedEpisodeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfEpisodeId = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeId");
          final int _cursorIndexOfViewedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "viewedAt");
          final int _cursorIndexOfWatchDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "watchDuration");
          final List<ViewedEpisodeEntity> _result = new ArrayList<ViewedEpisodeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ViewedEpisodeEntity _item;
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpEpisodeId;
            _tmpEpisodeId = _cursor.getString(_cursorIndexOfEpisodeId);
            final long _tmpViewedAt;
            _tmpViewedAt = _cursor.getLong(_cursorIndexOfViewedAt);
            final long _tmpWatchDuration;
            _tmpWatchDuration = _cursor.getLong(_cursorIndexOfWatchDuration);
            _item = new ViewedEpisodeEntity(_tmpUserId,_tmpEpisodeId,_tmpViewedAt,_tmpWatchDuration);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getWatchProgress(final String userId, final String animeId,
      final Continuation<? super WatchProgressEntity> $completion) {
    final String _sql = "SELECT * FROM watch_progress WHERE userId = ? AND animeId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    _argIndex = 2;
    _statement.bindString(_argIndex, animeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<WatchProgressEntity>() {
      @Override
      @Nullable
      public WatchProgressEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfAnimeId = CursorUtil.getColumnIndexOrThrow(_cursor, "animeId");
          final int _cursorIndexOfEpisodeId = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeId");
          final int _cursorIndexOfEpisodeNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeNumber");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final WatchProgressEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpAnimeId;
            _tmpAnimeId = _cursor.getString(_cursorIndexOfAnimeId);
            final String _tmpEpisodeId;
            _tmpEpisodeId = _cursor.getString(_cursorIndexOfEpisodeId);
            final int _tmpEpisodeNumber;
            _tmpEpisodeNumber = _cursor.getInt(_cursorIndexOfEpisodeNumber);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _result = new WatchProgressEntity(_tmpUserId,_tmpAnimeId,_tmpEpisodeId,_tmpEpisodeNumber,_tmpProgress,_tmpTimestamp);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllWatchProgress(final String userId,
      final Continuation<? super List<WatchProgressEntity>> $completion) {
    final String _sql = "SELECT * FROM watch_progress WHERE userId = ? ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<WatchProgressEntity>>() {
      @Override
      @NonNull
      public List<WatchProgressEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfAnimeId = CursorUtil.getColumnIndexOrThrow(_cursor, "animeId");
          final int _cursorIndexOfEpisodeId = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeId");
          final int _cursorIndexOfEpisodeNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeNumber");
          final int _cursorIndexOfProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "progress");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<WatchProgressEntity> _result = new ArrayList<WatchProgressEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final WatchProgressEntity _item;
            final String _tmpUserId;
            _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            final String _tmpAnimeId;
            _tmpAnimeId = _cursor.getString(_cursorIndexOfAnimeId);
            final String _tmpEpisodeId;
            _tmpEpisodeId = _cursor.getString(_cursorIndexOfEpisodeId);
            final int _tmpEpisodeNumber;
            _tmpEpisodeNumber = _cursor.getInt(_cursorIndexOfEpisodeNumber);
            final float _tmpProgress;
            _tmpProgress = _cursor.getFloat(_cursorIndexOfProgress);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item = new WatchProgressEntity(_tmpUserId,_tmpAnimeId,_tmpEpisodeId,_tmpEpisodeNumber,_tmpProgress,_tmpTimestamp);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getDownloadedEpisodes(final String userId,
      final Continuation<? super List<DownloadedEpisodeEntity>> $completion) {
    final String _sql = "SELECT * FROM downloaded_episode WHERE ownerUserId = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DownloadedEpisodeEntity>>() {
      @Override
      @NonNull
      public List<DownloadedEpisodeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAnimeId = CursorUtil.getColumnIndexOrThrow(_cursor, "animeId");
          final int _cursorIndexOfAnimeName = CursorUtil.getColumnIndexOrThrow(_cursor, "animeName");
          final int _cursorIndexOfAnimePoster = CursorUtil.getColumnIndexOrThrow(_cursor, "animePoster");
          final int _cursorIndexOfEpisodeNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeNumber");
          final int _cursorIndexOfEpisodeId = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeId");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfServerName = CursorUtil.getColumnIndexOrThrow(_cursor, "serverName");
          final int _cursorIndexOfPlayerId = CursorUtil.getColumnIndexOrThrow(_cursor, "playerId");
          final int _cursorIndexOfDownloadStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadStatus");
          final int _cursorIndexOfDownloadProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadProgress");
          final int _cursorIndexOfTotalBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "totalBytes");
          final int _cursorIndexOfDownloadedBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadedBytes");
          final int _cursorIndexOfLocalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "localPath");
          final int _cursorIndexOfStreamUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "streamUrl");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfLastError = CursorUtil.getColumnIndexOrThrow(_cursor, "lastError");
          final int _cursorIndexOfOwnerUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerUserId");
          final List<DownloadedEpisodeEntity> _result = new ArrayList<DownloadedEpisodeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DownloadedEpisodeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpAnimeId;
            _tmpAnimeId = _cursor.getString(_cursorIndexOfAnimeId);
            final String _tmpAnimeName;
            _tmpAnimeName = _cursor.getString(_cursorIndexOfAnimeName);
            final String _tmpAnimePoster;
            _tmpAnimePoster = _cursor.getString(_cursorIndexOfAnimePoster);
            final int _tmpEpisodeNumber;
            _tmpEpisodeNumber = _cursor.getInt(_cursorIndexOfEpisodeNumber);
            final String _tmpEpisodeId;
            _tmpEpisodeId = _cursor.getString(_cursorIndexOfEpisodeId);
            final String _tmpLanguage;
            _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            final String _tmpServerName;
            _tmpServerName = _cursor.getString(_cursorIndexOfServerName);
            final String _tmpPlayerId;
            _tmpPlayerId = _cursor.getString(_cursorIndexOfPlayerId);
            final int _tmpDownloadStatus;
            _tmpDownloadStatus = _cursor.getInt(_cursorIndexOfDownloadStatus);
            final float _tmpDownloadProgress;
            _tmpDownloadProgress = _cursor.getFloat(_cursorIndexOfDownloadProgress);
            final long _tmpTotalBytes;
            _tmpTotalBytes = _cursor.getLong(_cursorIndexOfTotalBytes);
            final long _tmpDownloadedBytes;
            _tmpDownloadedBytes = _cursor.getLong(_cursorIndexOfDownloadedBytes);
            final String _tmpLocalPath;
            if (_cursor.isNull(_cursorIndexOfLocalPath)) {
              _tmpLocalPath = null;
            } else {
              _tmpLocalPath = _cursor.getString(_cursorIndexOfLocalPath);
            }
            final String _tmpStreamUrl;
            _tmpStreamUrl = _cursor.getString(_cursorIndexOfStreamUrl);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final String _tmpLastError;
            if (_cursor.isNull(_cursorIndexOfLastError)) {
              _tmpLastError = null;
            } else {
              _tmpLastError = _cursor.getString(_cursorIndexOfLastError);
            }
            final String _tmpOwnerUserId;
            _tmpOwnerUserId = _cursor.getString(_cursorIndexOfOwnerUserId);
            _item = new DownloadedEpisodeEntity(_tmpId,_tmpAnimeId,_tmpAnimeName,_tmpAnimePoster,_tmpEpisodeNumber,_tmpEpisodeId,_tmpLanguage,_tmpServerName,_tmpPlayerId,_tmpDownloadStatus,_tmpDownloadProgress,_tmpTotalBytes,_tmpDownloadedBytes,_tmpLocalPath,_tmpStreamUrl,_tmpCreatedAt,_tmpCompletedAt,_tmpLastError,_tmpOwnerUserId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getActiveDownloads(final String userId,
      final Continuation<? super List<DownloadedEpisodeEntity>> $completion) {
    final String _sql = "SELECT * FROM downloaded_episode WHERE ownerUserId = ? AND downloadStatus != 2";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<DownloadedEpisodeEntity>>() {
      @Override
      @NonNull
      public List<DownloadedEpisodeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAnimeId = CursorUtil.getColumnIndexOrThrow(_cursor, "animeId");
          final int _cursorIndexOfAnimeName = CursorUtil.getColumnIndexOrThrow(_cursor, "animeName");
          final int _cursorIndexOfAnimePoster = CursorUtil.getColumnIndexOrThrow(_cursor, "animePoster");
          final int _cursorIndexOfEpisodeNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeNumber");
          final int _cursorIndexOfEpisodeId = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeId");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfServerName = CursorUtil.getColumnIndexOrThrow(_cursor, "serverName");
          final int _cursorIndexOfPlayerId = CursorUtil.getColumnIndexOrThrow(_cursor, "playerId");
          final int _cursorIndexOfDownloadStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadStatus");
          final int _cursorIndexOfDownloadProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadProgress");
          final int _cursorIndexOfTotalBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "totalBytes");
          final int _cursorIndexOfDownloadedBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadedBytes");
          final int _cursorIndexOfLocalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "localPath");
          final int _cursorIndexOfStreamUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "streamUrl");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfLastError = CursorUtil.getColumnIndexOrThrow(_cursor, "lastError");
          final int _cursorIndexOfOwnerUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerUserId");
          final List<DownloadedEpisodeEntity> _result = new ArrayList<DownloadedEpisodeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final DownloadedEpisodeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpAnimeId;
            _tmpAnimeId = _cursor.getString(_cursorIndexOfAnimeId);
            final String _tmpAnimeName;
            _tmpAnimeName = _cursor.getString(_cursorIndexOfAnimeName);
            final String _tmpAnimePoster;
            _tmpAnimePoster = _cursor.getString(_cursorIndexOfAnimePoster);
            final int _tmpEpisodeNumber;
            _tmpEpisodeNumber = _cursor.getInt(_cursorIndexOfEpisodeNumber);
            final String _tmpEpisodeId;
            _tmpEpisodeId = _cursor.getString(_cursorIndexOfEpisodeId);
            final String _tmpLanguage;
            _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            final String _tmpServerName;
            _tmpServerName = _cursor.getString(_cursorIndexOfServerName);
            final String _tmpPlayerId;
            _tmpPlayerId = _cursor.getString(_cursorIndexOfPlayerId);
            final int _tmpDownloadStatus;
            _tmpDownloadStatus = _cursor.getInt(_cursorIndexOfDownloadStatus);
            final float _tmpDownloadProgress;
            _tmpDownloadProgress = _cursor.getFloat(_cursorIndexOfDownloadProgress);
            final long _tmpTotalBytes;
            _tmpTotalBytes = _cursor.getLong(_cursorIndexOfTotalBytes);
            final long _tmpDownloadedBytes;
            _tmpDownloadedBytes = _cursor.getLong(_cursorIndexOfDownloadedBytes);
            final String _tmpLocalPath;
            if (_cursor.isNull(_cursorIndexOfLocalPath)) {
              _tmpLocalPath = null;
            } else {
              _tmpLocalPath = _cursor.getString(_cursorIndexOfLocalPath);
            }
            final String _tmpStreamUrl;
            _tmpStreamUrl = _cursor.getString(_cursorIndexOfStreamUrl);
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
            }
            final String _tmpLastError;
            if (_cursor.isNull(_cursorIndexOfLastError)) {
              _tmpLastError = null;
            } else {
              _tmpLastError = _cursor.getString(_cursorIndexOfLastError);
            }
            final String _tmpOwnerUserId;
            _tmpOwnerUserId = _cursor.getString(_cursorIndexOfOwnerUserId);
            _item = new DownloadedEpisodeEntity(_tmpId,_tmpAnimeId,_tmpAnimeName,_tmpAnimePoster,_tmpEpisodeNumber,_tmpEpisodeId,_tmpLanguage,_tmpServerName,_tmpPlayerId,_tmpDownloadStatus,_tmpDownloadProgress,_tmpTotalBytes,_tmpDownloadedBytes,_tmpLocalPath,_tmpStreamUrl,_tmpCreatedAt,_tmpCompletedAt,_tmpLastError,_tmpOwnerUserId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getRecentSearches(final int limit,
      final Continuation<? super List<SearchedAnimeEntity>> $completion) {
    final String _sql = "SELECT * FROM searched_anime ORDER BY searchedAt DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<SearchedAnimeEntity>>() {
      @Override
      @NonNull
      public List<SearchedAnimeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfQuery = CursorUtil.getColumnIndexOrThrow(_cursor, "query");
          final int _cursorIndexOfResults = CursorUtil.getColumnIndexOrThrow(_cursor, "results");
          final int _cursorIndexOfSearchedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "searchedAt");
          final List<SearchedAnimeEntity> _result = new ArrayList<SearchedAnimeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final SearchedAnimeEntity _item;
            final String _tmpQuery;
            _tmpQuery = _cursor.getString(_cursorIndexOfQuery);
            final String _tmpResults;
            _tmpResults = _cursor.getString(_cursorIndexOfResults);
            final long _tmpSearchedAt;
            _tmpSearchedAt = _cursor.getLong(_cursorIndexOfSearchedAt);
            _item = new SearchedAnimeEntity(_tmpQuery,_tmpResults,_tmpSearchedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
