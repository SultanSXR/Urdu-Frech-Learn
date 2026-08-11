package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_progress")
data class UserProgress(
    @PrimaryKey val id: Int = 1,
    val currentStreak: Int = 1,
    val bestStreak: Int = 1,
    val lastActiveTimestamp: Long = System.currentTimeMillis(),
    val totalXp: Int = 120,
    val completedLessonsCount: Int = 0,
    val masteredWordsCount: Int = 0,
    val dailyGoalTarget: Int = 3,       // e.g. 3 lessons or exercises
    val todayCompletedCount: Int = 1,
    val interfaceLanguage: String = "EN", // "EN" or "UR"
    val lastSyncedTimestamp: Long = System.currentTimeMillis(),
    val isOfflineModeSimulated: Boolean = false
)

@Entity(tableName = "favorite_words")
data class FavoriteWordEntity(
    @PrimaryKey val wordId: Int
)

@Entity(tableName = "mastered_words")
data class MasteredWordEntity(
    @PrimaryKey val wordId: Int
)

@Entity(tableName = "completed_lessons")
data class CompletedLessonEntity(
    @PrimaryKey val lessonId: Int,
    val score: Int,
    val timestamp: Long = System.currentTimeMillis()
)
