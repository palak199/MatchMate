package com.example.matchmatee.ui.home

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.matchmatee.ui.components.ProfileCard
import com.example.matchmatee.utils.InternetCheck
import com.example.matchmatee.viewmodel.HomeViewModel

@Composable
fun HomeScreen(context: Context,
                       snackbarHostState: SnackbarHostState) {
    val viewModel: HomeViewModel = viewModel(factory = HomeViewModel.provideFactory(context))
    val profiles by viewModel.profiles.collectAsState()
    LaunchedEffect(Unit) {
        if(!InternetCheck.isInternetAvailable(context)) {
            Log.d("plk", "internet ni h")
            snackbarHostState.showSnackbar("Please check your Internet connection")
        }
    }

    if (profiles.isNotEmpty()) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

            items(profiles) { profile ->
                ProfileCard(
                    profile = profile,
                    onAccept = {
                        viewModel.acceptProfile(profile)
                        },
                    onReject = { viewModel.rejectProfile(profile) }
                )

            }
        }
        }
    else {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No profiles available.")
        }
    }
}