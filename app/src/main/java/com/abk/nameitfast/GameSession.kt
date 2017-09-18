package com.abk.nameitfast

import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by kgilmer on 9/16/17.
 */
class GameSession constructor(val questionCount: Int) {

    companion object {
        val rng : Random = Random()
        val colorSet: MutableSet<Color> = EnumSet.allOf(Color::class.java)
    }

    enum class Color (val rgb: Int) {
        RED(android.graphics.Color.RED),
        GREEN(android.graphics.Color.GREEN),
        BLUE(android.graphics.Color.BLUE),
        BLACK(android.graphics.Color.BLACK),
        CYAN(android.graphics.Color.CYAN),
        DARKGRAY(android.graphics.Color.DKGRAY),
        MAGENTA(android.graphics.Color.MAGENTA),
        YELLOW(android.graphics.Color.YELLOW),
        WHITE(android.graphics.Color.WHITE)
    }

    data class Question(val color: Color, val labels: List<Color>) {

    }

    fun generateQuestions(): Iterable<Question> {
        val questionList = mutableListOf<Question>();

        for (i in 1..questionCount) {
            val randQuestion = Color.values()[rng.nextInt(Color.values().size)]
            questionList.add(Question(randQuestion, createRandomLabels(randQuestion)))
        }

        return questionList
    }

    private fun createRandomLabels(requiredColor: Color): List<Color> {
        val shuffledColors: MutableList<Color> = ArrayList()

        shuffledColors.addAll(Color.values())

        do {
            Collections.shuffle(shuffledColors)
        } while (!shuffledColors.subList(0, 4).contains(requiredColor))

        return shuffledColors.subList(0, 4)
    }
}