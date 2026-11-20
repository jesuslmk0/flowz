package com.darkdeveloper.kawaiianimes.data.db.query;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import com.darkdeveloper.kawaiianimes.data.db.converter.Converters;
import com.darkdeveloper.kawaiianimes.data.db.entity.AnimeEntity;
import com.darkdeveloper.kawaiianimes.data.db.entity.AnimeStatus;
import com.darkdeveloper.kawaiianimes.data.db.entity.AnimeType;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AnimeQueries_Impl implements AnimeQueries {
  private final RoomDatabase __db;

  private final Converters __converters = new Converters();

  public AnimeQueries_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public Object getAnimesWithEpisodes(final int limit,
      final Continuation<? super List<AnimeEntity>> $completion) {
    final String _sql = "\n"
            + "        SELECT a.*, COUNT(e.id) as episode_count\n"
            + "        FROM anime a\n"
            + "        LEFT JOIN episode e ON e.animeId = a.id\n"
            + "        GROUP BY a.id\n"
            + "        HAVING episode_count > 0\n"
            + "        ORDER BY a.views DESC\n"
            + "        LIMIT ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
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
  public Object getAnimesByLanguage(final String language, final int limit, final int offset,
      final Continuation<? super List<AnimeEntity>> $completion) {
    final String _sql = "\n"
            + "        SELECT DISTINCT a.*\n"
            + "        FROM anime a\n"
            + "        INNER JOIN episode e ON e.animeId = a.id\n"
            + "        WHERE e.languages LIKE '%' || ? || '%'\n"
            + "        ORDER BY a.updatedAt DESC\n"
            + "        LIMIT ? OFFSET ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 3);
    int _argIndex = 1;
    _statement.bindString(_argIndex, language);
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
  public Object getByIds(final List<String> ids,
      final Continuation<? super List<AnimeEntity>> $completion) {
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM anime WHERE id IN (");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (String _item : ids) {
      _statement.bindString(_argIndex, _item);
      _argIndex++;
    }
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
            final AnimeEntity _item_1;
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
            _item_1 = new AnimeEntity(_tmpId,_tmpName,_tmpNameAlternative,_tmpPoster,_tmpBanner,_tmpOverview,_tmpAired,_tmpType,_tmpStatus,_tmpGenres,_tmpRating,_tmpVoteAverage,_tmpSeason,_tmpBroadcast,_tmpPrequel,_tmpSequel,_tmpRelated,_tmpViews,_tmpUpdatedAt,_tmpLanguages,_tmpIsFavorite,_tmpIsWatchLater,_tmpLastViewedAt);
            _result.add(_item_1);
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
