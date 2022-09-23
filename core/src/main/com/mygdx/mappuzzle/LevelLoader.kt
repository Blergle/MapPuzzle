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
open class LevelLoader(var game : MapPuzzle) {

    /**
     * Creates the level based on the string id given.
     * creates the level by reading in geojson coordinates from a file located in a path specified by the level string, and
     * then creating pieces from that geojson information.
     * this allows us to create extremely accurate geographical puzzle pieces.
     * @param level a string representing the level to be created.
     * @return the created level
     */
    fun createLevel(level : String) : Level{
        var info = Gdx.files.internal("levels/$level/$level").readString()
        var featureCollection = FeatureCollection.fromJson(Gdx.files.internal("levels/$level/$level-1.json").readString());
        val numerator :Float= (JsonReader().parse(Gdx.files.internal("levels/$level/$level-scale.json").readString())).get("numerator").asFloat()
        val denominator :Float = (JsonReader().parse(Gdx.files.internal("levels/$level/$level-scale.json").readString())).get("denominator").asFloat()
        val heightOffset :Float = numerator/denominator;
        val l = Level()
        val features = featureCollection.features()
        var colors  = Array(game.colors!!.colors.size) { i -> true};
        var n = 0;
        for(f in features!!) {
            val piece = Piece()
            val coordinates = (f.geometry() as MultiPolygon)!!.coordinates()
            var color = 0
            if(n==game.colors!!.colors.size){
                colors  = Array(game.colors!!.colors.size) { i -> true };
                n=0;
            }else{
                n++
                var go = true;
                while(go){
                    color = Random.nextInt(0, game.colors!!.colors.size);
                    if(colors[color]){
                        colors[color]=false
                        go=false
                    }
                }
            }
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
            l.addInfo(info)
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