package com.darkdeveloper.kawaiianimes.data.db.converter

import androidx.room.TypeConverter
import com.darkdeveloper.kawaiianimes.data.db.entity.AnimeType
import com.darkdeveloper.kawaiianimes.data.db.entity.AnimeStatus
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString

class Converters {

    private val json = Json { ignoreUnknownKeys = true }
    private val dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE
    private val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    @TypeConverter
    fun fromAnimeType(type: AnimeType): String = type.name

    @TypeConverter
    fun toAnimeType(name: String): AnimeType = AnimeType.valueOf(name)

    @TypeConverter
    fun fromAnimeStatus(status: AnimeStatus): String = status.name

    @TypeConverter
    fun toAnimeStatus(name: String): AnimeStatus = AnimeStatus.valueOf(name)

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? = date?.format(dateFormatter)

    @TypeConverter
    fun toLocalDate(isoString: String?): LocalDate? = isoString?.let { LocalDate.parse(it, dateFormatter) }

    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime?): String? = dateTime?.format(dateTimeFormatter)

    @TypeConverter
    fun toLocalDateTime(isoString: String?): LocalDateTime? = isoString?.let { LocalDateTime.parse(it, dateTimeFormatter) }

    @TypeConverter
    fun fromStringList(list: List<String>): String = json.encodeToString(list)

    @TypeConverter
    fun toStringList(jsonString: String): List<String> = json.decodeFromString(jsonString)

    // Language and Server converters commented out to debug KSP issue
    // @TypeConverter
    // fun fromLanguageList(list: List<Language>): String = json.encodeToString(list)
    //
    // @TypeConverter
    // fun toLanguageList(jsonString: String): List<Language> = json.decodeFromString(jsonString)
    //
    // @TypeConverter
    // fun fromServerList(list: List<Server>): String = json.encodeToString(list)
    //
    // @TypeConverter
    // fun toServerList(jsonString: String): List<Server> = json.decodeFromString(jsonString)
}