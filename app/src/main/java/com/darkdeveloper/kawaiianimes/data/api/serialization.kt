package com.darkdeveloper.kawaiianimes.data.api

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.isLenient
import kotlinx.serialization.json.ignoreUnknownKeys

val kotlinxSerializationJson = Json {
    ignoreUnknownKeys = true
    isLenient = true
    encodeDefaults = false
    prettyPrint = false
    allowSpecialFloatingPointValues = true
    coerceInputValues = true
    useArrayPolymorphism = false
    classDiscriminator = "type"
}