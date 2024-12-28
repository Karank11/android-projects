package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.fragments.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game_won, container, false)
        binding.nextGameButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_gameWonFragment_to_titleFragment))

        val args = GameWonFragmentArgs.fromBundle(requireArguments())
        Toast.makeText(context, "correct questions: ${args.numCorrect} && questions attempted: ${args.numQuestions}", Toast.LENGTH_SHORT).show()
        return binding.root
    }
}