package com.example.matchmate.ui.actions

/* displays all accepted rejected profiles */


import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.matchmate.ui.components.UpdatedProfileCard
import com.example.matchmate.ui.theme.DarkGradient
import com.example.matchmate.ui.theme.LightGradient
import com.example.matchmate.viewmodel.ActionViewModel

@Composable
fun ActionScreen(context: Context) {
    val gradient = if(isSystemInDarkTheme()) DarkGradient else LightGradient
    val viewModel = remember { ActionViewModel(context) }
    val acceptedProfiles by viewModel.profiles.collectAsState()
    if (acceptedProfiles.isNotEmpty()) {
        Box(
            modifier = Modifier.background(brush = gradient),
        ) {
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
    }
    else {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(brush = gradient),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "There are no accepted profiles :(",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}
