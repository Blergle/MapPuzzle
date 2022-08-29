package com.mygdx.mappuzzle

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.net.HttpRequestBuilder.json
import com.badlogic.gdx.utils.JsonReader
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

    fun createLevel(level : String) : Level{
        var featureCollection = FeatureCollection.fromJson(Gdx.files.internal("levels/$level/$level-1.json").readString());
        var numerator :Float= (JsonReader().parse(Gdx.files.internal("levels/$level/$level-scale.json").readString())).get("numerator").asFloat()
        var denominator :Float = (JsonReader().parse(Gdx.files.internal("levels/$level/$level-scale.json").readString())).get("denominator").asFloat()
        var heightOffset :Float = numerator/denominator;
        val l = Level()
        val features = featureCollection.features()

        for(f in features!!) {
            val piece = Piece()
            val coordinates = (f.geometry() as MultiPolygon)!!.coordinates()
            val color = Random.nextInt(0, game.colors!!.colors.size);
            piece.createPolygon(game.colors!!.colors[color], coordinates[0][0], heightOffset)
            if(coordinates[0].size>1){
                for(i in 1 until coordinates[0].size){
                    piece.createHole(game.colors!!.holeColor!!, coordinates[0][i])
                }
            }
            if(coordinates.size>1){
                for(i in 1 until coordinates.size-1){
                    piece.addPolygon(game.colors!!.colors[color], coordinates[i][0])
                    if(coordinates[i].size>1){
                        for(j in 1 until coordinates[i].size){
                            piece.createHole(game.colors!!.holeColor!!, coordinates[i][j])
                        }
                    }
                }
                piece.recalculateValues();
            }
            l.addPiece(piece)
            l.sort()
        }


        featureCollection = FeatureCollection.fromJson(Gdx.files.internal("levels/$level/$level-0.json").readString())
        val piece = Piece()
        val coordinates = (featureCollection.features()!![0].geometry() as MultiPolygon).coordinates()
        piece.createPolygon(game.colors!!.outlineColor!!, coordinates[0][0], heightOffset)
        if(coordinates.size>1){
            for(i in 1 until coordinates.size-1){
                piece.addPolygon(game.colors!!.outlineColor!!, coordinates[i][0])
            }
            piece.recalculateValues();
        }
        l.outline = piece;
        return l
    }


}