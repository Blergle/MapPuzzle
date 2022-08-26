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
    var height = 200f
    var width = 200f

    var minX = 0f;
    var maxX = 0f;
    var minY = 0f;
    var maxY = 0f;

    var offsetX = 0f;
    var offsetY = 0f;
    //list of points in its polygon
    val points : ArrayList<Point> = ArrayList();

    var polygonRegion : PolygonRegion? = null;

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

    var polygon : PolygonSprite? = null;
    /**
     * polygon sprite which will be used to draw the Piece object, using the polygonSprite will allow
     * the polygon to undergo transformations like scaling much easier
     *
     * @param color the color of the piece.
     */
    fun createPolygon(color : Texture){
        //this section converts the geojson points into a format which can be turned into a polygon sprite
        var floats = FloatArray(points.size*2)
        var j = 0;
        offsetX = points[0].longitude().toFloat();
        offsetY = points[0].latitude().toFloat();
        minX = points[0].longitude().toFloat();
        maxX = points[0].longitude().toFloat();

        minY = points[0].latitude().toFloat();
        maxY = points[0].latitude().toFloat();

        for(i in 0 until points.size){
            var x = points[i].longitude().toFloat() - offsetX
            var y = points[i].latitude().toFloat() - offsetY
            floats[j++] = x
            floats[j++] = y

            if(points[i].longitude().toFloat() < minX){
                minX = points[i].longitude().toFloat()
            }
            if(points[i].longitude().toFloat() > maxX){
                maxX = points[i].longitude().toFloat()
            }
            if(points[i].latitude().toFloat() < minY){
                minY = points[i].latitude().toFloat()
            }
            if(points[i].latitude().toFloat() > maxY){
                maxY = points[i].latitude().toFloat()
            }
        }
        var heightOffset = 7f/4.5f;
        width = maxX - minX;
        height = (maxY - minY)*heightOffset;


        var triangulator = EarClippingTriangulator()
        var pr = PolygonRegion(TextureRegion(color), floats, triangulator.computeTriangles(floats).toArray().clone())
        polygonRegion = pr;
        polygon = PolygonSprite(pr)

        //contains functions for positioning the polygon, setposition determines where it will be drawn

        //set scale determines its size (the geojson coordinates are quite small so this number has to be pretty large)
        //a way to do this dynamically would be nice
        //im not entirely sure what this does but its required
        polygon!!.setOrigin(0f, 0f)
        polygon!!.setScale(1f, heightOffset);
        print(width/height);
        //sets the width and height of this object, currently this is only used for determining if the object has been
        // clicked and has nothing to do with how its actually drawn
        //this number will have to be set again if the pieces scale is ever changed


    }

    /**
     * checks if this piece is in the right place and returns true if so
     * @return true if the piece is in the right place and false if not.
     */
    fun checkPos() : Boolean{
        if(polygon!!.x <= offsetX - minX + 0.1f/width && polygon!!.x >= offsetX - minX - 0.1f/width){
            if(polygon!!.y <= offsetY - minY + 0.1f/height && polygon!!.y >= offsetY - minY - 0.1f/width){
                return true;
            }
        }
            return false;
    }

    @JvmName("setX1")
    fun setX(x : Float){
        polygon!!.setPosition(x, polygon!!.y)
    }
    @JvmName("setY1")
    fun setY(y : Float){
        polygon!!.setPosition(polygon!!.x, y)
    }
}