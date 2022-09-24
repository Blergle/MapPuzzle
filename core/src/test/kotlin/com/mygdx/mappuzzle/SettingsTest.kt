package com.mygdx.mappuzzle

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Captor
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.*

//@RunWith(MockitoJUnitRunner::class)
class SettingsTest { //this is probably better done as an instrumented test, since Settings accesses SharedPreferences for most functions

//    @Captor
    lateinit var intCaptor: KArgumentCaptor<Int>
//    @Captor
    lateinit var stringCaptor: KArgumentCaptor<String>

    private lateinit var settings: Settings

    @Before
    fun setUp() {
        settings = mock() //can't use spy(), but I want to check invocation of functions called within functions :(
        //settings = Settings()
    }

    @After
    fun tearDown() {
        Mockito.reset(settings)
    }

    @Test
    fun getSettings() {
    }

    @Test
    fun setSettings() {
    }

    @Test
    fun getCurrentLevel() {
    }

    @Test
    fun setCurrentLevel() {
    }

    @Test
    fun allowNotifications() {
    }

    @Test
    fun notificationsAllowed() {
    }

    @Test
    fun setLevelTest() {
        //val mock: MapPuzzle = spy()
        //settings = mock<Settings>{
        //    on {setLevel(1)}
        //}
        //mock.settings = settings
        //mock.settings.settings = Gdx.app.getPreferences("My Settings")
        //mock.settings.currentLevel = 0
        //mock.settings.setLevel(1)
        //Mockito.verify(mock.settings.settings).putInteger("Level", 1)
        //settings.settings = spy()
        //settings.currentLevel = 0
        intCaptor = argumentCaptor<Int>()
        stringCaptor = argumentCaptor<String>()
        whenever(settings.setLevel(anyInt())).thenCallRealMethod()
        settings.setLevel(1)
        Mockito.verify(settings).store(stringCaptor.capture(), intCaptor.capture())
        assertEquals("Level", stringCaptor.firstValue)
        assertEquals(1, intCaptor.firstValue)
        //Mockito.verify(settings).store(anyString(), anyInt())
    }

    @Test
    fun getLevel() {
    }

    @Test
    fun playRandom() {
    }

    @Test
    fun isRandom() {
    }
}