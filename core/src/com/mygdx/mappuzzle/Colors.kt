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
    var backgroundColor : Texture? = null;

    /**
     * creates all the colors to be used for pieces.
     */
    fun createColors(){
        //Outline color
        var pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.DARK_GRAY)
        pix.fill();
        outlineColor = Texture(pix)
        //Background color
        pix = Pixmap(1,1,Pixmap.Format.RGBA8888)
        pix.setColor(Color.GRAY)
        pix.fill()
        backgroundColor = Texture(pix)
        //hole color
        pix = Pixmap(1,1,Pixmap.Format.RGBA8888);
        pix.setColor(Color.DARK_GRAY);
        pix.fill();
        holeColor = Texture(pix);
        //RED
        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.RED)
        pix.fill();
        colors.add(Texture(pix))

        //RED
        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.GREEN)
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.BLUE)
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.YELLOW)
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

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.MAGENTA)
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.VIOLET)
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.OLIVE)
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.BROWN)
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.NAVY)
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.SCARLET)
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.CHARTREUSE)
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.TAN)
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
        backgroundColor!!.dispose()
        for(color in colors){
            color.dispose();
        }
    }
}