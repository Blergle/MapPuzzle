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
open class Piece() {
    var height = 200f
    var width = 200f

    var minX = 0f;
    var maxX = 0f;
    var minY = 0f;
    var maxY = 0f;
    /** the difference between the x and y units, this is necessary because the length
     *  of latitude units to longitude units vary depending on where you are on the planet*/
    var heightOffset = 0f;

    var offsetX = 0f;
    var offsetY = 0f;


    var polygon : PolygonSprite? = null;
    val polygons = ArrayList<Piece>()
    val holes = ArrayList<Piece>()

    /**
     * A constructor used to initialise a new Piece with and then create a base polygon
     * @param color the color of the polygon
     * @param points the coordinates that make up the new polygon
     * @param heightOffset the height difference between the width and height units.
     */
    constructor(color : Texture, points : List<Point>, heightOffset : Float) : this() {
        createPolygon(color, points, heightOffset);
    }
    /**
     * draws the polygon and all its child polygons
     * @param batch the batch used to draw thew polygon
     */
    fun draw(batch : PolygonSpriteBatch){
        polygon!!.draw(batch)
        for(p in polygons){
            p.polygon!!.setPosition(polygon!!.x + (p.offsetX-offsetX),polygon!!.y + (p.offsetY-offsetY)*heightOffset)
            p.draw(batch)
        }
        for(p in holes){
            p.polygon!!.setPosition(polygon!!.x + (p.offsetX-offsetX),polygon!!.y + (p.offsetY-offsetY)*heightOffset)
            p.draw(batch)
        }
    }

    /**
     * checks to see if the given x and y coordinates are currently in this objects bounds
     * returns true if so. Used mostly to see if the object has been clicked or not.
     *
     * @param x the x coordinate of the mouse click
     * @param y the y coordinate of the mouse click
     * @return true if the mouse click was within this polygons width and height
     */
    fun isIn(x : Float, y : Float): Boolean {
        if(x >polygon!!.x + (minX - offsetX)   && x < polygon!!.x + (minX - offsetX) + this.width && y > polygon!!.y + (minY - offsetY) && y < polygon!!.y + (minY - offsetY) + this.height){
            return true
        }
        return false
    }

    /**
     * creates a new polygon which will be drawn like a hole in the base polygon.
     * used to represent lakes and when a region is inside another region.
     * @param color the color of the hole
     * @param points the coordinates that make up the hole
     */
    fun createHole(color : Texture, points: List<Point>){
        holes.add(Piece(color, points, heightOffset));
    }

    /**
     * adds a child polygon which this Piece also represents.
     * Used for when a piece requires multiple polygons to be drawn.
     * @param color the color of the new polygon.
     * @param points the coordinates that make up the new polygon
     */
    fun addPolygon(color : Texture, points : List<Point>){
        polygons.add(Piece(color, points, heightOffset));
    }

    /**
     * recalculates various values used for coordinate conversions like width and height, now taking in to account extra added
     * polygons.
     */
    fun recalculateValues(){
        for(p in polygons){
            if(p.minX < minX){
                minX = p.minX
            }
            if(p.maxX > maxX){
                maxX= p.maxX
            }
            if(p.minY < minY){
                minY = p.minY
            }
            if(p.maxY > maxY){
                maxY = p.maxY
            }
        }
        width = maxX - minX
        height = (maxY - minY) *heightOffset
    }

    /**
     * polygon sprite which will be used to draw the Piece object, using the polygonSprite will allow
     * the polygon to undergo transformations like scaling much easier
     *
     * @param color the color of the piece.
     * @param points the coordinates that make up the polygon to be created.
     * @param heightOffset the estimated offset of longitude units to latitude units in this region
     */
    fun createPolygon(color : Texture, points : List<Point>, heightOffset : Float){
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

        this.heightOffset = heightOffset;
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
     *
     * @param outlineMinX the minimum x value of the outline piece in longitude
     * @param outlineMinY the minimum y value of the outline piece in latitude units
     * @param viewportHeight the height pof the viewport
     * @param outlineHeight the height of the outline piece
     * @param outlineWidth the width of the outlinePiece
     * @return true if the piece is in the right place and false if not.
     */
    fun checkPos(outlineMinX :Float, outlineMinY : Float, viewportHeight : Float, outlineHeight : Float, outlineWidth : Float) : Boolean{
        if(polygon!!.x + outlineMinX<= offsetX + 0.05f*outlineWidth && polygon!!.x + outlineMinX >= offsetX - 0.05f*outlineWidth){

            if((polygon!!.y + outlineMinY*heightOffset) - (viewportHeight - outlineHeight)<= offsetY*heightOffset + 0.05f*outlineHeight &&(polygon!!.y + outlineMinY*heightOffset) - (viewportHeight - outlineHeight) >= offsetY*heightOffset - 0.05f*outlineHeight){
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