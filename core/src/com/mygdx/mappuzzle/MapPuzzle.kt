package com.mygdx.mappuzzle

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Game
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.actions.Actions.show
import com.badlogic.gdx.utils.ScreenUtils

class MapPuzzle : Game() {
    var batch: SpriteBatch? = null
    var img: Texture? = null
    var assetManager: AssetManager? = null;
    override fun create() {
        //println("yes")
        assetManager = AssetManager();
        batch = SpriteBatch()
       // img = Texture("badlogic.jpg")

        this.setScreen(MainScreen(this))
    }

    override fun render() {

        //println("yes")
        //ScreenUtils.clear(1f, 0f, 0f, 1f)
        //batch!!.begin()
      //  batch!!.draw(img, 0f, 0f)
        //batch!!.end()

        super.render()

    }

    override fun dispose() {
        batch!!.dispose()
        img!!.dispose()
    }
}