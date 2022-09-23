package com.mygdx.mappuzzle

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.kotlin.*

class DailyPuzzleTest {

    private lateinit var dailyPuzzle: DailyPuzzle

    @Before
    fun setUp() {
        dailyPuzzle = mock()
    }

    @After
    fun tearDown() {
        Mockito.reset(dailyPuzzle)
    }

    @Test
    fun getFormat() {
    }

    @Test
    fun setFormat() {
    }

    @Test
    fun getToday() {
    }

    @Test
    fun setToday() {
    }

    @Test
    fun getStored() {
    }

    @Test
    fun setStored() {
    }

    @Test
    fun pickDailyLevel() {
    }

    @Test
    fun getSettings() {
    }

    @Test
    fun setSettings() {
    }
}