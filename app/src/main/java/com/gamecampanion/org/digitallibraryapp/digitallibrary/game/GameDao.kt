package com.gamecampanion.org.digitallibraryapp.digitallibrary.game

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gamecampanion.org.digitallibraryapp.Database.game.GameEntity

@Dao
interface GameDao {

    @Query("select * from GameEntity")
    fun getAllGames(): List<GameEntity>

    @Insert
    fun insertGame(game: GameEntity)

    @Delete
    fun deleteGame(game: GameEntity)
}