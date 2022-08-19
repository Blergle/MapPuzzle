package com.mygdx.mappuzzle

import com.badlogic.gdx.*
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.Json
import com.badlogic.gdx.utils.ScreenUtils
import com.mapbox.geojson.FeatureCollection
import java.util.*
import javax.xml.catalog.CatalogFeatures

/**
 * Main Screen of the game, this is where the level will be shown and the game is actually played
 */
class MainScreen(var game : MapPuzzle) : Screen, InputAdapter() {
    //this object is used to load levels, probably a better way of doing this
    var l = LevelLoader()

    //this color determines the background
    var color : Color = Color.LIGHT_GRAY

    var level : Level = Level()

    override fun show() {
        //makes it so mouse clicks are registered properly
        Gdx.input.inputProcessor = this

        //creates and sets the level to hungary
        level = l.createHungary()

    }





    override fun render(delta: Float) {
        ScreenUtils.clear(color)
        game.batch!!.begin()
        level.draw(game.batch!!)
        game.batch!!.end()
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {

    }

    override fun dispose() {
        level.dispose()
    }



    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return true
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return true
    }

    /**
     * listener used to drag and move pieces on click
     * the coordinate system for the mouse and for the screen is slightly different so if
     * this ever produces an unexpected result thats probably why
     */
    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        var mouseY = Gdx.graphics.height-screenY.toFloat()
        var currentPiece = level.get(screenX.toFloat(), mouseY.toFloat())
        if(currentPiece != null){
            currentPiece.setX(screenX.toFloat() - currentPiece.width/2)
            currentPiece.setY(mouseY.toFloat() - currentPiece.height/2)
        }

        return true
    }
}