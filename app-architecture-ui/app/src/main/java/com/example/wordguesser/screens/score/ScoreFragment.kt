package com.example.wordguesser.screens.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.wordguesser.R
import com.example.wordguesser.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {

    private lateinit var binding: FragmentScoreBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)

        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()
        binding.scoreText.text = scoreFragmentArgs.score.toString()

        val args = ScoreFragmentArgs.fromBundle(requireArguments())
        binding.scoreText.text = args.score.toString()

        binding.playAgainButton.setOnClickListener {
            findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment())
        }

        return binding.root
    }
}