package com.mumuca.mumucabass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import com.mumuca.mumucabass.ui.navigation.AppNavigation
import com.mumuca.mumucabass.ui.navigation.MumucaBassApp
import com.mumuca.mumucabass.ui.theme.MumucaBassTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MumucaBassTheme {
                MumucaBassApp()
            }
        }
    }
}