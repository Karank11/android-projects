package com.example.wordguesser.screens.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private var wordList: MutableList<String> = mutableListOf(
        "queen",
        "hospital",
        "basketball",
        "cat",
        "change",
        "snail",
        "soup",
        "calendar",
        "sad",
        "desk",
        "guitar",
        "home",
        "railway",
        "zebra",
        "jelly",
        "car",
        "crow",
        "trade",
        "bag",
        "roll",
        "bubble"
    )
    private var index = 0
    private val _word = MutableLiveData<String>()
    val word: LiveData<String> get() = _word
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    init {
        Log.i("GameViewModel", "GameViewModel is created!!")
        wordList.shuffle()
        _score.value = 0
        setWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "Game VM destroyed!")
    }

    fun onCorrect() {
        _score.value = score.value?.plus(1)
        setWord()
    }

     fun onSkip() {
        _score.value = score.value?.minus(1)
         setWord()
    }

    private fun setWord() {
        if (index < wordList.size) {
            _word.value = wordList[index++]
        } else {
//            gameFinished()
        }
    }
}