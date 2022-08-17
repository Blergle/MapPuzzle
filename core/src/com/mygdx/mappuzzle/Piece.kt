package com.mygdx.mappuzzle

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.*
import com.badlogic.gdx.graphics.g3d.particles.ParticleSorter
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.EarClippingTriangulator
import com.mapbox.geojson.Point

/**
 * individual puzzle piece, contains an x, y, width and height number for displaying on the screen.
 * additionally contains a PolygonSprite which is a sprite of the complex polygon used to represent
 * the country being shown
 */
class Piece() {
    var height = 200
    var width = 200
    //list of points in its polygon
    val points : ArrayList<Point> = ArrayList();

    /**
     * draws the polygon
     */
    fun draw(batch : PolygonSpriteBatch){
        polygon!!.draw(batch)
    }

    /**
     * checks to see if the given x and y coordinates are currently in this objexts bounds
     * returns true if so. Used mostly to see if the object has been clicked or not.
     */
    fun isIn(x : Float, y : Float): Boolean {
        if(x > this.polygon!!.x && x < this.polygon!!.x + this.width && y > this.polygon!!.y && y < this.polygon!!.y + this.height){
            return true
        }
        return false
    }

    /**
     * add a point to this objects polygon
     */
    fun addPoint(p : Point){
        points.add(p)
    }

    /**
     * polygon sprite which will be used to draw the Piece object, using the polygonSprite will allow
     * the polygon to undergo transformations like scaling much easier
     */
    var polygon : PolygonSprite? = null;
    var tex : Texture? = null
    fun createPolygon(){
        //this section allows the polygon to be filled in with a block of color
        var pix = Pixmap(1,1, Pixmap.Format.RGBA8888);

        //change the color here to switch what color the polygon will be drawn as
        pix.setColor(Color.RED)
        pix.fill();
        tex = Texture(pix)

        //this section converts the geojson points into a format which can be turned into a polygon sprite
        var floats = FloatArray(points.size*2)
        var j = 0;
        for(i in 0 until points.size){
            floats[j++] = points[i].longitude().toFloat()
            floats[j++] = points[i].latitude().toFloat()
        }
        var triangulator = EarClippingTriangulator()
        var pr = PolygonRegion(TextureRegion(tex), floats, triangulator.computeTriangles(floats).toArray())
        polygon = PolygonSprite(pr)

        //contains functions for positioning the polygon, setposition determines where it will be drawn
        polygon!!.setPosition(100f,100f)
        //set scale determines its size (the geojson coordinates are quite small so this number has to be pretty large)
        //a way to do this dynamically would be nice
        polygon!!.setScale(200f)
        //im not entirely sure what this does but its required
        polygon!!.setOrigin(floats[0],floats[1])
        //sets the width and height of this object, currently this is only used for determining if the object has been
        // clicked and has nothing to do with how its actually drawn
        //this number will have to be set again if the pieces scale is ever changed
        width = polygon!!.width.toInt()*200
        height = polygon!!.height.toInt()*200



        pix.dispose()

    }

    @JvmName("setX1")
    fun setX(x : Float){
        polygon!!.setPosition(x, polygon!!.y)
    }
    @JvmName("setY1")
    fun setY(y : Float){
        polygon!!.setPosition(polygon!!.x, y)
    }
    fun dispose(){
        tex!!.dispose()

    }

}