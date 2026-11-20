# ProGuard rules for KawaiiAnimes

# Keep Koin annotations
-keep class org.koin.** { *; }
-keepclassmembers class * {
    @org.koin.core.qualifier.Qualifier *;
}

# Keep Kotlin serialization
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}

# Keep Ktor
-keep class io.ktor.** { *; }
-keepclassmembers class * {
    @io.ktor.client.plugins.contentnegotiation.ContentNegotiation *;
}

# Keep Room
-keep class androidx.room.** { *; }
-keep class com.darkdeveloper.kawaiianimes.data.db.** { *; }

# Keep ExoPlayer
-keep class androidx.media3.** { *; }

# Keep Coil
-keep class coil.** { *; }
-keep class io.coil.** { *; }

# Keep Coroutines
-keep class kotlinx.coroutines.** { *; }

# Keep Kotlinx Datetime
-keep class kotlinx.datetime.** { *; }

# Keep Accompanist
-keep class com.google.accompanist.** { *; }

# Keep Material3
-keep class androidx.compose.material3.** { *; }

# Keep Navigation
-keep class androidx.navigation.** { *; }

# Keep our models
-keep class com.darkdeveloper.kawaiianimes.domain.model.** { *; }
-keep class com.darkdeveloper.kawaiianimes.data.api.dto.** { *; }
-keep class com.darkdeveloper.kawaiianimes.data.db.entity.** { *; }

# Keep Extractors
-keep class com.darkdeveloper.kawaiianimes.data.extractors.** { *; }

# Remove logging in release
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
}