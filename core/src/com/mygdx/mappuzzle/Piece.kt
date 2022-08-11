package com.mygdx.mappuzzle

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g3d.particles.ParticleSorter

class Piece(var x : Float, var y : Float) {
    val height = 200
    val width = 200
    fun draw(batch : SpriteBatch){
        batch.draw(T.t, x, y)
    }
    companion object T{
        var t :Texture? = null
        fun setTexture(t : Texture){
            this.t = t
        }
    }
    fun isIn(x : Float, y : Float): Boolean {
        if(x > this.x && x < this.x + this.width && y > this.y && y < this.y + this.height){
            return true
        }
        return false
    }

}