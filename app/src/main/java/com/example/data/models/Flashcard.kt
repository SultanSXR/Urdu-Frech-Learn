package com.example.data.models

data class Flashcard(
    val id: Int,
    val frenchWord: String,         // e.g. "Bonjour"
    val frenchPhoneticEn: String,   // e.g. "Boh-zhoor"
    val frenchPhoneticUr: String,   // Urdu script phonetic: "بونژور (ن٘ نکیلی آواز)"
    val englishMeaning: String,     // e.g. "Hello / Good morning"
    val urduMeaning: String,        // e.g. "سلام / صبح بخیر"
    val category: String,           // e.g. "Greetings", "Food", "Numbers", "Travel", "Essentials"
    val categoryUrdu: String,       // e.g. "سلام اور آداب", "کھانا پیتا", "اعداﺪ", "سفر", "ضروری باتیں"
    val exampleSentenceFrench: String, // e.g. "Bonjour, comment allez-vous?"
    val exampleSentenceEn: String,     // e.g. "Hello, how are you?"
    val exampleSentenceUr: String,     // e.g. "سلام، آپ کیسے ہیں؟"
    val isMastered: Boolean = false,
    val isFavorite: Boolean = false
)
