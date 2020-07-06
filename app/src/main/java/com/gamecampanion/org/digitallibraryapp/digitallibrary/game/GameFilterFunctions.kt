package com.gamecampanion.org.digitallibraryapp.digitallibrary.game

import com.gamecampanion.org.digitallibraryapp.Database.game.GameEntity
import com.gamecampanion.org.digitallibraryapp.digitallibrary.FilterFunctions
import com.gamecampanion.org.digitallibraryapp.digitallibrary.genres.GameGenreEnum

class GameFilterFunctions() : FilterFunctions() {

    fun filterGameListName(list: List<GameEntity>, gameName: String): List<GameEntity>{
        return list.filter { e -> e.gameName == gameName }.toList()
    }

    fun filterGameListPlatform(list: List<GameEntity>, platform: String): List<GameEntity>{
        return list.filter { e -> e.platform == platform }.toList()
    }

    fun filterGameListRating(list: List<GameEntity>, rating: Int): List<GameEntity>{
        return list.filter { e -> e.rating == rating }.toList()
    }

    fun filterGameListGenre(list: List<GameEntity>, gameGenreEnum: GameGenreEnum): List<GameEntity>{
        return list.filter { e -> e.genre == gameGenreEnum.toString() }.toList()
    }

}