package com.example.wordguesser.screens.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wordguesser.R
import com.example.wordguesser.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {

    private lateinit var binding: FragmentScoreBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)

        // approach 1 to get args bundle
        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()

        /** approach 2 to get args bundle
            val args = ScoreFragmentArgs.fromBundle(requireArguments())
        **/

        val scoreViewModelFactory = ScoreViewModelFactory(scoreFragmentArgs.score)
        val scoreViewModel = ViewModelProvider(this, scoreViewModelFactory).get(ScoreViewModel::class.java)

        scoreViewModel.finalScore.observe(viewLifecycleOwner) { updatedScore ->
            binding.scoreText.text = updatedScore.toString()
        }

        binding.playAgainButton.setOnClickListener {
            scoreViewModel.onPlayAgain()
        }

        scoreViewModel.playAgainEvent.observe(viewLifecycleOwner) { playAgain ->
            if (playAgain) {
                findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment())
                scoreViewModel.onPlayAgainComplete()
            }
        }


        return binding.root
    }
}