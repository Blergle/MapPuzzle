package com.mygdx.mappuzzle

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture

/**
 * small class used for creating and storing colors used for pieces.
 * makes managing and disposing of colors easier later down the line.
 */
open class Colors {
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
        pix.setColor(Color.GRAY)
        pix.fill();
        outlineColor = Texture(pix)
        //Background color
        pix = Pixmap(1,1,Pixmap.Format.RGBA8888)
        pix.setColor(Color.LIGHT_GRAY)
        pix.fill()
        backgroundColor = Texture(pix)
        //hole color
        pix = Pixmap(1,1,Pixmap.Format.RGBA8888);
        pix.setColor(Color.GRAY);
        pix.fill();
        holeColor = Texture(pix);
        //RED
        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.rgba8888(251f,210f,213f, 1f))
        pix.fill();
        colors.add(Texture(pix))

        //RED
        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.rgba8888(241f,177f,202f,1f))
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.rgba8888(219f,210f,123f,1f))
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.rgba8888(238f,214f,95f,1f))
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.rgba8888(255f,185f,145f,1f))
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.rgba8888(170f,219f,161f,1f))
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.rgba8888(156f,208f,224f,1f))
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.rgba8888(86f,177f,228f,1f))
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.rgba8888(139f,157f,214f,1f))
        pix.fill();
        colors.add(Texture(pix))

        pix = Pixmap(1,1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.rgba8888(203f,195f,232f,1f))
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