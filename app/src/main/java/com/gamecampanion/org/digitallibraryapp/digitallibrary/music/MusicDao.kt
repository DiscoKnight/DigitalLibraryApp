package com.gamecampanion.org.digitallibraryapp.digitallibrary.music

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.gamecampanion.org.digitallibraryapp.Database.music.MusicEntity

@Dao
interface MusicDao {
    @Query("select * from MusicEntity")
    fun getAllGames(): List<MusicEntity>

    @Insert
    fun insertGame(music: MusicEntity)
}