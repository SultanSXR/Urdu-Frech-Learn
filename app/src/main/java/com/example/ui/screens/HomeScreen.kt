package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Style
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.FrenchDataRepository
import com.example.ui.components.AudioPlayButton
import com.example.ui.components.UrduFrenchPhoneticSpotlightCard
import com.example.ui.theme.AccentOrange
import com.example.ui.theme.AmberGold
import com.example.ui.theme.CrimsonRed
import com.example.ui.theme.ForestPrimary
import com.example.ui.theme.NaturalBorder
import com.example.ui.theme.NavyLight
import com.example.ui.theme.NavyPrimary
import com.example.ui.theme.SageContainer
import com.example.ui.theme.SageText
import com.example.ui.theme.TextPrimaryLight
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.UiLanguage

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    userStreak: Int,
    totalXp: Int,
    dailyCompleted: Int,
    dailyTarget: Int,
    uiLanguage: UiLanguage,
    onNavigate: (AppScreen) -> Unit,
    modifier: Modifier = Modifier
) {
    val isUrdu = uiLanguage == UiLanguage.URDU
    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Welcome Banner Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, NaturalBorder, RoundedCornerShape(28.dp))
                .testTag("home_welcome_card"),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = SageContainer),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = if (isUrdu) "خوش آمدید! 🇫🇷" else "Bonjour & Welcome! 🇫🇷",
                                color = ForestPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (isUrdu) "فرانسیسی زبان اردو تلفظ کے ساتھ" else "French with English & Urdu Phonetics",
                                color = TextPrimaryLight,
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 20.sp
                            )
                        }

                        // Flame Icon
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(AccentOrange.copy(alpha = 0.18f))
                                .clickable { onNavigate(AppScreen.STREAK_STATS) },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Whatshot,
                                contentDescription = "Streak Flame",
                                tint = AccentOrange,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Daily Streak & Goal Bar
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = if (isUrdu) "روزانہ کا ہدف: $dailyCompleted/$dailyTarget مشقیں" else "Daily Goal: $dailyCompleted/$dailyTarget exercises",
                                    color = SageText,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = "$userStreak Days Streak 🔥",
                                    color = AccentOrange,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            LinearProgressIndicator(
                                progress = { (dailyCompleted.toFloat() / dailyTarget.coerceAtLeast(1)).coerceIn(0f, 1f) },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .clip(CircleShape),
                                color = ForestPrimary,
                                trackColor = Color.White.copy(alpha = 0.6f)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Urdu-French Phonetic Advantage Card
        UrduFrenchPhoneticSpotlightCard(isUrduUi = isUrdu)

        Spacer(modifier = Modifier.height(18.dp))

        // Quick Navigation Grid / Hub
        Text(
            text = if (isUrdu) "اہم زمرے" else "Learning Modules",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HomeQuickCard(
                titleEn = "Alphabet & Sounds",
                titleUr = "حروف اور آوازیں",
                descEn = "Audio for every letter",
                descUr = "ہر حرف کی فرانسیسی آواز",
                icon = Icons.Default.Abc,
                badgeText = "A - Z",
                badgeBg = NavyPrimary,
                onClick = { onNavigate(AppScreen.ALPHABET) },
                modifier = Modifier.weight(1f)
            )

            HomeQuickCard(
                titleEn = "Interactive Lessons",
                titleUr = "تعلیمی اسباق",
                descEn = "Quizzes & dialogues",
                descUr = "سوالات اور گفتگو",
                icon = Icons.Default.Book,
                badgeText = "50 XP",
                badgeBg = CrimsonRed,
                onClick = { onNavigate(AppScreen.LESSONS) },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HomeQuickCard(
                titleEn = "Flashcards",
                titleUr = "3D فلیش کارڈز",
                descEn = "Flip & memorize",
                descUr = "الفاظ یاد کریں",
                icon = Icons.Default.Style,
                badgeText = "Vocab",
                badgeBg = AccentOrange,
                onClick = { onNavigate(AppScreen.FLASHCARDS) },
                modifier = Modifier.weight(1f)
            )

            HomeQuickCard(
                titleEn = "Pronunciation Lab",
                titleUr = "تلفظ کی مشق",
                descEn = "Voice recognition %",
                descUr = "آواز کی جانچ",
                icon = Icons.Default.Mic,
                badgeText = "Voice",
                badgeBg = Color(0xFF7B1FA2),
                onClick = { onNavigate(AppScreen.PRONUNCIATION_LAB) },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            HomeQuickCard(
                titleEn = "Offline Downloads & Sync",
                titleUr = "آف لائن ڈاؤن لوڈز اور سنک",
                descEn = "Download lessons for offline use",
                descUr = "بغیر انٹرنیٹ کے سیکھیں",
                icon = Icons.Default.CloudDownload,
                badgeText = "Offline",
                badgeBg = ForestPrimary,
                onClick = { onNavigate(AppScreen.OFFLINE_SYNC) },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Word of the Day Spotlight
        val wordOfTheDay = FrenchDataRepository.flashcards.first()
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, NaturalBorder, RoundedCornerShape(24.dp)),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (isUrdu) "آج کا فرانسیسی لفظ" else "Word of the Day 🌟",
                        color = ForestPrimary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    AudioPlayButton(
                        onClick = { viewModel.speakFrench(wordOfTheDay.frenchWord) }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = wordOfTheDay.frenchWord,
                    fontWeight = FontWeight.Black,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "EN: ${wordOfTheDay.frenchPhoneticEn} • ",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                    Text(
                        text = "اردو: ${wordOfTheDay.frenchPhoneticUr}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = ForestPrimary
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = if (isUrdu) "معنی: ${wordOfTheDay.urduMeaning} (${wordOfTheDay.englishMeaning})" else "Meaning: ${wordOfTheDay.englishMeaning} | ${wordOfTheDay.urduMeaning}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun HomeQuickCard(
    titleEn: String,
    titleUr: String,
    descEn: String,
    descUr: String,
    icon: ImageVector,
    badgeText: String,
    badgeBg: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .border(1.dp, NaturalBorder, RoundedCornerShape(24.dp))
            .clip(RoundedCornerShape(24.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(38.dp)
                        .clip(CircleShape)
                        .background(badgeBg.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, contentDescription = titleEn, tint = badgeBg)
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(badgeBg)
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(badgeText, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = titleEn,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurface
            )

            Text(
                text = titleUr,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp,
                color = ForestPrimary
            )

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = descUr,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}
