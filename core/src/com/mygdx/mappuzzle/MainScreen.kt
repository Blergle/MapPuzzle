package com.mygdx.mappuzzle

import com.badlogic.gdx.*
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.ScreenUtils
import java.util.*


class MainScreen(game : Game) : Screen, InputAdapter() {

    val mapPuzzle = game
    override fun show() {
        Gdx.input.inputProcessor = this
    }
    var r = 0f
    var g = 0f
    var b = 1f


    override fun render(delta: Float) {
        ScreenUtils.clear(r, g, b, 1f)
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
        return true
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return true
    }

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        return true
    }
}