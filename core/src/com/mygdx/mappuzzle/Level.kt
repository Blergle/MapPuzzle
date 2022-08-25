package com.mygdx.mappuzzle

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * this object will represent a Level so put anything level specific in here
 * currently just contains an array of pieces for drawing.
 */
class Level {

    val pieces: MutableList<Piece> = ArrayList()

    fun draw(batch : PolygonSpriteBatch) {
        for(Piece in pieces){
            Piece.draw(batch)
        }
    }

/**
 * @param x value of the
 * */
    fun get(x : Float, y : Float) : Piece?{
        for(Piece in pieces){
            if(Piece.isIn(x,y)){
                return Piece
            }
        }
        return null
    }

    /** Function is to add a piece to the drawing
     * @param p a piece to be added to the list
     *
     */
    fun addPiece(p : Piece){
        pieces.add(p)
    }

    /**
     * Dispose the piece. It must not be used after that.
     */
    fun dispose(){
        for(Piece in pieces){
            Piece.dispose()
        }
    }

}