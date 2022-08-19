package com.mygdx.mappuzzle.views

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.utils.viewport.StretchViewport
import com.mygdx.mappuzzle.MapPuzzle

class MenuScreen(myGame: MapPuzzle) : Screen {
    private var parent: MapPuzzle
    var stage: Stage

    override fun show() {
        // Create a table that fills the screen. Everything else will go inside this table.
        val table = Table()
        table.setFillParent(true)
        table.debug = true
        stage.addActor(table)

        val skin = Skin(Gdx.files.internal("skin/flat-earth-ui.json"))
        val newGame = TextButton("New Game", skin)
        val preferences = TextButton("Preferences", skin)
        val exit = TextButton("Exit", skin)

        table.add(newGame).fillX().uniformX().width((Gdx.graphics.width/2).toFloat()).height((Gdx.graphics.height/15).toFloat())
        table.row().pad(10f, 0f, 10f, 0f)
        table.add(preferences).fillX().uniformX().width((Gdx.graphics.width/2).toFloat()).height((Gdx.graphics.height/15).toFloat())
        table.row()
        table.add(exit).fillX().uniformX().width((Gdx.graphics.width/2).toFloat()).height((Gdx.graphics.height/15).toFloat())

        exit.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                Gdx.app.exit()
            }
        })
        newGame.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                parent.changeScreen(MapPuzzle.APPLICATION)
            }
        })
        preferences.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                parent.changeScreen(MapPuzzle.PREFERENCES)
            }
        })
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act(Math.min(Gdx.graphics.deltaTime, 1 / 30f))
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun pause() {}
    override fun resume() {}
    override fun hide() {}

    override fun dispose() {
        stage.dispose()
    }

    init {
        parent = myGame
        stage = Stage(ScreenViewport())
        Gdx.input.inputProcessor = stage
    }
}