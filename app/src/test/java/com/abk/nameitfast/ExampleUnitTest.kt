package com.abk.nameitfast

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun happyPath() {
        val gs: GameSession = GameSession(10)

        val session = gs.generateQuestions().toList()

        assertTrue(session.size == 10)

        session.forEach {
            assertNotNull("expected number of possible answers.", it.labels.size == 4)
        }
    }
}
