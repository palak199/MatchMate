package com.example.matchmatee.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.matchmatee.R
import com.example.matchmatee.domain.UserProfile
import com.example.matchmatee.ui.theme.DarkGradient
import com.example.matchmatee.ui.theme.LightGradient

@Composable
fun ProfileScreen() {
    val gradient = if(isSystemInDarkTheme()) DarkGradient else LightGradient
    val currentUser = UserProfile(
        uuid = "me",
        name = "Kapil Sharma",
        age = 32,
        city = "Ahmedabad",
        imageUrl = "https://randomuser.me/api/portraits/men/10.jpg",
        education = "M.Tech",
        religion = "Hindu",
        community = "Rajput",
        profession = "Engineer"
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = gradient),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .height(525.dp)
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .background(MaterialTheme.colorScheme.background),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),

            ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )  {
                AsyncImage(
                    model = currentUser.imageUrl,
                    contentDescription = "Your Photo",
                    modifier = Modifier
                        .size(180.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentScale = ContentScale.Crop,
                    error = painterResource(id = R.drawable.error_imagef)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "${currentUser.name}, ${currentUser.age}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                Text(
                    text = currentUser.city,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(16.dp))

                ProfileInfoRow("Education", currentUser.education)
                ProfileInfoRow("Religion", currentUser.religion)
                ProfileInfoRow("Community", currentUser.community)
                ProfileInfoRow("Profession", currentUser.profession)
            }
        }
    }
}

@Composable
fun ProfileInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontWeight = FontWeight.SemiBold, color = MaterialTheme.colorScheme.onPrimary)
        Text(text = value, color = MaterialTheme.colorScheme.onPrimary)
    }
}
