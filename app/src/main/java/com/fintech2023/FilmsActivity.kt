package com.fintech2023

import android.os.Bundle
import androidx.activity.ComponentActivity

import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fintech2023.compose.FilmsApp
import com.fintech2023.ui.theme.FilmsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilmsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            FilmsTheme {
                FilmsApp()
            }
        }
    }
}
