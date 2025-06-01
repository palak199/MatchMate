package com.example.matchmatee.ui.home

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.matchmatee.ui.components.ProfileCard
import com.example.matchmatee.ui.theme.LightGradient
import com.example.matchmatee.ui.theme.DarkGradient
import com.example.matchmatee.utils.InternetCheck
import com.example.matchmatee.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    context: Context,
    viewModel: HomeViewModel,
    snackbarHostState: SnackbarHostState
) {
    val gradient = if(isSystemInDarkTheme()) DarkGradient else LightGradient
    val profiles by viewModel.profiles.collectAsState()

    // Internet check and show snackbar if no internet
    LaunchedEffect(Unit) {
        viewModel.msg.collect {
            message -> snackbarHostState.showSnackbar(message)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient)
    ) {
        if (profiles.isEmpty()) {
            // Show message if no profiles
            Text(
                text = "No profiles available.",
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            // Show profiles list
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 12.dp, bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(profiles) { profile ->
                    ProfileCard(
                        profile = profile,
                        onAccept = { viewModel.acceptProfile(profile) },
                        onReject = { viewModel.rejectProfile(profile) }
                    )
                }
            }
        }
    }
}
