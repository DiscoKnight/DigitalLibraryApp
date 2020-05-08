package com.gamecampanion.org.digitallibraryapp.digitallibrary.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gamecampanion.org.digitallibraryapp.Database.movie.MovieEntity
import com.gamecampanion.org.digitallibraryapp.Database.music.MusicEntity

@Dao
interface MovieDao {

    @Query("select * from MovieEntity")
    fun getAllGames(): List<MovieEntity>

    @Insert
    fun insertGame(movie: MovieEntity)
}