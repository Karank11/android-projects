package com.example.wordguesser.screens.game

import android.os.Bundle
import android.text.format.DateUtils
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
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        binding.gameViewModel = gameViewModel
        binding.lifecycleOwner = this

        gameViewModel.eventGameFinish.observe(viewLifecycleOwner) { isGameFinished ->
            if (isGameFinished) {
                gameFinished()
                gameViewModel.onGameFinishComplete()
            }
        }

        gameViewModel.currentTime.observe(viewLifecycleOwner) { newTime ->
            binding.timerText.text = DateUtils.formatElapsedTime(newTime)
        }

        return binding.root
    }

    private fun gameFinished() {
        val currentScore = gameViewModel.score.value ?: 0
        val action = GameFragmentDirections.actionGameFragmentToScoreFragment(currentScore)
        findNavController().navigate(action)
    }
}