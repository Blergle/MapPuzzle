package com.mygdx.mappuzzle

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Game
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.scenes.scene2d.actions.Actions.show
import com.badlogic.gdx.utils.Json
import com.badlogic.gdx.utils.ScreenUtils
import com.mapbox.geojson.FeatureCollection

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