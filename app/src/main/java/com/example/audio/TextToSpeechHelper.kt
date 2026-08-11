package com.example.audio

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.Locale

class TextToSpeechHelper(context: Context) : TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = TextToSpeech(context.applicationContext, this)
    var isInitialized: Boolean = false
        private set

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts?.setLanguage(Locale.FRENCH)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "French language is missing or not supported on this device.")
            } else {
                isInitialized = true
                tts?.setSpeechRate(0.85f) // Slightly relaxed rate for clear language learning
            }
        } else {
            Log.e("TTS", "TextToSpeech initialization failed with status $status")
        }
    }

    fun speakFrench(text: String) {
        if (text.isBlank()) return
        if (isInitialized) {
            tts?.language = Locale.FRENCH
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "FrenchTTS_${System.currentTimeMillis()}")
        }
    }

    fun speakUrduOrEnglish(text: String, isUrdu: Boolean) {
        if (text.isBlank()) return
        if (isInitialized) {
            val targetLocale = if (isUrdu) Locale("ur", "PK") else Locale.ENGLISH
            val result = tts?.setLanguage(targetLocale)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                tts?.language = Locale.ENGLISH
            }
            tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "OtherTTS_${System.currentTimeMillis()}")
        }
    }

    fun stop() {
        tts?.stop()
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
        tts = null
    }
}
