package com.example.wordguesser.screens.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.wordguesser.R
import com.example.wordguesser.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private var word = ""
    private var score = 0
    private var wordList: MutableList<String> = mutableListOf("queen", "hospital", "basketball", "cat", "change", "snail", "soup", "calendar", "sad", "desk", "guitar", "home", "railway", "zebra", "jelly", "car", "crow", "trade", "bag", "roll", "bubble")
    private var index = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)
        wordList.shuffle()
        setWordAndScore()

        binding.correctButton.setOnClickListener { onCorrect() }
        binding.skipButton.setOnClickListener { onSkip() }

        return binding.root
    }

    private fun setWordAndScore() {
        if (index >= wordList.size) return
        word = wordList[index]
        binding.wordText.text = word
        binding.currentScoreText.text = score.toString()
    }

    private fun nextWord() {
        index++
        if (index < wordList.size) {
            setWordAndScore()
        } else {
            gameFinished()
        }
    }

    private fun onCorrect() {
        score++
        nextWord()
    }

    private fun onSkip() {
        score--
        nextWord()
    }

    private fun gameFinished() {
        findNavController().navigate(GameFragmentDirections.actionGameFragmentToScoreFragment(score))
    }
}