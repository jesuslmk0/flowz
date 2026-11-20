package com.darkdeveloper.kawaiianimes

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import com.darkdeveloper.kawaiianimes.di.*

class KawaiiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@KawaiiApplication)
            modules(
                apiModule,
                databaseModule,
                repositoryModule,
                useCaseModule,
                extractorModule,
                playerModule,
                viewModelModule,
                securityModule
            )
        }
    }
}