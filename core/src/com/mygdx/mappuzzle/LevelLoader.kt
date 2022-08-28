package com.mygdx.mappuzzle

import com.badlogic.gdx.Gdx
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.Geometry
import com.mapbox.geojson.MultiPolygon
import kotlin.random.Random

/**
 * object used to load specific levels, currently just creates hungary
 * a way to do this dynamically is probably better but this works as a placeholder
 */
class LevelLoader(var game : MapPuzzle) {

    fun createLevel() : Level{
        var hungary = FeatureCollection.fromJson(Gdx.files.internal("hungary.json").readString());
        val l = Level()
        val features = hungary.features()

        for(f in features!!) {
            val piece = Piece()
            val coordinates = (f.geometry() as MultiPolygon)!!.coordinates()
            val color = Random.nextInt(0, game.colors!!.colors.size);
            piece.createPolygon(game.colors!!.colors[color], coordinates[0][0])

            if(coordinates[0].size>1){
                for(i in 1 until coordinates[0].size){
                    piece.createHole(game.colors!!.holeColor!!, coordinates[0][i])
                }
            }
            l.addPiece(piece)
            l.sort()
        }


        hungary = FeatureCollection.fromJson(Gdx.files.internal("hungary_0.json").readString())
        val piece = Piece()
        val coordinates = (hungary.features()!![0].geometry() as MultiPolygon).coordinates()
        piece.createPolygon(game.colors!!.outlineColor!!, coordinates[0][0])
        l.outline = piece;
        return l


    }
}