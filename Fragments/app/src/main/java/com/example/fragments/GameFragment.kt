package com.example.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.fragments.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private val questions: MutableList<Question> = mutableListOf(
        Question(text = "What is Android Jetpack?",
            options = listOf("all of these", "tools", "documentation", "libraries")),
        Question(text = "Base class for Layout?",
            options = listOf("ViewGroup", "ViewSet", "ViewCollection", "ViewRoot")),
        Question(text = "Layout for complex Screens?",
            options = listOf("ConstraintLayout", "GridLayout", "LinearLayout", "FrameLayout")),
        Question(text = "Pushing structured data into a Layout?",
            options = listOf("Data Binding", "Data Pushing", "Set Text", "OnClick")),
        Question(text = "Inflate layout in fragments?",
            options = listOf("onCreateView", "onViewCreated", "onCreateLayout", "onInflateLayout")),
        Question(text = "Build system for Android?",
            options = listOf("Gradle", "Graddle", "Grodle", "Groyle")),
        Question(text = "Android vector format?",
            options = listOf("VectorDrawable", "AndroidVectorDrawable", "DrawableVector", "AndroidVector")),
        Question(text = "Android Navigation Component?",
            options = listOf("NavController", "NavCentral", "NavMaster", "NavSwitcher")),
        Question(text = "Registers app with launcher?",
            options = listOf("intent-filter", "app-registry", "launcher-registry", "app-launcher")),
        Question(text = "Mark a layout for Data Binding?",
            options = listOf("<layout>", "<binding>", "<data-binding>", "<dbinding>"))
    )
    lateinit var currentQuestion: Question
    lateinit var options: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = ((questions.size + 1) / 2).coerceAtMost(3)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        // Shuffles the questions and sets the question index to the first question.
        randomizeQuestions()

        // Bind this fragment class to the layout
        binding.game = this

        binding.submitButton.setOnClickListener {view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            if (checkedId != -1) {
                var clickedOption = 0
                when(checkedId) {
                    R.id.firstAnswerRadioButton -> clickedOption = 0
                    R.id.secondAnswerRadioButton -> clickedOption = 1
                    R.id.thirdAnswerRadioButton -> clickedOption = 2
                    R.id.fourthAnswerRadioButton -> clickedOption = 3
                }

                /**
                 * The first answer in the original question is always the correct one, so if our answer matches, we have the correct answer.
                 */
                if (options[clickedOption] == currentQuestion.options[0]) {
                    questionIndex++
                    if (questionIndex < numQuestions) {
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        /** all questions completed, we won!! Go to GameWonFragment **/
                        view.findNavController().navigate(R.id.action_gameFragment_to_gameWonFragment)
                    }
                } else {
                    /** Game over! A wrong answer sends us to the gameOverFragment. **/
                    view.findNavController().navigate(R.id.action_gameFragment_to_gameOverFragment)
                }
            }
        }
        return binding.root
    }

    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        options = currentQuestion.options.toMutableList()
        // and shuffle them
        options.shuffle()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_trivia_question, questionIndex + 1, numQuestions)
    }
}