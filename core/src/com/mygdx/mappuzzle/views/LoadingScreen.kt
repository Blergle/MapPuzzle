package com.mygdx.mappuzzle.views

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.mygdx.mappuzzle.MapPuzzle

class LoadingScreen(var game : MapPuzzle) : Screen {

    //public var parent: MapPuzzle = game

    override fun show() {
        game.assetManager!!.load("piece.jpg", Texture::class.java)
    }

    override fun render(delta: Float) {

//        if(game.assetManager!!.update(17)){
//            game.screen = MainScreen(game)
//        }

        game.changeScreen(MapPuzzle.MENU)
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
