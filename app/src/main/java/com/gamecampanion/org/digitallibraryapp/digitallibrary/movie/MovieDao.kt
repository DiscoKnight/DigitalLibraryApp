package com.gamecampanion.org.digitallibraryapp.digitallibrary.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.gamecampanion.org.digitallibraryapp.Database.movie.MovieEntity

@Dao
interface MovieDao {

    @Query("select * from MovieEntity")
    fun getAllMovie(): List<MovieEntity>

    @Insert
    fun insertMovie(movie: MovieEntity)

    @Update
    fun updateMovie(movie: MovieEntity)
}