package com.mygdx.mappuzzle

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.*

class LevelTest {

    private lateinit var level: Level

    @Before
    fun setUp() {
        level = mock()
    }

    @After
    fun tearDown() {
        Mockito.reset(level)
    }

    @Test
    fun getOutline() {
    }

    @Test
    fun setOutline() {
    }

    @Test
    fun getPieces() {
    }

    @Test
    fun setPieces() {
    }

    @Test
    fun draw() {
    }

    @Test
    fun get() {
    }

    @Test
    fun sort() {
    }

    @Test
    fun addPiece() {
    }
}