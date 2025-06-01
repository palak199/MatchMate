// File: MainScaffold.kt
package com.example.matchmate.ui.nav

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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.matchmate.R
import com.example.matchmate.ui.actions.ActionScreen
import com.example.matchmate.ui.home.HomeScreen
import com.example.matchmate.ui.profile.ProfileScreen
import com.example.matchmate.utils.TopBar
import com.example.matchmate.viewmodel.HomeViewModel

@Composable
fun MainScaffold() {
    val navController = rememberNavController()
    val items = listOf("home", "matches", "profile")
    val context = LocalContext.current
    val viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(context))
    val snackbarHostState = remember { SnackbarHostState() }
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route
    Scaffold(
        topBar = {
            TopBar(
                title = when (currentRoute) {
                    "home" -> "Matches"
                    "matches" -> "Accepted"
                    "profile" -> "My Profile"
                    else -> "MatchMate"
                },
                showRefresh = when (currentRoute) {
                    "home" -> true
                    else -> false
                },
                onRefresh = {
                    viewModel.refreshProfiles(context)
                }
            )
        },
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
                composable("home") { HomeScreen(LocalContext.current, viewModel, snackbarHostState) }
                composable("matches") { ActionScreen(LocalContext.current) }
                composable("profile") { ProfileScreen() }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 18.dp)
                    .clip(RoundedCornerShape(40.dp)) // Fully round pill
                    .background(Color.White.copy(alpha = 0.4f))
                    .shadow(elevation = 12.dp, shape = RoundedCornerShape(40.dp))
                    .padding(horizontal = 32.dp, vertical = 10.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items.forEach { screen ->
                        IconButton(
                            onClick = { navController.navigate(screen) },
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(
                                    if (currentRoute == screen)
                                        MaterialTheme.colorScheme.background
                                    else
                                        MaterialTheme.colorScheme.surface
                                )
                        ) {
                            Icon(
                                painter = painterResource(id = getTabIcon(screen)),
                                contentDescription = screen,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
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