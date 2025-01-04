package com.example.wordguesser.screens.game

import android.os.Bundle
import android.text.format.DateUtils
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        Log.i("GameFragment", "viewModelProvider is called for game VM")
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        gameViewModel.score.observe(viewLifecycleOwner) { newScore ->
            binding.currentScoreText.text = newScore.toString()
        }
        gameViewModel.word.observe(viewLifecycleOwner) { newWord ->
            binding.wordText.text = newWord
        }
        gameViewModel.eventGameFinish.observe(viewLifecycleOwner) { isGameFinished ->
            if (isGameFinished) {
                gameFinished()
                gameViewModel.onGameFinishComplete()
            }
        }

        gameViewModel.currentTime.observe(viewLifecycleOwner) { newTime ->
            binding.timerText.text = DateUtils.formatElapsedTime(newTime)
        }

        binding.correctButton.setOnClickListener {
            gameViewModel.onCorrect()
        }
        binding.skipButton.setOnClickListener {
            gameViewModel.onSkip()
        }
        return binding.root
    }

    private fun gameFinished() {
        val currentScore = gameViewModel.score.value ?: 0
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment(currentScore)
        findNavController().navigate(action)
    }
}