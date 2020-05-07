package com.gamecampanion.org.digitallibraryapp.digitallibrary.game

import com.gamecampanion.org.digitallibraryapp.Database.game.GameEntity

open class GameDaoImpl: GameDao {

    override fun getAllGames(): List<GameEntity>{ return emptyList()}

    override fun insertGame(game: GameEntity){}
}