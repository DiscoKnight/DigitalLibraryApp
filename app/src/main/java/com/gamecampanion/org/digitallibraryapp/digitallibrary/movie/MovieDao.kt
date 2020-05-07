package com.gamecampanion.org.digitallibraryapp.digitallibrary.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gamecampanion.org.digitallibraryapp.Database.music.MusicEntity

@Dao
interface MovieDao {

    @Query("select * from MusicEntity")
    fun getAllGames(): List<MusicEntity>

    @Insert
    fun insertGame(music: MusicEntity)
}