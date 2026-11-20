package com.darkdeveloper.kawaiianimes.di

import com.darkdeveloper.kawaiianimes.data.remoteconfig.RemoteConfigRepository
import com.darkdeveloper.kawaiianimes.data.security.SecureTokenStore
import org.koin.dsl.module

val securityModule = module {
    single { SecureTokenStore(get()) }
    single { RemoteConfigRepository(get()) }
}