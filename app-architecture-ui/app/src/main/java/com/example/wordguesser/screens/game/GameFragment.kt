package com.example.wordguesser.screens.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wordguesser.R
import com.example.wordguesser.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        Log.i("GameFragment", "viewModelProvider is called for game VM")
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        updateWordAndScore()

        binding.correctButton.setOnClickListener {
            gameViewModel.onCorrect()
            updateWordAndScore()
        }
        binding.skipButton.setOnClickListener {
            gameViewModel.onSkip()
            updateWordAndScore()
        }
        return binding.root
    }

    private fun updateWordAndScore() {
        binding.wordText.text = gameViewModel.word
        binding.currentScoreText.text = gameViewModel.score.toString()
    }

    private fun gameFinished() {
        findNavController().navigate(GameFragmentDirections.actionGameFragmentToScoreFragment(gameViewModel.score))
    }
}