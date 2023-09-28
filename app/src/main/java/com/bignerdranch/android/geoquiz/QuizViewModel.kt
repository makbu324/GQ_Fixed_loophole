package com.bignerdranch.android.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.SavedStateHandle

private  const val TAG = "QuizViewModel"
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val IS_CHEATER_KEY = "IS_CHEATER_KEY"

class QuizViewModel(private val savedStatedHandle: SavedStateHandle): ViewModel() {
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
//
    var isCheater: Boolean
        get() = savedStatedHandle.get(IS_CHEATER_KEY) ?: false
        set(value) = savedStatedHandle.set(IS_CHEATER_KEY, value)

    private var currentIndex
        get() = savedStatedHandle.get(CURRENT_INDEX_KEY) ?: 0
        set(value) = savedStatedHandle.set(CURRENT_INDEX_KEY, value)

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moreToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}