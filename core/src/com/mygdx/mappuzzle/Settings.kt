package com.mygdx.mappuzzle

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import kotlin.properties.Delegates

open class Settings {
    var settings: Preferences = Gdx.app.getPreferences("My Settings") //for persisting settings after app closure
    var currentLevel by Delegates.notNull<Int>() //set the level to be played - shouldn't ever be set directly, but kept as class variable for quicker access
    private var notifications by Delegates.notNull<Boolean>() //example setting we could add: daily notifications enabled or not  - shouldn't ever be set directly, but kept as class variable for quicker access
    private var random by Delegates.notNull<Boolean>()

    //example setting - allow or disallow notifications
    fun allowNotifications(hasNotifications: Boolean) {
        notifications = hasNotifications
        settings.putBoolean("Notifications", notifications)
        settings.flush() //persists the setting
    }

    //example settings accessor - safer than accessing class variable
    fun notificationsAllowed(): Boolean {
        return settings.getBoolean("Notifications", false)
    }

    //set which entry in the levels list is to be played
    fun setLevel(level: Int){
        currentLevel = level
        settings.putInteger("Level", currentLevel)
        settings.flush()
    }

    //get the index in the levels list of the current level - safer than accessing class variable
    fun getLevel(): Int {
        if(!settings.contains("Level")){
            setLevel(-1)
        }
        return settings.getInteger("Level", 0)
    }

    fun playRandom(rand: Boolean){
        random = rand
        settings.putBoolean("Random", rand)
        settings.flush()
    }

    fun isRandom(): Boolean {
        return settings.getBoolean("Random", false)
    }

}