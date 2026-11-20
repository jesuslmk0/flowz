package com.darkdeveloper.kawaiianimes.data.db.query;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import com.darkdeveloper.kawaiianimes.data.db.entity.PlayerEntity;
import com.darkdeveloper.kawaiianimes.data.db.entity.ServerEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class PlayerQueries_Impl implements PlayerQueries {
  private final RoomDatabase __db;

  public PlayerQueries_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public Object getByEpisodeId(final String episodeId,
      final Continuation<? super List<PlayerEntity>> $completion) {
    final String _sql = "SELECT * FROM player WHERE episodeId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, episodeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PlayerEntity>>() {
      @Override
      @NonNull
      public List<PlayerEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEpisodeId = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeId");
          final int _cursorIndexOfEmbedUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "embedUrl");
          final int _cursorIndexOfReferer = CursorUtil.getColumnIndexOrThrow(_cursor, "referer");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfVideoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "videoUrl");
          final int _cursorIndexOfServers = CursorUtil.getColumnIndexOrThrow(_cursor, "servers");
          final List<PlayerEntity> _result = new ArrayList<PlayerEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PlayerEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpEpisodeId;
            _tmpEpisodeId = _cursor.getString(_cursorIndexOfEpisodeId);
            final String _tmpEmbedUrl;
            _tmpEmbedUrl = _cursor.getString(_cursorIndexOfEmbedUrl);
            final String _tmpReferer;
            _tmpReferer = _cursor.getString(_cursorIndexOfReferer);
            final String _tmpLanguage;
            _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            final String _tmpVideoUrl;
            if (_cursor.isNull(_cursorIndexOfVideoUrl)) {
              _tmpVideoUrl = null;
            } else {
              _tmpVideoUrl = _cursor.getString(_cursorIndexOfVideoUrl);
            }
            final String _tmpServers;
            _tmpServers = _cursor.getString(_cursorIndexOfServers);
            _item = new PlayerEntity(_tmpId,_tmpEpisodeId,_tmpEmbedUrl,_tmpReferer,_tmpLanguage,_tmpVideoUrl,_tmpServers);
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
  public Object getById(final String id, final Continuation<? super PlayerEntity> $completion) {
    final String _sql = "SELECT * FROM player WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PlayerEntity>() {
      @Override
      @Nullable
      public PlayerEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEpisodeId = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeId");
          final int _cursorIndexOfEmbedUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "embedUrl");
          final int _cursorIndexOfReferer = CursorUtil.getColumnIndexOrThrow(_cursor, "referer");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfVideoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "videoUrl");
          final int _cursorIndexOfServers = CursorUtil.getColumnIndexOrThrow(_cursor, "servers");
          final PlayerEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpEpisodeId;
            _tmpEpisodeId = _cursor.getString(_cursorIndexOfEpisodeId);
            final String _tmpEmbedUrl;
            _tmpEmbedUrl = _cursor.getString(_cursorIndexOfEmbedUrl);
            final String _tmpReferer;
            _tmpReferer = _cursor.getString(_cursorIndexOfReferer);
            final String _tmpLanguage;
            _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            final String _tmpVideoUrl;
            if (_cursor.isNull(_cursorIndexOfVideoUrl)) {
              _tmpVideoUrl = null;
            } else {
              _tmpVideoUrl = _cursor.getString(_cursorIndexOfVideoUrl);
            }
            final String _tmpServers;
            _tmpServers = _cursor.getString(_cursorIndexOfServers);
            _result = new PlayerEntity(_tmpId,_tmpEpisodeId,_tmpEmbedUrl,_tmpReferer,_tmpLanguage,_tmpVideoUrl,_tmpServers);
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
  public Object getServersByEpisodeId(final String episodeId,
      final Continuation<? super List<ServerEntity>> $completion) {
    final String _sql = "SELECT * FROM server WHERE episodeId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, episodeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ServerEntity>>() {
      @Override
      @NonNull
      public List<ServerEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEpisodeId = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfEmbedUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "embedUrl");
          final int _cursorIndexOfQuality = CursorUtil.getColumnIndexOrThrow(_cursor, "quality");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfIsPremium = CursorUtil.getColumnIndexOrThrow(_cursor, "isPremium");
          final List<ServerEntity> _result = new ArrayList<ServerEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ServerEntity _item;
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpEpisodeId;
            _tmpEpisodeId = _cursor.getString(_cursorIndexOfEpisodeId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final int _tmpType;
            _tmpType = _cursor.getInt(_cursorIndexOfType);
            final String _tmpEmbedUrl;
            _tmpEmbedUrl = _cursor.getString(_cursorIndexOfEmbedUrl);
            final Integer _tmpQuality;
            if (_cursor.isNull(_cursorIndexOfQuality)) {
              _tmpQuality = null;
            } else {
              _tmpQuality = _cursor.getInt(_cursorIndexOfQuality);
            }
            final String _tmpLanguage;
            _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            final int _tmpStatus;
            _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
            final boolean _tmpIsPremium;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPremium);
            _tmpIsPremium = _tmp != 0;
            _item = new ServerEntity(_tmpId,_tmpEpisodeId,_tmpName,_tmpType,_tmpEmbedUrl,_tmpQuality,_tmpLanguage,_tmpStatus,_tmpIsPremium);
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
  public Object getServerById(final String id,
      final Continuation<? super ServerEntity> $completion) {
    final String _sql = "SELECT * FROM server WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<ServerEntity>() {
      @Override
      @Nullable
      public ServerEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfEpisodeId = CursorUtil.getColumnIndexOrThrow(_cursor, "episodeId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfEmbedUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "embedUrl");
          final int _cursorIndexOfQuality = CursorUtil.getColumnIndexOrThrow(_cursor, "quality");
          final int _cursorIndexOfLanguage = CursorUtil.getColumnIndexOrThrow(_cursor, "language");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfIsPremium = CursorUtil.getColumnIndexOrThrow(_cursor, "isPremium");
          final ServerEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            _tmpId = _cursor.getString(_cursorIndexOfId);
            final String _tmpEpisodeId;
            _tmpEpisodeId = _cursor.getString(_cursorIndexOfEpisodeId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final int _tmpType;
            _tmpType = _cursor.getInt(_cursorIndexOfType);
            final String _tmpEmbedUrl;
            _tmpEmbedUrl = _cursor.getString(_cursorIndexOfEmbedUrl);
            final Integer _tmpQuality;
            if (_cursor.isNull(_cursorIndexOfQuality)) {
              _tmpQuality = null;
            } else {
              _tmpQuality = _cursor.getInt(_cursorIndexOfQuality);
            }
            final String _tmpLanguage;
            _tmpLanguage = _cursor.getString(_cursorIndexOfLanguage);
            final int _tmpStatus;
            _tmpStatus = _cursor.getInt(_cursorIndexOfStatus);
            final boolean _tmpIsPremium;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsPremium);
            _tmpIsPremium = _tmp != 0;
            _result = new ServerEntity(_tmpId,_tmpEpisodeId,_tmpName,_tmpType,_tmpEmbedUrl,_tmpQuality,_tmpLanguage,_tmpStatus,_tmpIsPremium);
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
  public Object getLanguagesForEpisode(final String episodeId,
      final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT DISTINCT language FROM player WHERE episodeId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, episodeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            _item = _cursor.getString(0);
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
  public Object getLanguagesForAnime(final String animeId,
      final Continuation<? super List<String>> $completion) {
    final String _sql = "SELECT DISTINCT p.language FROM player p INNER JOIN episode e ON e.id = p.episodeId WHERE e.animeId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, animeId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<String>>() {
      @Override
      @NonNull
      public List<String> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<String> _result = new ArrayList<String>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final String _item;
            _item = _cursor.getString(0);
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
