package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.models.CompletedLessonEntity
import com.example.data.models.DownloadedContentEntity
import com.example.data.models.FavoriteWordEntity
import com.example.data.models.MasteredWordEntity
import com.example.data.models.PendingSyncEntity
import com.example.data.models.UserProgress
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProgressDao {
    @Query("SELECT * FROM user_progress WHERE id = 1")
    fun getUserProgress(): Flow<UserProgress?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserProgress(progress: UserProgress)

    @Query("SELECT * FROM favorite_words")
    fun getFavoriteWordIds(): Flow<List<FavoriteWordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteWord(fav: FavoriteWordEntity)

    @Query("DELETE FROM favorite_words WHERE wordId = :wordId")
    suspend fun removeFavoriteWord(wordId: Int)

    @Query("SELECT * FROM mastered_words")
    fun getMasteredWordIds(): Flow<List<MasteredWordEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMasteredWord(mastered: MasteredWordEntity)

    @Query("DELETE FROM mastered_words WHERE wordId = :wordId")
    suspend fun removeMasteredWord(wordId: Int)

    @Query("SELECT * FROM completed_lessons")
    fun getCompletedLessons(): Flow<List<CompletedLessonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCompletedLesson(completed: CompletedLessonEntity)

    // Downloaded Content Management
    @Query("SELECT * FROM downloaded_content")
    fun getAllDownloadedContent(): Flow<List<DownloadedContentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDownloadedContent(content: DownloadedContentEntity)

    @Query("DELETE FROM downloaded_content WHERE id = :id")
    suspend fun removeDownloadedContent(id: String)

    // Sync Queue Management
    @Query("SELECT * FROM pending_sync ORDER BY timestamp ASC")
    fun getPendingSyncQueue(): Flow<List<PendingSyncEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPendingSyncItem(item: PendingSyncEntity)

    @Query("DELETE FROM pending_sync")
    suspend fun clearPendingSyncQueue()
}
