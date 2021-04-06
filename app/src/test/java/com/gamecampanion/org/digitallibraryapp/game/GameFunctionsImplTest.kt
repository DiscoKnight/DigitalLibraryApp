package com.gamecampanion.org.digitallibraryapp.game

import com.gamecampanion.org.digitallibraryapp.httprestclient.MyRESTClient
import com.gamecampanion.org.digitallibraryapp.model.GameRestModel
import com.gamecampanion.org.digitallibraryapp.model.PublisherModel
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mock

internal class GameFunctionsImplTest {

    @Mock
    val MyRESTClient = MyRESTClient()

    val url: String = "http://localhost:8080/api/v1.0/mongo/game/getAllGames";

    var underTest: GameFunctions = GameFunctionsImpl(MyRESTClient)

    @Test
    fun test_OrderByRating() {
        var resultList = underTest.orderGameByRating(createList())
    }

    @Test
    fun test_orderByName() {
        var resultList = underTest.orderGameByName(createList())
    }

    @Test
    fun test_findByName() {
        var game = underTest.findGameByName(createList(), "Ghost of Tsushima")

        if (game != null) {
            assert(game.get().gameName.contentEquals("Ghost of Tsushima"))
        }
    }

    @Test
    fun test_isReleasedYes() {
        var isGame = underTest.isGameReleased(createList().get(0))

        assertTrue(isGame)
    }

    @Test
    fun test_isReleasedNo() {
        var isGame = underTest.isGameReleased(createList().get(1))

        assertFalse(isGame)
    }

    @Test
    fun test_findGameByReleaseDate() {
        var game = underTest.findGameByReleaseDate(createList(), "2019-01-01")

        assertTrue(game.get().gameName.contentEquals("Ghost of Tsushima"))
    }

    fun createList(): List<GameRestModel> {
        var testList = ArrayList<GameRestModel>()

        testList.add(
            GameRestModel(
                "A",
                "Ghost of Tsushima",
                "Action/Adventure",
                5,
                true,
                "",
                "2019-01-01",
                createPublisherModel("SIE", "Sucker Punch")
            )
        )
        testList.add(
            GameRestModel(
                "C",
                "Kena: Bridge of Spirits",
                "RPG",
                2,
                true,
                "",
                "2023-01-01",
                createPublisherModel("SIE", "Ember labs")
            )
        )
        testList.add(
            GameRestModel(
                "D",
                "Mass Effect: Legendary Edition",
                "ARPG",
                4,
                true,
                "",
                "",
                createPublisherModel("EA", "Bioware")
            )
        )
        testList.add(
            GameRestModel(
                "Z",
                "God of War",
                "Action/Adventure",
                3,
                true,
                "",
                "",
                createPublisherModel("SIE", "Santa Monica")
            )
        )
        testList.add(
            GameRestModel(
                "H", "Cyberpunk 2077", "RPG", 1, true, "", "", createPublisherModel("CDPR", "CDPR")
            )
        )

        return testList;
    }

    fun createPublisherModel(name: String, dev: String): PublisherModel {
        return PublisherModel(name, dev)
    }
}