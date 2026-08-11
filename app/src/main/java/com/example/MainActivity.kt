package com.example

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.example.ui.components.AppBottomNavBar
import com.example.ui.components.AppTopBar
import com.example.ui.screens.ActiveConversationScreen
import com.example.ui.screens.ActiveLessonScreen
import com.example.ui.screens.AlphabetScreen
import com.example.ui.screens.ConversationScreen
import com.example.ui.screens.FlashcardsScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.LessonsScreen
import com.example.ui.screens.OfflineSyncScreen
import com.example.ui.screens.PronunciationLabScreen
import com.example.ui.screens.StreakProgressScreen
import com.example.ui.theme.FrenchUrduTheme
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        // Speech permission result handled
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Request Record Audio Permission if needed for voice recognition
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }

        setContent {
            FrenchUrduTheme {
                val currentScreen by viewModel.currentScreen.collectAsState()
                val uiLanguage by viewModel.uiLanguage.collectAsState()
                val userProgress by viewModel.userProgress.collectAsState()
                val pendingSyncQueue by viewModel.pendingSyncQueue.collectAsState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        AppTopBar(
                            currentLanguage = uiLanguage,
                            currentStreak = userProgress.currentStreak,
                            totalXp = userProgress.totalXp,
                            isOfflineSimulated = userProgress.isOfflineModeSimulated,
                            hasPendingSync = pendingSyncQueue.isNotEmpty(),
                            onLanguageToggle = { viewModel.toggleLanguage() },
                            onStreakClick = { viewModel.navigateTo(AppScreen.STREAK_STATS) },
                            onOfflineSyncClick = { viewModel.navigateTo(AppScreen.OFFLINE_SYNC) }
                        )
                    },
                    bottomBar = {
                        AppBottomNavBar(
                            currentScreen = currentScreen,
                            currentLanguage = uiLanguage,
                            onNavigate = { viewModel.navigateTo(it) }
                        )
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        when (currentScreen) {
                            AppScreen.HOME -> HomeScreen(
                                viewModel = viewModel,
                                userStreak = userProgress.currentStreak,
                                totalXp = userProgress.totalXp,
                                dailyCompleted = userProgress.todayCompletedCount,
                                dailyTarget = userProgress.dailyGoalTarget,
                                uiLanguage = uiLanguage,
                                onNavigate = { viewModel.navigateTo(it) }
                            )

                            AppScreen.ALPHABET -> AlphabetScreen(
                                viewModel = viewModel,
                                uiLanguage = uiLanguage
                            )

                            AppScreen.LESSONS -> LessonsScreen(
                                viewModel = viewModel,
                                uiLanguage = uiLanguage,
                                onStartLesson = { viewModel.startLesson(it) }
                            )

                            AppScreen.ACTIVE_LESSON -> {
                                viewModel.activeLesson.value?.let { lesson ->
                                    ActiveLessonScreen(
                                        lesson = lesson,
                                        viewModel = viewModel,
                                        uiLanguage = uiLanguage,
                                        onFinish = { viewModel.navigateTo(AppScreen.LESSONS) }
                                    )
                                } ?: viewModel.navigateTo(AppScreen.LESSONS)
                            }

                            AppScreen.FLASHCARDS -> FlashcardsScreen(
                                viewModel = viewModel,
                                uiLanguage = uiLanguage
                            )

                            AppScreen.CONVERSATION -> ConversationScreen(
                                viewModel = viewModel,
                                uiLanguage = uiLanguage,
                                onStartScenario = { viewModel.startConversation(it) },
                                onOpenPronunciationLab = { viewModel.navigateTo(AppScreen.PRONUNCIATION_LAB) }
                            )

                            AppScreen.ACTIVE_CONVERSATION -> {
                                viewModel.activeConversation.value?.let { scenario ->
                                    ActiveConversationScreen(
                                        scenario = scenario,
                                        viewModel = viewModel,
                                        uiLanguage = uiLanguage,
                                        onBack = { viewModel.navigateTo(AppScreen.CONVERSATION) }
                                    )
                                } ?: viewModel.navigateTo(AppScreen.CONVERSATION)
                            }

                            AppScreen.PRONUNCIATION_LAB -> PronunciationLabScreen(
                                viewModel = viewModel,
                                uiLanguage = uiLanguage,
                                onBack = { viewModel.navigateTo(AppScreen.CONVERSATION) }
                            )

                            AppScreen.STREAK_STATS -> StreakProgressScreen(
                                userProgress = userProgress,
                                uiLanguage = uiLanguage,
                                onBack = { viewModel.navigateTo(AppScreen.HOME) }
                            )

                            AppScreen.OFFLINE_SYNC -> OfflineSyncScreen(
                                viewModel = viewModel
                            )
                        }
                    }
                }
            }
        }
    }
}
