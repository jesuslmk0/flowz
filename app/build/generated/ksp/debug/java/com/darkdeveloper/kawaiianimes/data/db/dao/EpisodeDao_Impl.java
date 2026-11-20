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
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.darkdeveloper.kawaiianimes.data.db.converter.Converters;
import com.darkdeveloper.kawaiianimes.data.db.entity.EpisodeEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class EpisodeDao_Impl implements EpisodeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<EpisodeEntity> __insertionAdapterOfEpisodeEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<EpisodeEntity> __updateAdapterOfEpisodeEntity;

  public EpisodeDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEpisodeEntity = new EntityInsertionAdapter<EpisodeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `episode` (`id`,`animeId`,`number`,`title`,`overview`,`aired`,`duration`,`thumbnail`,`filler`,`languages`,`servers`,`isDownloaded`,`downloadProgress`,`localPath`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EpisodeEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getAnimeId());
        statement.bindLong(3, entity.getNumber());
        if (entity.getTitle() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTitle());
        }
        if (entity.getOverview() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getOverview());
        }
        final String _tmp = __converters.fromLocalDateTime(entity.getAired());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp);
        }
        if (entity.getDuration() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getDuration());
        }
        if (entity.getThumbnail() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getThumbnail());
        }
        final int _tmp_1 = entity.getFiller() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
        statement.bindString(10, entity.getLanguages());
        statement.bindString(11, entity.getServers());
        final int _tmp_2 = entity.isDownloaded() ? 1 : 0;
        statement.bindLong(12, _tmp_2);
        statement.bindDouble(13, entity.getDownloadProgress());
        if (entity.getLocalPath() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getLocalPath());
        }
      }
    };
    this.__updateAdapterOfEpisodeEntity = new EntityDeletionOrUpdateAdapter<EpisodeEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `episode` SET `id` = ?,`animeId` = ?,`number` = ?,`title` = ?,`overview` = ?,`aired` = ?,`duration` = ?,`thumbnail` = ?,`filler` = ?,`languages` = ?,`servers` = ?,`isDownloaded` = ?,`downloadProgress` = ?,`localPath` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final EpisodeEntity entity) {
        statement.bindString(1, entity.getId());
        statement.bindString(2, entity.getAnimeId());
        statement.bindLong(3, entity.getNumber());
        if (entity.getTitle() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTitle());
        }
        if (entity.getOverview() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getOverview());
        }
        final String _tmp = __converters.fromLocalDateTime(entity.getAired());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp);
        }
        if (entity.getDuration() == null) {
          statement.bindNull(7);
        } else {
          statement.bindLong(7, entity.getDuration());
        }
        if (entity.getThumbnail() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getThumbnail());
        }
        final int _tmp_1 = entity.getFiller() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
        statement.bindString(10, entity.getLanguages());
        statement.bindString(11, entity.getServers());
        final int _tmp_2 = entity.isDownloaded() ? 1 : 0;
        statement.bindLong(12, _tmp_2);
        statement.bindDouble(13, entity.getDownloadProgress());
        if (entity.getLocalPath() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getLocalPath());
        }
        statement.bindString(15, entity.getId());
      }
    };
  }

  @Override
  public Object insertAll(final List<EpisodeEntity> episodes,
      final Continuation<? super List<Long>> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        __db.beginTransaction();
        try {
          final List<Long> _result = __insertionAdapterOfEpisodeEntity.insertAndReturnIdsList(episodes);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insert(final EpisodeEntity episode, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfEpisodeEntity.insertAndReturnId(episode);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final EpisodeEntity episode,
      final Continuation<? super Integer> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        int _total = 0;
        __db.beginTransaction();
        try {
          _total += __updateAdapterOfEpisodeEntity.handle(episode);
          __db.setTransactionSuccessful();
          return _total;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getById(final String id, final Continuation<? super EpisodeEntity> $completion) {
    final String _sql = "SELECT * FROM episode WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<EpisodeEntity>() {
      @Override
      @Nullable
      public EpisodeEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAnimeId = CursorUtil.getColumnIndexOrThrow(_cursor, "animeId");
          final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
          final int _cursorIndexOfFiller = CursorUtil.getColumnIndexOrThrow(_cursor, "filler");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfServers = CursorUtil.getColumnIndexOrThrow(_cursor, "servers");
          final int _cursorIndexOfIsDownloaded = CursorUtil.getColumnIndexOrThrow(_cursor, "isDownloaded");
          final int _cursorIndexOfDownloadProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadProgress");
          final int _cursorIndexOfLocalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "localPath");
          final EpisodeEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpAnimeId;
            _tmpAnimeId = _cursor.getString(_cursorIndexOfAnimeId);
            final int _tmpNumber;
            _tmpNumber = _cursor.getInt(_cursorIndexOfNumber);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDateTime _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDateTime(_tmp);
            final Integer _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
            }
            final String _tmpThumbnail;
            if (_cursor.isNull(_cursorIndexOfThumbnail)) {
              _tmpThumbnail = null;
            } else {
              _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
            }
            final boolean _tmpFiller;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfFiller);
            _tmpFiller = _tmp_1 != 0;
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final String _tmpServers;
            _tmpServers = _cursor.getString(_cursorIndexOfServers);
            final boolean _tmpIsDownloaded;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsDownloaded);
            _tmpIsDownloaded = _tmp_2 != 0;
            final float _tmpDownloadProgress;
            _tmpDownloadProgress = _cursor.getFloat(_cursorIndexOfDownloadProgress);
            final String _tmpLocalPath;
            if (_cursor.isNull(_cursorIndexOfLocalPath)) {
              _tmpLocalPath = null;
            } else {
              _tmpLocalPath = _cursor.getString(_cursorIndexOfLocalPath);
            }
            _result = new EpisodeEntity(_tmpId,_tmpAnimeId,_tmpNumber,_tmpTitle,_tmpOverview,_tmpAired,_tmpDuration,_tmpThumbnail,_tmpFiller,_tmpLanguages,_tmpServers,_tmpIsDownloaded,_tmpDownloadProgress,_tmpLocalPath);
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
  public Object getByAnimeId(final String animeId,
      final Continuation<? super List<EpisodeEntity>> $completion) {
    final String _sql = "SELECT * FROM episode WHERE animeId = ? ORDER BY number ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, animeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<EpisodeEntity>>() {
      @Override
      @NonNull
      public List<EpisodeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAnimeId = CursorUtil.getColumnIndexOrThrow(_cursor, "animeId");
          final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
          final int _cursorIndexOfFiller = CursorUtil.getColumnIndexOrThrow(_cursor, "filler");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfServers = CursorUtil.getColumnIndexOrThrow(_cursor, "servers");
          final int _cursorIndexOfIsDownloaded = CursorUtil.getColumnIndexOrThrow(_cursor, "isDownloaded");
          final int _cursorIndexOfDownloadProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadProgress");
          final int _cursorIndexOfLocalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "localPath");
          final List<EpisodeEntity> _result = new ArrayList<EpisodeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EpisodeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpAnimeId;
            _tmpAnimeId = _cursor.getString(_cursorIndexOfAnimeId);
            final int _tmpNumber;
            _tmpNumber = _cursor.getInt(_cursorIndexOfNumber);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDateTime _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDateTime(_tmp);
            final Integer _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
            }
            final String _tmpThumbnail;
            if (_cursor.isNull(_cursorIndexOfThumbnail)) {
              _tmpThumbnail = null;
            } else {
              _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
            }
            final boolean _tmpFiller;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfFiller);
            _tmpFiller = _tmp_1 != 0;
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final String _tmpServers;
            _tmpServers = _cursor.getString(_cursorIndexOfServers);
            final boolean _tmpIsDownloaded;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsDownloaded);
            _tmpIsDownloaded = _tmp_2 != 0;
            final float _tmpDownloadProgress;
            _tmpDownloadProgress = _cursor.getFloat(_cursorIndexOfDownloadProgress);
            final String _tmpLocalPath;
            if (_cursor.isNull(_cursorIndexOfLocalPath)) {
              _tmpLocalPath = null;
            } else {
              _tmpLocalPath = _cursor.getString(_cursorIndexOfLocalPath);
            }
            _item = new EpisodeEntity(_tmpId,_tmpAnimeId,_tmpNumber,_tmpTitle,_tmpOverview,_tmpAired,_tmpDuration,_tmpThumbnail,_tmpFiller,_tmpLanguages,_tmpServers,_tmpIsDownloaded,_tmpDownloadProgress,_tmpLocalPath);
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
  public Flow<List<EpisodeEntity>> observeByAnimeId(final String animeId) {
    final String _sql = "SELECT * FROM episode WHERE animeId = ? ORDER BY number ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, animeId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"episode"}, new Callable<List<EpisodeEntity>>() {
      @Override
      @NonNull
      public List<EpisodeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAnimeId = CursorUtil.getColumnIndexOrThrow(_cursor, "animeId");
          final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
          final int _cursorIndexOfFiller = CursorUtil.getColumnIndexOrThrow(_cursor, "filler");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfServers = CursorUtil.getColumnIndexOrThrow(_cursor, "servers");
          final int _cursorIndexOfIsDownloaded = CursorUtil.getColumnIndexOrThrow(_cursor, "isDownloaded");
          final int _cursorIndexOfDownloadProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadProgress");
          final int _cursorIndexOfLocalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "localPath");
          final List<EpisodeEntity> _result = new ArrayList<EpisodeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EpisodeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpAnimeId;
            _tmpAnimeId = _cursor.getString(_cursorIndexOfAnimeId);
            final int _tmpNumber;
            _tmpNumber = _cursor.getInt(_cursorIndexOfNumber);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDateTime _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDateTime(_tmp);
            final Integer _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
            }
            final String _tmpThumbnail;
            if (_cursor.isNull(_cursorIndexOfThumbnail)) {
              _tmpThumbnail = null;
            } else {
              _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
            }
            final boolean _tmpFiller;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfFiller);
            _tmpFiller = _tmp_1 != 0;
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final String _tmpServers;
            _tmpServers = _cursor.getString(_cursorIndexOfServers);
            final boolean _tmpIsDownloaded;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsDownloaded);
            _tmpIsDownloaded = _tmp_2 != 0;
            final float _tmpDownloadProgress;
            _tmpDownloadProgress = _cursor.getFloat(_cursorIndexOfDownloadProgress);
            final String _tmpLocalPath;
            if (_cursor.isNull(_cursorIndexOfLocalPath)) {
              _tmpLocalPath = null;
            } else {
              _tmpLocalPath = _cursor.getString(_cursorIndexOfLocalPath);
            }
            _item = new EpisodeEntity(_tmpId,_tmpAnimeId,_tmpNumber,_tmpTitle,_tmpOverview,_tmpAired,_tmpDuration,_tmpThumbnail,_tmpFiller,_tmpLanguages,_tmpServers,_tmpIsDownloaded,_tmpDownloadProgress,_tmpLocalPath);
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
  public Object getByNumber(final String animeId, final int number,
      final Continuation<? super EpisodeEntity> $completion) {
    final String _sql = "SELECT * FROM episode WHERE animeId = ? AND number = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, animeId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, number);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<EpisodeEntity>() {
      @Override
      @Nullable
      public EpisodeEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAnimeId = CursorUtil.getColumnIndexOrThrow(_cursor, "animeId");
          final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
          final int _cursorIndexOfFiller = CursorUtil.getColumnIndexOrThrow(_cursor, "filler");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfServers = CursorUtil.getColumnIndexOrThrow(_cursor, "servers");
          final int _cursorIndexOfIsDownloaded = CursorUtil.getColumnIndexOrThrow(_cursor, "isDownloaded");
          final int _cursorIndexOfDownloadProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadProgress");
          final int _cursorIndexOfLocalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "localPath");
          final EpisodeEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpAnimeId;
            _tmpAnimeId = _cursor.getString(_cursorIndexOfAnimeId);
            final int _tmpNumber;
            _tmpNumber = _cursor.getInt(_cursorIndexOfNumber);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDateTime _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDateTime(_tmp);
            final Integer _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
            }
            final String _tmpThumbnail;
            if (_cursor.isNull(_cursorIndexOfThumbnail)) {
              _tmpThumbnail = null;
            } else {
              _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
            }
            final boolean _tmpFiller;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfFiller);
            _tmpFiller = _tmp_1 != 0;
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final String _tmpServers;
            _tmpServers = _cursor.getString(_cursorIndexOfServers);
            final boolean _tmpIsDownloaded;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsDownloaded);
            _tmpIsDownloaded = _tmp_2 != 0;
            final float _tmpDownloadProgress;
            _tmpDownloadProgress = _cursor.getFloat(_cursorIndexOfDownloadProgress);
            final String _tmpLocalPath;
            if (_cursor.isNull(_cursorIndexOfLocalPath)) {
              _tmpLocalPath = null;
            } else {
              _tmpLocalPath = _cursor.getString(_cursorIndexOfLocalPath);
            }
            _result = new EpisodeEntity(_tmpId,_tmpAnimeId,_tmpNumber,_tmpTitle,_tmpOverview,_tmpAired,_tmpDuration,_tmpThumbnail,_tmpFiller,_tmpLanguages,_tmpServers,_tmpIsDownloaded,_tmpDownloadProgress,_tmpLocalPath);
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
  public Object getNext(final String animeId, final int currentNumber,
      final Continuation<? super EpisodeEntity> $completion) {
    final String _sql = "SELECT * FROM episode WHERE animeId = ? AND number > ? ORDER BY number ASC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, animeId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, currentNumber);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<EpisodeEntity>() {
      @Override
      @Nullable
      public EpisodeEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAnimeId = CursorUtil.getColumnIndexOrThrow(_cursor, "animeId");
          final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
          final int _cursorIndexOfFiller = CursorUtil.getColumnIndexOrThrow(_cursor, "filler");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfServers = CursorUtil.getColumnIndexOrThrow(_cursor, "servers");
          final int _cursorIndexOfIsDownloaded = CursorUtil.getColumnIndexOrThrow(_cursor, "isDownloaded");
          final int _cursorIndexOfDownloadProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadProgress");
          final int _cursorIndexOfLocalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "localPath");
          final EpisodeEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpAnimeId;
            _tmpAnimeId = _cursor.getString(_cursorIndexOfAnimeId);
            final int _tmpNumber;
            _tmpNumber = _cursor.getInt(_cursorIndexOfNumber);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDateTime _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDateTime(_tmp);
            final Integer _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
            }
            final String _tmpThumbnail;
            if (_cursor.isNull(_cursorIndexOfThumbnail)) {
              _tmpThumbnail = null;
            } else {
              _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
            }
            final boolean _tmpFiller;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfFiller);
            _tmpFiller = _tmp_1 != 0;
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final String _tmpServers;
            _tmpServers = _cursor.getString(_cursorIndexOfServers);
            final boolean _tmpIsDownloaded;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsDownloaded);
            _tmpIsDownloaded = _tmp_2 != 0;
            final float _tmpDownloadProgress;
            _tmpDownloadProgress = _cursor.getFloat(_cursorIndexOfDownloadProgress);
            final String _tmpLocalPath;
            if (_cursor.isNull(_cursorIndexOfLocalPath)) {
              _tmpLocalPath = null;
            } else {
              _tmpLocalPath = _cursor.getString(_cursorIndexOfLocalPath);
            }
            _result = new EpisodeEntity(_tmpId,_tmpAnimeId,_tmpNumber,_tmpTitle,_tmpOverview,_tmpAired,_tmpDuration,_tmpThumbnail,_tmpFiller,_tmpLanguages,_tmpServers,_tmpIsDownloaded,_tmpDownloadProgress,_tmpLocalPath);
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
  public Object getPrevious(final String animeId, final int currentNumber,
      final Continuation<? super EpisodeEntity> $completion) {
    final String _sql = "SELECT * FROM episode WHERE animeId = ? AND number < ? ORDER BY number DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, animeId);
    _argIndex = 2;
    _statement.bindLong(_argIndex, currentNumber);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<EpisodeEntity>() {
      @Override
      @Nullable
      public EpisodeEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAnimeId = CursorUtil.getColumnIndexOrThrow(_cursor, "animeId");
          final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
          final int _cursorIndexOfFiller = CursorUtil.getColumnIndexOrThrow(_cursor, "filler");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfServers = CursorUtil.getColumnIndexOrThrow(_cursor, "servers");
          final int _cursorIndexOfIsDownloaded = CursorUtil.getColumnIndexOrThrow(_cursor, "isDownloaded");
          final int _cursorIndexOfDownloadProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadProgress");
          final int _cursorIndexOfLocalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "localPath");
          final EpisodeEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpAnimeId;
            _tmpAnimeId = _cursor.getString(_cursorIndexOfAnimeId);
            final int _tmpNumber;
            _tmpNumber = _cursor.getInt(_cursorIndexOfNumber);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDateTime _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDateTime(_tmp);
            final Integer _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
            }
            final String _tmpThumbnail;
            if (_cursor.isNull(_cursorIndexOfThumbnail)) {
              _tmpThumbnail = null;
            } else {
              _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
            }
            final boolean _tmpFiller;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfFiller);
            _tmpFiller = _tmp_1 != 0;
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final String _tmpServers;
            _tmpServers = _cursor.getString(_cursorIndexOfServers);
            final boolean _tmpIsDownloaded;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsDownloaded);
            _tmpIsDownloaded = _tmp_2 != 0;
            final float _tmpDownloadProgress;
            _tmpDownloadProgress = _cursor.getFloat(_cursorIndexOfDownloadProgress);
            final String _tmpLocalPath;
            if (_cursor.isNull(_cursorIndexOfLocalPath)) {
              _tmpLocalPath = null;
            } else {
              _tmpLocalPath = _cursor.getString(_cursorIndexOfLocalPath);
            }
            _result = new EpisodeEntity(_tmpId,_tmpAnimeId,_tmpNumber,_tmpTitle,_tmpOverview,_tmpAired,_tmpDuration,_tmpThumbnail,_tmpFiller,_tmpLanguages,_tmpServers,_tmpIsDownloaded,_tmpDownloadProgress,_tmpLocalPath);
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
  public Object getRecentByLanguage(final String language, final int limit,
      final Continuation<? super List<EpisodeEntity>> $completion) {
    final String _sql = "SELECT * FROM episode WHERE languages LIKE '%' || ? || '%' ORDER BY aired DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, language);
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<EpisodeEntity>>() {
      @Override
      @NonNull
      public List<EpisodeEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfAnimeId = CursorUtil.getColumnIndexOrThrow(_cursor, "animeId");
          final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfOverview = CursorUtil.getColumnIndexOrThrow(_cursor, "overview");
          final int _cursorIndexOfAired = CursorUtil.getColumnIndexOrThrow(_cursor, "aired");
          final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
          final int _cursorIndexOfThumbnail = CursorUtil.getColumnIndexOrThrow(_cursor, "thumbnail");
          final int _cursorIndexOfFiller = CursorUtil.getColumnIndexOrThrow(_cursor, "filler");
          final int _cursorIndexOfLanguages = CursorUtil.getColumnIndexOrThrow(_cursor, "languages");
          final int _cursorIndexOfServers = CursorUtil.getColumnIndexOrThrow(_cursor, "servers");
          final int _cursorIndexOfIsDownloaded = CursorUtil.getColumnIndexOrThrow(_cursor, "isDownloaded");
          final int _cursorIndexOfDownloadProgress = CursorUtil.getColumnIndexOrThrow(_cursor, "downloadProgress");
          final int _cursorIndexOfLocalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "localPath");
          final List<EpisodeEntity> _result = new ArrayList<EpisodeEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final EpisodeEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpAnimeId;
            _tmpAnimeId = _cursor.getString(_cursorIndexOfAnimeId);
            final int _tmpNumber;
            _tmpNumber = _cursor.getInt(_cursorIndexOfNumber);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpOverview;
            if (_cursor.isNull(_cursorIndexOfOverview)) {
              _tmpOverview = null;
            } else {
              _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
            }
            final LocalDateTime _tmpAired;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfAired)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfAired);
            }
            _tmpAired = __converters.toLocalDateTime(_tmp);
            final Integer _tmpDuration;
            if (_cursor.isNull(_cursorIndexOfDuration)) {
              _tmpDuration = null;
            } else {
              _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
            }
            final String _tmpThumbnail;
            if (_cursor.isNull(_cursorIndexOfThumbnail)) {
              _tmpThumbnail = null;
            } else {
              _tmpThumbnail = _cursor.getString(_cursorIndexOfThumbnail);
            }
            final boolean _tmpFiller;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfFiller);
            _tmpFiller = _tmp_1 != 0;
            final String _tmpLanguages;
            _tmpLanguages = _cursor.getString(_cursorIndexOfLanguages);
            final String _tmpServers;
            _tmpServers = _cursor.getString(_cursorIndexOfServers);
            final boolean _tmpIsDownloaded;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsDownloaded);
            _tmpIsDownloaded = _tmp_2 != 0;
            final float _tmpDownloadProgress;
            _tmpDownloadProgress = _cursor.getFloat(_cursorIndexOfDownloadProgress);
            final String _tmpLocalPath;
            if (_cursor.isNull(_cursorIndexOfLocalPath)) {
              _tmpLocalPath = null;
            } else {
              _tmpLocalPath = _cursor.getString(_cursorIndexOfLocalPath);
            }
            _item = new EpisodeEntity(_tmpId,_tmpAnimeId,_tmpNumber,_tmpTitle,_tmpOverview,_tmpAired,_tmpDuration,_tmpThumbnail,_tmpFiller,_tmpLanguages,_tmpServers,_tmpIsDownloaded,_tmpDownloadProgress,_tmpLocalPath);
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
  public Object countByAnimeId(final String animeId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM episode WHERE animeId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, animeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
