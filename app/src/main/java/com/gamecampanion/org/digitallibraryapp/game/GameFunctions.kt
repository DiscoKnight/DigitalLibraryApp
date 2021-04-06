package com.gamecampanion.org.digitallibraryapp.game

import com.gamecampanion.org.digitallibraryapp.model.GameRestModel
import okhttp3.Request
import java.util.*

interface GameFunctions {

    fun getGameList(request: Request): List<GameRestModel>

    fun orderGameByName(gamesList: List<GameRestModel>): List<GameRestModel>

    fun orderGameByRating(gamesList: List<GameRestModel>): List<GameRestModel>

    fun orderGameByGenre(gamesList: List<GameRestModel>): List<GameRestModel>

    fun findGameByName(gamesList: List<GameRestModel>, gameName: String): Optional<GameRestModel>

    fun isGameReleased(game: GameRestModel): Boolean

    fun findGameByDev(gamesList: List<GameRestModel>, devName: String): List<GameRestModel>

    fun findGameByGenre(gamesList: List<GameRestModel>, genre: String): List<GameRestModel>

    fun findGameByReleaseDate(
        gamesList: List<GameRestModel>,
        genre: String
    ): Optional<GameRestModel>

}