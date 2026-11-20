package com.darkdeveloper.kawaiianimes.data.db.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.darkdeveloper.kawaiianimes.data.db.converter.Converters;
import com.darkdeveloper.kawaiianimes.data.db.entity.AnimeEntity;
import com.darkdeveloper.kawaiianimes.data.db.entity.AnimeStatus;
import com.darkdeveloper.kawaiianimes.data.db.entity.AnimeType;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AnimeDao_Impl implements AnimeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AnimeEntity> __insertionAdapterOfAnimeEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<AnimeEntity> __updateAdapterOfAnimeEntity;

  private final SharedSQLiteStatement __preparedStmtOfSetFavorite;

  private final SharedSQLiteStatement __preparedStmtOfSetWatchLater;

  private final SharedSQLiteStatement __preparedStmtOfUpdateLastViewed;

  private final SharedSQLiteStatement __preparedStmtOfCleanupOld;

  public AnimeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAnimeEntity = new EntityInsertionAdapter<AnimeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `anime` (`id`,`name`,`nameAlternative`,`poster`,`banner`,`overview`,`aired`,`type`,`status`,`genres`,`rating`,`voteAverage`,`season`,`broadcast`,`prequel`,`sequel`,`related`,`views`,`updatedAt`,`languages`,`isFavorite`,`isWatchLater`,`lastViewedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AnimeEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        if (entity.getNameAlternative() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getNameAlternative());
        }
        statement.bindString(4, entity.getPoster());
        if (entity.getBanner() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getBanner());
        }
        if (entity.getOverview() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getOverview());
        }
        final String _tmp = __converters.fromLocalDate(entity.getAired());
        if (_tmp == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp);
        }
        final String _tmp_1 = __converters.fromAnimeType(entity.getType());
        statement.bindString(8, _tmp_1);
        final String _tmp_2 = __converters.fromAnimeStatus(entity.getStatus());
        statement.bindString(9, _tmp_2);
        statement.bindString(10, entity.getGenres());
        statement.bindDouble(11, entity.getRating());
        statement.bindDouble(12, entity.getVoteAverage());
        if (entity.getSeason() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getSeason());
        }
        if (entity.getBroadcast() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getBroadcast());
        }
        if (entity.getPrequel() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getPrequel());
        }
        if (entity.getSequel() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getSequel());
        }
        if (entity.getRelated() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getRelated());
        }
        statement.bindLong(18, entity.getViews());
        statement.bindLong(19, entity.getUpdatedAt());
        statement.bindString(20, entity.getLanguages());
        final int _tmp_3 = entity.isFavorite() ? 1 : 0;
        statement.bindLong(21, _tmp_3);
        final int _tmp_4 = entity.isWatchLater() ? 1 : 0;
        statement.bindLong(22, _tmp_4);
        statement.bindLong(23, entity.getLastViewedAt());
      }
    };
    this.__updateAdapterOfAnimeEntity = new EntityDeletionOrUpdateAdapter<AnimeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `anime` SET `id` = ?,`name` = ?,`nameAlternative` = ?,`poster` = ?,`banner` = ?,`overview` = ?,`aired` = ?,`type` = ?,`status` = ?,`genres` = ?,`rating` = ?,`voteAverage` = ?,`season` = ?,`broadcast` = ?,`prequel` = ?,`sequel` = ?,`related` = ?,`views` = ?,`updatedAt` = ?,`languages` = ?,`isFavorite` = ?,`isWatchLater` = ?,`lastViewedAt` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AnimeEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getName());
        if (entity.getNameAlternative() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getNameAlternative());
        }
        statement.bindString(4, entity.getPoster());
        if (entity.getBanner() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getBanner());
        }
        if (entity.getOverview() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getOverview());
        }
        final String _tmp = __converters.fromLocalDate(entity.getAired());
        if (_tmp == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp);
        }
        final String _tmp_1 = __converters.fromAnimeType(entity.getType());
        statement.bindString(8, _tmp_1);
        final String _tmp_2 = __converters.fromAnimeStatus(entity.getStatus());
        statement.bindString(9, _tmp_2);
        statement.bindString(10, entity.getGenres());
        statement.bindDouble(11, entity.getRating());
        statement.bindDouble(12, entity.getVoteAverage());
        if (entity.getSeason() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getSeason());
        }
        if (entity.getBroadcast() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getBroadcast());
        }
        if (entity.getPrequel() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getPrequel());
        }
        if (entity.getSequel() == null) {
          statement.bindNull(16);
        } else {
          statement.bindString(16, entity.getSequel());
        }
        if (entity.getRelated() == null) {
          statement.bindNull(17);
        } else {
          statement.bindString(17, entity.getRelated());
        }
        statement.bindLong(18, entity.getViews());
        statement.bindLong(19, entity.getUpdatedAt());
        statement.bindString(20, entity.getLanguages());
        final int _tmp_3 = entity.isFavorite() ? 1 : 0;
        statement.bindLong(21, _tmp_3);
        final int _tmp_4 = entity.isWatchLater() ? 1 : 0;
        statement.bindLong(22, _tmp_4);
        statement.bindLong(23, entity.getLastViewedAt());
        statement.bindString(24, entity.getId());
      }
    };
    this.__preparedStmtOfSetFavorite = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE anime SET isFavorite = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetWatchLater = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE anime SET isWatchLater = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateLastViewed = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE anime SET lastViewedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfCleanupOld = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM anime WHERE updatedAt < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<AnimeEntity> animes,
      final Continuation<? super List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfAnimeEntity.insertAndReturnIdsList(animes);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insert(final AnimeEntity anime, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfAnimeEntity.insertAndReturnId(anime);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final AnimeEntity anime, final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total += __updateAdapterOfAnimeEntity.handle(anime);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object setFavorite(final String id, final boolean isFavorite,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfSetFavorite.acquire();
        int _argIndex = 1;
        final int _tmp = isFavorite ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
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
          __preparedStmtOfSetFavorite.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object setWatchLater(final String id, final boolean isWatchLater,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfSetWatchLater.acquire();
        int _argIndex = 1;
        final int _tmp = isWatchLater ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
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
          __preparedStmtOfSetWatchLater.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateLastViewed(final String id, final long timestamp,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateLastViewed.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, timestamp);
        _argIndex = 2;
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
          __preparedStmtOfUpdateLastViewed.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object cleanupOld(final long threshold, final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfCleanupOld.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, threshold);
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
          __preparedStmtOfCleanupOld.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getById(final String id, final Continuation<? super AnimeEntity> $completion) {
    final String _sql = "SELECT * FROM anime WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AnimeEntity>() {
      @Override
      @Nullable
      public AnimeEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameAlternative = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlternative");
          final int _cursorIndexOfPoster = CursorUtil.getColumnIndexOrThrow(_cursor, "poster");
          final int _cursorIndexOfBanner = CursorUtil.getColumnIndexOrThrow(_cursor, "banner");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfGenres = CursorUtil.getColumnIndexOrThrow(_cursor, "genres");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfBroadcast = CursorUtil.getColumnIndexOrThrow(_cursor, "broadcast");
          final int _cursorIndexOfPrequel = CursorUtil.getColumnIndexOrThrow(_cursor, "prequel");
          final int _cursorIndexOfSequel = CursorUtil.getColumnIndexOrThrow(_cursor, "sequel");
          final int _cursorIndexOfRelated = CursorUtil.getColumnIndexOrThrow(_cursor, "related");
          final int _cursorIndexOfViews = CursorUtil.getColumnIndexOrThrow(_cursor, "views");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsWatchLater = CursorUtil.getColumnIndexOrThrow(_cursor, "isWatchLater");
          final int _cursorIndexOfLastViewedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastViewedAt");
          final AnimeEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpNameAlternative;
            if (_cursor.isNull(_cursorIndexOfNameAlternative)) {
              _tmpNameAlternative = null;
            } else {
              _tmpNameAlternative = _cursor.getString(_cursorIndexOfNameAlternative);
            }
            final String _tmpPoster;
            _tmpPoster = _cursor.getString(_cursorIndexOfPoster);
            final String _tmpBanner;
            if (_cursor.isNull(_cursorIndexOfBanner)) {
              _tmpBanner = null;
            } else {
              _tmpBanner = _cursor.getString(_cursorIndexOfBanner);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDate _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDate(_tmp);
            final AnimeType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toAnimeType(_tmp_1);
            final AnimeStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toAnimeStatus(_tmp_2);
            final String _tmpGenres;
            _tmpGenres = _cursor.getString(_cursorIndexOfGenres);
            final double _tmpRating;
            _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final String _tmpSeason;
            if (_cursor.isNull(_cursorIndexOfSeason)) {
              _tmpSeason = null;
            } else {
              _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            }
            final String _tmpBroadcast;
            if (_cursor.isNull(_cursorIndexOfBroadcast)) {
              _tmpBroadcast = null;
            } else {
              _tmpBroadcast = _cursor.getString(_cursorIndexOfBroadcast);
            }
            final String _tmpPrequel;
            if (_cursor.isNull(_cursorIndexOfPrequel)) {
              _tmpPrequel = null;
            } else {
              _tmpPrequel = _cursor.getString(_cursorIndexOfPrequel);
            }
            final String _tmpSequel;
            if (_cursor.isNull(_cursorIndexOfSequel)) {
              _tmpSequel = null;
            } else {
              _tmpSequel = _cursor.getString(_cursorIndexOfSequel);
            }
            final String _tmpRelated;
            if (_cursor.isNull(_cursorIndexOfRelated)) {
              _tmpRelated = null;
            } else {
              _tmpRelated = _cursor.getString(_cursorIndexOfRelated);
            }
            final long _tmpViews;
            _tmpViews = _cursor.getLong(_cursorIndexOfViews);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final boolean _tmpIsFavorite;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_3 != 0;
            final boolean _tmpIsWatchLater;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsWatchLater);
            _tmpIsWatchLater = _tmp_4 != 0;
            final long _tmpLastViewedAt;
            _tmpLastViewedAt = _cursor.getLong(_cursorIndexOfLastViewedAt);
            _result = new AnimeEntity(_tmpId,_tmpName,_tmpNameAlternative,_tmpPoster,_tmpBanner,_tmpOverview,_tmpAired,_tmpType,_tmpStatus,_tmpGenres,_tmpRating,_tmpVoteAverage,_tmpSeason,_tmpBroadcast,_tmpPrequel,_tmpSequel,_tmpRelated,_tmpViews,_tmpUpdatedAt,_tmpLanguages,_tmpIsFavorite,_tmpIsWatchLater,_tmpLastViewedAt);
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
  public Flow<AnimeEntity> observeById(final String id) {
    final String _sql = "SELECT * FROM anime WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"anime"}, new Callable<AnimeEntity>() {
      @Override
      @Nullable
      public AnimeEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameAlternative = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlternative");
          final int _cursorIndexOfPoster = CursorUtil.getColumnIndexOrThrow(_cursor, "poster");
          final int _cursorIndexOfBanner = CursorUtil.getColumnIndexOrThrow(_cursor, "banner");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfGenres = CursorUtil.getColumnIndexOrThrow(_cursor, "genres");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfBroadcast = CursorUtil.getColumnIndexOrThrow(_cursor, "broadcast");
          final int _cursorIndexOfPrequel = CursorUtil.getColumnIndexOrThrow(_cursor, "prequel");
          final int _cursorIndexOfSequel = CursorUtil.getColumnIndexOrThrow(_cursor, "sequel");
          final int _cursorIndexOfRelated = CursorUtil.getColumnIndexOrThrow(_cursor, "related");
          final int _cursorIndexOfViews = CursorUtil.getColumnIndexOrThrow(_cursor, "views");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsWatchLater = CursorUtil.getColumnIndexOrThrow(_cursor, "isWatchLater");
          final int _cursorIndexOfLastViewedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastViewedAt");
          final AnimeEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpNameAlternative;
            if (_cursor.isNull(_cursorIndexOfNameAlternative)) {
              _tmpNameAlternative = null;
            } else {
              _tmpNameAlternative = _cursor.getString(_cursorIndexOfNameAlternative);
            }
            final String _tmpPoster;
            _tmpPoster = _cursor.getString(_cursorIndexOfPoster);
            final String _tmpBanner;
            if (_cursor.isNull(_cursorIndexOfBanner)) {
              _tmpBanner = null;
            } else {
              _tmpBanner = _cursor.getString(_cursorIndexOfBanner);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDate _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDate(_tmp);
            final AnimeType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toAnimeType(_tmp_1);
            final AnimeStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toAnimeStatus(_tmp_2);
            final String _tmpGenres;
            _tmpGenres = _cursor.getString(_cursorIndexOfGenres);
            final double _tmpRating;
            _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final String _tmpSeason;
            if (_cursor.isNull(_cursorIndexOfSeason)) {
              _tmpSeason = null;
            } else {
              _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            }
            final String _tmpBroadcast;
            if (_cursor.isNull(_cursorIndexOfBroadcast)) {
              _tmpBroadcast = null;
            } else {
              _tmpBroadcast = _cursor.getString(_cursorIndexOfBroadcast);
            }
            final String _tmpPrequel;
            if (_cursor.isNull(_cursorIndexOfPrequel)) {
              _tmpPrequel = null;
            } else {
              _tmpPrequel = _cursor.getString(_cursorIndexOfPrequel);
            }
            final String _tmpSequel;
            if (_cursor.isNull(_cursorIndexOfSequel)) {
              _tmpSequel = null;
            } else {
              _tmpSequel = _cursor.getString(_cursorIndexOfSequel);
            }
            final String _tmpRelated;
            if (_cursor.isNull(_cursorIndexOfRelated)) {
              _tmpRelated = null;
            } else {
              _tmpRelated = _cursor.getString(_cursorIndexOfRelated);
            }
            final long _tmpViews;
            _tmpViews = _cursor.getLong(_cursorIndexOfViews);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final boolean _tmpIsFavorite;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_3 != 0;
            final boolean _tmpIsWatchLater;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsWatchLater);
            _tmpIsWatchLater = _tmp_4 != 0;
            final long _tmpLastViewedAt;
            _tmpLastViewedAt = _cursor.getLong(_cursorIndexOfLastViewedAt);
            _result = new AnimeEntity(_tmpId,_tmpName,_tmpNameAlternative,_tmpPoster,_tmpBanner,_tmpOverview,_tmpAired,_tmpType,_tmpStatus,_tmpGenres,_tmpRating,_tmpVoteAverage,_tmpSeason,_tmpBroadcast,_tmpPrequel,_tmpSequel,_tmpRelated,_tmpViews,_tmpUpdatedAt,_tmpLanguages,_tmpIsFavorite,_tmpIsWatchLater,_tmpLastViewedAt);
          } else {
            _result = null;
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
  public Object getAll(final int limit, final int offset,
      final Continuation<? super List<AnimeEntity>> $completion) {
    final String _sql = "SELECT * FROM anime ORDER BY updatedAt DESC LIMIT ? OFFSET ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    _argIndex = 2;
    _statement.bindLong(_argIndex, offset);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AnimeEntity>>() {
      @Override
      @NonNull
      public List<AnimeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameAlternative = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlternative");
          final int _cursorIndexOfPoster = CursorUtil.getColumnIndexOrThrow(_cursor, "poster");
          final int _cursorIndexOfBanner = CursorUtil.getColumnIndexOrThrow(_cursor, "banner");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfGenres = CursorUtil.getColumnIndexOrThrow(_cursor, "genres");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfBroadcast = CursorUtil.getColumnIndexOrThrow(_cursor, "broadcast");
          final int _cursorIndexOfPrequel = CursorUtil.getColumnIndexOrThrow(_cursor, "prequel");
          final int _cursorIndexOfSequel = CursorUtil.getColumnIndexOrThrow(_cursor, "sequel");
          final int _cursorIndexOfRelated = CursorUtil.getColumnIndexOrThrow(_cursor, "related");
          final int _cursorIndexOfViews = CursorUtil.getColumnIndexOrThrow(_cursor, "views");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsWatchLater = CursorUtil.getColumnIndexOrThrow(_cursor, "isWatchLater");
          final int _cursorIndexOfLastViewedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastViewedAt");
          final List<AnimeEntity> _result = new ArrayList<AnimeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AnimeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpNameAlternative;
            if (_cursor.isNull(_cursorIndexOfNameAlternative)) {
              _tmpNameAlternative = null;
            } else {
              _tmpNameAlternative = _cursor.getString(_cursorIndexOfNameAlternative);
            }
            final String _tmpPoster;
            _tmpPoster = _cursor.getString(_cursorIndexOfPoster);
            final String _tmpBanner;
            if (_cursor.isNull(_cursorIndexOfBanner)) {
              _tmpBanner = null;
            } else {
              _tmpBanner = _cursor.getString(_cursorIndexOfBanner);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDate _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDate(_tmp);
            final AnimeType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toAnimeType(_tmp_1);
            final AnimeStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toAnimeStatus(_tmp_2);
            final String _tmpGenres;
            _tmpGenres = _cursor.getString(_cursorIndexOfGenres);
            final double _tmpRating;
            _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final String _tmpSeason;
            if (_cursor.isNull(_cursorIndexOfSeason)) {
              _tmpSeason = null;
            } else {
              _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            }
            final String _tmpBroadcast;
            if (_cursor.isNull(_cursorIndexOfBroadcast)) {
              _tmpBroadcast = null;
            } else {
              _tmpBroadcast = _cursor.getString(_cursorIndexOfBroadcast);
            }
            final String _tmpPrequel;
            if (_cursor.isNull(_cursorIndexOfPrequel)) {
              _tmpPrequel = null;
            } else {
              _tmpPrequel = _cursor.getString(_cursorIndexOfPrequel);
            }
            final String _tmpSequel;
            if (_cursor.isNull(_cursorIndexOfSequel)) {
              _tmpSequel = null;
            } else {
              _tmpSequel = _cursor.getString(_cursorIndexOfSequel);
            }
            final String _tmpRelated;
            if (_cursor.isNull(_cursorIndexOfRelated)) {
              _tmpRelated = null;
            } else {
              _tmpRelated = _cursor.getString(_cursorIndexOfRelated);
            }
            final long _tmpViews;
            _tmpViews = _cursor.getLong(_cursorIndexOfViews);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final boolean _tmpIsFavorite;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_3 != 0;
            final boolean _tmpIsWatchLater;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsWatchLater);
            _tmpIsWatchLater = _tmp_4 != 0;
            final long _tmpLastViewedAt;
            _tmpLastViewedAt = _cursor.getLong(_cursorIndexOfLastViewedAt);
            _item = new AnimeEntity(_tmpId,_tmpName,_tmpNameAlternative,_tmpPoster,_tmpBanner,_tmpOverview,_tmpAired,_tmpType,_tmpStatus,_tmpGenres,_tmpRating,_tmpVoteAverage,_tmpSeason,_tmpBroadcast,_tmpPrequel,_tmpSequel,_tmpRelated,_tmpViews,_tmpUpdatedAt,_tmpLanguages,_tmpIsFavorite,_tmpIsWatchLater,_tmpLastViewedAt);
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
  public Flow<List<AnimeEntity>> observeAll(final int limit, final int offset) {
    final String _sql = "SELECT * FROM anime ORDER BY updatedAt DESC LIMIT ? OFFSET ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    _argIndex = 2;
    _statement.bindLong(_argIndex, offset);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"anime"}, new Callable<List<AnimeEntity>>() {
      @Override
      @NonNull
      public List<AnimeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameAlternative = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlternative");
          final int _cursorIndexOfPoster = CursorUtil.getColumnIndexOrThrow(_cursor, "poster");
          final int _cursorIndexOfBanner = CursorUtil.getColumnIndexOrThrow(_cursor, "banner");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfGenres = CursorUtil.getColumnIndexOrThrow(_cursor, "genres");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfBroadcast = CursorUtil.getColumnIndexOrThrow(_cursor, "broadcast");
          final int _cursorIndexOfPrequel = CursorUtil.getColumnIndexOrThrow(_cursor, "prequel");
          final int _cursorIndexOfSequel = CursorUtil.getColumnIndexOrThrow(_cursor, "sequel");
          final int _cursorIndexOfRelated = CursorUtil.getColumnIndexOrThrow(_cursor, "related");
          final int _cursorIndexOfViews = CursorUtil.getColumnIndexOrThrow(_cursor, "views");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsWatchLater = CursorUtil.getColumnIndexOrThrow(_cursor, "isWatchLater");
          final int _cursorIndexOfLastViewedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastViewedAt");
          final List<AnimeEntity> _result = new ArrayList<AnimeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AnimeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpNameAlternative;
            if (_cursor.isNull(_cursorIndexOfNameAlternative)) {
              _tmpNameAlternative = null;
            } else {
              _tmpNameAlternative = _cursor.getString(_cursorIndexOfNameAlternative);
            }
            final String _tmpPoster;
            _tmpPoster = _cursor.getString(_cursorIndexOfPoster);
            final String _tmpBanner;
            if (_cursor.isNull(_cursorIndexOfBanner)) {
              _tmpBanner = null;
            } else {
              _tmpBanner = _cursor.getString(_cursorIndexOfBanner);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDate _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDate(_tmp);
            final AnimeType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toAnimeType(_tmp_1);
            final AnimeStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toAnimeStatus(_tmp_2);
            final String _tmpGenres;
            _tmpGenres = _cursor.getString(_cursorIndexOfGenres);
            final double _tmpRating;
            _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final String _tmpSeason;
            if (_cursor.isNull(_cursorIndexOfSeason)) {
              _tmpSeason = null;
            } else {
              _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            }
            final String _tmpBroadcast;
            if (_cursor.isNull(_cursorIndexOfBroadcast)) {
              _tmpBroadcast = null;
            } else {
              _tmpBroadcast = _cursor.getString(_cursorIndexOfBroadcast);
            }
            final String _tmpPrequel;
            if (_cursor.isNull(_cursorIndexOfPrequel)) {
              _tmpPrequel = null;
            } else {
              _tmpPrequel = _cursor.getString(_cursorIndexOfPrequel);
            }
            final String _tmpSequel;
            if (_cursor.isNull(_cursorIndexOfSequel)) {
              _tmpSequel = null;
            } else {
              _tmpSequel = _cursor.getString(_cursorIndexOfSequel);
            }
            final String _tmpRelated;
            if (_cursor.isNull(_cursorIndexOfRelated)) {
              _tmpRelated = null;
            } else {
              _tmpRelated = _cursor.getString(_cursorIndexOfRelated);
            }
            final long _tmpViews;
            _tmpViews = _cursor.getLong(_cursorIndexOfViews);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final boolean _tmpIsFavorite;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_3 != 0;
            final boolean _tmpIsWatchLater;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsWatchLater);
            _tmpIsWatchLater = _tmp_4 != 0;
            final long _tmpLastViewedAt;
            _tmpLastViewedAt = _cursor.getLong(_cursorIndexOfLastViewedAt);
            _item = new AnimeEntity(_tmpId,_tmpName,_tmpNameAlternative,_tmpPoster,_tmpBanner,_tmpOverview,_tmpAired,_tmpType,_tmpStatus,_tmpGenres,_tmpRating,_tmpVoteAverage,_tmpSeason,_tmpBroadcast,_tmpPrequel,_tmpSequel,_tmpRelated,_tmpViews,_tmpUpdatedAt,_tmpLanguages,_tmpIsFavorite,_tmpIsWatchLater,_tmpLastViewedAt);
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
  public Object search(final String query, final int limit,
      final Continuation<? super List<AnimeEntity>> $completion) {
    final String _sql = "SELECT * FROM anime WHERE name LIKE '%' || ? || '%' OR nameAlternative LIKE '%' || ? || '%' ORDER BY views DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    _argIndex = 3;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AnimeEntity>>() {
      @Override
      @NonNull
      public List<AnimeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameAlternative = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlternative");
          final int _cursorIndexOfPoster = CursorUtil.getColumnIndexOrThrow(_cursor, "poster");
          final int _cursorIndexOfBanner = CursorUtil.getColumnIndexOrThrow(_cursor, "banner");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfGenres = CursorUtil.getColumnIndexOrThrow(_cursor, "genres");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfBroadcast = CursorUtil.getColumnIndexOrThrow(_cursor, "broadcast");
          final int _cursorIndexOfPrequel = CursorUtil.getColumnIndexOrThrow(_cursor, "prequel");
          final int _cursorIndexOfSequel = CursorUtil.getColumnIndexOrThrow(_cursor, "sequel");
          final int _cursorIndexOfRelated = CursorUtil.getColumnIndexOrThrow(_cursor, "related");
          final int _cursorIndexOfViews = CursorUtil.getColumnIndexOrThrow(_cursor, "views");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsWatchLater = CursorUtil.getColumnIndexOrThrow(_cursor, "isWatchLater");
          final int _cursorIndexOfLastViewedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastViewedAt");
          final List<AnimeEntity> _result = new ArrayList<AnimeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AnimeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpNameAlternative;
            if (_cursor.isNull(_cursorIndexOfNameAlternative)) {
              _tmpNameAlternative = null;
            } else {
              _tmpNameAlternative = _cursor.getString(_cursorIndexOfNameAlternative);
            }
            final String _tmpPoster;
            _tmpPoster = _cursor.getString(_cursorIndexOfPoster);
            final String _tmpBanner;
            if (_cursor.isNull(_cursorIndexOfBanner)) {
              _tmpBanner = null;
            } else {
              _tmpBanner = _cursor.getString(_cursorIndexOfBanner);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDate _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDate(_tmp);
            final AnimeType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toAnimeType(_tmp_1);
            final AnimeStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toAnimeStatus(_tmp_2);
            final String _tmpGenres;
            _tmpGenres = _cursor.getString(_cursorIndexOfGenres);
            final double _tmpRating;
            _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final String _tmpSeason;
            if (_cursor.isNull(_cursorIndexOfSeason)) {
              _tmpSeason = null;
            } else {
              _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            }
            final String _tmpBroadcast;
            if (_cursor.isNull(_cursorIndexOfBroadcast)) {
              _tmpBroadcast = null;
            } else {
              _tmpBroadcast = _cursor.getString(_cursorIndexOfBroadcast);
            }
            final String _tmpPrequel;
            if (_cursor.isNull(_cursorIndexOfPrequel)) {
              _tmpPrequel = null;
            } else {
              _tmpPrequel = _cursor.getString(_cursorIndexOfPrequel);
            }
            final String _tmpSequel;
            if (_cursor.isNull(_cursorIndexOfSequel)) {
              _tmpSequel = null;
            } else {
              _tmpSequel = _cursor.getString(_cursorIndexOfSequel);
            }
            final String _tmpRelated;
            if (_cursor.isNull(_cursorIndexOfRelated)) {
              _tmpRelated = null;
            } else {
              _tmpRelated = _cursor.getString(_cursorIndexOfRelated);
            }
            final long _tmpViews;
            _tmpViews = _cursor.getLong(_cursorIndexOfViews);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final boolean _tmpIsFavorite;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_3 != 0;
            final boolean _tmpIsWatchLater;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsWatchLater);
            _tmpIsWatchLater = _tmp_4 != 0;
            final long _tmpLastViewedAt;
            _tmpLastViewedAt = _cursor.getLong(_cursorIndexOfLastViewedAt);
            _item = new AnimeEntity(_tmpId,_tmpName,_tmpNameAlternative,_tmpPoster,_tmpBanner,_tmpOverview,_tmpAired,_tmpType,_tmpStatus,_tmpGenres,_tmpRating,_tmpVoteAverage,_tmpSeason,_tmpBroadcast,_tmpPrequel,_tmpSequel,_tmpRelated,_tmpViews,_tmpUpdatedAt,_tmpLanguages,_tmpIsFavorite,_tmpIsWatchLater,_tmpLastViewedAt);
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
  public Object getByGenre(final String genre, final int limit, final int offset,
      final Continuation<? super List<AnimeEntity>> $completion) {
    final String _sql = "SELECT * FROM anime WHERE ? IN (SELECT value FROM json_each(genres)) ORDER BY views DESC LIMIT ? OFFSET ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindString(_argIndex, genre);
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    _argIndex = 3;
    _statement.bindLong(_argIndex, offset);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AnimeEntity>>() {
      @Override
      @NonNull
      public List<AnimeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameAlternative = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlternative");
          final int _cursorIndexOfPoster = CursorUtil.getColumnIndexOrThrow(_cursor, "poster");
          final int _cursorIndexOfBanner = CursorUtil.getColumnIndexOrThrow(_cursor, "banner");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfGenres = CursorUtil.getColumnIndexOrThrow(_cursor, "genres");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfBroadcast = CursorUtil.getColumnIndexOrThrow(_cursor, "broadcast");
          final int _cursorIndexOfPrequel = CursorUtil.getColumnIndexOrThrow(_cursor, "prequel");
          final int _cursorIndexOfSequel = CursorUtil.getColumnIndexOrThrow(_cursor, "sequel");
          final int _cursorIndexOfRelated = CursorUtil.getColumnIndexOrThrow(_cursor, "related");
          final int _cursorIndexOfViews = CursorUtil.getColumnIndexOrThrow(_cursor, "views");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsWatchLater = CursorUtil.getColumnIndexOrThrow(_cursor, "isWatchLater");
          final int _cursorIndexOfLastViewedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastViewedAt");
          final List<AnimeEntity> _result = new ArrayList<AnimeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AnimeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpNameAlternative;
            if (_cursor.isNull(_cursorIndexOfNameAlternative)) {
              _tmpNameAlternative = null;
            } else {
              _tmpNameAlternative = _cursor.getString(_cursorIndexOfNameAlternative);
            }
            final String _tmpPoster;
            _tmpPoster = _cursor.getString(_cursorIndexOfPoster);
            final String _tmpBanner;
            if (_cursor.isNull(_cursorIndexOfBanner)) {
              _tmpBanner = null;
            } else {
              _tmpBanner = _cursor.getString(_cursorIndexOfBanner);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDate _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDate(_tmp);
            final AnimeType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toAnimeType(_tmp_1);
            final AnimeStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toAnimeStatus(_tmp_2);
            final String _tmpGenres;
            _tmpGenres = _cursor.getString(_cursorIndexOfGenres);
            final double _tmpRating;
            _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final String _tmpSeason;
            if (_cursor.isNull(_cursorIndexOfSeason)) {
              _tmpSeason = null;
            } else {
              _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            }
            final String _tmpBroadcast;
            if (_cursor.isNull(_cursorIndexOfBroadcast)) {
              _tmpBroadcast = null;
            } else {
              _tmpBroadcast = _cursor.getString(_cursorIndexOfBroadcast);
            }
            final String _tmpPrequel;
            if (_cursor.isNull(_cursorIndexOfPrequel)) {
              _tmpPrequel = null;
            } else {
              _tmpPrequel = _cursor.getString(_cursorIndexOfPrequel);
            }
            final String _tmpSequel;
            if (_cursor.isNull(_cursorIndexOfSequel)) {
              _tmpSequel = null;
            } else {
              _tmpSequel = _cursor.getString(_cursorIndexOfSequel);
            }
            final String _tmpRelated;
            if (_cursor.isNull(_cursorIndexOfRelated)) {
              _tmpRelated = null;
            } else {
              _tmpRelated = _cursor.getString(_cursorIndexOfRelated);
            }
            final long _tmpViews;
            _tmpViews = _cursor.getLong(_cursorIndexOfViews);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final boolean _tmpIsFavorite;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_3 != 0;
            final boolean _tmpIsWatchLater;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsWatchLater);
            _tmpIsWatchLater = _tmp_4 != 0;
            final long _tmpLastViewedAt;
            _tmpLastViewedAt = _cursor.getLong(_cursorIndexOfLastViewedAt);
            _item = new AnimeEntity(_tmpId,_tmpName,_tmpNameAlternative,_tmpPoster,_tmpBanner,_tmpOverview,_tmpAired,_tmpType,_tmpStatus,_tmpGenres,_tmpRating,_tmpVoteAverage,_tmpSeason,_tmpBroadcast,_tmpPrequel,_tmpSequel,_tmpRelated,_tmpViews,_tmpUpdatedAt,_tmpLanguages,_tmpIsFavorite,_tmpIsWatchLater,_tmpLastViewedAt);
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
  public Object getByStatus(final String status, final int limit, final int offset,
      final Continuation<? super List<AnimeEntity>> $completion) {
    final String _sql = "SELECT * FROM anime WHERE status = ? ORDER BY updatedAt DESC LIMIT ? OFFSET ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindString(_argIndex, status);
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    _argIndex = 3;
    _statement.bindLong(_argIndex, offset);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AnimeEntity>>() {
      @Override
      @NonNull
      public List<AnimeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameAlternative = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlternative");
          final int _cursorIndexOfPoster = CursorUtil.getColumnIndexOrThrow(_cursor, "poster");
          final int _cursorIndexOfBanner = CursorUtil.getColumnIndexOrThrow(_cursor, "banner");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfGenres = CursorUtil.getColumnIndexOrThrow(_cursor, "genres");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfBroadcast = CursorUtil.getColumnIndexOrThrow(_cursor, "broadcast");
          final int _cursorIndexOfPrequel = CursorUtil.getColumnIndexOrThrow(_cursor, "prequel");
          final int _cursorIndexOfSequel = CursorUtil.getColumnIndexOrThrow(_cursor, "sequel");
          final int _cursorIndexOfRelated = CursorUtil.getColumnIndexOrThrow(_cursor, "related");
          final int _cursorIndexOfViews = CursorUtil.getColumnIndexOrThrow(_cursor, "views");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsWatchLater = CursorUtil.getColumnIndexOrThrow(_cursor, "isWatchLater");
          final int _cursorIndexOfLastViewedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastViewedAt");
          final List<AnimeEntity> _result = new ArrayList<AnimeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AnimeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpNameAlternative;
            if (_cursor.isNull(_cursorIndexOfNameAlternative)) {
              _tmpNameAlternative = null;
            } else {
              _tmpNameAlternative = _cursor.getString(_cursorIndexOfNameAlternative);
            }
            final String _tmpPoster;
            _tmpPoster = _cursor.getString(_cursorIndexOfPoster);
            final String _tmpBanner;
            if (_cursor.isNull(_cursorIndexOfBanner)) {
              _tmpBanner = null;
            } else {
              _tmpBanner = _cursor.getString(_cursorIndexOfBanner);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDate _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDate(_tmp);
            final AnimeType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toAnimeType(_tmp_1);
            final AnimeStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toAnimeStatus(_tmp_2);
            final String _tmpGenres;
            _tmpGenres = _cursor.getString(_cursorIndexOfGenres);
            final double _tmpRating;
            _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final String _tmpSeason;
            if (_cursor.isNull(_cursorIndexOfSeason)) {
              _tmpSeason = null;
            } else {
              _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            }
            final String _tmpBroadcast;
            if (_cursor.isNull(_cursorIndexOfBroadcast)) {
              _tmpBroadcast = null;
            } else {
              _tmpBroadcast = _cursor.getString(_cursorIndexOfBroadcast);
            }
            final String _tmpPrequel;
            if (_cursor.isNull(_cursorIndexOfPrequel)) {
              _tmpPrequel = null;
            } else {
              _tmpPrequel = _cursor.getString(_cursorIndexOfPrequel);
            }
            final String _tmpSequel;
            if (_cursor.isNull(_cursorIndexOfSequel)) {
              _tmpSequel = null;
            } else {
              _tmpSequel = _cursor.getString(_cursorIndexOfSequel);
            }
            final String _tmpRelated;
            if (_cursor.isNull(_cursorIndexOfRelated)) {
              _tmpRelated = null;
            } else {
              _tmpRelated = _cursor.getString(_cursorIndexOfRelated);
            }
            final long _tmpViews;
            _tmpViews = _cursor.getLong(_cursorIndexOfViews);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final boolean _tmpIsFavorite;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_3 != 0;
            final boolean _tmpIsWatchLater;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsWatchLater);
            _tmpIsWatchLater = _tmp_4 != 0;
            final long _tmpLastViewedAt;
            _tmpLastViewedAt = _cursor.getLong(_cursorIndexOfLastViewedAt);
            _item = new AnimeEntity(_tmpId,_tmpName,_tmpNameAlternative,_tmpPoster,_tmpBanner,_tmpOverview,_tmpAired,_tmpType,_tmpStatus,_tmpGenres,_tmpRating,_tmpVoteAverage,_tmpSeason,_tmpBroadcast,_tmpPrequel,_tmpSequel,_tmpRelated,_tmpViews,_tmpUpdatedAt,_tmpLanguages,_tmpIsFavorite,_tmpIsWatchLater,_tmpLastViewedAt);
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
  public Object getByType(final String type, final int limit, final int offset,
      final Continuation<? super List<AnimeEntity>> $completion) {
    final String _sql = "SELECT * FROM anime WHERE type = ? ORDER BY updatedAt DESC LIMIT ? OFFSET ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindString(_argIndex, type);
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    _argIndex = 3;
    _statement.bindLong(_argIndex, offset);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AnimeEntity>>() {
      @Override
      @NonNull
      public List<AnimeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameAlternative = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlternative");
          final int _cursorIndexOfPoster = CursorUtil.getColumnIndexOrThrow(_cursor, "poster");
          final int _cursorIndexOfBanner = CursorUtil.getColumnIndexOrThrow(_cursor, "banner");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfGenres = CursorUtil.getColumnIndexOrThrow(_cursor, "genres");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfBroadcast = CursorUtil.getColumnIndexOrThrow(_cursor, "broadcast");
          final int _cursorIndexOfPrequel = CursorUtil.getColumnIndexOrThrow(_cursor, "prequel");
          final int _cursorIndexOfSequel = CursorUtil.getColumnIndexOrThrow(_cursor, "sequel");
          final int _cursorIndexOfRelated = CursorUtil.getColumnIndexOrThrow(_cursor, "related");
          final int _cursorIndexOfViews = CursorUtil.getColumnIndexOrThrow(_cursor, "views");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsWatchLater = CursorUtil.getColumnIndexOrThrow(_cursor, "isWatchLater");
          final int _cursorIndexOfLastViewedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastViewedAt");
          final List<AnimeEntity> _result = new ArrayList<AnimeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AnimeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpNameAlternative;
            if (_cursor.isNull(_cursorIndexOfNameAlternative)) {
              _tmpNameAlternative = null;
            } else {
              _tmpNameAlternative = _cursor.getString(_cursorIndexOfNameAlternative);
            }
            final String _tmpPoster;
            _tmpPoster = _cursor.getString(_cursorIndexOfPoster);
            final String _tmpBanner;
            if (_cursor.isNull(_cursorIndexOfBanner)) {
              _tmpBanner = null;
            } else {
              _tmpBanner = _cursor.getString(_cursorIndexOfBanner);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDate _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDate(_tmp);
            final AnimeType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toAnimeType(_tmp_1);
            final AnimeStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toAnimeStatus(_tmp_2);
            final String _tmpGenres;
            _tmpGenres = _cursor.getString(_cursorIndexOfGenres);
            final double _tmpRating;
            _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final String _tmpSeason;
            if (_cursor.isNull(_cursorIndexOfSeason)) {
              _tmpSeason = null;
            } else {
              _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            }
            final String _tmpBroadcast;
            if (_cursor.isNull(_cursorIndexOfBroadcast)) {
              _tmpBroadcast = null;
            } else {
              _tmpBroadcast = _cursor.getString(_cursorIndexOfBroadcast);
            }
            final String _tmpPrequel;
            if (_cursor.isNull(_cursorIndexOfPrequel)) {
              _tmpPrequel = null;
            } else {
              _tmpPrequel = _cursor.getString(_cursorIndexOfPrequel);
            }
            final String _tmpSequel;
            if (_cursor.isNull(_cursorIndexOfSequel)) {
              _tmpSequel = null;
            } else {
              _tmpSequel = _cursor.getString(_cursorIndexOfSequel);
            }
            final String _tmpRelated;
            if (_cursor.isNull(_cursorIndexOfRelated)) {
              _tmpRelated = null;
            } else {
              _tmpRelated = _cursor.getString(_cursorIndexOfRelated);
            }
            final long _tmpViews;
            _tmpViews = _cursor.getLong(_cursorIndexOfViews);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final boolean _tmpIsFavorite;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_3 != 0;
            final boolean _tmpIsWatchLater;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsWatchLater);
            _tmpIsWatchLater = _tmp_4 != 0;
            final long _tmpLastViewedAt;
            _tmpLastViewedAt = _cursor.getLong(_cursorIndexOfLastViewedAt);
            _item = new AnimeEntity(_tmpId,_tmpName,_tmpNameAlternative,_tmpPoster,_tmpBanner,_tmpOverview,_tmpAired,_tmpType,_tmpStatus,_tmpGenres,_tmpRating,_tmpVoteAverage,_tmpSeason,_tmpBroadcast,_tmpPrequel,_tmpSequel,_tmpRelated,_tmpViews,_tmpUpdatedAt,_tmpLanguages,_tmpIsFavorite,_tmpIsWatchLater,_tmpLastViewedAt);
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
  public Object getByDub(final String dub, final int limit, final int offset,
      final Continuation<? super List<AnimeEntity>> $completion) {
    final String _sql = "SELECT * FROM anime WHERE ? IN (SELECT value FROM json_each(languages)) ORDER BY updatedAt DESC LIMIT ? OFFSET ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindString(_argIndex, dub);
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    _argIndex = 3;
    _statement.bindLong(_argIndex, offset);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AnimeEntity>>() {
      @Override
      @NonNull
      public List<AnimeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameAlternative = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlternative");
          final int _cursorIndexOfPoster = CursorUtil.getColumnIndexOrThrow(_cursor, "poster");
          final int _cursorIndexOfBanner = CursorUtil.getColumnIndexOrThrow(_cursor, "banner");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfGenres = CursorUtil.getColumnIndexOrThrow(_cursor, "genres");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfBroadcast = CursorUtil.getColumnIndexOrThrow(_cursor, "broadcast");
          final int _cursorIndexOfPrequel = CursorUtil.getColumnIndexOrThrow(_cursor, "prequel");
          final int _cursorIndexOfSequel = CursorUtil.getColumnIndexOrThrow(_cursor, "sequel");
          final int _cursorIndexOfRelated = CursorUtil.getColumnIndexOrThrow(_cursor, "related");
          final int _cursorIndexOfViews = CursorUtil.getColumnIndexOrThrow(_cursor, "views");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsWatchLater = CursorUtil.getColumnIndexOrThrow(_cursor, "isWatchLater");
          final int _cursorIndexOfLastViewedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastViewedAt");
          final List<AnimeEntity> _result = new ArrayList<AnimeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AnimeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpNameAlternative;
            if (_cursor.isNull(_cursorIndexOfNameAlternative)) {
              _tmpNameAlternative = null;
            } else {
              _tmpNameAlternative = _cursor.getString(_cursorIndexOfNameAlternative);
            }
            final String _tmpPoster;
            _tmpPoster = _cursor.getString(_cursorIndexOfPoster);
            final String _tmpBanner;
            if (_cursor.isNull(_cursorIndexOfBanner)) {
              _tmpBanner = null;
            } else {
              _tmpBanner = _cursor.getString(_cursorIndexOfBanner);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDate _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDate(_tmp);
            final AnimeType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toAnimeType(_tmp_1);
            final AnimeStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toAnimeStatus(_tmp_2);
            final String _tmpGenres;
            _tmpGenres = _cursor.getString(_cursorIndexOfGenres);
            final double _tmpRating;
            _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final String _tmpSeason;
            if (_cursor.isNull(_cursorIndexOfSeason)) {
              _tmpSeason = null;
            } else {
              _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            }
            final String _tmpBroadcast;
            if (_cursor.isNull(_cursorIndexOfBroadcast)) {
              _tmpBroadcast = null;
            } else {
              _tmpBroadcast = _cursor.getString(_cursorIndexOfBroadcast);
            }
            final String _tmpPrequel;
            if (_cursor.isNull(_cursorIndexOfPrequel)) {
              _tmpPrequel = null;
            } else {
              _tmpPrequel = _cursor.getString(_cursorIndexOfPrequel);
            }
            final String _tmpSequel;
            if (_cursor.isNull(_cursorIndexOfSequel)) {
              _tmpSequel = null;
            } else {
              _tmpSequel = _cursor.getString(_cursorIndexOfSequel);
            }
            final String _tmpRelated;
            if (_cursor.isNull(_cursorIndexOfRelated)) {
              _tmpRelated = null;
            } else {
              _tmpRelated = _cursor.getString(_cursorIndexOfRelated);
            }
            final long _tmpViews;
            _tmpViews = _cursor.getLong(_cursorIndexOfViews);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final boolean _tmpIsFavorite;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_3 != 0;
            final boolean _tmpIsWatchLater;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsWatchLater);
            _tmpIsWatchLater = _tmp_4 != 0;
            final long _tmpLastViewedAt;
            _tmpLastViewedAt = _cursor.getLong(_cursorIndexOfLastViewedAt);
            _item = new AnimeEntity(_tmpId,_tmpName,_tmpNameAlternative,_tmpPoster,_tmpBanner,_tmpOverview,_tmpAired,_tmpType,_tmpStatus,_tmpGenres,_tmpRating,_tmpVoteAverage,_tmpSeason,_tmpBroadcast,_tmpPrequel,_tmpSequel,_tmpRelated,_tmpViews,_tmpUpdatedAt,_tmpLanguages,_tmpIsFavorite,_tmpIsWatchLater,_tmpLastViewedAt);
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
  public Object getMostViewed(final int limit, final int offset,
      final Continuation<? super List<AnimeEntity>> $completion) {
    final String _sql = "SELECT * FROM anime ORDER BY views DESC LIMIT ? OFFSET ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    _argIndex = 2;
    _statement.bindLong(_argIndex, offset);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AnimeEntity>>() {
      @Override
      @NonNull
      public List<AnimeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameAlternative = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlternative");
          final int _cursorIndexOfPoster = CursorUtil.getColumnIndexOrThrow(_cursor, "poster");
          final int _cursorIndexOfBanner = CursorUtil.getColumnIndexOrThrow(_cursor, "banner");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfGenres = CursorUtil.getColumnIndexOrThrow(_cursor, "genres");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfBroadcast = CursorUtil.getColumnIndexOrThrow(_cursor, "broadcast");
          final int _cursorIndexOfPrequel = CursorUtil.getColumnIndexOrThrow(_cursor, "prequel");
          final int _cursorIndexOfSequel = CursorUtil.getColumnIndexOrThrow(_cursor, "sequel");
          final int _cursorIndexOfRelated = CursorUtil.getColumnIndexOrThrow(_cursor, "related");
          final int _cursorIndexOfViews = CursorUtil.getColumnIndexOrThrow(_cursor, "views");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsWatchLater = CursorUtil.getColumnIndexOrThrow(_cursor, "isWatchLater");
          final int _cursorIndexOfLastViewedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastViewedAt");
          final List<AnimeEntity> _result = new ArrayList<AnimeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AnimeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpNameAlternative;
            if (_cursor.isNull(_cursorIndexOfNameAlternative)) {
              _tmpNameAlternative = null;
            } else {
              _tmpNameAlternative = _cursor.getString(_cursorIndexOfNameAlternative);
            }
            final String _tmpPoster;
            _tmpPoster = _cursor.getString(_cursorIndexOfPoster);
            final String _tmpBanner;
            if (_cursor.isNull(_cursorIndexOfBanner)) {
              _tmpBanner = null;
            } else {
              _tmpBanner = _cursor.getString(_cursorIndexOfBanner);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDate _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDate(_tmp);
            final AnimeType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toAnimeType(_tmp_1);
            final AnimeStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toAnimeStatus(_tmp_2);
            final String _tmpGenres;
            _tmpGenres = _cursor.getString(_cursorIndexOfGenres);
            final double _tmpRating;
            _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final String _tmpSeason;
            if (_cursor.isNull(_cursorIndexOfSeason)) {
              _tmpSeason = null;
            } else {
              _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            }
            final String _tmpBroadcast;
            if (_cursor.isNull(_cursorIndexOfBroadcast)) {
              _tmpBroadcast = null;
            } else {
              _tmpBroadcast = _cursor.getString(_cursorIndexOfBroadcast);
            }
            final String _tmpPrequel;
            if (_cursor.isNull(_cursorIndexOfPrequel)) {
              _tmpPrequel = null;
            } else {
              _tmpPrequel = _cursor.getString(_cursorIndexOfPrequel);
            }
            final String _tmpSequel;
            if (_cursor.isNull(_cursorIndexOfSequel)) {
              _tmpSequel = null;
            } else {
              _tmpSequel = _cursor.getString(_cursorIndexOfSequel);
            }
            final String _tmpRelated;
            if (_cursor.isNull(_cursorIndexOfRelated)) {
              _tmpRelated = null;
            } else {
              _tmpRelated = _cursor.getString(_cursorIndexOfRelated);
            }
            final long _tmpViews;
            _tmpViews = _cursor.getLong(_cursorIndexOfViews);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final boolean _tmpIsFavorite;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_3 != 0;
            final boolean _tmpIsWatchLater;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsWatchLater);
            _tmpIsWatchLater = _tmp_4 != 0;
            final long _tmpLastViewedAt;
            _tmpLastViewedAt = _cursor.getLong(_cursorIndexOfLastViewedAt);
            _item = new AnimeEntity(_tmpId,_tmpName,_tmpNameAlternative,_tmpPoster,_tmpBanner,_tmpOverview,_tmpAired,_tmpType,_tmpStatus,_tmpGenres,_tmpRating,_tmpVoteAverage,_tmpSeason,_tmpBroadcast,_tmpPrequel,_tmpSequel,_tmpRelated,_tmpViews,_tmpUpdatedAt,_tmpLanguages,_tmpIsFavorite,_tmpIsWatchLater,_tmpLastViewedAt);
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
  public Object getThisWeekCalendar(final Continuation<? super List<AnimeEntity>> $completion) {
    final String _sql = "SELECT * FROM anime WHERE aired >= date('now') AND aired <= date('now', '+7 days') ORDER BY aired ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AnimeEntity>>() {
      @Override
      @NonNull
      public List<AnimeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameAlternative = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlternative");
          final int _cursorIndexOfPoster = CursorUtil.getColumnIndexOrThrow(_cursor, "poster");
          final int _cursorIndexOfBanner = CursorUtil.getColumnIndexOrThrow(_cursor, "banner");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfGenres = CursorUtil.getColumnIndexOrThrow(_cursor, "genres");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfBroadcast = CursorUtil.getColumnIndexOrThrow(_cursor, "broadcast");
          final int _cursorIndexOfPrequel = CursorUtil.getColumnIndexOrThrow(_cursor, "prequel");
          final int _cursorIndexOfSequel = CursorUtil.getColumnIndexOrThrow(_cursor, "sequel");
          final int _cursorIndexOfRelated = CursorUtil.getColumnIndexOrThrow(_cursor, "related");
          final int _cursorIndexOfViews = CursorUtil.getColumnIndexOrThrow(_cursor, "views");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsWatchLater = CursorUtil.getColumnIndexOrThrow(_cursor, "isWatchLater");
          final int _cursorIndexOfLastViewedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastViewedAt");
          final List<AnimeEntity> _result = new ArrayList<AnimeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AnimeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpNameAlternative;
            if (_cursor.isNull(_cursorIndexOfNameAlternative)) {
              _tmpNameAlternative = null;
            } else {
              _tmpNameAlternative = _cursor.getString(_cursorIndexOfNameAlternative);
            }
            final String _tmpPoster;
            _tmpPoster = _cursor.getString(_cursorIndexOfPoster);
            final String _tmpBanner;
            if (_cursor.isNull(_cursorIndexOfBanner)) {
              _tmpBanner = null;
            } else {
              _tmpBanner = _cursor.getString(_cursorIndexOfBanner);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDate _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDate(_tmp);
            final AnimeType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toAnimeType(_tmp_1);
            final AnimeStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toAnimeStatus(_tmp_2);
            final String _tmpGenres;
            _tmpGenres = _cursor.getString(_cursorIndexOfGenres);
            final double _tmpRating;
            _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final String _tmpSeason;
            if (_cursor.isNull(_cursorIndexOfSeason)) {
              _tmpSeason = null;
            } else {
              _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            }
            final String _tmpBroadcast;
            if (_cursor.isNull(_cursorIndexOfBroadcast)) {
              _tmpBroadcast = null;
            } else {
              _tmpBroadcast = _cursor.getString(_cursorIndexOfBroadcast);
            }
            final String _tmpPrequel;
            if (_cursor.isNull(_cursorIndexOfPrequel)) {
              _tmpPrequel = null;
            } else {
              _tmpPrequel = _cursor.getString(_cursorIndexOfPrequel);
            }
            final String _tmpSequel;
            if (_cursor.isNull(_cursorIndexOfSequel)) {
              _tmpSequel = null;
            } else {
              _tmpSequel = _cursor.getString(_cursorIndexOfSequel);
            }
            final String _tmpRelated;
            if (_cursor.isNull(_cursorIndexOfRelated)) {
              _tmpRelated = null;
            } else {
              _tmpRelated = _cursor.getString(_cursorIndexOfRelated);
            }
            final long _tmpViews;
            _tmpViews = _cursor.getLong(_cursorIndexOfViews);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final boolean _tmpIsFavorite;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_3 != 0;
            final boolean _tmpIsWatchLater;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsWatchLater);
            _tmpIsWatchLater = _tmp_4 != 0;
            final long _tmpLastViewedAt;
            _tmpLastViewedAt = _cursor.getLong(_cursorIndexOfLastViewedAt);
            _item = new AnimeEntity(_tmpId,_tmpName,_tmpNameAlternative,_tmpPoster,_tmpBanner,_tmpOverview,_tmpAired,_tmpType,_tmpStatus,_tmpGenres,_tmpRating,_tmpVoteAverage,_tmpSeason,_tmpBroadcast,_tmpPrequel,_tmpSequel,_tmpRelated,_tmpViews,_tmpUpdatedAt,_tmpLanguages,_tmpIsFavorite,_tmpIsWatchLater,_tmpLastViewedAt);
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
  public Object getFavorites(final Continuation<? super List<AnimeEntity>> $completion) {
    final String _sql = "SELECT * FROM anime WHERE isFavorite = 1 ORDER BY lastViewedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AnimeEntity>>() {
      @Override
      @NonNull
      public List<AnimeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameAlternative = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlternative");
          final int _cursorIndexOfPoster = CursorUtil.getColumnIndexOrThrow(_cursor, "poster");
          final int _cursorIndexOfBanner = CursorUtil.getColumnIndexOrThrow(_cursor, "banner");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfGenres = CursorUtil.getColumnIndexOrThrow(_cursor, "genres");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfBroadcast = CursorUtil.getColumnIndexOrThrow(_cursor, "broadcast");
          final int _cursorIndexOfPrequel = CursorUtil.getColumnIndexOrThrow(_cursor, "prequel");
          final int _cursorIndexOfSequel = CursorUtil.getColumnIndexOrThrow(_cursor, "sequel");
          final int _cursorIndexOfRelated = CursorUtil.getColumnIndexOrThrow(_cursor, "related");
          final int _cursorIndexOfViews = CursorUtil.getColumnIndexOrThrow(_cursor, "views");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsWatchLater = CursorUtil.getColumnIndexOrThrow(_cursor, "isWatchLater");
          final int _cursorIndexOfLastViewedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastViewedAt");
          final List<AnimeEntity> _result = new ArrayList<AnimeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AnimeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpNameAlternative;
            if (_cursor.isNull(_cursorIndexOfNameAlternative)) {
              _tmpNameAlternative = null;
            } else {
              _tmpNameAlternative = _cursor.getString(_cursorIndexOfNameAlternative);
            }
            final String _tmpPoster;
            _tmpPoster = _cursor.getString(_cursorIndexOfPoster);
            final String _tmpBanner;
            if (_cursor.isNull(_cursorIndexOfBanner)) {
              _tmpBanner = null;
            } else {
              _tmpBanner = _cursor.getString(_cursorIndexOfBanner);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDate _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDate(_tmp);
            final AnimeType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toAnimeType(_tmp_1);
            final AnimeStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toAnimeStatus(_tmp_2);
            final String _tmpGenres;
            _tmpGenres = _cursor.getString(_cursorIndexOfGenres);
            final double _tmpRating;
            _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final String _tmpSeason;
            if (_cursor.isNull(_cursorIndexOfSeason)) {
              _tmpSeason = null;
            } else {
              _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            }
            final String _tmpBroadcast;
            if (_cursor.isNull(_cursorIndexOfBroadcast)) {
              _tmpBroadcast = null;
            } else {
              _tmpBroadcast = _cursor.getString(_cursorIndexOfBroadcast);
            }
            final String _tmpPrequel;
            if (_cursor.isNull(_cursorIndexOfPrequel)) {
              _tmpPrequel = null;
            } else {
              _tmpPrequel = _cursor.getString(_cursorIndexOfPrequel);
            }
            final String _tmpSequel;
            if (_cursor.isNull(_cursorIndexOfSequel)) {
              _tmpSequel = null;
            } else {
              _tmpSequel = _cursor.getString(_cursorIndexOfSequel);
            }
            final String _tmpRelated;
            if (_cursor.isNull(_cursorIndexOfRelated)) {
              _tmpRelated = null;
            } else {
              _tmpRelated = _cursor.getString(_cursorIndexOfRelated);
            }
            final long _tmpViews;
            _tmpViews = _cursor.getLong(_cursorIndexOfViews);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final boolean _tmpIsFavorite;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_3 != 0;
            final boolean _tmpIsWatchLater;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsWatchLater);
            _tmpIsWatchLater = _tmp_4 != 0;
            final long _tmpLastViewedAt;
            _tmpLastViewedAt = _cursor.getLong(_cursorIndexOfLastViewedAt);
            _item = new AnimeEntity(_tmpId,_tmpName,_tmpNameAlternative,_tmpPoster,_tmpBanner,_tmpOverview,_tmpAired,_tmpType,_tmpStatus,_tmpGenres,_tmpRating,_tmpVoteAverage,_tmpSeason,_tmpBroadcast,_tmpPrequel,_tmpSequel,_tmpRelated,_tmpViews,_tmpUpdatedAt,_tmpLanguages,_tmpIsFavorite,_tmpIsWatchLater,_tmpLastViewedAt);
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
  public Object getWatchLater(final Continuation<? super List<AnimeEntity>> $completion) {
    final String _sql = "SELECT * FROM anime WHERE isWatchLater = 1 ORDER BY lastViewedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<AnimeEntity>>() {
      @Override
      @NonNull
      public List<AnimeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfNameAlternative = CursorUtil.getColumnIndexOrThrow(_cursor, "nameAlternative");
          final int _cursorIndexOfPoster = CursorUtil.getColumnIndexOrThrow(_cursor, "poster");
          final int _cursorIndexOfBanner = CursorUtil.getColumnIndexOrThrow(_cursor, "banner");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfGenres = CursorUtil.getColumnIndexOrThrow(_cursor, "genres");
          final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
          final int _cursorIndexOfVoteAverage = CursorUtil.getColumnIndexOrThrow(_cursor, "voteAverage");
          final int _cursorIndexOfSeason = CursorUtil.getColumnIndexOrThrow(_cursor, "season");
          final int _cursorIndexOfBroadcast = CursorUtil.getColumnIndexOrThrow(_cursor, "broadcast");
          final int _cursorIndexOfPrequel = CursorUtil.getColumnIndexOrThrow(_cursor, "prequel");
          final int _cursorIndexOfSequel = CursorUtil.getColumnIndexOrThrow(_cursor, "sequel");
          final int _cursorIndexOfRelated = CursorUtil.getColumnIndexOrThrow(_cursor, "related");
          final int _cursorIndexOfViews = CursorUtil.getColumnIndexOrThrow(_cursor, "views");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsWatchLater = CursorUtil.getColumnIndexOrThrow(_cursor, "isWatchLater");
          final int _cursorIndexOfLastViewedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastViewedAt");
          final List<AnimeEntity> _result = new ArrayList<AnimeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AnimeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpNameAlternative;
            if (_cursor.isNull(_cursorIndexOfNameAlternative)) {
              _tmpNameAlternative = null;
            } else {
              _tmpNameAlternative = _cursor.getString(_cursorIndexOfNameAlternative);
            }
            final String _tmpPoster;
            _tmpPoster = _cursor.getString(_cursorIndexOfPoster);
            final String _tmpBanner;
            if (_cursor.isNull(_cursorIndexOfBanner)) {
              _tmpBanner = null;
            } else {
              _tmpBanner = _cursor.getString(_cursorIndexOfBanner);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDate _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDate(_tmp);
            final AnimeType _tmpType;
            final String _tmp_1;
            _tmp_1 = _cursor.getString(_cursorIndexOfType);
            _tmpType = __converters.toAnimeType(_tmp_1);
            final AnimeStatus _tmpStatus;
            final String _tmp_2;
            _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            _tmpStatus = __converters.toAnimeStatus(_tmp_2);
            final String _tmpGenres;
            _tmpGenres = _cursor.getString(_cursorIndexOfGenres);
            final double _tmpRating;
            _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
            final double _tmpVoteAverage;
            _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
            final String _tmpSeason;
            if (_cursor.isNull(_cursorIndexOfSeason)) {
              _tmpSeason = null;
            } else {
              _tmpSeason = _cursor.getString(_cursorIndexOfSeason);
            }
            final String _tmpBroadcast;
            if (_cursor.isNull(_cursorIndexOfBroadcast)) {
              _tmpBroadcast = null;
            } else {
              _tmpBroadcast = _cursor.getString(_cursorIndexOfBroadcast);
            }
            final String _tmpPrequel;
            if (_cursor.isNull(_cursorIndexOfPrequel)) {
              _tmpPrequel = null;
            } else {
              _tmpPrequel = _cursor.getString(_cursorIndexOfPrequel);
            }
            final String _tmpSequel;
            if (_cursor.isNull(_cursorIndexOfSequel)) {
              _tmpSequel = null;
            } else {
              _tmpSequel = _cursor.getString(_cursorIndexOfSequel);
            }
            final String _tmpRelated;
            if (_cursor.isNull(_cursorIndexOfRelated)) {
              _tmpRelated = null;
            } else {
              _tmpRelated = _cursor.getString(_cursorIndexOfRelated);
            }
            final long _tmpViews;
            _tmpViews = _cursor.getLong(_cursorIndexOfViews);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final boolean _tmpIsFavorite;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_3 != 0;
            final boolean _tmpIsWatchLater;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsWatchLater);
            _tmpIsWatchLater = _tmp_4 != 0;
            final long _tmpLastViewedAt;
            _tmpLastViewedAt = _cursor.getLong(_cursorIndexOfLastViewedAt);
            _item = new AnimeEntity(_tmpId,_tmpName,_tmpNameAlternative,_tmpPoster,_tmpBanner,_tmpOverview,_tmpAired,_tmpType,_tmpStatus,_tmpGenres,_tmpRating,_tmpVoteAverage,_tmpSeason,_tmpBroadcast,_tmpPrequel,_tmpSequel,_tmpRelated,_tmpViews,_tmpUpdatedAt,_tmpLanguages,_tmpIsFavorite,_tmpIsWatchLater,_tmpLastViewedAt);
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
