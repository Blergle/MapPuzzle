package com.mygdx.mappuzzle.views

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.mygdx.mappuzzle.MapPuzzle

class SettingsScreen (var game : MapPuzzle) : Screen {

    public var parent: MapPuzzle = game

    var stage: Stage = Stage(ScreenViewport())

    override fun show() {
        //TODO("Currently just testing Settings - redesign to be more usable")

        // Create a table that fills the screen. Everything else will go inside this table.
        val table = Table()
        table.setFillParent(true)
        //table.debug = true
        stage.addActor(table)

        // Import the UI assets from files.
        val skin = Skin(Gdx.files.internal("skin/flat-earth-ui.json"))

        // Create buttons for the menu.
        val menu = TextButton("Menu", skin)
        val random = TextButton("Random", skin)
        val exit = TextButton("Exit", skin)

        // Add the menu buttons to the table and change their sizes.
        table.add(menu).fillX().uniformX().width((Gdx.graphics.width/2).toFloat()).height((Gdx.graphics.height/15).toFloat())
        table.row().pad(10f, 0f, 10f, 0f)
        table.add(random).fillX().uniformX().width((Gdx.graphics.width/2).toFloat()).height((Gdx.graphics.height/15).toFloat())
        table.row()
        //table.add(exit).fillX().uniformX().width((Gdx.graphics.width/2).toFloat()).height((Gdx.graphics.height/15).toFloat())

        // Listen to when the exit button is pressed, when pressed exit the app.
        exit.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                dispose();
                Gdx.app.exit()
            }
        })

        // Listen to when the random button is pressed, when pressed toggle settings between random and daily puzzle.
        random.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                if(!parent.settings.settings.contains("Random")) {
                    parent.settings.playRandom(true)
                }else{
                    parent.settings.playRandom(!parent.settings.isRandom())
                }
                //Toggle needs to be made clear to player e.g. colour change, text, etc.
            }
        })

        // Listen for when the menu button is pressed, when pressed call the change screen
        // method and change the screen to the menu screen.
        menu.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent, actor: Actor) {
                dispose()
                parent.screen = MenuScreen(parent)
            }
        })
    }

    override fun render(delta: Float) {
        //TODO("Just testing - may change in redesign")
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        stage.act(Math.min(Gdx.graphics.deltaTime, 1 / 30f))
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        //TODO("Just testing - may change in redesign")
        stage.viewport.update(width, height, true)
    }

    override fun pause() {
        TODO("Not yet implemented")
    }

    override fun resume() {
        TODO("Not yet implemented")
    }

    override fun hide() {
        TODO("Not yet implemented")
    }

    override fun dispose() {
        //TODO("Just testing - may change in redesign")
        stage.dispose()
    }

    init {
        Gdx.input.inputProcessor = stage
    }
}