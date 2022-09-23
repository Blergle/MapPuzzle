package com.mygdx.mappuzzle

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.backends.headless.*
import com.badlogic.gdx.backends.headless.mock.graphics.MockGraphics
import com.badlogic.gdx.graphics.GL20
import net.bytebuddy.build.EntryPoint
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.kotlin.*


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

    //Tests adding elements to levels array
    @Test
    fun setLevelsTest() {
        mapPuzzle.levels = ArrayList()
        mapPuzzle.levels!!.add("Test1")
        var length = mapPuzzle.levels!!.size
        mapPuzzle.levels!!.add("Test2")
        assertTrue(mapPuzzle.levels!!.size > length && mapPuzzle.levels!!.contains("Test1") && mapPuzzle.levels!!.contains("Test2"))
    }

    //Tests create() function by copying everything it does (would be better to mock and use headless backend for libgdx, but I can't work out how to do that)
    @Test
    fun createTest() {
        //mapPuzzle.batch = PolygonSpriteBatch(32767) //this doesn't work without headless backend
        //var headless : HeadlessApplication
        //headless = HeadlessApplication()
        //HeadlessNativesLoader.load()
        //val mockGraphics = MockGraphics()
        //Gdx.graphics = mockGraphics
        //val headlessNet = HeadlessNet()
        //Gdx.net = headlessNet
        //val headlessFiles = HeadlessFiles()
        //Gdx.files = headlessFiles
        //Gdx.gl = mock(GL20::class.java)
        //val config = HeadlessApplicationConfiguration()
        //val myGdxGame: ApplicationListener = EntryPoint.getHeadlessApplication(config) // this doesn't work
        mapPuzzle.assetManager = AssetManager()
        //mapPuzzle.colors = Colors() //this doesn't work without headless backend
        //mapPuzzle.colors!!.createColors() //this doesn't work without headless backend
        mapPuzzle.levels = ArrayList();
        mapPuzzle.createLevels()
        //mapPuzzle.screen = LoadingScreen(mapPuzzle) //this should probably be UI testing only
        assertNotNull(mapPuzzle.assetManager)
        //assertNotNull(mapPuzzle.batch) //this doesn't work without headless backend
        //assertNotNull(mapPuzzle.colors) //this doesn't work without headless backend
        //assertNotNull(mapPuzzle.colors!!.backgroundColor) //this doesn't work without headless backend
        assertNotNull(mapPuzzle.levels) //this doesn't work without headless backend
        assertTrue(mapPuzzle.levels!!.contains("france")) //Checks call to createLevels(). Only works if createLevels() works - probably need to change that
        //assertTrue(mapPuzzle.screen == LoadingScreen(mapPuzzle)) //this should probably be UI testing only
    }

    //this is already tested in createTest() - tests createLevels()
    @Test
    fun createLevelsTest() {
        mapPuzzle.levels = ArrayList()
        mapPuzzle.createLevels()
        assertTrue(mapPuzzle.levels!!.contains("france"))
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