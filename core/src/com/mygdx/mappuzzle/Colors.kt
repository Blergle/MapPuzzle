package com.mygdx.mappuzzle

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture

/**
 * small class used for creating and storing colors used for pieces.
 * makes managing and disposing of colors easier later down the line.
 */
class Colors {
    val colors : ArrayList<Texture> = ArrayList()
    var outlineColor : Texture? = null;
    var holeColor : Texture? =  null;

    /**
     * creates all the colors to be used for pieces.
     */
    fun createColors(){
        //Outline color
        var pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.GRAY)
        pix.fill();
        outlineColor = Texture(pix)
        //hole color
        pix = Pixmap(1,1,Pixmap.Format.RGBA8888);
        pix.setColor(Color.GRAY);
        pix.fill();
        holeColor = Texture(pix);
        //RED
        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.RED)
        pix.fill();
        colors.add(Texture(pix))

        //RED
        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.BLUE)
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.GREEN)
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.YELLOW)
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.SKY)
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.TEAL)
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.NAVY)
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.ORANGE)
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.MAROON)
        pix.fill();
        colors.add(Texture(pix))

        pix.dispose();
    }

    /**
     * disposes of color Textures before app close.
     */
    fun dispose(){
        outlineColor!!.dispose();
        holeColor!!.dispose();
        for(color in colors){
            color.dispose();
        }
    }
}