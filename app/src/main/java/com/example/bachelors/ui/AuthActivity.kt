package com.example.bachelors.ui

import LogInScreenRoute
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.bachelors.features.common.components.ToastHost

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            Box {
                LogInScreenRoute()
                ToastHost()
            }
        }
    }
}