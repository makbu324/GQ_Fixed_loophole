package com.bignerdranch.android.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.bignerdranch.android.geoquiz.databinding.ActivityCheatBinding
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

const val EXTRA_ANSWER_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"

private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true"

private var answerIsTrue = false

const val clicked_already_wow = "clicked_already_wow"

class clickedAlready(private val savedStatedHandle: SavedStateHandle): ViewModel() {
    var clickedOrNot: Boolean
        get() = savedStatedHandle.get(clicked_already_wow) ?: false
        set(value) = savedStatedHandle.set(clicked_already_wow, value)
}

class CheatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCheatBinding

    private val clicked: clickedAlready by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        //have you cheated b4?
        if (clicked.clickedOrNot == true) {
            setAnswerShownResult(true)
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            binding.answerTextView.setText(answerText)
            Toast.makeText(
                this,
                "I know you cheated boi!",
                Toast.LENGTH_SHORT
            ).show()
        }

        binding.showAnswerButton.setOnClickListener {
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            binding.answerTextView.setText(answerText)
            setAnswerShownResult(true)

            //so next time you cheat we'd know!
            clicked.clickedOrNot = true
        }

    }

    private fun setAnswerShownResult(isAnswerShown: Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        }
        setResult(Activity.RESULT_OK, data )
    }

    companion object {
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}