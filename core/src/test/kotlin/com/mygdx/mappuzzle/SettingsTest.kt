package com.mygdx.mappuzzle

import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.kotlin.*

class SettingsTest {

    private lateinit var settings: Settings

    @Before
    fun setUp() {
        settings = mock()
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
    fun setLevel() {
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