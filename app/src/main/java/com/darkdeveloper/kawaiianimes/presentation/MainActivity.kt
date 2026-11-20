package com.darkdeveloper.kawaiianimes.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.Material3
import androidx.compose.ui.platform.setContent
import com.darkdeveloper.kawaiianimes.navigation.AppNavHost
import com.darkdeveloper.kawaiianimes.ui.theme.Theme
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinAndroidContext(this)
            Theme {
                Material3 {
                    AppNavHost()
                }
            }
        }
    }
}