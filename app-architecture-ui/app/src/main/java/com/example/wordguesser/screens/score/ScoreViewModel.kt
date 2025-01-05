package com.example.wordguesser.screens.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(receivedScore: Int): ViewModel() {

    private val _finalScore = MutableLiveData<Int>()
    val finalScore: LiveData<Int> get() = _finalScore

    private val _playAgainEvent = MutableLiveData<Boolean>()
    val playAgainEvent: LiveData<Boolean> get() = _playAgainEvent

    init {
        _finalScore.value = receivedScore
    }

    fun onPlayAgain() {
        _playAgainEvent.value = true
    }

    fun onPlayAgainComplete() {
        _playAgainEvent.value = false
    }
}
