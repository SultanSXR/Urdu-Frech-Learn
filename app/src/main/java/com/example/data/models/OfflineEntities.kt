package com.example.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "downloaded_content")
data class DownloadedContentEntity(
    @PrimaryKey val id: String, // e.g. "lesson_1", "flashcard_deck_Travel", "pronunciation_guide"
    val contentType: String,    // "LESSON", "FLASHCARD_DECK", "PRONUNCIATION_GUIDE"
    val contentRefId: Int = 0,
    val titleEn: String,
    val titleUr: String,
    val sizeKb: Int,
    val downloadedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "pending_sync")
data class PendingSyncEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val actionType: String, // "LESSON_COMPLETE", "WORD_MASTERED", "XP_GAINED"
    val details: String,    // Description for UI log
    val xpEarned: Int = 0,
    val timestamp: Long = System.currentTimeMillis()
)
