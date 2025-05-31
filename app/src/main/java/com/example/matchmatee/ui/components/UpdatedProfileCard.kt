package com.example.matchmatee.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
fun UpdatedProfileCard(profile: UserProfile) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column {
            AsyncImage(
                model = profile.imageUrl,
                contentDescription = "Profile Image",
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
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Status:")
                    val status = if (profile.isAccepted == true) "Accepted" else "Rejected"
                    val color = if (profile.isAccepted == true) Color(0xFF2E7D32) else Color.Red

                    Text(
                        text = status,
                        color = color,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
