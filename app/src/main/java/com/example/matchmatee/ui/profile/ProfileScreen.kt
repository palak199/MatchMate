package com.example.matchmatee.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.matchmatee.domain.UserProfile

@Composable
fun ProfileScreen() {
    val currentUser = UserProfile(
        uuid = "me",
        name = "Kapil Sharma",
        age = 27,
        city = "Ahmedabad",
        imageUrl = "https://randomuser.me/api/portraits/men/99.jpg",
        education = "M.Tech",
        religion = "Hindu",
        community = "Rajput",
        profession = "Engineer"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = currentUser.imageUrl,
            contentDescription = "Your Photo",
            modifier = Modifier
                .size(180.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "${currentUser.name}, ${currentUser.age}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
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

@Composable
fun ProfileInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, fontWeight = FontWeight.SemiBold)
        Text(text = value)
    }
}
