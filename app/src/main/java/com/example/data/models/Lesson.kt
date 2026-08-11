package com.example.data.models

data class LessonQuestion(
    val id: Int,
    val questionEn: String,
    val questionUr: String,
    val options: List<String>,       // 4 multiple choice options
    val correctAnswerIndex: Int,
    val explanationEn: String,
    val explanationUr: String,
    val audioTargetFrench: String    // French text to pronounce or listen to
)

data class LessonCard(
    val titleEn: String,
    val titleUr: String,
    val frenchPhrase: String,
    val phoneticEn: String,
    val phoneticUr: String,
    val meaningEn: String,
    val meaningUr: String,
    val urduSoundNote: String,       // Explains how Urdu phonetics help pronounce this sound
    val audioText: String
)

data class Lesson(
    val id: Int,
    val titleEn: String,
    val titleUr: String,
    val descriptionEn: String,
    val descriptionUr: String,
    val iconName: String,             // Icon identifier
    val xpReward: Int = 50,
    val cards: List<LessonCard>,
    val questions: List<LessonQuestion>
)
