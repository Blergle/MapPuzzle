package com.mygdx.mappuzzle

import com.badlogic.gdx.*
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.ScreenUtils

/**
 * Main Screen of the game, this is where the level will be shown and the game is actually played
 */
class MainScreen(var game : MapPuzzle) : Screen, InputAdapter() {
    //this object is used to load levels, probably a better way of doing this
    var l = LevelLoader(game)

    //this color determines the background
    var color : Color = Color.LIGHT_GRAY

    var level : Level = Level()
    var camera : OrthographicCamera = OrthographicCamera();

    override fun show() {


        //makes it so mouse clicks are registered properly
        Gdx.input.inputProcessor = this

        //creates and sets the level to hungary
        level = l.createLevel(game.levels!![1])
        camera.viewportWidth=(level.outline!!.width);
        camera.viewportHeight= (level.outline!!.width)*(Gdx.graphics.height.toFloat()/Gdx.graphics.width.toFloat());
        camera.position.set(camera.viewportWidth/2,camera.viewportHeight/2,0f)
        level.outline!!.polygon!!.setPosition(level.outline!!.offsetX - level.outline!!.minX, camera.viewportHeight-(level.outline!!.height))

        for(p in level.pieces){
            /*p.polygon!!.setPosition(Random.nextDouble(0.0, (camera.viewportWidth).toDouble()).toFloat(),
                    Random.nextDouble(0.0, camera.viewportHeight/2.toDouble()).toFloat())*/
            p.polygon!!.setPosition(2f,1f);
        }

    }
    fun setCamera(){

    }



    val img = Texture(Gdx.files.internal("complete.png"));

    override fun render(delta: Float) {
        var completed = true;
        camera.update();
        game.batch!!.projectionMatrix = camera.combined;
        ScreenUtils.clear(color)
        game.batch!!.begin()
        level.draw(game.batch!!)

        for(p in level.pieces){
            if(!p.checkPos(level.outline!!.minX,level.outline!!.minY, camera.viewportHeight, level.outline!!.height, level.outline!!.width)){
                completed = false;
            }
        }
        if(completed){
            game.batch!!.draw(img, 0f, camera.viewportHeight/2, camera.viewportWidth, camera.viewportWidth*(1467f/2200f))
        }

        game.batch!!.end()

    }

    override fun resize(width: Int, height: Int) {
    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {

    }

    override fun dispose() {
    }



    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        return true
    }

    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
        currentPiece = null;
        dragging = false;
        return true
    }
    var dragging = false;
    var currentPiece : Piece? = null;
    /**
     * listener used to drag and move pieces on click
     * the coordinate system for the mouse and for the screen is slightly different so if
     * this ever produces an unexpected result thats probably why
     */
    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        var worldCoordinates = camera.unproject(Vector3(screenX.toFloat(), screenY.toFloat(), 0f))
        if(!dragging) {
            currentPiece = level.get(worldCoordinates.x, worldCoordinates.y)
            if (currentPiece != null) {
                dragging = true
                currentPiece!!.setX(worldCoordinates.x - (currentPiece!!.width/2 + (currentPiece!!.minX - currentPiece!!.offsetX)))
                currentPiece!!.setY(worldCoordinates.y - (currentPiece!!.height/2 + (currentPiece!!.minY - currentPiece!!.offsetY)))
            }
        }else{
            currentPiece!!.setX(worldCoordinates.x - (currentPiece!!.width/2 + (currentPiece!!.minX - currentPiece!!.offsetX)))
            currentPiece!!.setY(worldCoordinates.y - (currentPiece!!.height/2 + (currentPiece!!.minY - currentPiece!!.offsetY)))
        }

        return true
    }
}