package com.example.ui.screens

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.models.ConversationScenario
import com.example.speech.SpeechState
import com.example.ui.components.AudioPlayButton
import com.example.ui.components.SpeechMicButton
import com.example.ui.theme.AccentGreen
import com.example.ui.theme.AmberGold
import com.example.ui.theme.NavyPrimary
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.UiLanguage

@Composable
fun ActiveConversationScreen(
    scenario: ConversationScenario,
    viewModel: MainViewModel,
    uiLanguage: UiLanguage,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isUrdu = uiLanguage == UiLanguage.URDU
    val speechState by viewModel.speechState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        // Top Bar
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
                    text = if (isUrdu) scenario.titleUr else scenario.titleEn,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
                Text(
                    text = if (isUrdu) scenario.situationUr else scenario.situationEn,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
                )
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Speech Evaluation Feedback Banner if active
        when (val state = speechState) {
            is SpeechState.Listening -> {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    colors = CardDefaults.cardColors(containerColor = NavyPrimary)
                ) {
                    Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Mic, contentDescription = "Listening", tint = AmberGold)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (isUrdu) "فرانسیسی آڈیو سنی جا رہی ہے... بولیں!" else "Listening to your French speech... Speak now!",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }
            }
            is SpeechState.Success -> {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    colors = CardDefaults.cardColors(containerColor = AccentGreen.copy(alpha = 0.15f))
                ) {
                    Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "${state.accuracyPercentage}% Accent Accuracy! ",
                            fontWeight = FontWeight.ExtraBold,
                            color = AccentGreen,
                            fontSize = 14.sp
                        )
                        Text(
                            text = if (isUrdu) state.feedbackMessageUr else state.feedbackMessageEn,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
            is SpeechState.Error -> {
                Card(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer)
                ) {
                    Text(
                        text = if (isUrdu) state.messageUr else state.messageEn,
                        modifier = Modifier.padding(12.dp),
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onErrorContainer
                    )
                }
            }
            SpeechState.Idle -> {}
        }

        // Dialogue Lines List
        LazyColumn(
            contentPadding = PaddingValues(bottom = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.testTag("dialogue_lines_list")
        ) {
            itemsIndexed(scenario.dialogueLines) { index, line ->
                val isUser = line.isUserTurn
                val bubbleBg = if (isUser) NavyPrimary.copy(alpha = 0.1f) else MaterialTheme.colorScheme.surface

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = bubbleBg),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = if (isUrdu) line.speakerNameUr else line.speakerNameEn,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = if (isUser) NavyPrimary else AmberGold
                            )

                            Row {
                                AudioPlayButton(
                                    size = 36.dp,
                                    onClick = { viewModel.speakFrench(line.frenchText) }
                                )

                                if (isUser) {
                                    Spacer(modifier = Modifier.width(6.dp))
                                    SpeechMicButton(
                                        isListening = speechState is SpeechState.Listening,
                                        size = 36.dp,
                                        onMicClick = {
                                            if (speechState is SpeechState.Listening) {
                                                viewModel.speechHelper.stopListening()
                                            } else {
                                                viewModel.speechHelper.startListening(line.frenchText)
                                            }
                                        }
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = line.frenchText,
                            fontWeight = FontWeight.Black,
                            fontSize = 18.sp,
                            color = NavyPrimary
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = "EN: ${line.phoneticEn}",
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
                        )

                        Text(
                            text = "اردو تلفظ: ${line.phoneticUr}",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = if (isUrdu) "ترجمہ: ${line.urduTranslation}" else "Translation: ${line.englishTranslation}",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )

                        if (line.urduSoundNote.isNotBlank()) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "💡 ${line.urduSoundNote}",
                                fontSize = 11.sp,
                                color = NavyPrimary
                            )
                        }
                    }
                }
            }
        }
    }
}
