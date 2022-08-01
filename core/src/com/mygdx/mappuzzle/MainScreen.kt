package com.mygdx.mappuzzle

import com.badlogic.gdx.*
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.ScreenUtils
import java.util.*


class MainScreen(var game : MapPuzzle) : Screen, InputAdapter() {

    override fun show() {
        Gdx.input.inputProcessor = this
    }
    var r = 0f
    var g = 0f
    var b = 1f
    var x = 0f
    var y = 0f

    var piece = Piece(x,y,game.assetManager!!.get("piece.jpg"))


    override fun render(delta: Float) {
        ScreenUtils.clear(r, g, b, 1f)
        game.batch!!.begin()
        piece.draw(game.batch!!)
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

    }



    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        r = MathUtils.random()
        g = MathUtils.random()
        b = MathUtils.random()



        piece.x = screenX.toFloat()
        piece.y = Gdx.graphics.height-screenY.toFloat()
        return true
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return true
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        return true
    }
}