package com.example.matchmatee.ui.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.matchmatee.ui.actions.ActionScreen
import com.example.matchmatee.ui.profile.ProfileScreen
import com.example.matchmatee.ui.home.HomeScreen

@Composable
fun MainScaffold() {
    var navController = rememberNavController()
    val items = listOf("home", "matches", "profile")

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { screen ->
                    NavigationBarItem(
                        selected = navController.currentDestination?.route == screen,
                        onClick = { navController.navigate(screen) },
                        icon = {
                            Icon(
                                painter = painterResource(id = getTabIcon(screen)),
                                contentDescription = screen
                            )
                        },
                        label = { Text(screen.capitalize()) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(LocalContext.current) }
            composable("matches") { ActionScreen(LocalContext.current) }
            composable("profile") { ProfileScreen() }
        }
    }
}
private fun getTabIcon(screen: String): Int {
    return when (screen) {
        "home" -> android.R.drawable.ic_menu_myplaces
        "matches" -> android.R.drawable.ic_menu_agenda
        "profile" -> android.R.drawable.ic_menu_info_details
        else -> android.R.drawable.ic_menu_help
    }
}