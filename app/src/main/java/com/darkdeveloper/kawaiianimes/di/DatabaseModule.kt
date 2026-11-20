package com.darkdeveloper.kawaiianimes.di

import android.content.Context
import androidx.room.Room
import com.darkdeveloper.kawaiianimes.data.db.KawaiiDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { KawaiiDatabase::class.java }
    single<Context> { androidContext() }
    single { Room.databaseBuilder(get<Context>(), KawaiiDatabase::class.java, KawaiiDatabase.DATABASE_NAME).build() }
}