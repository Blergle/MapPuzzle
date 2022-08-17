package com.mygdx.mappuzzle

import com.badlogic.gdx.Gdx
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.Geometry
import com.mapbox.geojson.MultiPolygon

/**
 * object used to load specific levels, currently just creates hungary
 * a way to do this dynamically is probably better but this works as a placeholder
 */
class LevelLoader {

    fun createHungary() : Level{
        var hungary = FeatureCollection.fromJson(Gdx.files.internal("hungary.json").readString());
        var l = Level()
        var features = hungary.features()

        for(f in features!!) {
            var piece = Piece()
            for (p in (f.geometry() as MultiPolygon)!!.coordinates()[0][0]) {
                piece.addPoint(p)
            }
            piece.createPolygon()
            l.addPiece(piece)
        }
        return l
    }
}