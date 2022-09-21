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
import com.mygdx.mappuzzle.DailyPuzzle
import com.mygdx.mappuzzle.MainScreen
import com.mygdx.mappuzzle.MapPuzzle

// This class contains all the UI components and functionality of the main menu.
class MenuScreen(var game: MapPuzzle) : Screen {

    var stage: Stage = Stage(ScreenViewport())

    override fun show() {
        // Create a table that fills the screen. Everything else will go inside this table.
        val table = Table()
        table.setFillParent(true)
        //table.debug = true
        stage.addActor(table)

        // Import the UI assets from files.
        val skin = Skin(Gdx.files.internal("skin/flat-earth-ui.json"))

        // Create buttons for the menu.
        val play = TextButton("Play", skin)
        val settings = TextButton("Settings", skin)
        val exit = TextButton("Exit", skin)

        // Add the menu buttons to the table and change their sizes.
        table.add(play).fillX().uniformX().width((Gdx.graphics.width/2).toFloat()).height((Gdx.graphics.height/15).toFloat())
        table.row().pad(10f, 0f, 10f, 0f)
        table.add(settings).fillX().uniformX().width((Gdx.graphics.width/2).toFloat()).height((Gdx.graphics.height/15).toFloat())
        table.row()
        //table.add(exit).fillX().uniformX().width((Gdx.graphics.width/2).toFloat()).height((Gdx.graphics.height/15).toFloat())

        // Listen to when the exit button is pressed, when pressed exit the app.
        exit.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                dispose();
                Gdx.app.exit()
            }
        })

        // Listen for when the play button is pressed, when pressed call the change screen
        // method and change the screen to the main screen.
        play.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                dispose()
                game.dailyPuzzle.pickDailyLevel((game.settings.getLevel() + 1) % game.levels!!.size)
                game.screen = MainScreen(game)
            }
        })

        // Listen for when the preferences button is pressed, when it is pressed call the
        // change screen method and change to the preferences screen.
        settings.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                dispose();
                game.screen = SettingsScreen(game)
            }
        })
    }

    // The render method clears the screen before drawing the stage.
    override fun render(delta: Float) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act(Math.min(Gdx.graphics.deltaTime, 1 / 30f))
        stage.draw()
    }

    // resize method will update the viewport size if the screen size is changed.
    override fun resize(width: Int, height: Int) {
        stage.viewport.update(width, height, true)
    }

    override fun pause() {}
    override fun resume() {}
    override fun hide() {}

    // This method calls dispose on the stage, so the stage is disposed.
    override fun dispose() {
        stage.dispose()
    }

    init {
        Gdx.input.inputProcessor = stage
    }
}