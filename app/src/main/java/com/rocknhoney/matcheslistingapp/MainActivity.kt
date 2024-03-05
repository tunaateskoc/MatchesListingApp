package com.rocknhoney.matcheslistingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.rocknhoney.matcheslistingapp.core.presentation.navigation.Navigation
import com.rocknhoney.matcheslistingapp.core.presentation.theme.MAIN_COLOR
import com.rocknhoney.matcheslistingapp.core.presentation.theme.MatchesListingAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MatchesListingAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MAIN_COLOR
                ) {
                    Navigation()
                }
            }
        }
    }
}