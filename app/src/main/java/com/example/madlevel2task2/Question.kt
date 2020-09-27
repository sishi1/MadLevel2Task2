package com.example.madlevel2task2

import androidx.recyclerview.widget.ItemTouchHelper

data class Question (var question: String, var answer: Int){
    companion object {
        private const val correct = ItemTouchHelper.RIGHT
        private const val incorrect = ItemTouchHelper.LEFT

        val questions = arrayOf(
            "A 'val' and 'var' are the same.",
            "Mobile Application Development grants 12 ECTs.",
            "A unit in Kotlin corresponds to void in Java.",
            "In Kotlin 'when' replaces the 'switch' operator in Java.",
            "Sishi is great at procrastinating."
        )

        val answers = arrayOf(
            incorrect,
            incorrect,
            correct,
            correct,
            correct
        )
    }
}