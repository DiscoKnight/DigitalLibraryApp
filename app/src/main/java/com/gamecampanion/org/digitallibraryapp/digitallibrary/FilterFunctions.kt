package com.gamecampanion.org.digitallibraryapp.digitallibrary

import java.time.LocalDate
import java.time.Period

abstract class FilterFunctions {

    fun isPreOrdered(releaseDate: LocalDate): Boolean{
        return Period.between(releaseDate, LocalDate.now()).days >= 0
    }

    fun daysToRelease(releaseDate: LocalDate): Int {
        return Period.between(releaseDate, LocalDate.now()).days
    }
}