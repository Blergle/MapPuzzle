package com.mygdx.mappuzzle

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Piece(var x : Float, var y : Float, var t : Texture) {
    fun draw(batch : SpriteBatch){
        batch.draw(t, x, y)
    }
}