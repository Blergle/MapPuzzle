package com.mygdx.mappuzzle

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture

class LoadingScreen(var game : MapPuzzle) : Screen {
    override fun show() {
        game.assetManager!!.load("piece.jpg", Texture::class.java)
    }

    override fun render(delta: Float) {
        if(game.assetManager!!.update(17)){
            game.screen = MainScreen(game)
        }
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
}