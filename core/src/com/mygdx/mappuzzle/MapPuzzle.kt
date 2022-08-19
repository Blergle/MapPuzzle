package com.mygdx.mappuzzle

import com.badlogic.gdx.Game
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.mappuzzle.views.*

public open class MapPuzzle : Game() {

    var font: BitmapFont? = null
    var batch: SpriteBatch? = null
    var img: Texture? = null
    var assetManager: AssetManager? = null;

    private var loadingScreen: LoadingScreen? = null
    private var settingsScreen: SettingsScreen? = null
    private var menuScreen: MenuScreen? = null
    private var mainScreen: MainScreen? = null
    private var endScreen: EndScreen? = null

    fun changeScreen(screen: Int) {
        when (screen) {
            MENU -> {
                if (menuScreen == null) menuScreen = MenuScreen(this)
                setScreen(menuScreen)
            }
            SETTINGS -> {
                if (settingsScreen == null) settingsScreen = SettingsScreen(this)
                setScreen(settingsScreen)
            }
            APPLICATION -> {
                if (mainScreen == null) mainScreen = MainScreen(this)
                setScreen(mainScreen)
            }
            ENDGAME -> {
                if (endScreen == null) endScreen = EndScreen(this)
                setScreen(endScreen)
            }
        }
    }
    override fun create() {
        //println("yes")
        assetManager = AssetManager();
        batch = SpriteBatch()
        font = BitmapFont();

        //this.setScreen(LoadingScreen(this))
        //this.setScreen(orch(this));

        loadingScreen = LoadingScreen(this)
        setScreen(loadingScreen)
    }

    override fun render() {

        //println("yes")
        //ScreenUtils.clear(1f, 0f, 0f, 1f)
        //batch!!.begin()
      //  batch!!.draw(img, 0f, 0f)
        //batch!!.end()

        super.render()
    }

    override fun dispose() {
        batch!!.dispose()
        img!!.dispose()
        super.dispose()
    }
    companion object {
        const val MENU = 0
        const val SETTINGS = 1
        const val APPLICATION = 2
        const val ENDGAME = 3
    }
}