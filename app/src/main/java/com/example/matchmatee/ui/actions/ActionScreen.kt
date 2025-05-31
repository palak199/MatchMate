package com.example.matchmatee.ui.actions

/* displays all accepted rejected profiles */


import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.matchmatee.ui.components.UpdatedProfileCard
import com.example.matchmatee.viewmodel.ActionViewModel
import com.example.matchmatee.viewmodel.HomeViewModel

@Composable
fun ActionScreen(context: Context) {

    val viewModel = remember { ActionViewModel(context) }
    val acceptedProfiles by viewModel.profiles.collectAsState()
    Log.d("plk", acceptedProfiles.toString())
    if (acceptedProfiles.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(acceptedProfiles) { profile ->
                UpdatedProfileCard(profile = profile)
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
