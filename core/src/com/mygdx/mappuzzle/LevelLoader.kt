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

    fun createHungary() : Level{
        var hungary = FeatureCollection.fromJson(Gdx.files.internal("hungary.json").readString());
        var l = Level()
        var features = hungary.features()

        for(f in features!!) {
            var piece = Piece()
            for (p in (f.geometry() as MultiPolygon)!!.coordinates()[0][0]) {
                piece.addPoint(p)
            }
            var color = Random.nextInt(0, game.colors!!.colors.size);
            piece.createPolygon(game.colors!!.colors[color])
            l.addPiece(piece)
        }

        hungary = FeatureCollection.fromJson(Gdx.files.internal("hungary_0.json").readString())
        var piece = Piece()

        for (p in (hungary.features()!![0].geometry() as MultiPolygon).coordinates()[0][0]) {
            piece.addPoint(p)
        }

        piece.createPolygon(game.colors!!.outlineColor!!)
        l.outline = piece;
        return l


    }
}