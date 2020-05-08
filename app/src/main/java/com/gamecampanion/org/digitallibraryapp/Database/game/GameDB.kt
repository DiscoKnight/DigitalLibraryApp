package com.gamecampanion.org.digitallibraryapp.Database.game

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gamecampanion.org.digitallibraryapp.digitallibrary.game.GameDao

@Database(entities = arrayOf(GameEntity::class), version =  1)
abstract class GameDatabase() : RoomDatabase(){
    abstract fun gameDao(): GameDao

}