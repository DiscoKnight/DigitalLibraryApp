package com.gamecampanion.org.digitallibraryapp.Database.movie

import androidx.room.Database
import com.gamecampanion.org.digitallibraryapp.digitallibrary.movie.MovieDao

@Database(entities = arrayOf(MovieEntity::class), version = 1)
abstract class MovieDB() {
    abstract fun movieDao(): MovieDao
}