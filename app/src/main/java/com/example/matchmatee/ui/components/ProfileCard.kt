package com.example.matchmatee.ui.components

import android.util.Log
import android.view.RoundedCorner
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.matchmatee.R
import com.example.matchmatee.domain.UserProfile

@Composable
fun ProfileCard(
    profile: UserProfile,
    onAccept: () -> Unit,
    onReject: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            AsyncImage(
                model = profile.imageUrl,
                contentDescription = "Profile img",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
                error = painterResource(id = R.drawable.error_imagef)
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "${profile.name}, ${profile.age}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = profile.city,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Match Score: ${profile.matchScore}",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = onReject,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Reject")
                    }

                    Button(
                        onClick = onAccept,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
                    ) {
                        Text("Accept")
                    }
                }
            }
        }
    }
}