package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.models.CompletedLessonEntity
import com.example.data.models.DownloadedContentEntity
import com.example.data.models.FavoriteWordEntity
import com.example.data.models.MasteredWordEntity
import com.example.data.models.PendingSyncEntity
import com.example.data.models.UserProgress

@Database(
    entities = [
        UserProgress::class,
        FavoriteWordEntity::class,
        MasteredWordEntity::class,
        CompletedLessonEntity::class,
        DownloadedContentEntity::class,
        PendingSyncEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userProgressDao(): UserProgressDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "french_urdu_learn_db"
                )
                .fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
