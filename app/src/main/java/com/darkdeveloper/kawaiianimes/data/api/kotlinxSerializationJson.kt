package com.darkdeveloper.kawaiianimes.data.api

import kotlinx.serialization.json.Json

val kotlinxSerializationJson = Json {
    ignoreUnknownKeys = true
    isLenient = true
    allowSpecialFloatingPointValues = true
    useArrayPolymorphism = false
}