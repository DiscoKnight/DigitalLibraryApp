package com.gamecampanion.org.digitallibraryapp

import java.time.LocalDate
import java.time.Period

class DigitialLibrary {

    fun timeToReleaseDays(releaseDate: LocalDate): Int{
        var period = Period.between(releaseDate, LocalDate.now())

        return period.days
    }

}