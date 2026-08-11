package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.CloudOff
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AccentOrange
import com.example.ui.theme.ForestPrimary
import com.example.ui.theme.NaturalBorder
import com.example.ui.theme.SageContainer
import com.example.ui.theme.SageText
import com.example.ui.theme.SurfaceLight
import com.example.ui.theme.TerracottaFlame
import com.example.ui.theme.TextPrimaryLight
import com.example.ui.theme.WarmAmber
import com.example.ui.viewmodel.UiLanguage

@Composable
fun AppTopBar(
    currentLanguage: UiLanguage,
    currentStreak: Int,
    totalXp: Int,
    isOfflineSimulated: Boolean = false,
    hasPendingSync: Boolean = false,
    onLanguageToggle: () -> Unit,
    onStreakClick: () -> Unit,
    onOfflineSyncClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .border(width = (0.5).dp, color = NaturalBorder),
        color = SurfaceLight,
        shadowElevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // App Title Emblem
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(ForestPrimary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "FR",
                        fontWeight = FontWeight.Black,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(
                        text = if (currentLanguage == UiLanguage.URDU) "فرانسیسی سیکھیں" else "Learn French",
                        color = TextPrimaryLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    )
                    Text(
                        text = if (currentLanguage == UiLanguage.URDU) "اردو اور انگریزی کے ساتھ" else "English & Urdu",
                        color = SageText,
                        fontSize = 10.sp
                    )
                }
            }

            // Streak, Offline Sync & Language Switcher
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Offline Sync Button
                Row(
                    modifier = Modifier
                        .testTag("offline_sync_button")
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (isOfflineSimulated) TerracottaFlame.copy(alpha = 0.15f) else SageContainer)
                        .border(1.dp, if (isOfflineSimulated) TerracottaFlame else NaturalBorder, RoundedCornerShape(20.dp))
                        .clickable { onOfflineSyncClick() }
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (isOfflineSimulated) Icons.Default.CloudOff else Icons.Default.CloudDownload,
                        contentDescription = "Offline Sync",
                        tint = if (isOfflineSimulated) TerracottaFlame else ForestPrimary,
                        modifier = Modifier.size(16.dp)
                    )
                    if (hasPendingSync || isOfflineSimulated) {
                        Spacer(modifier = Modifier.width(2.dp))
                        Text(
                            text = if (isOfflineSimulated) "Offline" else "Sync",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isOfflineSimulated) TerracottaFlame else ForestPrimary
                        )
                    }
                }

                Spacer(modifier = Modifier.width(4.dp))

                // Streak Badge
                Row(
                    modifier = Modifier
                        .testTag("streak_badge")
                        .clip(RoundedCornerShape(20.dp))
                        .background(SageContainer)
                        .border(1.dp, NaturalBorder, RoundedCornerShape(20.dp))
                        .clickable { onStreakClick() }
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Whatshot,
                        contentDescription = "Streak",
                        tint = AccentOrange,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = "$currentStreak",
                        color = TextPrimaryLight,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                // Dual-Language Switch Button
                Row(
                    modifier = Modifier
                        .testTag("language_toggle_button")
                        .clip(RoundedCornerShape(20.dp))
                        .background(ForestPrimary)
                        .clickable { onLanguageToggle() }
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Translate,
                        contentDescription = "Language Toggle",
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Text(
                        text = if (currentLanguage == UiLanguage.URDU) "اردو" else "EN",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}

