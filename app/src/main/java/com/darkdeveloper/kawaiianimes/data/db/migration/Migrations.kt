package com.darkdeveloper.kawaiianimes.data.db.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object Migrations {
    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Future migrations will go here
        }
    }

    val ALL = listOf(MIGRATION_1_2)
}