package com.example.matchmatee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.matchmatee.ui.nav.MainScaffold
import com.example.matchmatee.ui.theme.MatchMateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MatchMateTheme {
                MainScaffold()
            }
        }
    }
}
