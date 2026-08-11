package com.example.data.models

data class DialogueLine(
    val speakerNameEn: String,
    val speakerNameUr: String,
    val isUserTurn: Boolean,         // True if user practices speaking this phrase
    val frenchText: String,
    val phoneticEn: String,
    val phoneticUr: String,
    val englishTranslation: String,
    val urduTranslation: String,
    val urduSoundNote: String = ""
)

data class ConversationScenario(
    val id: Int,
    val titleEn: String,
    val titleUr: String,
    val situationEn: String,
    val situationUr: String,
    val category: String,
    val dialogueLines: List<DialogueLine>
)
