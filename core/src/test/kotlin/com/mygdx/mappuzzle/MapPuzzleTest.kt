package com.mygdx.mappuzzle

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.kotlin.mock

class MapPuzzleTest {

    private lateinit var mapPuzzle : MapPuzzle

    @Before
    fun setUp() {
        mapPuzzle = mock()
    }

    @After
    fun tearDown() {
        Mockito.reset(mapPuzzle)
    }

    //probably don't need to test this
    @Test
    fun getBatch() {
    }

    //probably don't need to test this
    @Test
    fun setBatch() {
    }

    //probably don't need to test this
    @Test
    fun getAssetManager() {
    }

    //probably don't need to test this
    @Test
    fun setAssetManager() {
    }

    //probably don't need to test this
    @Test
    fun getColors() {
    }

    //probably don't need to test this
    @Test
    fun setColors() {
    }

    //probably don't need to test this
    @Test
    fun getLevels() {
    }

    //probably don't need to test this
    @Test
    fun setLevelsTest() {
        mapPuzzle.levels = ArrayList()
        mapPuzzle.levels!!.add("Test1")
        var length = mapPuzzle.levels!!.size
        mapPuzzle.levels!!.add("Test2")
        assertTrue(mapPuzzle.levels!!.size > length && mapPuzzle.levels!!.contains("Test1") && mapPuzzle.levels!!.contains("Test2"))
    }

    @Test
    fun create() {
    }

    @Test
    fun createLevels() {
    }

    //probably don't need to test this
    @Test
    fun render() {
    }

    //probably don't need to test this
    @Test
    fun dispose() {
    }
}