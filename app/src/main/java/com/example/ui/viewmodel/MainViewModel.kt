package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.TextToSpeechHelper
import com.example.data.FrenchDataRepository
import com.example.data.local.AppDatabase
import com.example.data.models.CompletedLessonEntity
import com.example.data.models.ConversationScenario
import com.example.data.models.DownloadedContentEntity
import com.example.data.models.FavoriteWordEntity
import com.example.data.models.Flashcard
import com.example.data.models.FrenchAlphabetItem
import com.example.data.models.Lesson
import com.example.data.models.MasteredWordEntity
import com.example.data.models.PendingSyncEntity
import com.example.data.models.UserProgress
import com.example.speech.SpeechRecognitionHelper
import com.example.speech.SpeechState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppScreen {
    HOME,
    ALPHABET,
    LESSONS,
    ACTIVE_LESSON,
    FLASHCARDS,
    CONVERSATION,
    ACTIVE_CONVERSATION,
    PRONUNCIATION_LAB,
    STREAK_STATS,
    OFFLINE_SYNC
}

enum class UiLanguage {
    ENGLISH,
    URDU
}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getDatabase(application)
    private val dao = db.userProgressDao()

    val ttsHelper = TextToSpeechHelper(application)
    val speechHelper = SpeechRecognitionHelper(application)

    // UI Language Toggle State
    private val _uiLanguage = MutableStateFlow(UiLanguage.ENGLISH)
    val uiLanguage: StateFlow<UiLanguage> = _uiLanguage

    // Active Screen State
    private val _currentScreen = MutableStateFlow(AppScreen.HOME)
    val currentScreen: StateFlow<AppScreen> = _currentScreen

    // Selected Items
    val activeLesson = MutableStateFlow<Lesson?>(null)
    val activeConversation = MutableStateFlow<ConversationScenario?>(null)

    // Speech State
    val speechState: StateFlow<SpeechState> = speechHelper.speechState

    // Sync state
    val isSyncing = MutableStateFlow(false)

    // User Progress from Room DB
    val userProgress: StateFlow<UserProgress> = dao.getUserProgress()
        .combine(MutableStateFlow(Unit)) { progress, _ ->
            progress ?: UserProgress()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UserProgress()
        )

    val favoriteWordIds: StateFlow<Set<Int>> = dao.getFavoriteWordIds()
        .combine(MutableStateFlow(Unit)) { list, _ ->
            list.map { it.wordId }.toSet()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptySet()
        )

    val masteredWordIds: StateFlow<Set<Int>> = dao.getMasteredWordIds()
        .combine(MutableStateFlow(Unit)) { list, _ ->
            list.map { it.wordId }.toSet()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptySet()
        )

    // Downloaded Offline Content State
    val downloadedContentList: StateFlow<List<DownloadedContentEntity>> = dao.getAllDownloadedContent()
        .combine(MutableStateFlow(Unit)) { list, _ -> list }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val downloadedIdsSet: StateFlow<Set<String>> = downloadedContentList
        .combine(MutableStateFlow(Unit)) { list, _ ->
            list.map { it.id }.toSet()
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptySet()
        )

    // Pending Offline Sync Queue State
    val pendingSyncQueue: StateFlow<List<PendingSyncEntity>> = dao.getPendingSyncQueue()
        .combine(MutableStateFlow(Unit)) { list, _ -> list }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    init {
        checkAndUpdateStreakOnLaunch()
    }

    private fun checkAndUpdateStreakOnLaunch() {
        viewModelScope.launch {
            val current = userProgress.value
            val now = System.currentTimeMillis()
            val diffHours = (now - current.lastActiveTimestamp) / (1000 * 60 * 60)

            val updatedProgress = if (diffHours in 24..48) {
                // Streak increment
                current.copy(
                    currentStreak = current.currentStreak + 1,
                    bestStreak = maxOf(current.bestStreak, current.currentStreak + 1),
                    lastActiveTimestamp = now
                )
            } else if (diffHours > 48) {
                // Reset streak
                current.copy(
                    currentStreak = 1,
                    lastActiveTimestamp = now
                )
            } else {
                current.copy(lastActiveTimestamp = now)
            }

            dao.saveUserProgress(updatedProgress)
        }
    }

    fun toggleLanguage() {
        val newLang = if (_uiLanguage.value == UiLanguage.ENGLISH) UiLanguage.URDU else UiLanguage.ENGLISH
        _uiLanguage.value = newLang
    }

    fun setLanguage(lang: UiLanguage) {
        _uiLanguage.value = lang
    }

    fun navigateTo(screen: AppScreen) {
        _currentScreen.value = screen
    }

    fun startLesson(lesson: Lesson) {
        activeLesson.value = lesson
        _currentScreen.value = AppScreen.ACTIVE_LESSON
    }

    fun startConversation(scenario: ConversationScenario) {
        activeConversation.value = scenario
        _currentScreen.value = AppScreen.ACTIVE_CONVERSATION
    }

    fun speakFrench(text: String) {
        ttsHelper.speakFrench(text)
    }

    fun speakPrompt(text: String, isUrdu: Boolean) {
        ttsHelper.speakUrduOrEnglish(text, isUrdu)
    }

    fun toggleOfflineModeSimulation() {
        viewModelScope.launch {
            val current = userProgress.value
            val newOfflineState = !current.isOfflineModeSimulated
            dao.saveUserProgress(current.copy(isOfflineModeSimulated = newOfflineState))
        }
    }

    fun downloadLesson(lesson: Lesson) {
        viewModelScope.launch {
            val entity = DownloadedContentEntity(
                id = "lesson_${lesson.id}",
                contentType = "LESSON",
                contentRefId = lesson.id,
                titleEn = lesson.titleEn,
                titleUr = lesson.titleUr,
                sizeKb = 450 + (lesson.cards.size * 80)
            )
            dao.addDownloadedContent(entity)
        }
    }

    fun downloadFlashcardDeck(categoryEn: String, categoryUr: String) {
        viewModelScope.launch {
            val entity = DownloadedContentEntity(
                id = "flashcard_deck_$categoryEn",
                contentType = "FLASHCARD_DECK",
                contentRefId = 0,
                titleEn = "$categoryEn Deck",
                titleUr = "$categoryUr ڈیک",
                sizeKb = 620
            )
            dao.addDownloadedContent(entity)
        }
    }

    fun downloadPronunciationGuide() {
        viewModelScope.launch {
            val entity = DownloadedContentEntity(
                id = "pronunciation_guide",
                contentType = "PRONUNCIATION_GUIDE",
                contentRefId = 0,
                titleEn = "Urdu-French Phonetic Audio Guide",
                titleUr = "اردو صوتیاتی رہنما پیک",
                sizeKb = 1250
            )
            dao.addDownloadedContent(entity)
        }
    }

    fun removeDownloadedContent(id: String) {
        viewModelScope.launch {
            dao.removeDownloadedContent(id)
        }
    }

    fun syncPendingProgress() {
        viewModelScope.launch {
            if (isSyncing.value) return@launch
            isSyncing.value = true
            delay(1200) // Simulate cloud sync network roundtrip
            dao.clearPendingSyncQueue()
            val current = userProgress.value
            dao.saveUserProgress(current.copy(lastSyncedTimestamp = System.currentTimeMillis()))
            isSyncing.value = false
        }
    }

    fun toggleFavoriteWord(wordId: Int) {
        viewModelScope.launch {
            if (favoriteWordIds.value.contains(wordId)) {
                dao.removeFavoriteWord(wordId)
            } else {
                dao.addFavoriteWord(FavoriteWordEntity(wordId))
            }
        }
    }

    fun toggleMasteredWord(wordId: Int) {
        viewModelScope.launch {
            if (masteredWordIds.value.contains(wordId)) {
                dao.removeMasteredWord(wordId)
            } else {
                dao.addMasteredWord(MasteredWordEntity(wordId))
                addXp(15, "Mastered word #$wordId")
            }
        }
    }

    fun addXp(amount: Int, reason: String = "Exercise completed") {
        viewModelScope.launch {
            val current = userProgress.value
            val isOffline = current.isOfflineModeSimulated
            val newXp = current.totalXp + amount
            val newProgress = current.copy(
                totalXp = newXp,
                todayCompletedCount = current.todayCompletedCount + 1,
                lastSyncedTimestamp = if (isOffline) current.lastSyncedTimestamp else System.currentTimeMillis()
            )
            dao.saveUserProgress(newProgress)

            if (isOffline) {
                dao.addPendingSyncItem(
                    PendingSyncEntity(
                        actionType = "XP_GAINED",
                        details = "$reason (+${amount} XP)",
                        xpEarned = amount
                    )
                )
            }
        }
    }

    fun completeLesson(lessonId: Int, score: Int) {
        viewModelScope.launch {
            val current = userProgress.value
            val isOffline = current.isOfflineModeSimulated
            val earnedXp = 50 + (score * 10)
            val newProgress = current.copy(
                totalXp = current.totalXp + earnedXp,
                completedLessonsCount = current.completedLessonsCount + 1,
                todayCompletedCount = current.todayCompletedCount + 1,
                lastSyncedTimestamp = if (isOffline) current.lastSyncedTimestamp else System.currentTimeMillis()
            )
            dao.saveUserProgress(newProgress)
            dao.addCompletedLesson(CompletedLessonEntity(lessonId = lessonId, score = score))

            if (isOffline) {
                dao.addPendingSyncItem(
                    PendingSyncEntity(
                        actionType = "LESSON_COMPLETE",
                        details = "Lesson #$lessonId completed (Score: $score/3, +${earnedXp} XP)",
                        xpEarned = earnedXp
                    )
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        ttsHelper.shutdown()
        speechHelper.destroy()
    }
}
