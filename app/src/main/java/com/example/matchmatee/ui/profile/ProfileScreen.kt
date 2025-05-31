package com.example.matchmatee.ui.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.matchmatee.domain.UserProfile

@Composable
fun ProfileScreen() {
    // Static dummy user for now
    val currentUser = UserProfile(
        uuid = "me",
        name = "Your Name",
        age = 27,
        city = "Your City",
        imageUrl = "https://randomuser.me/api/portraits/men/99.jpg",
        education = "M.Tech",
        religion = "Hindu",
        community = "Rajput"
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
                .size(180.dp),
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
