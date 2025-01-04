package com.example.wordguesser.screens.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    var word = ""
    var score = 0
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

    init {
        Log.i("GameViewModel", "GameViewModel is created!!")
        wordList.shuffle()
        setWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "Game VM destroyed!")
    }

    fun onCorrect() {
        score++
        setWord()
    }

     fun onSkip() {
        score--
         setWord()
    }

    private fun setWord() {
        if (index < wordList.size) {
            word = wordList[index++]
        } else {
//            gameFinished()
        }
    }
}