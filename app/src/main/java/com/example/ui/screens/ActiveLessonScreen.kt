package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.data.models.Lesson
import com.example.ui.components.AudioPlayButton
import com.example.ui.theme.AccentGreen
import com.example.ui.theme.AmberGold
import com.example.ui.theme.CrimsonRed
import com.example.ui.theme.NavyPrimary
import com.example.ui.viewmodel.MainViewModel
import com.example.ui.viewmodel.UiLanguage

@Composable
fun ActiveLessonScreen(
    lesson: Lesson,
    viewModel: MainViewModel,
    uiLanguage: UiLanguage,
    onFinish: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isUrdu = uiLanguage == UiLanguage.URDU
    var currentCardIndex by remember { mutableIntStateOf(0) }
    var inQuizPhase by remember { mutableStateOf(false) }
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var quizSubmitted by remember { mutableStateOf(false) }
    var correctAnswersCount by remember { mutableIntStateOf(0) }
    var lessonCompleted by remember { mutableStateOf(false) }

    val totalSteps = lesson.cards.size + lesson.questions.size
    val currentStep = if (!inQuizPhase) currentCardIndex + 1 else lesson.cards.size + currentQuestionIndex + 1

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Top Navigation Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onFinish) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = NavyPrimary)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if (isUrdu) lesson.titleUr else lesson.titleEn,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                LinearProgressIndicator(
                    progress = { currentStep.toFloat() / totalSteps },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp)),
                    color = AmberGold,
                    trackColor = NavyPrimary.copy(alpha = 0.15f)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (lessonCompleted) {
            // Lesson Success Screen
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Default.EmojiEvents,
                        contentDescription = "Success",
                        tint = AmberGold,
                        modifier = Modifier.size(72.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = if (isUrdu) "سبق مکمل ہو گیا! 🎉" else "Lesson Completed! 🎉",
                        fontWeight = FontWeight.Black,
                        fontSize = 22.sp,
                        color = NavyPrimary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = if (isUrdu) "آپ نے $correctAnswersCount/${lesson.questions.size} سوالات کے صحیح جواب دیے!" else "You answered $correctAnswersCount/${lesson.questions.size} quiz questions correctly!",
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = onFinish,
                        colors = ButtonDefaults.buttonColors(containerColor = NavyPrimary),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (isUrdu) "آگے بڑھیں" else "Continue", fontWeight = FontWeight.Bold)
                    }
                }
            }
        } else if (!inQuizPhase) {
            // Card Study Phase
            val card = lesson.cards[currentCardIndex]

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("lesson_card_display"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (isUrdu) "تعلیمی جملہ ${currentCardIndex + 1}/${lesson.cards.size}" else "Learning Card ${currentCardIndex + 1}/${lesson.cards.size}",
                            color = NavyPrimary,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )

                        AudioPlayButton(
                            size = 48.dp,
                            onClick = { viewModel.speakFrench(card.audioText) }
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = card.frenchPhrase,
                        fontWeight = FontWeight.Black,
                        fontSize = 26.sp,
                        color = NavyPrimary
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Phonetic: ${card.phoneticEn}",
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "اردو تلفظ: ${card.phoneticUr}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Meaning Box
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(NavyPrimary.copy(alpha = 0.08f))
                            .padding(12.dp)
                    ) {
                        Column {
                            Text(
                                text = "Meaning: ${card.meaningEn}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "اردو معنی: ${card.meaningUr}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = NavyPrimary
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Urdu Phonetic Explanation
                    Text(
                        text = "💡 ${card.urduSoundNote}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (currentCardIndex + 1 < lesson.cards.size) {
                        currentCardIndex++
                    } else {
                        inQuizPhase = true
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(containerColor = NavyPrimary)
            ) {
                Text(
                    text = if (currentCardIndex + 1 < lesson.cards.size) (if (isUrdu) "اگلا جملہ" else "Next Card") else (if (isUrdu) "کویز شروع کریں" else "Start Quiz Drill"),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            // Interactive Quiz Phase
            val question = lesson.questions[currentQuestionIndex]

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("quiz_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Quiz ${currentQuestionIndex + 1}/${lesson.questions.size}",
                            color = AmberGold,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )

                        AudioPlayButton(
                            size = 40.dp,
                            onClick = { viewModel.speakFrench(question.audioTargetFrench) }
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = if (isUrdu) question.questionUr else question.questionEn,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Options
                    question.options.forEachIndexed { index, option ->
                        val isSelected = selectedOptionIndex == index
                        val isCorrect = index == question.correctAnswerIndex

                        val optionBg = when {
                            quizSubmitted && isCorrect -> AccentGreen.copy(alpha = 0.2f)
                            quizSubmitted && isSelected && !isCorrect -> CrimsonRed.copy(alpha = 0.2f)
                            isSelected -> NavyPrimary.copy(alpha = 0.15f)
                            else -> MaterialTheme.colorScheme.background
                        }

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable(enabled = !quizSubmitted) {
                                    selectedOptionIndex = index
                                },
                            colors = CardDefaults.cardColors(containerColor = optionBg)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(14.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = option,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    modifier = Modifier.weight(1f)
                                )
                                if (quizSubmitted && isCorrect) {
                                    Icon(Icons.Default.CheckCircle, contentDescription = "Correct", tint = AccentGreen)
                                } else if (quizSubmitted && isSelected && !isCorrect) {
                                    Icon(Icons.Default.Close, contentDescription = "Incorrect", tint = CrimsonRed)
                                }
                            }
                        }
                    }

                    if (quizSubmitted) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = if (isUrdu) question.explanationUr else question.explanationEn,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = NavyPrimary
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            if (!quizSubmitted) {
                Button(
                    onClick = {
                        if (selectedOptionIndex != null) {
                            quizSubmitted = true
                            if (selectedOptionIndex == question.correctAnswerIndex) {
                                correctAnswersCount++
                            }
                        }
                    },
                    enabled = selectedOptionIndex != null,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = NavyPrimary)
                ) {
                    Text(if (isUrdu) "جواب چیک کریں" else "Check Answer", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
            } else {
                Button(
                    onClick = {
                        if (currentQuestionIndex + 1 < lesson.questions.size) {
                            currentQuestionIndex++
                            selectedOptionIndex = null
                            quizSubmitted = false
                        } else {
                            viewModel.completeLesson(lesson.id, correctAnswersCount)
                            lessonCompleted = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = NavyPrimary)
                ) {
                    Text(
                        text = if (currentQuestionIndex + 1 < lesson.questions.size) (if (isUrdu) "اگلا سوال" else "Next Question") else (if (isUrdu) "نتیجہ دیکھیں" else "Finish & Collect XP"),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
