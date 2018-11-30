package com.workshop.universityannouncementsboard.extra

import org.junit.*
import org.junit.Assert.*
import kotlin.system.*

class MutableLazyKtTest {

    @Test
    fun `I don't have to wait if I changed value first`() {
        val time = measureTimeMillis {
            var game: Game? by mutableLazy { readGameFromSave() }
            game = Game()
            print(game)
        }
        assert(time in 0..100)
    }

    @Test
    fun `I have to wait if I changed value first`() {
        val time = measureTimeMillis {
            val game: Game? by mutableLazy { readGameFromSave() }
            print(game)
        }
        assert(time in 450..550)
    }

    @Test
    fun `MutableLazy should accept nullable values`() {
        val lazy by mutableLazy<String?> { null }
        assertNull(lazy)

        var lazy2 by mutableLazy<String?> { "A" }
        lazy2 = null
        assertNull(lazy2)
    }

    private class Game()

    private fun readGameFromSave(): Game? {
        Thread.sleep(500)
        return Game()
    }
}