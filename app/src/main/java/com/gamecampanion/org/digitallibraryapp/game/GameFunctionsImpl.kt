package com.gamecampanion.org.digitallibraryapp.game

import com.gamecampanion.org.digitallibraryapp.httprestclient.MyRESTClient
import com.gamecampanion.org.digitallibraryapp.model.GameRestModel
import com.google.gson.Gson
import okhttp3.Request
import java.time.LocalDate
import java.util.*
import java.util.stream.Collectors

class GameFunctionsImpl(var client: MyRESTClient) : GameFunctions {

    override fun getGameList(request: Request): List<GameRestModel> {

        var response = client.getClient().newCall(request).execute()

        return Gson().fromJson(response.body()?.string(), Array<GameRestModel>::class.java).toList()

    }

    override fun orderGameByName(gamesList: List<GameRestModel>): List<GameRestModel> {

        return gamesList.stream()
            .sorted(Comparator.comparing(GameRestModel::gameName))
            .collect(Collectors.toList())
    }

    override fun orderGameByRating(gamesList: List<GameRestModel>): List<GameRestModel> {

        return gamesList.stream()
            .sorted(Comparator.comparing(GameRestModel::rating))
            .collect(Collectors.toList())
            .reversed()

    }

    override fun orderGameByGenre(gamesList: List<GameRestModel>): List<GameRestModel> {
        return gamesList.stream()
            .sorted(Comparator.comparing(GameRestModel::gameGenre))
            .collect(Collectors.toList())
    }

    override fun findGameByGenre(
        gamesList: List<GameRestModel>,
        genre: String
    ): List<GameRestModel> {
        return gamesList.stream().filter { e -> e.gameGenre.contentEquals(genre) }
            .collect(Collectors.toList())
    }

    override fun findGameByName(
        gamesList: List<GameRestModel>,
        gameName: String
    ): Optional<GameRestModel> {
        return Optional.ofNullable(gamesList.find { e -> e.gameName.contentEquals(gameName) })
    }

    override fun findGameByDev(gamesList: List<GameRestModel>, devName: String): List<GameRestModel> {
        return gamesList.stream()
            .filter { e -> e.gamePublisher.dev.contentEquals(devName) }
            .collect(Collectors.toList())
    }

    override fun isGameReleased(game: GameRestModel): Boolean {
        return LocalDate.now().isAfter(LocalDate.parse(game.releaseDate))

    }

    override fun findGameByReleaseDate(
        gamesList: List<GameRestModel>,
        releaseDate: String
    ): Optional<GameRestModel> {
        return Optional.ofNullable(gamesList.find { e -> e.releaseDate.contentEquals(releaseDate) })
    }
}