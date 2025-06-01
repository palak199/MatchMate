package com.example.matchmatee.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.matchmatee.R
import com.example.matchmatee.domain.UserProfile
import com.example.matchmatee.ui.theme.LightGradient
import com.example.matchmatee.ui.theme.MatchMateTheme
import kotlinx.coroutines.delay

@Composable
fun ProfileCard(
    profile: UserProfile,
    onAccept: () -> Unit,
    onReject: () -> Unit
) {
    var triggerReject by remember { mutableStateOf(false) }
    var triggerAccept by remember { mutableStateOf(false) }
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(triggerAccept) {
        if(triggerAccept) {
            delay(500)
            onAccept()
            triggerAccept = false
        }
    }

    LaunchedEffect(triggerReject) {
        if(triggerReject) {
            delay(500)
            onReject()
            triggerReject = false
        }
    }
    AnimatedVisibility(
        visible = visible,
        exit = shrinkVertically(animationSpec = tween(500)) + fadeOut(animationSpec = tween(500))
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(380.dp)
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(20.dp)),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    AsyncImage(
                        model = profile.imageUrl,
                        contentDescription = "Profile Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                        contentScale = ContentScale.Crop,
                        placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
                        error = painterResource(id = R.drawable.error_imagef)
                    )

                    // Floating Match Score Badge
                    val badgeColor = when {
                        profile.matchScore >= 90 -> Color(0xFF388E3C) // Green
                        profile.matchScore >= 70 -> Color(0xFFFFA000) // Amber
                        else -> Color(0xFFD32F2F)                    // Red
                    }

                    Text(
                        text = "Match ${profile.matchScore}%",
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .background(badgeColor, shape = RoundedCornerShape(12.dp))
                            .padding(horizontal = 10.dp, vertical = 4.dp),
                        color = Color.White,
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = profile.name,
                        color = Color.DarkGray,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "${profile.age} yrs • ${profile.city}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "${profile.education} • ${profile.religion}, ${profile.community}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircleActionButton(
                            icon = Icons.Filled.Close,
                            background = Color(0xFFEF5350),
                            onClick = {
                                visible = false
                                triggerReject = true
                            }
                        )
                        CircleActionButton(
                            icon = Icons.Filled.Check,
                            background = Color(0xFF66BB6A),
                            onClick = {
                                visible = false
                                triggerAccept = true
                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun CircleActionButton(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    background: Color,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .shadow(elevation = 4.dp, shape = CircleShape)
            .clip(CircleShape)
            .background(background),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}
