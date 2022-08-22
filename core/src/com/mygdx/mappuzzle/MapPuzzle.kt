package com.mygdx.mappuzzle

import com.badlogic.gdx.Game
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch

/**
 * main Game class, controls which screen is currently being shown
 * and contains various global  variables that need to be passed between screens
 */
class MapPuzzle : Game() {
    //used to draw polygons
    var batch: PolygonSpriteBatch? = null
    //manages external assets that need to be loaded
    var assetManager: AssetManager? = null;

    /**
     * runs on application startup,
     * put any initializers for global variables in here
     */
    override fun create() {
        assetManager = AssetManager();
        batch = PolygonSpriteBatch()
        //this command switches the current screen being displayed
        this.setScreen(LoadingScreen(this))
    }

    override fun render() {
        super.render()
    }

    /**
     * runs on application close, various libgdx objects need to be disposed of to
     * be freed from memory properly, dispose of any global variable with a dispose function here or
     * a memory leak will occur
     */
    override fun dispose() {
        batch!!.dispose()
    }
}