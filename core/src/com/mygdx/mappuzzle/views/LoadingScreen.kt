package com.mygdx.mappuzzle

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Texture
import com.mygdx.mappuzzle.views.MenuScreen

/**
 * screen class used to load assets and levels before the main screen is shown
 * currently empty b ut if anything needs to be loaaded before a level can be shown put it here.
 */
class LoadingScreen(var game : MapPuzzle) : Screen {
    override fun show() {

    }

    override fun render(delta: Float) {
        //switches screen onces all the assets are loaded
        if(game.assetManager!!.update(17)){
            game.screen = MenuScreen(game)
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