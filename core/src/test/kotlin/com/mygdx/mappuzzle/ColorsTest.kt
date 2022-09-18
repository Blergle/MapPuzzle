package com.mygdx.mappuzzle

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.kotlin.*


//these tests probably can't be made without using a headless backend for libgdx because the class deals with graphics
class ColorsTest {

    private lateinit var colors: Colors

    @Before
    fun setUp() {
        colors = mock()
    }

    @After
    fun tearDown() {
        Mockito.reset(colors)
    }

    @Test
    fun getColors() {
    }

    @Test
    fun getOutlineColor() {
    }

    @Test
    fun setOutlineColor() {
    }

    @Test
    fun getHoleColor() {
    }

    @Test
    fun setHoleColor() {
    }

    @Test
    fun getBackgroundColor() {
    }

    @Test
    fun setBackgroundColor() {
    }

    @Test
    fun createColors() {
    }

    @Test
    fun dispose() {
    }
}