package com.example.madlevel2task2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2task2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        for (i: Int in Question.questions.indices) {
            questions.add(
                Question(
                    Question.questions[i],
                    Question.answers[i]
                )
            )
        }

        binding.rvQuestions.layoutManager =
            LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        binding.rvQuestions.adapter = questionAdapter
        binding.rvQuestions.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(binding.rvQuestions)
        questionAdapter.notifyDataSetChanged()
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if (questions[position].answer == direction)
                    questions.removeAt(position)
                else
                    Snackbar.make(binding.rvQuestions[position], "Incorrect. Try again.",
                        Snackbar.LENGTH_SHORT).show()

                questionAdapter.notifyDataSetChanged()
            }
        }

        return ItemTouchHelper(callback)
    }
}