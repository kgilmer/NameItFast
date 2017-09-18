package com.abk.nameitfast

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        var gameSession = GameSession(6)
        var questions = ArrayDeque<GameSession.Question>()
        var lastTime: Long = -1L
        var answerTimes: MutableList<Long> = ArrayList()
        var correctCount: Double = 0.0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button1).setOnClickListener {
            checkAnswer(questions.pop(), 0)
            loadQuestions()
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            checkAnswer(questions.pop(), 1)
            loadQuestions()
        }

        findViewById<Button>(R.id.button3).setOnClickListener {
            checkAnswer(questions.pop(), 2)
            loadQuestions()
        }

        findViewById<Button>(R.id.button4).setOnClickListener {
            checkAnswer(questions.pop(), 3)
            loadQuestions()
        }

        loadQuestions()
    }

    private fun loadQuestions() {
        if (questions.isEmpty()) {
            if (!answerTimes.isEmpty()) {
                showMessage(calculateAverage(answerTimes), ((correctCount / gameSession.questionCount) * 100f))
                answerTimes.clear()
                correctCount = 0.0
            }
            questions.addAll(gameSession.generateQuestions())
        }

        val question = questions.peek()

        findViewById<View>(R.id.question).setBackgroundColor(question.color.rgb)
        findViewById<Button>(R.id.button1).setText(question.labels[0].name, TextView.BufferType.NORMAL)
        findViewById<Button>(R.id.button2).setText(question.labels[1].name, TextView.BufferType.NORMAL)
        findViewById<Button>(R.id.button3).setText(question.labels[2].name, TextView.BufferType.NORMAL)
        findViewById<Button>(R.id.button4).setText(question.labels[3].name, TextView.BufferType.NORMAL)
    }

    private fun showMessage(calculateAverage: Double, percentCorrect: Double) {
        Toast.makeText(applicationContext, "Average time " + (calculateAverage / 1000F) + " correct: " + percentCorrect, Toast.LENGTH_LONG).show()
    }

    private fun calculateAverage(answerTimes: List<Long>): Double {
        return answerTimes.average()
    }

    private fun checkAnswer(q: GameSession.Question, index: Int) {
        if (lastTime != -1L) {
            answerTimes.add(System.currentTimeMillis() - lastTime)
        }
        lastTime = System.currentTimeMillis()

        if (q.color.name == q.labels[index].name) {
            correctCount++
        }
    }
}
