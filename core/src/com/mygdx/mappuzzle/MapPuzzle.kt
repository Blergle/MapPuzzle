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
    //colors used for pieces
    var colors : Colors? = null;

    var levels : ArrayList<String>? = null;

    var settings : Settings = Settings()

    var dailyPuzzle: DailyPuzzle = DailyPuzzle(settings)

    /**
     * runs on application startup,
     * put any initializers for global variables in here
     */
    override fun create() {
        assetManager = AssetManager();
        batch = PolygonSpriteBatch(32767)
        colors = Colors();
        colors!!.createColors()
        levels = ArrayList();
        createLevels()
        //this command switches the current screen being displayed
        this.setScreen(LoadingScreen(this))
    }

    /**
     * this is where level ids are added to the game,
     * adding an id here will eventually enable the game to pick it and load it.
     */
    fun createLevels(){
        levels!!.add("hungary");
        levels!!.add("france");
        levels!!.add("germany")
        levels!!.add("lithuania")
        levels!!.add("slovakia")
        levels!!.add("nepal")
        levels!!.add("brazil")
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
        colors!!.dispose();
        assetManager!!.dispose();
    }
}