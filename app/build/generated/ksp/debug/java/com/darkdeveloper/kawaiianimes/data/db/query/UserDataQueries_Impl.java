package com.darkdeveloper.kawaiianimes.data.db.query;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import com.darkdeveloper.kawaiianimes.data.db.converter.Converters;
import com.darkdeveloper.kawaiianimes.data.db.entity.AnimeEntity;
import com.darkdeveloper.kawaiianimes.data.db.entity.AnimeStatus;
import com.darkdeveloper.kawaiianimes.data.db.entity.AnimeType;
import com.darkdeveloper.kawaiianimes.data.db.entity.DownloadedEpisodeEntity;
import com.darkdeveloper.kawaiianimes.data.db.entity.EpisodeEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDataQueries_Impl implements UserDataQueries {
  private final RoomDatabase __db;

  private final Converters __converters = new Converters();

  public UserDataQueries_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
  }

  @Override
  public Object getFavoriteAnimes(final String userId,
      final Continuation<? super List<AnimeEntity>> $completion) {
    final String _sql = "SELECT a.* FROM anime a INNER JOIN user_favorite uf ON uf.animeId = a.id WHERE uf.userId = ? ORDER BY uf.addedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
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
  public Object getWatchLaterAnimes(final String userId,
      final Continuation<? super List<AnimeEntity>> $completion) {
    final String _sql = "SELECT a.* FROM anime a INNER JOIN user_watch_later uwl ON uwl.animeId = a.id WHERE uwl.userId = ? ORDER BY uwl.addedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
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
  public Object getWatchingAnimes(final String userId,
      final Continuation<? super List<AnimeEntity>> $completion) {
    final String _sql = "SELECT a.*, uw.currentEpisode, uw.progress FROM anime a INNER JOIN user_watching uw ON uw.animeId = a.id WHERE uw.userId = ? ORDER BY uw.updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
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
  public Object getRecentViewedEpisodes(final String userId, final int limit,
      final Continuation<? super List<EpisodeEntity>> $completion) {
    final String _sql = "SELECT e.*, a.name as animeName, a.poster as animePoster FROM episode e INNER JOIN anime a ON a.id = e.animeId INNER JOIN viewed_episode ve ON ve.episodeId = e.id WHERE ve.userId = ? ORDER BY ve.viewedAt DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, userId);
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
  public Object getUserDownloads(final String userId,
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
