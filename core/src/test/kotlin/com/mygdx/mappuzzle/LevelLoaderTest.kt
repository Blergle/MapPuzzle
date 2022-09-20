package com.mygdx.mappuzzle

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.*

class LevelLoaderTest {

    private lateinit var levelLoader: LevelLoader

    @Before
    fun setUp() {
        levelLoader = mock()
    }

    @After
    fun tearDown() {
        Mockito.reset(levelLoader)
    }

    @Test
    fun createLevel() {
    }

    @Test
    fun getGame() {
    }

    @Test
    fun setGame() {
    }
}