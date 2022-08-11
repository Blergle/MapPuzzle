package com.mygdx.mappuzzle

import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Level {

    val pieces: MutableList<Piece> = ArrayList()

    fun start(){
        pieces.add(Piece(10f,10f))
        pieces.add(Piece(20f,20f))
        pieces.add(Piece(30f,30f))
    }

    fun draw(batch : SpriteBatch) {
        for(Piece in pieces){
            Piece.draw(batch)
        }
    }


    fun get(x : Float, y : Float) : Piece?{
        for(Piece in pieces){
            if(Piece.isIn(x,y)){
                return Piece
            }
        }
        return null
    }

}