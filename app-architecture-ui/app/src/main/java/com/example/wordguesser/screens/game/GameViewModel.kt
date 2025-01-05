package com.example.wordguesser.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    companion object {
        const val ONE_SECOND = 1000L
        const val COUNTDOWN_TIME = 10000L
    }

    private var wordList: MutableList<String> = mutableListOf("queen","hospital","basketball","cat","change","snail","soup","calendar","sad","desk","guitar","home","railway","zebra","jelly","car","crow","trade","bag","roll","bubble")
    private var index = 0

    private val _word = MutableLiveData<String>()
    val word: LiveData<String> get() = _word

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean> get() = _eventGameFinish

    private val timer: CountDownTimer
    private val _currentTime = MutableLiveData<String>()
    val currentTime: LiveData<String> get() = _currentTime

    init {
        wordList.shuffle()
        _score.value = 0
        setWord()
        _eventGameFinish.value = false

        // start time
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                val time = millisUntilFinished / ONE_SECOND
                _currentTime.value = DateUtils.formatElapsedTime(time)
            }

            override fun onFinish() {
                val time = 0L
                _currentTime.value = time.toString()
                _eventGameFinish.value = true
            }
        }
        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "Game VM destroyed!")
        timer.cancel()
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
        if (currentTime.value == (0L).toString()) {
            _eventGameFinish.value = true
        } else {
            if (index < wordList.size) {
                _word.value = wordList[index++]
            } else {
                _eventGameFinish.value = true
            }
        }
    }

    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }
}