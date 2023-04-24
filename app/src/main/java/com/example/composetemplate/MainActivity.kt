package com.example.composetemplate

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.composetemplate.navigation.MainNavHost
import com.example.composetemplate.ui.theme.ComposeTemplateTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTemplateTheme {
                MainNavHost(
                    showToast = { text ->
                        Toast.makeText(this, text, Toast.LENGTH_SHORT).apply {
                            this.show()
                        }
                    },
                    onBack = { this.finishAffinity() }
                )
            }
        }
    }
}