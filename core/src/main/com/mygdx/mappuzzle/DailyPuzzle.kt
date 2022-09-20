package com.mygdx.mappuzzle

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences
import java.text.SimpleDateFormat
import java.util.*
import com.mygdx.mappuzzle.MapPuzzle
import com.mygdx.mappuzzle.Settings


class DailyPuzzle (var settings: Settings) {
    var format: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    var today: String = format.format(Date())

    var stored: Preferences = Gdx.app.getPreferences("Daily Puzzle")

    private fun compareDate(): Boolean{
        return today == stored.getString("Date", "")
    }

    //store date if changed
    private fun storeDate(){
            stored.putString("Date", today) //updates stored date
            stored.flush() //persists change
    }

    //updates daily level if
    fun pickDailyLevel(level: Int){
        if(!stored.contains("Date") || !compareDate()){
            storeDate()
            settings.setLevel(level)
        }
    }
}