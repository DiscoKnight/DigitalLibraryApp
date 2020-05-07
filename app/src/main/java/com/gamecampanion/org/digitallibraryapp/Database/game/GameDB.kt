package com.gamecampanion.org.digitallibraryapp.Database.game

import androidx.room.Database
import com.gamecampanion.org.digitallibraryapp.digitallibrary.game.GameDao

@Database(entities = arrayOf(GameEntity::class), version =  1)
abstract class GameDatabase(){
    abstract fun gameDao(): GameDao

}