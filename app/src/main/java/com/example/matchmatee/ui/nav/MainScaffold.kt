// File: MainScaffold.kt
package com.example.matchmatee.ui.nav

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.matchmatee.R
import com.example.matchmatee.ui.actions.ActionScreen
import com.example.matchmatee.ui.home.HomeScreen
import com.example.matchmatee.ui.profile.ProfileScreen

@Composable
fun MainScaffold() {
    val navController = rememberNavController()
    val items = listOf("home", "matches", "profile")
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = {
            // Bottom bar empty, since we use custom floating group
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            // Main content
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(innerPadding)
            ) {
                composable("home") { HomeScreen(LocalContext.current, snackbarHostState) }
                composable("matches") { ActionScreen(LocalContext.current) }
                composable("profile") { ProfileScreen() }
            }

            // Floating group of three round icons
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(24.dp)
                    .shadow(8.dp, RoundedCornerShape(28.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .clip(RoundedCornerShape(28.dp))
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items.forEach { screen ->
                    IconButton(
                        onClick = { navController.navigate(screen) },
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(
                                if (navController.currentDestination?.route == screen)
                                    MaterialTheme.colorScheme.primaryContainer
                                else
                                    MaterialTheme.colorScheme.surface
                            )
                    ) {
                        Icon(
                            painter = painterResource(id = getTabIcon(screen)),
                            contentDescription = screen,
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

        }
    }
}

private fun getTabIcon(screen: String): Int {
    return when (screen) {
        "home" -> R.drawable.ic_home
        "matches" -> R.drawable.ic_heart
        "profile" -> R.drawable.ic_profile
        else -> android.R.drawable.ic_menu_help
    }
}