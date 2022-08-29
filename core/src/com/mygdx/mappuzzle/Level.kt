package com.mygdx.mappuzzle

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * this object will represent a Level so put anything level specific in here
 * currently just contains an array of pieces for drawing.
 */
class Level {
    var outline : Piece? = null;
    var pieces: MutableList<Piece> = ArrayList()

    fun draw(batch : PolygonSpriteBatch) {
        outline!!.draw(batch);
        for(Piece in pieces){
            Piece.draw(batch)
        }
    }
/** */
    fun get(x : Float, y : Float) : Piece?{
        for(Piece in pieces.reversed()){
            if(Piece.isIn(x,y)){
                return Piece
            }
        }
        return null
    }

    fun sort(){
        val sorted = (pieces.sortedWith(compareBy { -it.holes.size }))
        pieces.clear();
        for(p in sorted){
            pieces.add(p);
        }
    }



    fun addPiece(p : Piece){
        pieces.add(p)
    }
}