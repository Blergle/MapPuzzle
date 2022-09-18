package com.mygdx.mappuzzle

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * this object will represent a Level so put anything level specific in here
 * currently just contains an array of pieces for drawing.
 */
open class Level {
    var outline : Piece? = null;
    var pieces: MutableList<Piece> = ArrayList()

    /**
     * draws all the pieces contained in this level and
     * the outline for this level
     * @param batch the batch used to draw the pieces.
     */
    fun draw(batch : PolygonSpriteBatch) {
        outline!!.draw(batch);
        for(Piece in pieces){
            Piece.draw(batch)
        }
    }
/** returns the piece at these x and y coordinates, returns null if piece isnt found
 *
 * @param x the x coordinate of the target piece
 * @param y the y coordinate of the target piece
 * @return the Piece if it is found, null if not.
 * */
    fun get(x : Float, y : Float) : Piece?{
        for(Piece in pieces.reversed()){
            if(Piece.isIn(x,y)){
                return Piece
            }
        }
        return null
    }

    /**
     * sorts the pieces in this level based on the amount of holes they have.
     * This is to ensure that a piece which is supposed to go in another pieces hole is always
     * drawn on top of that piece.
     */
    fun sort(){
        val sorted = (pieces.sortedWith(compareBy { -it.holes.size }))
        pieces.clear();
        for(p in sorted){
            pieces.add(p);
        }
    }

    /**
     * adds a piece to the level.
     * @param p the piece to be added.
     */
    fun addPiece(p : Piece){
        pieces.add(p)
    }
}