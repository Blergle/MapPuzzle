package com.mygdx.mappuzzle

import com.badlogic.gdx.Gdx
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

    var heightOffset = 0f;

    var offsetX = 0f;
    var offsetY = 0f;


    var polygon : PolygonSprite? = null;
    val polygons = ArrayList<Piece>()
    val holes = ArrayList<Piece>()

    constructor(color : Texture, points : List<Point>) : this() {
        createPolygon(color, points);
    }
    /**
     * draws the polygon
     */
    fun draw(batch : PolygonSpriteBatch){
        polygon!!.draw(batch)
        for(p in holes){
            p.polygon!!.setPosition(polygon!!.x + (p.offsetX-offsetX),polygon!!.y + (p.offsetY-offsetY)*heightOffset)
            p.draw(batch)
        }
    }

    /**
     * checks to see if the given x and y coordinates are currently in this objexts bounds
     * returns true if so. Used mostly to see if the object has been clicked or not.
     */
    fun isIn(x : Float, y : Float): Boolean {
        if(x >polygon!!.x + (minX - offsetX)   && x < polygon!!.x + (minX - offsetX) + this.width && y > polygon!!.y + (minY - offsetY) && y < polygon!!.y + (minY - offsetY) + this.height){
            return true
        }
        return false
    }

    fun createHole(color : Texture, points: List<Point>){
        holes.add(Piece(color, points));
    }


    /**
     * polygon sprite which will be used to draw the Piece object, using the polygonSprite will allow
     * the polygon to undergo transformations like scaling much easier
     *
     * @param color the color of the piece.
     */
    fun createPolygon(color : Texture, points : List<Point>){
        //this section converts the geojson points into a format which can be turned into a polygon sprite
        val floats = FloatArray(points.size*2)
        var j = 0;
        offsetX = points[0].longitude().toFloat();
        offsetY = points[0].latitude().toFloat();
        minX = points[0].longitude().toFloat();
        maxX = points[0].longitude().toFloat();

        minY = points[0].latitude().toFloat();
        maxY = points[0].latitude().toFloat();

        for(i in points.indices){
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

        heightOffset = 7f/4.5f;
        width = maxX - minX;
        height = (maxY - minY)*heightOffset;

        val triangulator = EarClippingTriangulator()
        val pr = PolygonRegion(TextureRegion(color), floats, triangulator.computeTriangles(floats).toArray().clone())
        polygon = PolygonSprite(pr)


        polygon!!.setOrigin(0f, 0f)
        polygon!!.setScale(1f, heightOffset);
    }

    /**
     * checks if this piece is in the right place and returns true if so
     * @return true if the piece is in the right place and false if not.
     */
    fun checkPos(outlineMinX :Float, outlineMinY : Float, viewportHeight : Float, outlineHeight : Float) : Boolean{
        if(polygon!!.x + outlineMinX<= offsetX + 0.1f/width && polygon!!.x + outlineMinX >= offsetX - 0.1f/width){

            if((polygon!!.y + outlineMinY*heightOffset) - (viewportHeight - outlineHeight)<= offsetY*heightOffset + 0.2f/height &&(polygon!!.y + outlineMinY*heightOffset) - (viewportHeight - outlineHeight) >= offsetY*heightOffset - 0.2f/height){
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