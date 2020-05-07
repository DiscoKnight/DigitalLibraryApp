package com.gamecampanion.org.digitallibraryapp

import com.gamecampanion.org.digitallibraryapp.Database.game.GameEntity
import java.time.LocalDate
import java.time.Period

class DigitialLibrary {


    fun timeToReleaseDays(releaseDate: LocalDate): Int{

        var period = Period.between(releaseDate, LocalDate.now())

        return period.days
    }

    fun filterList(list: List<GameEntity>, filterValue: String): List<GameEntity>{
        var result = list.filter { e -> e.gameName == filterValue }.toList()

        return result
    }
}