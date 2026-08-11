package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speech.SpeechState
import com.example.ui.components.AudioPlayButton
import com.example.ui.components.SpeechMicButton
import com.example.ui.theme.AccentGreen
import com.example.ui.theme.AmberGold
import com.example.ui.theme.NavyPrimary
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.UiLanguage

@Composable
fun PronunciationLabScreen(
    viewModel: MainViewModel,
    uiLanguage: UiLanguage,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isUrdu = uiLanguage == UiLanguage.URDU
    val speechState by viewModel.speechState.collectAsState()

    var targetPhrase by remember { mutableStateOf("Bonjour, comment allez-vous?") }

    val presetPhrases = listOf(
        "Bonjour, comment allez-vous?",
        "Merci beaucoup",
        "S'il vous plaît",
        "Je m'appelle Ali",
        "Enchanté de vous rencontrer",
        "Au revoir et à bientôt"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = NavyPrimary)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = if (isUrdu) "فرانسیسی تلفظ لیبارٹری 🎙️" else "French Pronunciation Lab 🎙️",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )
                Text(
                    text = if (isUrdu) "بولیں اور فرانسیسی لہجے کی جانچ کریں" else "Speak French & get instant accuracy score %",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Preset Phrase Quick Selectors
        Text(
            text = if (isUrdu) "مقبول جملے کا انتخاب کریں:" else "Select a French phrase to test:",
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 8.dp)
        ) {
            items(presetPhrases) { phrase ->
                val isSelected = targetPhrase == phrase
                FilterChip(
                    selected = isSelected,
                    onClick = {
                        targetPhrase = phrase
                        viewModel.speechHelper.reset()
                    },
                    label = { Text(phrase, fontSize = 12.sp, fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = NavyPrimary,
                        selectedLabelColor = Color.White
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Target Phrase Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("pronunciation_lab_target_card"),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isUrdu) "فرانسیسی جملہ:" else "Target French Phrase:",
                    fontSize = 12.sp,
                    color = AmberGold,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = targetPhrase,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = NavyPrimary,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    AudioPlayButton(
                        size = 48.dp,
                        onClick = { viewModel.speakFrench(targetPhrase) }
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = if (isUrdu) "پہلے فرانسیسی آڈیو سنیں" else "Listen native audio first",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = NavyPrimary
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Voice Recording Section
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = NavyPrimary.copy(alpha = 0.06f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isUrdu) "مائیک پر ٹیپ کریں اور بولیں:" else "Tap microphone to test your French pronunciation:",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                SpeechMicButton(
                    isListening = speechState is SpeechState.Listening,
                    size = 72.dp,
                    onMicClick = {
                        if (speechState is SpeechState.Listening) {
                            viewModel.speechHelper.stopListening()
                        } else {
                            viewModel.speechHelper.startListening(targetPhrase)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Evaluation Results State
                when (val state = speechState) {
                    is SpeechState.Listening -> {
                        Text(
                            text = if (isUrdu) "سنا جا رہا ہے... فرانسیسی بولیں!" else "Listening... Speak French now!",
                            color = NavyPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }
                    is SpeechState.Success -> {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = AccentGreen.copy(alpha = 0.15f)),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "${state.accuracyPercentage}% Accuracy",
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Black,
                                    color = AccentGreen
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Text(
                                    text = if (isUrdu) state.feedbackMessageUr else state.feedbackMessageEn,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "Spoken: \"${state.recognizedText}\"",
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                                )
                            }
                        }
                    }
                    is SpeechState.Error -> {
                        Text(
                            text = if (isUrdu) state.messageUr else state.messageEn,
                            color = MaterialTheme.colorScheme.error,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                    SpeechState.Idle -> {
                        Text(
                            text = if (isUrdu) "فرانسیسی آواز ریکارڈ کرنے کے لیے مائیک دبائیں" else "Tap mic to record speech",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }
                }
            }
        }
    }
}
