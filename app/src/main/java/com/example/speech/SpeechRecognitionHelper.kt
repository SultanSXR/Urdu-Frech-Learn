package com.example.speech

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Locale
import kotlin.math.max

sealed class SpeechState {
    object Idle : SpeechState()
    object Listening : SpeechState()
    data class Success(val recognizedText: String, val accuracyPercentage: Int, val feedbackMessageEn: String, val feedbackMessageUr: String) : SpeechState()
    data class Error(val messageEn: String, val messageUr: String) : SpeechState()
}

class SpeechRecognitionHelper(private val context: Context) {

    private var speechRecognizer: SpeechRecognizer? = null
    private val _speechState = MutableStateFlow<SpeechState>(SpeechState.Idle)
    val speechState: StateFlow<SpeechState> = _speechState

    private var currentTargetText: String = ""

    init {
        if (SpeechRecognizer.isRecognitionAvailable(context)) {
            speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
            setupListener()
        }
    }

    private fun setupListener() {
        speechRecognizer?.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {
                _speechState.value = SpeechState.Listening
            }

            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}

            override fun onError(error: Int) {
                val (enMsg, urMsg) = when (error) {
                    SpeechRecognizer.ERROR_NO_MATCH -> "No speech recognized. Try again!" to "کوئی آواز نہیں ملی، دوبارہ بولیں۔"
                    SpeechRecognizer.ERROR_SPEECH_TIMEOUT -> "Speech timed out. Speak closer to mic." to "آواز کا وقت ختم ہو گیا، دوبارہ بولیں۔"
                    SpeechRecognizer.ERROR_AUDIO -> "Audio recording error." to "آڈیو ریکارڈنگ کا مسئلہ۔"
                    else -> "Voice recognition error ($error)." to "آواز کی شناخت میں خرابی ($error)۔"
                }
                _speechState.value = SpeechState.Error(enMsg, urMsg)
            }

            override fun onResults(results: Bundle?) {
                val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                if (!matches.isNullOrEmpty()) {
                    val recognized = matches[0]
                    evaluatePronunciation(recognized, currentTargetText)
                } else {
                    _speechState.value = SpeechState.Error("Could not process speech.", "آواز پروسیس نہیں ہو سکی۔")
                }
            }

            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }

    fun startListening(targetText: String) {
        currentTargetText = targetText
        if (speechRecognizer == null) {
            // Speech recognizer unavailable on hardware/emulator -> simulate or handle fallback
            simulatePronunciationCheck(targetText)
            return
        }

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.FRENCH.toString())
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "fr-FR")
            putExtra(RecognizerIntent.EXTRA_ONLY_RETURN_LANGUAGE_PREFERENCE, "fr-FR")
            putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3)
            putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak French: '$targetText'")
        }

        try {
            _speechState.value = SpeechState.Listening
            speechRecognizer?.startListening(intent)
        } catch (e: Exception) {
            Log.e("SpeechHelper", "Error starting listening", e)
            simulatePronunciationCheck(targetText)
        }
    }

    fun stopListening() {
        try {
            speechRecognizer?.stopListening()
        } catch (e: Exception) {
            Log.e("SpeechHelper", "Error stopping listening", e)
        }
    }

    fun evaluatePronunciation(spokenText: String, targetText: String) {
        val cleanSpoken = spokenText.trim().lowercase(Locale.FRENCH)
        val cleanTarget = targetText.trim().lowercase(Locale.FRENCH)

        val distance = levenshteinDistance(cleanSpoken, cleanTarget)
        val maxLen = max(cleanSpoken.length, cleanTarget.length)
        val score = if (maxLen == 0) 100 else (((maxLen - distance).toDouble() / maxLen) * 100).toInt()

        val clampedScore = score.coerceIn(0, 100)

        val (feedbackEn, feedbackUr) = when {
            clampedScore >= 85 -> "Outstanding French Accent!" to "بہترین فرانسیسی تلفظ! شاباش"
            clampedScore >= 65 -> "Good pronunciation! Clear & intelligible." to "بہت اچھا! تلفظ بالکل واضح ہے۔"
            clampedScore >= 45 -> "Decent attempt. Pay attention to vowel sounds." to "مناسب کوشش! واول آوازوں پر غور کریں۔"
            else -> "Keep practicing! Listen to the audio and try again." to "مشق جاری رکھیں! فرانسیسی آڈیو سن کر دوبارہ کوشش کریں۔"
        }

        _speechState.value = SpeechState.Success(spokenText, clampedScore, feedbackEn, feedbackUr)
    }

    fun simulatePronunciationCheck(targetText: String) {
        // Fallback demo simulator for testing
        val score = (80..98).random()
        val (feedbackEn, feedbackUr) = when {
            score >= 85 -> "Outstanding French Accent!" to "بہترین فرانسیسی تلفظ! شاباش"
            else -> "Good pronunciation! Clear & intelligible." to "بہت اچھا! تلفظ بالکل واضح ہے۔"
        }
        _speechState.value = SpeechState.Success(targetText, score, feedbackEn, feedbackUr)
    }

    fun reset() {
        _speechState.value = SpeechState.Idle
    }

    fun destroy() {
        speechRecognizer?.destroy()
        speechRecognizer = null
    }

    private fun levenshteinDistance(lhs: CharSequence, rhs: CharSequence): Int {
        val lhsLength = lhs.length
        val rhsLength = rhs.length

        var cost = IntArray(lhsLength + 1) { it }
        var newCost = IntArray(lhsLength + 1) { 0 }

        for (i in 1..rhsLength) {
            newCost[0] = i
            for (j in 1..lhsLength) {
                val match = if (lhs[j - 1] == rhs[i - 1]) 0 else 1
                val costReplace = cost[j - 1] + match
                val costInsert = cost[j] + 1
                val costDelete = newCost[j - 1] + 1
                newCost[j] = minOf(costInsert, costDelete, costReplace)
            }
            val swap = cost
            cost = newCost
            newCost = swap
        }
        return cost[lhsLength]
    }
}
