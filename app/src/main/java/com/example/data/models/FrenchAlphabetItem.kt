package com.example.data.models

data class FrenchAlphabetItem(
    val letter: String,             // e.g. "A a", "É é", "R r"
    val frenchSound: String,        // Phonetic sound in French (e.g. "ah", "ay", "ehr")
    val englishPhonetic: String,    // e.g. "Ah (like 'a' in father)"
    val urduPhonetic: String,       // Urdu script phonetic e.g. "آ (آب کی طرح)"
    val urduExplanation: String,    // Explanation in Urdu why this sound works e.g. "اردو 'آ' کی طرح لمبی آواز"
    val exampleFrenchWord: String,  // e.g. "Avion"
    val exampleEnglish: String,     // e.g. "Airplane"
    val exampleUrdu: String,        // e.g. "جہاز (جہاز)"
    val soundTipEn: String,
    val soundTipUr: String
)
